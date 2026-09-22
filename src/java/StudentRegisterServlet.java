import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "StudentRegisterServlet", urlPatterns = {"/StudentRegisterServlet", "/studentRegister"})
public class StudentRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("name");
        String studentId = req.getParameter("studentId");
        String classGrade = req.getParameter("classGrade");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        if (name == null || email == null || password == null || name.trim().isEmpty() || email.trim().isEmpty()) {
            out.println("<script>alert('All required fields must be filled!'); window.location.href='student.html';</script>");
            return;
        }

        if (studentId == null || studentId.trim().isEmpty()) {
            studentId = "STU-" + (int)(Math.random() * 9000 + 1000);
        }

        if (classGrade == null || classGrade.trim().isEmpty()) {
            classGrade = "Class 10";
        }

        if (confirm != null && !password.equals(confirm)) {
            out.println("<script>alert('Passwords do not match!'); window.location.href='student.html';</script>");
            return;
        }

        name = name.trim();
        email = email.trim();
        studentId = studentId.trim();
        classGrade = classGrade.trim();
        String hashedPassword = PasswordUtil.hashPassword(password);

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                // Offline fallback session
                HttpSession session = req.getSession();
                session.setAttribute("studentName", name);
                session.setAttribute("studentId", studentId);
                session.setAttribute("studentEmail", email);
                session.setAttribute("classGrade", classGrade);
                session.setAttribute("userRole", "STUDENT");
                res.sendRedirect("studenthome.html");
                return;
            }

            String query = "INSERT INTO students(name, studentId, class_grade, email, password) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, studentId);
                ps.setString(3, classGrade);
                ps.setString(4, email);
                ps.setString(5, hashedPassword);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    HttpSession session = req.getSession();
                    session.setAttribute("studentName", name);
                    session.setAttribute("studentId", studentId);
                    session.setAttribute("studentEmail", email);
                    session.setAttribute("classGrade", classGrade);
                    session.setAttribute("userRole", "STUDENT");
                    res.sendRedirect("studenthome.html");
                } else {
                    out.println("<script>alert('Registration failed. Please try again.'); window.location.href='student.html';</script>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('Registration Error: " + e.getMessage().replace("'", "\\'") + "'); window.location.href='student.html';</script>");
        }
    }
}