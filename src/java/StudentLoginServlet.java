import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "StudentLoginServlet", urlPatterns = {"/StudentLoginServlet", "/studentLogin"})
public class StudentLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            out.println("<script>alert('Please enter your email and password.'); window.location.href='student.html';</script>");
            return;
        }

        email = email.trim();
        password = password.trim();

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                // If DB offline, allow default demo student login
                if (email.equalsIgnoreCase("student@example.com") && password.equals("student123")) {
                    HttpSession session = req.getSession();
                    session.setAttribute("studentName", "Ashutosh Shehra");
                    session.setAttribute("studentId", "STU-2026");
                    session.setAttribute("studentEmail", email);
                    session.setAttribute("classGrade", "Class 10");
                    session.setAttribute("userRole", "STUDENT");
                    res.sendRedirect("studenthome.html");
                    return;
                }
                out.println("<script>alert('Database offline and credentials do not match demo student (student@example.com / student123)!'); window.location.href='student.html';</script>");
                return;
            }

            String query = "SELECT * FROM students WHERE email = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String storedPass = rs.getString("password");
                        if (PasswordUtil.verifyPassword(password, storedPass)) {
                            HttpSession session = req.getSession();
                            session.setAttribute("studentName", rs.getString("name"));
                            session.setAttribute("studentId", rs.getString("studentId"));
                            session.setAttribute("studentEmail", rs.getString("email"));
                            String grade = rs.getString("class_grade");
                            session.setAttribute("classGrade", grade != null ? grade : "Class 10");
                            session.setAttribute("userRole", "STUDENT");
                            session.setMaxInactiveInterval(60 * 60);

                            res.sendRedirect("studenthome.html");
                            return;
                        }
                    }
                    out.println("<script>alert('Invalid Student Email or Password!'); window.location.href='student.html';</script>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('Server Error: " + e.getMessage().replace("'", "\\'") + "'); window.location.href='student.html';</script>");
        }
    }
}