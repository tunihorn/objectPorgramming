module com.example.sem5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sem5 to javafx.fxml;
    exports com.example.sem5;
}