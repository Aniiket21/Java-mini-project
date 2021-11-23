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
    public void switchToRegister(ActionEvent event) throws IOException {
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
    private Label username;
    @FXML
    private Label password;
    @FXML
    private TextField usernametextfield;
    @FXML
    private PasswordField passwordtextfield;
    @FXML
    private Button cancel;



    public void LoginOnAction(ActionEvent event)throws IOException{
        Login.setText("Invalid Login!");
        if (usernametextfield.getText().isBlank()==false && passwordtextfield.getText().isBlank()==false){
            Login.setText("Try to login");
            validateLogin();
        }
        else{Login.setText("Enter your Username and Password");}
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();

    }

    public void validateLogin()throws IOException{
        DatabaseConnection  connection =new DatabaseConnection();
        Connection connectDB = connection.getConnected();

        String verifylogin ="SELECT count(1) FROM Registration_details WHERE username='"+usernametextfield.getText()+"'AND password='"+passwordtextfield.getText()+"'";
        try{
            Statement statement=connectDB.createStatement();
            ResultSet queryResult =statement.executeQuery(verifylogin);
            while (queryResult.next()){
                if(queryResult.getInt(1)==1){
                    Login.setText("Welcome");
                }else {
                    Login.setText("Enter valid Username or password");
                }
            }
        } catch (Exception event){
            event.printStackTrace();
        }
    }



    //EventPage control functions
    public void switchToMainPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainpage.fxml")));
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