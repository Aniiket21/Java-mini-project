module project.javaminiproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens project.javaminiproject to javafx.fxml;
    exports project.javaminiproject;
}