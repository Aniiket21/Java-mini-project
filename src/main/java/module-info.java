module project.javaminiproject {
    requires javafx.controls;
    requires javafx.fxml;
requires javafx.graphics;
opens sample;
    opens project.javaminiproject to javafx.fxml;
    exports project.javaminiproject;
}