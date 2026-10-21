package com.example.demo;

import javafx.scene.Node;

public abstract class GeometricShape {

    protected Node shape;

    public Node getShape() {
        return shape;
    }

    public abstract void draw();

    public abstract void move();

    public void moveDown() {
        shape.setLayoutY(shape.getLayoutY() + 30);
    }

    protected void registerShape() {
        shape.setUserData(this);
    }
}
