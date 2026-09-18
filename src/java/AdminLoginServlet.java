import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Login")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            out.println("<h3 style='color:red;'>All fields are required ❌</h3>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String query = "SELECT * FROM admins WHERE email=? AND password=?";

            try (Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/exam?useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "ashu0811");
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, email.trim());
                ps.setString(2, password.trim());

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        HttpSession session = req.getSession();
                        session.setAttribute("adminName", rs.getString("name"));
                        session.setMaxInactiveInterval(30 * 60); // 30 min session

                        res.sendRedirect("teacherhome.html"); // fixed redirect
                    } else {
                        out.println("<h3 style='color:red;'>Invalid Email or Password ❌</h3>");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }
}