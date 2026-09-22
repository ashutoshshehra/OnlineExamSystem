import java.sql.Connection;
import java.sql.DriverManager;

public final class DBConnection {
    private DBConnection() { }

    private static String setting(String key, String fallback) {
        String value = System.getProperty(key);
        if (value == null || value.trim().isEmpty()) value = System.getenv(key);
        return value == null || value.trim().isEmpty() ? fallback : value.trim();
    }

    public static Connection getConnection() {
        String url = setting("SMARTEXAM_DB_URL", "jdbc:mysql://localhost:3306/exam?useSSL=true&serverTimezone=UTC");
        String user = setting("SMARTEXAM_DB_USER", "root");
        String password = setting("SMARTEXAM_DB_PASSWORD", null);
        if (password == null) {
            System.err.println("Database password is not configured. Set SMARTEXAM_DB_PASSWORD.");
            return null;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getClass().getSimpleName());
            return null;
        }
    }

    public static boolean testConnection() {
        try (Connection c = getConnection()) {
            return c != null && !c.isClosed();
        } catch (Exception e) { return false; }
    }

    public static void closeQuietly(AutoCloseable resource) {
        if (resource != null) try { resource.close(); } catch (Exception ignored) { }
    }
}
