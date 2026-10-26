module com.example.nakupny_zoznm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nakupny_zoznm to javafx.fxml;
    exports com.example.nakupny_zoznm;
}