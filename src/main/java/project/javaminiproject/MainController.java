package project.javaminiproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.util.Objects;
import java.sql.*;
 //Registration
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;


public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    //login control functions
    public void switchToMainApp(ActionEvent event) throws IOException {


    }
    public void SwitchToRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }





    //register control functions




    //forgot page control functions
    public void switchToforgotpage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("forgotuserpass.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    //main page control functions
    public void switchToEventPage(ActionEvent event) throws IOException {

    }
    @FXML
    public void SwitchTologinpage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    //login database connection
    @FXML
    private Button Login;
    @FXML
    private TextField usernametextfield;
    @FXML
    private PasswordField passwordtextfield;
    @FXML
    private TextField cancel;



    public void LoginOnAction(ActionEvent event)throws IOException{
        //Login.setText("Invalid Login!");
        if (!usernametextfield.getText().isBlank() && !passwordtextfield.getText().isBlank()){
            Login.setText("Try to login");
            if (validateLogin()){
                SwitchToMainPage(event);
            }
        }
        else{Login.setText("Enter your Username and Password");}
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();

    }

    public boolean validateLogin()throws IOException{
        DatabaseConnection  connection =new DatabaseConnection();
        Connection connectDB = connection.getConnected();

        String verifylogin ="SELECT count(1) FROM Registration_details WHERE username='"+usernametextfield.getText()+"'AND password='"+passwordtextfield.getText()+"'";
        try{
            Statement statement=connectDB.createStatement();
            ResultSet queryResult =statement.executeQuery(verifylogin);
            while (queryResult.next()){
                if(queryResult.getInt(1)==1){
                    //Login.setText("Welcome");
                    return true;
                }else {
                    //Login.setText("Enter valid Username or password");
                    return false;
                }
            }
        } catch (Exception event){
            event.printStackTrace();
        }
        return false;
    }
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField num;
    @FXML
    private TextField email;
    public void registerdetails(ActionEvent e) {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB= connection.getConnected();
        String rdetails="INSERT INTO Registration_details(Firstname,Lastname,Phonenumber,Emailid,Username,Password) VALUES ('"+fName.getText()+"','"+lName.getText()+"','"+num.getText()+"','"+email.getText()+"','"+usernametextfield.getText()+"','"+passwordtextfield.getText()+"')";
        if(!fName.getText().isEmpty()&&!lName.getText().isEmpty()&&!num.getText().isEmpty()&&!usernametextfield.getText().isEmpty()&&!passwordtextfield.getText().isEmpty()){
        try{
            Statement statement=connectDB.createStatement();
            int a = statement.executeUpdate(rdetails);
            if (a==1)
                SwitchTologinpage(e);
        } catch (Exception event){
            event.printStackTrace();
        }
        }
    }


    //EventPage control functions
    public void SwitchToMainPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tempMain.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }




//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}