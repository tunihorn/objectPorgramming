package com.example.nakupny_zoznm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private ListView<Item> list;

    @FXML
    private TextField name;

    @FXML
    private TextField count;

    @FXML
    private Label totalLabel;

    private ObservableList<Item> items =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        list.setItems(items);
        updateTotal();
    }

    @FXML
    private void add() {
        String itemName = name.getText();

        if (itemName.trim().isEmpty()) {
            return;
        }

        int itemCount;

        try {
            itemCount = Integer.parseInt(count.getText());
        } catch (NumberFormatException e) {
            return;
        }

        if (itemCount <= 0) {
            return;
        }

        Item newItem = new Item(itemName, itemCount);

        items.add(newItem);

        name.clear();
        count.clear();

        updateTotal();
    }

    @FXML
    private void remove() {
        int index = list.getSelectionModel().getSelectedIndex();

        if (index != -1) {
            items.remove(index);
            updateTotal();
        }
    }

    @FXML
    private void update() {
        int index = list.getSelectionModel().getSelectedIndex();

        if (index == -1) {
            return;
        }

        String itemName = name.getText();

        if (itemName.trim().isEmpty()) {
            return;
        }

        int itemCount;

        try {
            itemCount = Integer.parseInt(count.getText());
        } catch (NumberFormatException e) {
            return;
        }

        if (itemCount <= 0) {
            return;
        }

        Item item = items.get(index);

        item.setName(itemName);
        item.setCount(itemCount);

        list.refresh();

        name.clear();
        count.clear();
    }

    private void updateTotal() {
        totalLabel.setText("Počet položiek: " + items.size());
    }
}
