package com.example.sem5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField text_field;

    @FXML
    private ListView<String> list_view;


    private ObservableList<String> zoznam = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        list_view.setItems(zoznam);
    }

    @FXML
    private void pridaj() {
        String text = text_field.getText();

        if ( text != null ){
            zoznam.add(text);
            text_field.clear();
        }

    }

    @FXML
    private void zmaz() {
        int index = list_view.getSelectionModel().getSelectedIndex();

        if (index >= 0){
            zoznam.remove(index);
        }
    }

    @FXML
    private void edit() {
        int index = list_view.getSelectionModel().getSelectedIndex();
        String text = text_field.getText();

        if (index >= 0 && text != null) {
            zoznam.set(index, text);
            text_field.clear();
        }
    }


}
