import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/AdminLoginServlet", "/Login"})
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        res.setHeader("Cache-Control", "no-store");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email == null || password == null || email.trim().isEmpty() || password.isEmpty()) {
            fail(res, "Please fill in both email and password!"); return;
        }
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) { fail(res, "The service is temporarily unavailable."); return; }
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM admins WHERE email = ?")) {
                ps.setString(1, email.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next() || !PasswordUtil.verifyPassword(password, rs.getString("password"))) {
                        fail(res, "Invalid email or password."); return;
                    }
                    if (PasswordUtil.needsRehash(rs.getString("password"))) {
                        try (PreparedStatement up = con.prepareStatement("UPDATE admins SET password=? WHERE id=?")) {
                            up.setString(1, PasswordUtil.hashPassword(password)); up.setInt(2, rs.getInt("id")); up.executeUpdate();
                        }
                    }
                    HttpSession old = req.getSession(false); if (old != null) old.invalidate();
                    HttpSession session = req.getSession(true);
                    Teacher teacher = new Teacher(rs.getString("name"), rs.getString("adminId"), rs.getString("email"), rs.getString("phone"), rs.getString("avatar"));
                    session.setAttribute("adminName", teacher.getName()); session.setAttribute("currentTeacher", teacher);
                    session.setAttribute("userRole", "ADMIN"); session.setAttribute("userEmail", email.trim()); session.setMaxInactiveInterval(3600);
                    res.sendRedirect("teacherhome.html");
                }
            }
        } catch (Exception e) { getServletContext().log("Admin login failed", e); fail(res, "Unable to complete login."); }
    }
    private void fail(HttpServletResponse res, String message) throws IOException {
        res.sendRedirect("admin.html?error=" + java.net.URLEncoder.encode(message, "UTF-8"));
    }
}
