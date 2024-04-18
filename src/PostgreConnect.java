package connect;


import java.sql.*;

public class PostgreConnect {
    private static String url = "jdbc:postgresql://localhost:5432/crud";
    private static String username = "crud";
    private static String password = "crud";

    public static Connection connectToPostgre() throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            throw e;
        }
    }


}
