import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AIQuestionServlet", urlPatterns = {"/aiGenerateQuestions", "/AIQuestionServlet"})
public class AIQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();

        String classGrade = req.getParameter("classGrade");
        String subject = req.getParameter("subject");
        String topic = req.getParameter("topic");
        String difficulty = req.getParameter("difficulty");
        String countStr = req.getParameter("count");

        if (topic == null || topic.trim().isEmpty()) {
            topic = "General Concepts";
        }
        if (subject == null || subject.trim().isEmpty()) {
            subject = "General";
        }
        if (classGrade == null || classGrade.trim().isEmpty()) {
            classGrade = "Class 10";
        }
        int count = 5;
        if (countStr != null) {
            try {
                count = Math.min(Math.max(Integer.parseInt(countStr), 1), 15);
            } catch (Exception ignored) {}
        }

        // Generate synthetic questions based on topic and class
        List<Question> generated = generateSyntheticQuestions(classGrade, subject, topic, difficulty, count);

        StringBuilder json = new StringBuilder("{\"status\":\"success\",\"topic\":\"")
            .append(escapeJson(topic)).append("\",\"questions\":[");

        for (int i = 0; i < generated.size(); i++) {
            Question q = generated.get(i);
            if (i > 0) json.append(",");
            json.append("{");
            json.append("\"id\":").append(i + 1).append(",");
            json.append("\"questionText\":\"").append(escapeJson(q.getQuestionText())).append("\",");
            json.append("\"optionA\":\"").append(escapeJson(q.getOptionA())).append("\",");
            json.append("\"optionB\":\"").append(escapeJson(q.getOptionB())).append("\",");
            json.append("\"optionC\":\"").append(escapeJson(q.getOptionC())).append("\",");
            json.append("\"optionD\":\"").append(escapeJson(q.getOptionD())).append("\",");
            json.append("\"correctOption\":\"").append(q.getCorrectOption()).append("\",");
            json.append("\"explanation\":\"").append(escapeJson(q.getExplanation())).append("\",");
            json.append("\"marks\":").append(q.getMarks());
            json.append("}");
        }
        json.append("]}");
        out.print(json.toString());
    }

    private List<Question> generateSyntheticQuestions(String grade, String subject, String topic, String diff, int count) {
        List<Question> list = new ArrayList<>();
        String t = topic.trim();

        for (int i = 1; i <= count; i++) {
            Question q = new Question();
            q.setId(i);
            q.setMarks(diff != null && diff.equalsIgnoreCase("hard") ? 4 : 2);

            switch (i % 5) {
                case 1:
                    q.setQuestionText("What is the fundamental definition or core principle behind " + t + " in " + subject + "?");
                    q.setOptionA("A core process or property governing " + t + " under standard conditions");
                    q.setOptionB("An arbitrary constant with no practical application");
                    q.setOptionC("A temporary state observed only in isolation");
                    q.setOptionD("An obsolete concept replaced in modern science");
                    q.setCorrectOption("A");
                    q.setExplanation("The core principle of " + t + " is defined by its foundational governing laws and direct real-world observable properties.");
                    break;
                case 2:
                    q.setQuestionText("Which of the following statements is mathematically or conceptually ACCURATE regarding " + t + "?");
                    q.setOptionA("It contradicts standard conservation laws");
                    q.setOptionB("It exhibits directly proportional relationships under controlled equilibrium");
                    q.setOptionC("It remains completely static and non-reactive");
                    q.setOptionD("It only functions in a vacuum without any medium");
                    q.setCorrectOption("B");
                    q.setExplanation("In " + subject + ", " + t + " exhibits direct equilibrium and predictable proportionality as verified experimentally.");
                    break;
                case 3:
                    q.setQuestionText("In practical applications of " + t + " (" + grade + "), what primary factor most significantly influences its efficiency?");
                    q.setOptionA("Random fluctuations in ambient noise");
                    q.setOptionB("System parameters, temperature/medium gradients, and input energy");
                    q.setOptionC("The geographical orientation of the apparatus");
                    q.setOptionD("It operates with 100% efficiency regardless of parameters");
                    q.setCorrectOption("B");
                    q.setExplanation("Efficiency and rate in " + t + " are fundamentally determined by system state parameters, potential gradients, and resistance factors.");
                    break;
                case 4:
                    q.setQuestionText("What is the primary role or consequence of " + t + " within its broader domain in " + subject + "?");
                    q.setOptionA("It facilitates state transition and equilibrium optimization");
                    q.setOptionB("It destabilizes the surrounding structure permanently");
                    q.setOptionC("It eliminates all thermodynamic entropy");
                    q.setOptionD("It has no observable consequence");
                    q.setCorrectOption("A");
                    q.setExplanation("A primary purpose of " + t + " is to enable predictable state progression and maintain structured equilibrium within the system.");
                    break;
                default:
                    q.setQuestionText("Which scientific or technical unit/notation is commonly associated with measuring or expressing " + t + "?");
                    q.setOptionA("Standard SI or metric dimensional derived units");
                    q.setOptionB("Light-years per second");
                    q.setOptionC("Atmospheres per cubic millimeter");
                    q.setOptionD("Unitless pure imaginary scalars exclusively");
                    q.setCorrectOption("A");
                    q.setExplanation(t + " is expressed using standard SI or calibrated dimensional units appropriate to " + subject + ".");
                    break;
            }
            list.add(q);
        }
        return list;
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ").replace("\r", " ");
    }
}
