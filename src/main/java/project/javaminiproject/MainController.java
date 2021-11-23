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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.sql.Connection;
import java.sql.ResultSet;
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
    private TextField passwordtextfield;



    public void LoginOnAction(ActionEvent event){
        if (usernametextfield.getText().isBlank()==false && passwordtextfield.getText().isBlank()==false){
            Login.setText("Try to login");
            validateLogin();
        }
        else{Login.setText("Enter your Username and Password");}
    }
     public void validateLogin(){
        Databaseconnection  connection =new Databaseconnection();
         Connection connectDB = connectNow.getConnection;

        String verifylogin ="SELECT count(1) FROM Registration_details WHERE username='"+usernametextfield.getText()+"'AND password='"+passwordtextfield.getText()+"'";
        try{
            Statement statement=connectDB.createStatement();
            ResultSet queryResult =statement.executeQuery(verifylogin);
            while (queryResult.next()){
               if(queryResult.getInt(1)==1){
                   Login.setText("Welcome");
               }else {
                   Login.setText("Incorrect credentials please try again");
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


