import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/AdminLoginServlet", "/Login"})
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            out.println("<script>alert('Please fill in both email and password!'); window.location.href='admin.html';</script>");
            return;
        }

        email = email.trim();
        password = password.trim();

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                // If DB offline, allow default demo admin login for local testing
                if (email.equalsIgnoreCase("admin@example.com") && password.equals("admin123")) {
                    HttpSession session = req.getSession();
                    Teacher teacher = new Teacher("Dr. Rajesh Sharma", "T-101", email, "+91 9876543210", null);
                    session.setAttribute("adminName", teacher.getName());
                    session.setAttribute("currentTeacher", teacher);
                    session.setAttribute("userRole", "ADMIN");
                    session.setAttribute("userEmail", email);
                    res.sendRedirect("teacherhome.html");
                    return;
                }
                out.println("<script>alert('Database offline and credentials do not match demo admin (admin@example.com / admin123)!'); window.location.href='admin.html';</script>");
                return;
            }

            String query = "SELECT * FROM admins WHERE email = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String storedPass = rs.getString("password");
                        if (PasswordUtil.verifyPassword(password, storedPass)) {
                            HttpSession session = req.getSession();
                            Teacher teacher = new Teacher(
                                rs.getString("name"),
                                rs.getString("adminId"),
                                rs.getString("email"),
                                rs.getString("phone"),
                                rs.getString("avatar")
                            );
                            session.setAttribute("adminName", teacher.getName());
                            session.setAttribute("currentTeacher", teacher);
                            session.setAttribute("userRole", "ADMIN");
                            session.setAttribute("userEmail", email);
                            session.setMaxInactiveInterval(60 * 60); // 1 hour session

                            res.sendRedirect("teacherhome.html");
                            return;
                        }
                    }
                    out.println("<script>alert('Invalid Email or Password!'); window.location.href='admin.html';</script>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('Server Error: " + e.getMessage().replace("'", "\\'") + "'); window.location.href='admin.html';</script>");
        }
    }
}