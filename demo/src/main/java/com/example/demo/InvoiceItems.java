package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InvoiceItems {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty id_invoice = new SimpleIntegerProperty();
    private SimpleIntegerProperty amount = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleStringProperty category = new SimpleStringProperty();


    public InvoiceItems(int id, int invoice_id,int amount, String name, String category, double price) {
        this.id.set(id);
        this.id_invoice.set(invoice_id);
        this.amount.set(amount);
        this.name.set(name);
        this.category.set(category);
        this.price.set(price);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId_invoice() {
        return id_invoice.get();
    }

    public SimpleIntegerProperty id_invoiceProperty() {
        return id_invoice;
    }

    public void setId_invoice(int id_invoice) {
        this.id_invoice.set(id_invoice);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }


}
