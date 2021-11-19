module project.javaminiproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.javaminiproject to javafx.fxml;
    exports project.javaminiproject;
}