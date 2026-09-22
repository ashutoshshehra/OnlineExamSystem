import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "StudentLoginServlet", urlPatterns = {"/StudentLoginServlet", "/studentLogin"})
public class StudentLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        res.setHeader("Cache-Control", "no-store");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email == null || password == null || email.trim().isEmpty() || password.isEmpty()) {
            fail(res, "Please enter your email and password."); return;
        }
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) { fail(res, "The service is temporarily unavailable."); return; }
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM students WHERE email = ?")) {
                ps.setString(1, email.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next() || !PasswordUtil.verifyPassword(password, rs.getString("password"))) {
                        fail(res, "Invalid email or password."); return;
                    }
                    if (PasswordUtil.needsRehash(rs.getString("password"))) {
                        try (PreparedStatement up = con.prepareStatement("UPDATE students SET password=? WHERE id=?")) {
                            up.setString(1, PasswordUtil.hashPassword(password)); up.setInt(2, rs.getInt("id")); up.executeUpdate();
                        }
                    }
                    HttpSession old = req.getSession(false); if (old != null) old.invalidate();
                    HttpSession session = req.getSession(true);
                    session.setAttribute("studentName", rs.getString("name"));
                    session.setAttribute("studentId", rs.getString("studentId"));
                    session.setAttribute("studentEmail", rs.getString("email"));
                    session.setAttribute("classGrade", rs.getString("class_grade") == null ? "Class 10" : rs.getString("class_grade"));
                    session.setAttribute("userRole", "STUDENT"); session.setMaxInactiveInterval(3600);
                    res.sendRedirect("studenthome.html");
                }
            }
        } catch (Exception e) { getServletContext().log("Student login failed", e); fail(res, "Unable to complete login."); }
    }
    private void fail(HttpServletResponse res, String message) throws IOException {
        res.sendRedirect("student.html?error=" + java.net.URLEncoder.encode(message, "UTF-8"));
    }
}
