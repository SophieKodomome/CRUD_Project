import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PSQLConnect {
    private String url = "jdbc:postgresql://localhost:5432/crud";
    private String username = "crud";
    private String password = "crud";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
