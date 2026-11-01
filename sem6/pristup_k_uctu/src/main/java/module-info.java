module com.example.pristup_k_uctu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pristup_k_uctu to javafx.fxml;
    exports com.example.pristup_k_uctu;
}