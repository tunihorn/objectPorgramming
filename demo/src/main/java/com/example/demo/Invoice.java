package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Invoice {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty status = new SimpleStringProperty();

    public Invoice(int id, String date, String status) {
        this.id.set(id);
        this.date.set(date);
        this.status.set(status);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
