package project.javaminiproject;
 import java.sql.Connection;
 import java.sql.DriverManager;

public class Databaseconnection {
    public Connection databaseLink;
    public Connection getConnected(){
        String databaseName="EventManagement";
        String databaseUser="AnsfridPereira";
        String databasePassword="tSLvVhOTies17qPZ";

        String url="jdbc:mysql://localhost/" +databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception event){
            event.printStackTrace();
        }
return databaseLink;
    }
}
