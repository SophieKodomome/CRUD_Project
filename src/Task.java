import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
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

    public static ArrayList<Task> getTodoList() {
        ArrayList<Task> todolists = new ArrayList<>();
        String url = "jdbc:postgresql://localhost:5432/crud";
        String username = "crud";
        String password = "crud";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from todolist");

            while (result.next()) {
                Task task = new Task();
                task
                        .addId(result.getInt(1))
                        .addTask(result.getString(2))
                        .addOrderTask(result.getInt(3))
                        .addStatus(result.getBoolean(4))
                        .addRemindTask(result.getTimestamp(5))
                        .addDueTask(result.getTimestamp(6));
                todolists.add(task);
            }

            statement.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todolists;
    }
}
