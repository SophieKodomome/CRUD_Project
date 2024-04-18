import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteTask {
    private String url = "jdbc:postgresql://localhost:5432/crud";
    private String username = "crud";
    private String password = "crud";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("task");
    
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
    
            String sql = "INSERT INTO todolist (task, status, remind, due) VALUES (?, false, ?, ?)";
    
            PreparedStatement statement = connection.prepareStatement(sql);
    
            statement.setString(1, task);
            statement.setTimestamp(2, remindTime);
            statement.setTimestamp(3, dueTime);
    
            int rowsInserted = statement.executeUpdate();
    
            statement.close();
            connection.close();
    
            if (rowsInserted > 0) {
                response.sendRedirect("index.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Task cannot be added");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error: " + e.getMessage());
        }
    }
}
