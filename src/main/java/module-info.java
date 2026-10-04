module com.example.objectporgramming {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.objectporgramming to javafx.fxml;
    exports com.example.objectporgramming;
}