import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ExamServlet", urlPatterns = {"/exams", "/ExamServlet"})
public class ExamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();

        String classGrade = req.getParameter("class");
        String subject = req.getParameter("subject");
        String examIdStr = req.getParameter("id");

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                // If DB offline, send empty or client app will use offline seed
                out.print("{\"status\":\"offline\",\"message\":\"Database offline. Using built-in exams.\",\"exams\":[]}");
                return;
            }

            if (examIdStr != null && !examIdStr.isEmpty()) {
                int examId = Integer.parseInt(examIdStr);
                // Return specific exam and its questions
                String examSql = "SELECT * FROM exams WHERE id = ?";
                try (PreparedStatement ps = con.prepareStatement(examSql)) {
                    ps.setInt(1, examId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            StringBuilder json = new StringBuilder("{");
                            json.append("\"id\":").append(rs.getInt("id")).append(",");
                            json.append("\"title\":\"").append(escapeJson(rs.getString("title"))).append("\",");
                            json.append("\"classGrade\":\"").append(escapeJson(rs.getString("class_grade"))).append("\",");
                            json.append("\"subject\":\"").append(escapeJson(rs.getString("subject"))).append("\",");
                            json.append("\"durationMinutes\":").append(rs.getInt("duration_minutes")).append(",");
                            json.append("\"totalMarks\":").append(rs.getInt("total_marks")).append(",");
                            json.append("\"passingMarks\":").append(rs.getInt("passing_marks")).append(",");
                            json.append("\"questions\":[");

                            String qSql = "SELECT * FROM questions WHERE exam_id = ?";
                            try (PreparedStatement qps = con.prepareStatement(qSql)) {
                                qps.setInt(1, examId);
                                try (ResultSet qrs = qps.executeQuery()) {
                                    boolean first = true;
                                    while (qrs.next()) {
                                        if (!first) json.append(",");
                                        json.append("{");
                                        json.append("\"id\":").append(qrs.getInt("id")).append(",");
                                        json.append("\"questionText\":\"").append(escapeJson(qrs.getString("question_text"))).append("\",");
                                        json.append("\"optionA\":\"").append(escapeJson(qrs.getString("option_a"))).append("\",");
                                        json.append("\"optionB\":\"").append(escapeJson(qrs.getString("option_b"))).append("\",");
                                        json.append("\"optionC\":\"").append(escapeJson(qrs.getString("option_c"))).append("\",");
                                        json.append("\"optionD\":\"").append(escapeJson(qrs.getString("option_d"))).append("\",");
                                        json.append("\"correctOption\":\"").append(escapeJson(qrs.getString("correct_option"))).append("\",");
                                        json.append("\"explanation\":\"").append(escapeJson(qrs.getString("explanation"))).append("\",");
                                        json.append("\"marks\":").append(qrs.getInt("marks"));
                                        json.append("}");
                                        first = false;
                                    }
                                }
                            }
                            json.append("]}");
                            out.print(json.toString());
                            return;
                        }
                    }
                }
                out.print("{\"error\":\"Exam not found\"}");
                return;
            }

            // List exams
            StringBuilder query = new StringBuilder("SELECT * FROM exams WHERE 1=1");
            List<Object> params = new ArrayList<>();

            if (classGrade != null && !classGrade.trim().isEmpty() && !classGrade.equalsIgnoreCase("ALL")) {
                query.append(" AND class_grade = ?");
                params.add(classGrade.trim());
            }
            if (subject != null && !subject.trim().isEmpty() && !subject.equalsIgnoreCase("ALL")) {
                query.append(" AND subject = ?");
                params.add(subject.trim());
            }
            query.append(" ORDER BY id ASC");

            try (PreparedStatement ps = con.prepareStatement(query.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
                try (ResultSet rs = ps.executeQuery()) {
                    StringBuilder json = new StringBuilder("{\"status\":\"success\",\"exams\":[");
                    boolean first = true;
                    while (rs.next()) {
                        if (!first) json.append(",");
                        json.append("{");
                        json.append("\"id\":").append(rs.getInt("id")).append(",");
                        json.append("\"title\":\"").append(escapeJson(rs.getString("title"))).append("\",");
                        json.append("\"classGrade\":\"").append(escapeJson(rs.getString("class_grade"))).append("\",");
                        json.append("\"subject\":\"").append(escapeJson(rs.getString("subject"))).append("\",");
                        json.append("\"durationMinutes\":").append(rs.getInt("duration_minutes")).append(",");
                        json.append("\"totalMarks\":").append(rs.getInt("total_marks")).append(",");
                        json.append("\"passingMarks\":").append(rs.getInt("passing_marks"));
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
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
