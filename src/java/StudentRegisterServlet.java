import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "StudentRegisterServlet", urlPatterns = {"/StudentRegisterServlet", "/studentRegister"})
public class StudentRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name=req.getParameter("name"), id=req.getParameter("studentId"), grade=req.getParameter("classGrade");
        String email=req.getParameter("email"), password=req.getParameter("password"), confirm=req.getParameter("confirm");
        if (name==null || email==null || password==null || confirm==null || name.trim().isEmpty() || email.trim().isEmpty() || password.length()<8) { fail(res,"Name, email, and a password of at least 8 characters are required."); return; }
        if (!password.equals(confirm)) { fail(res,"Passwords do not match."); return; }
        if (id==null || id.trim().isEmpty()) id="STU-"+java.util.UUID.randomUUID().toString().substring(0,8).toUpperCase();
        if (grade==null || grade.trim().isEmpty()) grade="Class 10";
        try (Connection con=DBConnection.getConnection()) {
            if (con==null) { fail(res,"The service is temporarily unavailable."); return; }
            try (PreparedStatement ps=con.prepareStatement("INSERT INTO students(name, studentId, class_grade, email, password) VALUES (?, ?, ?, ?, ?)")) {
                ps.setString(1,name.trim()); ps.setString(2,id.trim()); ps.setString(3,grade.trim()); ps.setString(4,email.trim()); ps.setString(5,PasswordUtil.hashPassword(password)); ps.executeUpdate();
            }
            res.sendRedirect("student.html?registered=1");
        } catch(Exception e) { getServletContext().log("Student registration failed",e); fail(res,"Registration could not be completed. The email or student ID may already exist."); }
    }
    private void fail(HttpServletResponse res,String message) throws IOException { res.sendRedirect("student.html?error="+java.net.URLEncoder.encode(message,"UTF-8")); }
}
