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
    public int a = 1;
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
    //refresh button
    public void initial(ActionEvent event) throws IOException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnected();
        String getename = "SELECT * FROM Event_Details where eId=" + a;
        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(getename);
            while (resultSet.next()) {
                eName.setText(resultSet.getString("EventName"));
                eDetails.setText(resultSet.getString("EventDetails"));
                eDate.setText(resultSet.getString("EventDate"));
                eTime.setText(resultSet.getString("EventTime"));
                eLink.setText(resultSet.getString("EventLink"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //next, previous actions
    public boolean check(int c) {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnected();
        String check = "SELECT count(1) FROM Event_Details WHERE eId='"+c+"'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(check);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1){
                    return true;
                } else{
                    return false;
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
        return false;
    }
    public void next(ActionEvent event) throws IOException {
        a++;
        while(!check(a)&&(a<getMaxId()))
            a++;
        if (check(a))
            initial(event);
    }
    public void prev(ActionEvent event) throws IOException {
        if (a>1){
            a--;
            while (!check(a)&&a>1)
                a--;
        }
        if (check(a))
            initial(event);
    }
    //delete
    public void delete(ActionEvent e) throws IOException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnected();
        String delete = "DELETE FROM Event_Details WHERE eId='"+a+"'";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeQuery(delete);
        } catch (Exception event) {
            event.printStackTrace();
        }
    }
    //search
    @FXML
    private TextField search;
    public void search(ActionEvent e) throws IOException{
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnected();
        String s="Select * from Event_Details WHERE EventName='"+search.getText()+"'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet r=statement.executeQuery(s);
            while (r.next()){
                if(check(r.getInt("eId"))){
                    a=r.getInt("eId");
                    initial(e);}
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
    }
    //login
    @FXML
    private Button Login;
    @FXML
    private TextField usernametextfield;
    @FXML
    private PasswordField passwordtextfield;
    @FXML
    private TextField cancel;

    public void LoginOnAction(ActionEvent event) throws IOException {
        //Login.setText("Invalid Login!");
        if (!usernametextfield.getText().isBlank() && !passwordtextfield.getText().isBlank()) {
            Login.setText("Try to login");
            if (validateLogin()) {
                SwitchToMainPage(event);
            }
        } else {
            Login.setText("Enter your Username and Password");
        }
    }



    public boolean validateLogin() throws IOException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnected();

        String verifylogin = "SELECT count(1) FROM Registration_details WHERE username='" + usernametextfield.getText() + "'AND password='" + passwordtextfield.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                return queryResult.getInt(1) == 1;
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
        return false;
    }

    //register
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
        Connection connectDB = connection.getConnected();
        String rdetails = "INSERT INTO Registration_details(Firstname,Lastname,Phonenumber,Emailid,Username,Password) VALUES ('" + fName.getText() + "','" + lName.getText() + "','" + num.getText() + "','" + email.getText() + "','" + usernametextfield.getText() + "','" + passwordtextfield.getText() + "')";
        if (!fName.getText().isEmpty() && !lName.getText().isEmpty() && !num.getText().isEmpty() && !usernametextfield.getText().isEmpty() && !passwordtextfield.getText().isEmpty()) {
            try {
                Statement statement = connectDB.createStatement();
                int a = statement.executeUpdate(rdetails);
                if (a == 1)
                    SwitchTologinpage(e);
            } catch (Exception event) {
                event.printStackTrace();
            }
        } else {
            error.setText("Error");
        }
    }
    //create events
    public int getMaxId() {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnected();
        try {
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery("SELECT max(eId) FROM Event_Details");
            while (r.next()) {
                return r.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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

    public void createEvent(ActionEvent e) {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnected();
        String createvent = "INSERT INTO Event_Details(EventName,EventCategory,EventLink,EventDetails,EventDate,EventTime,eId) VALUES ('" + Eventname.getText() + "','" + EventCatergory.getText() + "','" + EventLink.getText() + "','" + EventDetails.getText() + "','" + EventDate.getText() + "','" + EventTime.getText() + "','" + (1 + getMaxId()) + "')";
        if (!Eventname.getText().isEmpty() && !EventCatergory.getText().isEmpty() && !EventLink.getText().isEmpty() && !EventDetails.getText().isEmpty() && !EventDate.getText().isEmpty() && !EventTime.getText().isEmpty()) {
            try {
                Statement statement = connectDB.createStatement();
                int b = statement.executeUpdate(createvent);
                if (b == 1) {
                    SwitchToMainPage(e);
                }
            } catch (Exception event) {
                event.printStackTrace();
            }
        } else {
            Incomplete.setText("Error");
        }
    }

    //switch to pages
    public void SwitchToCreateEventPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createevent.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void SwitchToMainPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainpage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void SwitchTologinpage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void SwitchToRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void switchToforgotpage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("forgotuserpass.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}