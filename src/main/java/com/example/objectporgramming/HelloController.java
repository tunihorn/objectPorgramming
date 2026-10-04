package com.example.objectporgramming;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField inputField;

    @FXML
    private ComboBox<String> unitBox;

    @FXML
    private Label resultLabel;

    @FXML
    public void initialize() {
        unitBox.getItems().addAll("mm", "cm", "m", "km");
        unitBox.setValue("m");
    }

    @FXML
    protected void convertDistance() {
        try {
            double value = Double.parseDouble(
                    inputField.getText().replace(",", ".")
            );

            double meters;

            switch (unitBox.getValue()) {
                case "mm":
                    meters = value / 1000;
                    break;

                case "cm":
                    meters = value / 100;
                    break;

                case "km":
                    meters = value * 1000;
                    break;

                default:
                    meters = value;
                    break;
            }

            double mm = meters * 1000;
            double cm = meters * 100;
            double m = meters;
            double km = meters / 1000;

            resultLabel.setText(
                    "Milimetre: " + mm + " mm\n" +
                            "Centimetre: " + cm + " cm\n" +
                            "Metre: " + m + " m\n" +
                            "Kilometre: " + km + " km"
            );

        } catch (NumberFormatException e) {
            resultLabel.setText("Zadajte platné číslo!");
        }
    }
}