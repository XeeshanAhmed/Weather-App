module com.example.SDA_Project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.SDA_Project to javafx.fxml;
    exports com.example.SDA_Project;
}