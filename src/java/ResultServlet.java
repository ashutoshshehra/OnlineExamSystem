import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ResultServlet", urlPatterns = {"/resultsApi", "/ResultServlet"})
public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String format = req.getParameter("format");
        String studentId = req.getParameter("studentId");
        String examIdStr = req.getParameter("examId");

        if ("csv".equalsIgnoreCase(format)) {
            res.setContentType("text/csv");
            res.setHeader("Content-Disposition", "attachment; filename=\"exam_results.csv\"");
            PrintWriter out = res.getWriter();
            out.println("Date,Student Name,Student ID,Exam Title,Score,Total Marks,Percentage,Status,Tab Switches");

            try (Connection con = DBConnection.getConnection()) {
                if (con != null) {
                    String sql = "SELECT a.*, e.title as exam_title FROM exam_attempts a LEFT JOIN exams e ON a.exam_id = e.id ORDER BY a.attempted_at DESC";
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(sql)) {
                        while (rs.next()) {
                            out.printf("\"%s\",\"%s\",\"%s\",\"%s\",%d,%d,%.1f,\"%s\",%d\n",
                                rs.getString("attempted_at"),
                                rs.getString("student_name"),
                                rs.getString("student_id"),
                                rs.getString("exam_title") != null ? rs.getString("exam_title") : "Exam " + rs.getInt("exam_id"),
                                rs.getInt("score"),
                                rs.getInt("total_marks"),
                                rs.getDouble("percentage"),
                                rs.getString("status"),
                                rs.getInt("tab_switch_count")
                            );
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        // Return JSON
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                out.print("{\"status\":\"offline\",\"results\":[]}");
                return;
            }

            StringBuilder query = new StringBuilder(
                "SELECT a.*, e.title as exam_title, e.subject FROM exam_attempts a LEFT JOIN exams e ON a.exam_id = e.id WHERE 1=1"
            );
            if (studentId != null && !studentId.trim().isEmpty()) {
                query.append(" AND a.student_id = ?");
            }
            if (examIdStr != null && !examIdStr.trim().isEmpty()) {
                query.append(" AND a.exam_id = ?");
            }
            query.append(" ORDER BY a.attempted_at DESC");

            try (PreparedStatement ps = con.prepareStatement(query.toString())) {
                int pIndex = 1;
                if (studentId != null && !studentId.trim().isEmpty()) {
                    ps.setString(pIndex++, studentId.trim());
                }
                if (examIdStr != null && !examIdStr.trim().isEmpty()) {
                    ps.setInt(pIndex++, Integer.parseInt(examIdStr.trim()));
                }

                try (ResultSet rs = ps.executeQuery()) {
                    StringBuilder json = new StringBuilder("{\"status\":\"success\",\"results\":[");
                    boolean first = true;
                    while (rs.next()) {
                        if (!first) json.append(",");
                        json.append("{");
                        json.append("\"id\":").append(rs.getInt("id")).append(",");
                        json.append("\"examId\":").append(rs.getInt("exam_id")).append(",");
                        json.append("\"examTitle\":\"").append(escapeJson(rs.getString("exam_title"))).append("\",");
                        json.append("\"subject\":\"").append(escapeJson(rs.getString("subject"))).append("\",");
                        json.append("\"studentId\":\"").append(escapeJson(rs.getString("student_id"))).append("\",");
                        json.append("\"studentName\":\"").append(escapeJson(rs.getString("student_name"))).append("\",");
                        json.append("\"score\":").append(rs.getInt("score")).append(",");
                        json.append("\"totalMarks\":").append(rs.getInt("total_marks")).append(",");
                        json.append("\"percentage\":").append(rs.getDouble("percentage")).append(",");
                        json.append("\"status\":\"").append(escapeJson(rs.getString("status"))).append("\",");
                        json.append("\"tabSwitchCount\":").append(rs.getInt("tab_switch_count")).append(",");
                        json.append("\"attemptedAt\":\"").append(rs.getString("attempted_at")).append("\"");
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

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ").replace("\r", " ");
    }
}
