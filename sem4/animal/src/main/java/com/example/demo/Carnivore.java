package com.example.demo;

import javafx.scene.Parent;
import javafx.scene.paint.Color;

public class Carnivore extends Animal {

    private double dx = 2;
    private double dy = 0;

    private boolean clockwise = true;

    public Carnivore(double x, double y) {
        super(x, y, 45, 35);

        draw();
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setFill(Color.SALMON);
        gc.fillRect(5, 5, 35, 25);

        gc.setStroke(Color.DARKRED);
        gc.strokeRect(5, 5, 35, 25);

        gc.setFill(Color.BLACK);
        gc.fillOval(29, 10, 4, 4);

    }

    @Override
    public void move() {
        if (stopped) {
            return;
        }

        Parent parent = getParent();

        if (parent == null) {
            return;
        }

        double maxX = parent.getLayoutBounds().getWidth() - getWidth();
        double maxY = parent.getLayoutBounds().getHeight() - getHeight();

        setLayoutX(getLayoutX() + dx);
        setLayoutY(getLayoutY() + dy);

        // Pravy okraj
        if (getLayoutX() >= maxX) {
            setLayoutX(maxX);

            if (dx > 0) {
                dx = 0;

                if (clockwise) {
                    dy = speed;
                } else {
                    dy = -speed;
                }
            }
        }

        // Lavy okraj
        if (getLayoutX() <= 0) {
            setLayoutX(0);

            if (dx < 0) {
                dx = 0;

                if (clockwise) {
                    dy = -speed;
                } else {
                    dy = speed;
                }
            }
        }

        // Dolny okraj
        if (getLayoutY() >= maxY) {
            setLayoutY(maxY);

            if (dy > 0) {
                dy = 0;

                if (clockwise) {
                    dx = -speed;
                } else {
                    dx = speed;
                }
            }
        }

        // Horny okraj
        if (getLayoutY() <= 0) {
            setLayoutY(0);

            if (dy < 0) {
                dy = 0;

                if (clockwise) {
                    dx = speed;
                } else {
                    dx = -speed;
                }
            }
        }
    }

    public void changeDirection() {
        clockwise = !clockwise;

        dx = -dx;
        dy = -dy;
    }
}
