package project.javaminiproject;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnected(){
        String databaseName="EventManagement";
        String databaseUser="AnsfridPereira";
        String databasePassword="hnJTyCBzYMkCbMJ0";

        String url="jdbc:mysql://jblminiproject-do-user-10209104-0.b.db.ondigitalocean.com:25060/"+databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,databaseUser,databasePassword);
            System.out.println("Database connection done");
        }catch (Exception event){
            event.printStackTrace();
            System.out.println("database connection failure");
        }
        return databaseLink;
    }
}
