import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    /**
     * Hashes a plain-text password using SHA-256 algorithm.
     */
    public static String hashPassword(String password) {
        if (password == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.trim().getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return password; // Fallback
        }
    }

    /**
     * Verifies password matching against either hashed value or direct match.
     */
    public static boolean verifyPassword(String inputPassword, String storedPassword) {
        if (inputPassword == null || storedPassword == null) return false;
        String trimmedInput = inputPassword.trim();
        String trimmedStored = storedPassword.trim();

        // Check if stored password is plain text
        if (trimmedInput.equals(trimmedStored)) {
            return true;
        }

        // Check against SHA-256 hash
        String hashedInput = hashPassword(trimmedInput);
        return hashedInput.equalsIgnoreCase(trimmedStored);
    }
}
