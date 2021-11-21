module project.javaminiproject {
    requires javafx.controls;
    requires javafx.fxml;
requires javafx.graphics;
requires java.sql;
opens sample;
    opens project.javaminiproject to javafx.fxml;
    exports project.javaminiproject;
}