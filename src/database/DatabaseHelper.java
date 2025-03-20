package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/medicine_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "!@#mySQL123";


    public static Connection connect(){
        try{
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if(conn!=null){
                System.out.println("Connected to the database");
            }
            return conn;
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            // e.printStackTrace();
            return null;
        }
    }

    // public static void main(String[] args) {
    //     //Test the connection
    //     connect();
    // }
}