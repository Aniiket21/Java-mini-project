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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;


import java.io.IOException;
import java.util.Objects;
import java.sql.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public int a=1;

    //login control functions
    public void switchToMainApp(ActionEvent event) throws IOException {


    }
    public void SwitchToRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }





    //forgot page control functions
    public void switchToforgotpage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("forgotuserpass.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    //main page control functions
    @FXML
    private Text eName;
    @FXML
    private Label eDetails;
    @FXML
    private Label eTime;
    @FXML
    private Label eDate;
    @FXML
    private Label eLink;
    public void initial(ActionEvent event)throws IOException{
        DatabaseConnection  connection =new DatabaseConnection();
        Connection connectDB = connection.getConnected();
        String getename="SELECT * FROM Event_Details where eId="+a;
        try{
            Statement statement=connectDB.createStatement();
            ResultSet resultSet =statement.executeQuery(getename);
            while (resultSet.next()){
                eName.setText(resultSet.getString("EventName"));
                eDetails.setText(resultSet.getString("EventDetails"));
                eDate.setText(resultSet.getString("EventDate"));
                eTime.setText(resultSet.getString("EventTime"));
                eLink.setText(resultSet.getString("EventLink"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean nextAvailable(){
        DatabaseConnection  connection =new DatabaseConnection();
        Connection connectDB = connection.getConnected();
        try {
            Statement s=connectDB.createStatement();
            ResultSet r=s.executeQuery("SELECT max(eId) FROM Event_Details");
            while (r.next()){
                return a < r.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void next(ActionEvent event) throws IOException {
        if (nextAvailable()){
            a++;
            initial(event);
        }
    }
    public void prev(ActionEvent event) throws IOException {
        if (a>1){
            a--;
            initial(event);
        }
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
    @FXML
    private Label error;
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
        else {
            error.setText("Error");
        }
    }


    //EventPage control functions
    public void SwitchToMainPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainpage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    //CreateEvent control functions
    public void SwitchToCreateEventPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createevent.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private TextField Eventname;
    @FXML
    private TextField EventCatergory;
    @FXML
    private TextField EventLink;
    @FXML
    private TextField EventDetails;
    @FXML
    private TextField EventDate;
    @FXML
    private TextField EventTime;
    @FXML
    private Label Incomplete;

    public void createEvent(ActionEvent e){
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB= connection.getConnected();
        String createvent="INSERT INTO Event_Details(EventName,EventCategory,EventLink,EventDetails,EventDate,EventTime) VALUES ('"+Eventname.getText()+"','"+EventCatergory.getText()+"','"+EventLink.getText()+"','"+EventDetails.getText()+"','"+EventDate.getText()+"','"+EventTime.getText()+"')";
        if (!Eventname.getText().isEmpty()&&!EventCatergory.getText().isEmpty()&&!EventLink.getText().isEmpty()&&!EventDetails.getText().isEmpty()&&!EventDate.getText().isEmpty()&&!EventTime.getText().isEmpty())
        {
            try{
                Statement statement=connectDB.createStatement();
                int a = statement.executeUpdate(createvent);
                if (a==1)
                    SwitchToMainPage(e);
            } catch (Exception event) {
                event.printStackTrace();
            }
        }
        else {
            Incomplete.setText("Error");
        }
    }


//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}