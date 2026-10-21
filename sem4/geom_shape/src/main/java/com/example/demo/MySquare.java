package com.example.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MySquare extends GeometricShape {

    private double speed = 2;

    public MySquare(double x, double y) {
        shape = new Rectangle(45, 45, Color.GREEN);

        shape.setLayoutX(x);
        shape.setLayoutY(y);

        draw();
        registerShape();
    }

    @Override
    public void draw() {
        ((Rectangle) shape).setStroke(Color.BLACK);
    }

    @Override
    public void move() {
        shape.setLayoutY(shape.getLayoutY() + speed);

        double parentHeight = 400;

        if (shape.getParent() != null) {
            parentHeight = shape.getParent().getLayoutBounds().getHeight();
        }

        double height = ((Rectangle) shape).getHeight();

        if (shape.getLayoutY() <= 0 ||
                shape.getLayoutY() + height >= parentHeight) {
            speed = -speed;
        }
    }
}
