import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PSQLConnect {
    private static String url = "jdbc:postgresql://localhost:5432/crud";
    private static String username = "crud";
    private static String password = "crud";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
