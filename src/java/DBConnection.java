import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

     Connection con = null;

try {
    Class.forName("com.mysql.cj.jdbc.Driver");

    System.out.println("Driver Loaded");

         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/exam?useSSL=false&allowPublicKeyRetrieval=true","root","ashu0811");

    System.out.println("Connected Successfully");

} catch (Exception e) {
    System.out.println("Connection Failed ❌");
    e.printStackTrace();
}

        return con;
    }
}