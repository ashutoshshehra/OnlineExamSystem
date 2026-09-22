import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AdminRegisterServlet", urlPatterns = {"/AdminRegisterServlet"})
public class AdminRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name=req.getParameter("name"), id=req.getParameter("adminId"), email=req.getParameter("email");
        String password=req.getParameter("password"), confirm=req.getParameter("confirmPassword");
        if(name==null || email==null || password==null || confirm==null || name.trim().isEmpty() || email.trim().isEmpty() || password.length()<8) { fail(res,"Name, email, and a password of at least 8 characters are required."); return; }
        if(!password.equals(confirm)) { fail(res,"Passwords do not match."); return; }
        if(id==null || id.trim().isEmpty()) id="T-"+java.util.UUID.randomUUID().toString().substring(0,8).toUpperCase();
        try(Connection con=DBConnection.getConnection()) {
            if(con==null) { fail(res,"The service is temporarily unavailable."); return; }
            try(PreparedStatement ps=con.prepareStatement("INSERT INTO admins(name, adminId, email, password) VALUES (?, ?, ?, ?)")) {
                ps.setString(1,name.trim()); ps.setString(2,id.trim()); ps.setString(3,email.trim()); ps.setString(4,PasswordUtil.hashPassword(password)); ps.executeUpdate();
            }
            res.sendRedirect("admin.html?registered=1");
        } catch(Exception e) { getServletContext().log("Admin registration failed",e); fail(res,"Registration could not be completed. The email or employee ID may already exist."); }
    }
    private void fail(HttpServletResponse res,String message) throws IOException { res.sendRedirect("admin.html?error="+java.net.URLEncoder.encode(message,"UTF-8")); }
}
