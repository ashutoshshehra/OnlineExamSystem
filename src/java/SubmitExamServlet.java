import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "SubmitExamServlet", urlPatterns = {"/submitExam", "/SubmitExamServlet"})
public class SubmitExamServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();

        String examIdStr = req.getParameter("examId");
        String studentName = req.getParameter("studentName");
        String studentId = req.getParameter("studentId");
        String scoreStr = req.getParameter("score");
        String totalMarksStr = req.getParameter("totalMarks");
        String durationTakenStr = req.getParameter("durationTaken");
        String tabSwitchCountStr = req.getParameter("tabSwitchCount");

        if (studentName == null || studentName.trim().isEmpty()) {
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("studentName") != null) {
                studentName = (String) session.getAttribute("studentName");
            } else {
                studentName = "Student";
            }
        }

        if (studentId == null || studentId.trim().isEmpty()) {
            studentId = "STU-" + (int)(Math.random() * 9000 + 1000);
        }

        int examId = examIdStr != null ? Integer.parseInt(examIdStr) : 1;
        int score = scoreStr != null ? Integer.parseInt(scoreStr) : 0;
        int totalMarks = totalMarksStr != null ? Integer.parseInt(totalMarksStr) : 20;
        int durationTaken = durationTakenStr != null ? Integer.parseInt(durationTakenStr) : 0;
        int tabSwitchCount = tabSwitchCountStr != null ? Integer.parseInt(tabSwitchCountStr) : 0;

        double percentage = totalMarks > 0 ? ((double) score / totalMarks) * 100.0 : 0.0;
        String status = percentage >= 40.0 ? "PASS" : "FAIL";

        // Save into DB if available
        try (Connection con = DBConnection.getConnection()) {
            if (con != null) {
                String sql = "INSERT INTO exam_attempts (exam_id, student_id, student_name, score, total_marks, percentage, status, duration_taken, tab_switch_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, examId);
                    ps.setString(2, studentId);
                    ps.setString(3, studentName);
                    ps.setInt(4, score);
                    ps.setInt(5, totalMarks);
                    ps.setDouble(6, Math.round(percentage * 100.0) / 100.0);
                    ps.setString(7, status);
                    ps.setInt(8, durationTaken);
                    ps.setInt(9, tabSwitchCount);
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return JSON confirmation
        out.print(String.format(
            "{\"status\":\"success\",\"score\":%d,\"totalMarks\":%d,\"percentage\":%.1f,\"resultStatus\":\"%s\",\"studentName\":\"%s\",\"tabSwitchCount\":%d}",
            score, totalMarks, percentage, status, studentName.replace("\"", "\\\""), tabSwitchCount
        ));
    }
}
