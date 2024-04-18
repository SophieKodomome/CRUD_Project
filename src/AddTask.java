package myclasses;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.http.*;

import connect.*;
import task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTask extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        Timestamp remindTime = convertDateTimeFormat(request.getParameter("remind_time"));
        Timestamp dueTime =  convertDateTimeFormat(request.getParameter("due_time"));
    
        if (task.isEmpty() || dueTime ==null || remindTime==null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Information Incomplete");
            return;
        }
    
        try {
            Connection connection = PostgreConnect.connectToPostgre();

    
            int rowsInserted = Task.addTask(task, remindTime, dueTime,connection );


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
    

    private Timestamp convertDateTimeFormat(String date){
        // Define input format
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    
        // Parse input string to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
    
        // Convert LocalDateTime to Timestamp
        return Timestamp.valueOf(dateTime);
    }
}