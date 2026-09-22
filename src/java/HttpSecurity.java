import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public final class HttpSecurity {
    private HttpSecurity() { }
    public static void headers(HttpServletResponse response) {
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("Referrer-Policy", "no-referrer");
        response.setHeader("Cache-Control", "no-store");
    }
    public static void requireRole(javax.servlet.http.HttpServletRequest request, HttpServletResponse response, String role) throws IOException {
        Object actual=request.getSession(false)==null ? null : request.getSession(false).getAttribute("userRole");
        if (!role.equals(actual)) { response.sendError(HttpServletResponse.SC_UNAUTHORIZED); }
    }
}
