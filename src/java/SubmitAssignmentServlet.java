import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/submitAssignment")
@MultipartConfig
public class SubmitAssignmentServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/exam?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "ashu0811";

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {

            // Retrieve form data
            int assignmentId = Integer.parseInt(req.getParameter("assignmentId"));
            String studentName = req.getParameter("studentName");
            String notes = req.getParameter("notes");

            // Handle file upload
            Part filePart = req.getPart("file");
            String fileName = null;
            if (filePart != null && filePart.getSize() > 0) {
                String uploadDir = getServletContext().getRealPath("") + File.separator + "submissions";
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }
                // Generate a unique file name
                fileName = assignmentId + "_" + studentName.replaceAll("\\s+", "_") + "_" + System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
                filePart.write(uploadDir + File.separator + fileName);
            }

            // Insert submission into database
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement ps = con.prepareStatement(
                         "INSERT INTO submissions(assignment_id, student_name, notes, file_path) VALUES (?, ?, ?, ?)")) {

                ps.setInt(1, assignmentId);
                ps.setString(2, studentName);
                ps.setString(3, notes);
                ps.setString(4, fileName);
                int rows = ps.executeUpdate();

                if (rows > 0) {
                    out.println("<h3>Assignment submitted successfully!</h3>");
                } else {
                    out.println("<h3>Submission failed. Please try again.</h3>");
                }

            } catch (Exception dbEx) {
                dbEx.printStackTrace();
                out.println("<h3>Error: Could not save submission.</h3>");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            res.getWriter().println("<h3>Unexpected error occurred: " + ex.getMessage() + "</h3>");
        }
    }
}