package com.example.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends GeometricShape {

    private double speed = 2;

    public MyRectangle(double x, double y) {
        shape = new Rectangle(70, 40, Color.ORANGE);

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
        shape.setLayoutX(shape.getLayoutX() + speed);

        double parentWidth = 600;

        if (shape.getParent() != null) {
            parentWidth = shape.getParent().getLayoutBounds().getWidth();
        }

        double width = ((Rectangle) shape).getWidth();

        if (shape.getLayoutX() <= 0 ||
                shape.getLayoutX() + width >= parentWidth) {
            speed = -speed;
        }
    }
}
