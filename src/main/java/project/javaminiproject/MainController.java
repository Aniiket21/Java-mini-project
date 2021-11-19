package project.javaminiproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;


    //login control functions
    public void switchToMainApp(ActionEvent event) throws IOException {

    }
    public void switchToRegister(ActionEvent event) throws IOException {

    }








    //register control functions






    //main page control functions
    public void switchToEventPage(ActionEvent event) throws IOException {

    }
    @FXML
    public void SwitchTologinpage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage=new Stage();
        stage.setTitle("login page");
        stage.setScene(new Scene(root));
        stage.show();
    }





    //EventPage control functions
    public void switchToMainPage(ActionEvent event) throws IOException {

    }




//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}