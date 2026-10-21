package com.example.demo;

import javafx.scene.paint.Color;

public class Herbivore extends Animal {

    private final double startX;
    private final double startY;

    private final double squareSize = 120;

    // 0 = doprava, 1 = dole, 2 = dolava, 3 = hore
    private int direction = 0;

    public Herbivore(double x, double y) {
        super(x, y, 45, 35);

        startX = x;
        startY = y;

        draw();
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setFill(Color.LIGHTGREEN);
        gc.fillOval(5, 5, 35, 25);

        gc.setStroke(Color.DARKGREEN);
        gc.strokeOval(5, 5, 35, 25);

        gc.setFill(Color.BLACK);
        gc.fillOval(29, 11, 4, 4);
    }

    @Override
    public void move() {
        if (stopped) {
            return;
        }

        switch (direction) {
            case 0:
                setLayoutX(getLayoutX() + speed);

                if (getLayoutX() >= startX + squareSize) {
                    setLayoutX(startX + squareSize);
                    direction = 1;
                }
                break;

            case 1:
                setLayoutY(getLayoutY() + speed);

                if (getLayoutY() >= startY + squareSize) {
                    setLayoutY(startY + squareSize);
                    direction = 2;
                }
                break;

            case 2:
                setLayoutX(getLayoutX() - speed);

                if (getLayoutX() <= startX) {
                    setLayoutX(startX);
                    direction = 3;
                }
                break;

            case 3:
                setLayoutY(getLayoutY() - speed);

                if (getLayoutY() <= startY) {
                    setLayoutY(startY);
                    direction = 0;
                }
                break;
        }
    }
}
