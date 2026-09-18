import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/studentRegister")
public class StudentRegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
           String studentId= request.getParameter("studentId");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // 🔥 DIRECT DATABASE CONNECTION
            Class.forName("com.mysql.cj.jdbc.Driver");

                      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/exam?useSSL=false&allowPublicKeyRetrieval=true","root","ashu0811");

            // 🔍 Debug check
            if (con == null) {
                out.println("<h3 style='color:red;'>❌ Database not connected</h3>");
                return;
            }

            // 🔥 INSERT QUERY
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO students(name,email,password) VALUES(?,?,?)"
            );

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3 style='color:green;'>✅ Registration Successful</h3>");
                
                // redirect after 2 sec
                response.setHeader("refresh", "2;URL=studenthome.html");
            } else {
                out.println("<h3 style='color:red;'>❌ Registration Failed</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Error: " + e + "</h3>");
        }
    }
}