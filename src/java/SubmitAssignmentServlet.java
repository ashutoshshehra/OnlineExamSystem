import java.io.*;
import java.nio.file.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/submitAssignment")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=10*1024*1024, maxRequestSize=12*1024*1024)
public class SubmitAssignmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String rawId=req.getParameter("assignmentId"), studentName=req.getParameter("studentName"), notes=req.getParameter("notes");
        int assignmentId;
        try { assignmentId=Integer.parseInt(rawId); } catch(Exception e) { res.sendError(400,"Invalid assignment"); return; }
        if (studentName == null || studentName.trim().isEmpty()) { res.sendError(400,"Student name is required"); return; }
        Part part=req.getPart("file"); String storedName=null;
        if (part != null && part.getSize() > 0) {
            String original=part.getSubmittedFileName();
            String ext=""; int dot=original == null ? -1 : original.lastIndexOf('.');
            if (dot >= 0) ext=original.substring(dot).toLowerCase();
            if (!ext.matches("\\.(pdf|doc|docx|txt|jpg|jpeg|png)")) { res.sendError(415,"Unsupported file type"); return; }
            Path folder=Paths.get(System.getProperty("smartexam.upload.dir", System.getProperty("java.io.tmpdir")), "smartexam-submissions");
            Files.createDirectories(folder);
            storedName=assignmentId + "_" + java.util.UUID.randomUUID().toString() + ext;
            part.write(folder.resolve(storedName).toString());
        }
        try (Connection con=DBConnection.getConnection()) {
            if (con == null) { res.sendError(503,"Database unavailable"); return; }
            try (PreparedStatement ps=con.prepareStatement("INSERT INTO submissions(assignment_id, student_name, notes, file_name, file_path) VALUES (?, ?, ?, ?, ?)")) {
                ps.setInt(1,assignmentId); ps.setString(2,studentName.trim()); ps.setString(3,notes); ps.setString(4,storedName); ps.setString(5,storedName); ps.executeUpdate();
            }
        } catch(Exception e) { getServletContext().log("Assignment submission failed",e); res.sendError(500,"Could not save submission"); return; }
        res.sendRedirect("studentassignment.html?status=submitted");
    }
}
