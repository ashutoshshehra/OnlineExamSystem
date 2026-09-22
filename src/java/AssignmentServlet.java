import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AssignmentServlet", urlPatterns = {"/assignments", "/AssignmentServlet"})
public class AssignmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        String classGrade = req.getParameter("class");

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                out.print("{\"status\":\"offline\",\"assignments\":[]}");
                return;
            }

            StringBuilder sql = new StringBuilder("SELECT a.*, COUNT(s.id) as sub_count FROM assignments a LEFT JOIN submissions s ON a.id = s.assignment_id WHERE 1=1");
            if (classGrade != null && !classGrade.trim().isEmpty() && !classGrade.equalsIgnoreCase("ALL")) {
                sql.append(" AND a.class_grade = ?");
            }
            sql.append(" GROUP BY a.id ORDER BY a.due_date ASC");

            try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
                if (classGrade != null && !classGrade.trim().isEmpty() && !classGrade.equalsIgnoreCase("ALL")) {
                    ps.setString(1, classGrade.trim());
                }
                try (ResultSet rs = ps.executeQuery()) {
                    StringBuilder json = new StringBuilder("{\"status\":\"success\",\"assignments\":[");
                    boolean first = true;
                    while (rs.next()) {
                        if (!first) json.append(",");
                        json.append("{");
                        json.append("\"id\":").append(rs.getInt("id")).append(",");
                        json.append("\"title\":\"").append(escapeJson(rs.getString("title"))).append("\",");
                        json.append("\"classGrade\":\"").append(escapeJson(rs.getString("class_grade"))).append("\",");
                        json.append("\"subject\":\"").append(escapeJson(rs.getString("subject"))).append("\",");
                        json.append("\"dueDate\":\"").append(rs.getString("due_date")).append("\",");
                        json.append("\"description\":\"").append(escapeJson(rs.getString("description"))).append("\",");
                        json.append("\"submissionCount\":").append(rs.getInt("sub_count"));
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
        String classGrade = req.getParameter("classGrade");
        String subject = req.getParameter("subject");
        String dueDate = req.getParameter("dueDate");
        String description = req.getParameter("description");

        if (title == null || dueDate == null) {
            res.sendRedirect("teacherassignment.html?err=missing");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            if (con != null) {
                String sql = "INSERT INTO assignments(title, class_grade, subject, due_date, description) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, title);
                    ps.setString(2, classGrade != null ? classGrade : "Class 10");
                    ps.setString(3, subject != null ? subject : "General");
                    ps.setDate(4, Date.valueOf(dueDate));
                    ps.setString(5, description);
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("teacherassignment.html?status=created");
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ").replace("\r", " ");
    }
}
