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

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        String remindTime = convertDateTimeFormat(request.getParameter("remind_time"));
        String dueTime =  convertDateTimeFormat(request.getParameter("due_time"));

        if(task.isEmpty() || dueTime!=null || remindTime!=null){
            try{
                Connection connection = DriverManager.getConnection(url, username, password);

                String sql = "INSERT INTO todolist (task,status,remind,due) VALUES (?,false,?,?)";

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, task);
                statement.setString(2, remindTime);
                statement.setString(3, dueTime);

                int rowsInserted = statement.executeUpdate();

                statement.close();
                connection.close();

                if(rowsInserted > 0){
                    response.sendRedirect("index.html");
                }else{
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Task cannot be added");
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la connexion a la base de donnees "+remindTime+" "+dueTime);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Information Incomplete");
        }
    }

    private String convertDateTimeFormat(String date){

                LocalDateTime originalDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
                LocalDateTime convertedDateTime = originalDateTime.plusDays(1).withHour(23).withMinute(59).withSecond(59);
        
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String convertedDateTimeString = convertedDateTime.format(formatter);
        
                return convertedDateTimeString;
    }

}