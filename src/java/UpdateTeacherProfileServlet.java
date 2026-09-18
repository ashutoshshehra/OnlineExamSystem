import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UpdateTeacherProfile")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // max 5MB
public class UpdateTeacherProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("currentTeacher") == null){
            res.sendRedirect("admin.html");
            return;
        }

        Teacher teacher = (Teacher) session.getAttribute("currentTeacher");

        teacher.setName(req.getParameter("name"));
        teacher.setEmail(req.getParameter("email"));
        teacher.setPhone(req.getParameter("phone"));
        if(req.getParameter("password") != null && !req.getParameter("password").isEmpty()){
            teacher.setPassword(req.getParameter("password"));
        }

        // Handle avatar upload
       Part avatarPart = req.getPart("avatar");
if (avatarPart != null && avatarPart.getSize() > 0) {
    java.io.InputStream is = avatarPart.getInputStream();
    java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
    int nRead;
    byte[] data = new byte[1024];
    while ((nRead = is.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, nRead);
    }
    buffer.flush();
    byte[] bytes = buffer.toByteArray();
    String base64 = "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(bytes);
    teacher.setAvatar(base64);
}

        // Update DB
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/exam?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "ashu0811"
            );
            String sql = "UPDATE admin SET name=?, email=?, phone=?, password=?, avatar=? WHERE teacherId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, teacher.getName());
            ps.setString(2, teacher.getEmail());
            ps.setString(3, teacher.getPhone());
            ps.setString(4, teacher.getPassword());
            ps.setString(5, teacher.getAvatar());
            ps.setString(6, teacher.getTeacherId());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("currentTeacher", teacher); // update session
        res.sendRedirect("teacherhome.html");
    }
}