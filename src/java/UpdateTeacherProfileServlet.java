import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "UpdateTeacherProfileServlet", urlPatterns = {"/UpdateTeacherProfileServlet", "/UpdateTeacherProfile"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // max 5MB
public class UpdateTeacherProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        Teacher teacher = (Teacher) session.getAttribute("currentTeacher");
        if (teacher == null) {
            teacher = new Teacher("Teacher", "T-101", "admin@example.com", "", null);
        }

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        if (name != null && !name.trim().isEmpty()) teacher.setName(name.trim());
        if (email != null && !email.trim().isEmpty()) teacher.setEmail(email.trim());
        if (phone != null) teacher.setPhone(phone.trim());
        if (password != null && !password.trim().isEmpty()) {
            teacher.setPassword(PasswordUtil.hashPassword(password.trim()));
        }

        // Handle avatar upload
        try {
            Part avatarPart = req.getPart("avatar");
            if (avatarPart != null && avatarPart.getSize() > 0) {
                try (InputStream is = avatarPart.getInputStream();
                     ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                    byte[] data = new byte[1024];
                    int nRead;
                    while ((nRead = is.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, nRead);
                    }
                    String base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(buffer.toByteArray());
                    teacher.setAvatar(base64);
                }
            }
        } catch (Exception ignored) {
        }

        // Update database
        try (Connection con = DBConnection.getConnection()) {
            if (con != null) {
                String sql = "UPDATE admins SET name = ?, email = ?, phone = ?, avatar = ? WHERE adminId = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, teacher.getName());
                    ps.setString(2, teacher.getEmail());
                    ps.setString(3, teacher.getPhone());
                    ps.setString(4, teacher.getAvatar());
                    ps.setString(5, teacher.getTeacherId());
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("currentTeacher", teacher);
        session.setAttribute("adminName", teacher.getName());
        res.sendRedirect("teacher account.html?status=updated");
    }
}