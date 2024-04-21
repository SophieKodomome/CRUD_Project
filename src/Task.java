import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Task {
    private int id;
    private String task;
    private int orderTask;
    private Timestamp remind;
    private Timestamp due;
    private boolean status;

    public Task() {}

    public Task addId(int i) {
        this.id = i;
        return this;
    }

    public Task addTask(String t) {
        this.task = t;
        return this;
    }

    public Task addOrderTask(int o) {
        this.orderTask = o;
        return this;
    }

    public Task addStatus(boolean s) {
        this.status = s;
        return this;
    }

    public Task addRemindTask(Timestamp r) {
        this.remind = r;
        return this;
    }

    public Task addDueTask(Timestamp d) {
        this.due = d;
        return this;
    }
    
    public int getId(){
        return this.id;
    }

    public String getTask(){
        return this.task;
    }

    public int getOrderTask(){
        return this.orderTask;
    }

    public Timestamp getRemind(){
        return this.remind;
    }

    public Timestamp getDue(){
        return this.due;
    }

    public boolean getStatus(){
        return this.status;
    }
    public static int addTask(String task, Timestamp remindTime, Timestamp dueTime, Connection connection) throws SQLException {
        String sql = "INSERT INTO todolist (task, status, remind, due) VALUES (?, false, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, task);
        statement.setTimestamp(2, remindTime);
        statement.setTimestamp(3, dueTime);
        int rowsInserted = statement.executeUpdate();
        statement.close();
        return rowsInserted;
    }
}
