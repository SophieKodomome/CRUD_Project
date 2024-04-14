import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTask extends HttpServlet {
    private String url = "jdbc:postgresql://localhost:5432/crud";
    private String username = "crud";
    private String password = "crud";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        Timestamp remindTime = convertDateTimeFormat(request.getParameter("remind_time"));
        Timestamp dueTime =  convertDateTimeFormat(request.getParameter("due_time"));
    
        if (task.isEmpty() || dueTime ==null || remindTime==null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Information Incomplete");
            return;
        }
    
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
                response.sendRedirect("index.html");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Task cannot be added");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error: " + e.getMessage());
        }
    }
    

    private Timestamp convertDateTimeFormat(String date){
        // Define input format
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    
        // Parse input string to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
    
        // Convert LocalDateTime to Timestamp
        return Timestamp.valueOf(dateTime);
    }
}