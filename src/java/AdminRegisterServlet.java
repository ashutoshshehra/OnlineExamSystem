import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AdminRegisterServlet", urlPatterns = {"/AdminRegisterServlet"})
public class AdminRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("name");
        String adminId = req.getParameter("adminId");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirmPassword");

        if (name == null || email == null || password == null || name.trim().isEmpty() || email.trim().isEmpty()) {
            out.println("<script>alert('All fields are required!'); window.location.href='admin.html';</script>");
            return;
        }

        if (adminId == null || adminId.trim().isEmpty()) {
            adminId = "T-" + (int)(Math.random() * 900 + 100);
        }

        if (confirm != null && !password.equals(confirm)) {
            out.println("<script>alert('Passwords do not match!'); window.location.href='admin.html';</script>");
            return;
        }

        name = name.trim();
        email = email.trim();
        adminId = adminId.trim();
        String hashedPassword = PasswordUtil.hashPassword(password);

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                // Offline demo fallback session
                HttpSession session = req.getSession();
                Teacher teacher = new Teacher(name, adminId, email, "", null);
                session.setAttribute("adminName", name);
                session.setAttribute("currentTeacher", teacher);
                session.setAttribute("userRole", "ADMIN");
                session.setAttribute("userEmail", email);
                res.sendRedirect("teacherhome.html");
                return;
            }

            String query = "INSERT INTO admins(name, adminId, email, password) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, adminId);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    HttpSession session = req.getSession();
                    Teacher teacher = new Teacher(name, adminId, email, "", null);
                    session.setAttribute("adminName", name);
                    session.setAttribute("currentTeacher", teacher);
                    session.setAttribute("userRole", "ADMIN");
                    session.setAttribute("userEmail", email);
                    res.sendRedirect("teacherhome.html");
                } else {
                    out.println("<script>alert('Registration failed. Please try again.'); window.location.href='admin.html';</script>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('Registration Error: " + e.getMessage().replace("'", "\\'") + "'); window.location.href='admin.html';</script>");
        }
    }
}