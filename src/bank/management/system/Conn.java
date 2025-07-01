package bank.management.system;
import java.sql.*;

public class Conn {
    
    Connection connection;
    Statement statement;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/managementsystem", "root", "Rohan@workbench03");
            if (connection != null) {
                statement = connection.createStatement();
                System.out.println("HO GYA");
            } else {
                System.out.println("Failed to establish to the database.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
