import java.io.IOException;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String adminId = req.getParameter("adminId");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirmPassword");

        if (!password.equals(confirm)) {
            res.getWriter().println("Password not match!");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            String q = "INSERT INTO admins(name, adminId, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(q);

            ps.setString(1, name);
            ps.setString(2, adminId);
            ps.setString(3, email);
            ps.setString(4, password);

            int i = ps.executeUpdate();

            if (i > 0) {
                res.sendRedirect("techerhome.html");
            } else {
                res.getWriter().println("Registration failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}