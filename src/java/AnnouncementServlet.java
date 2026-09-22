import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AnnouncementServlet", urlPatterns = {"/announcements", "/AnnouncementServlet"})
public class AnnouncementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        String classGrade = req.getParameter("class");

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                out.print("{\"status\":\"offline\",\"announcements\":[]}");
                return;
            }

            String sql = "SELECT * FROM announcements WHERE target_class = 'ALL' OR target_class = ? ORDER BY created_at DESC";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, classGrade != null ? classGrade : "Class 10");
                try (ResultSet rs = ps.executeQuery()) {
                    StringBuilder json = new StringBuilder("{\"status\":\"success\",\"announcements\":[");
                    boolean first = true;
                    while (rs.next()) {
                        if (!first) json.append(",");
                        json.append("{");
                        json.append("\"id\":").append(rs.getInt("id")).append(",");
                        json.append("\"title\":\"").append(escapeJson(rs.getString("title"))).append("\",");
                        json.append("\"content\":\"").append(escapeJson(rs.getString("content"))).append("\",");
                        json.append("\"targetClass\":\"").append(escapeJson(rs.getString("target_class"))).append("\",");
                        json.append("\"createdBy\":\"").append(escapeJson(rs.getString("created_by"))).append("\",");
                        json.append("\"createdAt\":\"").append(rs.getString("created_at")).append("\"");
                        json.append("}");
                        first = false;
                    }
                    json.append("]}");
                    out.print(json.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"status\":\"error\",\"message\":\"" + escapeJson(e.getMessage()) + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String targetClass = req.getParameter("targetClass");
        String createdBy = req.getParameter("createdBy");

        if (title == null || content == null) {
            res.sendRedirect("teacherhome.html?err=missing");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            if (con != null) {
                String sql = "INSERT INTO announcements(title, content, target_class, created_by) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, title);
                    ps.setString(2, content);
                    ps.setString(3, targetClass != null ? targetClass : "ALL");
                    ps.setString(4, createdBy != null ? createdBy : "Teacher");
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("teacherhome.html?notice=posted");
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ").replace("\r", " ");
    }
}
