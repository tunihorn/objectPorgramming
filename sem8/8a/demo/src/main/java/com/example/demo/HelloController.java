package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class HelloController {
    @FXML
    private Label label;

    @FXML private TableView<Invoice> table1;
    @FXML private TableColumn<Invoice, Number> table1_id;
    @FXML private TableColumn<Invoice, String> table1_date;
    @FXML private TableColumn<Invoice, String> table1_status;

    @FXML private TableView<InvoiceItems> table2;
    @FXML private TableColumn<InvoiceItems, String> table2_name;
    @FXML private TableColumn<InvoiceItems, String> table2_category;
    @FXML private TableColumn<InvoiceItems, Number> table2_price;
    @FXML private TableColumn<InvoiceItems, Number> table2_quantity;

    private ObservableList<InvoiceItems> invoiceItemsData = FXCollections.observableArrayList();
    private ObservableList<Invoice> invoiceData = FXCollections.observableArrayList();

    private void getInvoices() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/javafx", "root", "");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM invoice");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                invoiceData.add(new Invoice(
                        resultSet.getInt("id"),
                        resultSet.getString("date"),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void getInvoiceItems(int id) {
        try {
            invoiceItemsData.clear();
            Double totalPrice = 0.0;
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javafx", "root", "");
            String sql = "SELECT invoice_items.id as id, id_invoice, amount, product.name as name, " +
                    "price, category.name as category FROM invoice_items " +
                    "JOIN invoice ON invoice_items.id_invoice = invoice.id " +
                    "JOIN product ON invoice_items.id_product = product.id " +
                    "JOIN category ON product.category_id = category.id " +
                    "WHERE id_invoice = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int amount = resultSet.getInt("amount");
                Double price = resultSet.getDouble("price");
                totalPrice = totalPrice + price * amount;

                InvoiceItems ii = new InvoiceItems(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_invoice"),
                        amount,
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        price
                );

                invoiceItemsData.add(ii);
            }
            label.setText(String.format("%.2f EUR", totalPrice));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        table1.setItems(invoiceData);
        table1_id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        table1_date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        table1_status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        table2.setItems(invoiceItemsData);
        table2_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        table2_category.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        table2_price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        table2_quantity.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

        getInvoices();

        table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getInvoiceItems(newSelection.getId());
            }
        });
    }
}