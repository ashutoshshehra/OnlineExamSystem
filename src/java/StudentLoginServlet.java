import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/studentLogin")
public class StudentLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // 🔥 DB CONNECTION DIRECTLY HERE
            Class.forName("com.mysql.cj.jdbc.Driver");

                      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/exam?useSSL=false&allowPublicKeyRetrieval=true","root","ashu0811");

            // 🔍 Debug
            if (con == null) {
                out.println("<h3 style='color:red;'>❌ Database not connected</h3>");
                return;
            } else {
                out.println("<h3 style='color:green;'>✅ Database Connected</h3>");
            }

            // 🔥 LOGIN QUERY
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM students WHERE email=? AND password=?"
            );

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                HttpSession session = request.getSession();
                session.setAttribute("studentName", rs.getString("name"));

                // redirect after success
                response.sendRedirect("studenthome.html");

            } else {
                out.println("<h3 style='color:red;'>Invalid Login ❌</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace(); // console me full error
            out.println("<h3 style='color:red;'>Error: " + e + "</h3>");
        }
    }
}