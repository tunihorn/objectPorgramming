module sk.robotapp {
    requires javafx.controls;
    requires javafx.fxml;
    opens sk.robotapp to javafx.fxml;
    exports sk.robotapp;
}
