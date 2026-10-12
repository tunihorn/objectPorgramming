package sk.robotapp;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Robot extends Rectangle {
    public enum Direction { UP, DOWN, LEFT, RIGHT }
    private Direction direction = Direction.RIGHT;
    private boolean automatic;
    private final double speed = 4.0;
    private final double baseWidth = 60, baseHeight = 40;
    private final Pane area;
    private long lastFrame;
    private final AnimationTimer timer;

    public Robot(Pane area, double x, double y) {
        super(60, 40, Color.DODGERBLUE);
        this.area = area;
        setArcWidth(12); setArcHeight(12); setStroke(Color.DARKBLUE); setStrokeWidth(2);
        setLayoutX(x); setLayoutY(y); updateAppearance();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (automatic && (lastFrame == 0 || now - lastFrame >= 12_000_000)) { moveAutomatic(); lastFrame = now; }
            }
        };
        timer.start();
    }

    public void move(Direction newDirection) { direction = newDirection; updateAppearance(); moveOneStep(false); }
    public void toggleAutomatic() { automatic = !automatic; updateAppearance(); }
    public boolean isAutomatic() { return automatic; }
    public Direction getDirection() { return direction; }

    private void moveAutomatic() { moveOneStep(true); }

    private void moveOneStep(boolean bounce) {
        double nx = getLayoutX(), ny = getLayoutY();
        switch (direction) {
            case UP -> ny -= speed; case DOWN -> ny += speed; case LEFT -> nx -= speed; case RIGHT -> nx += speed;
        }
        double maxX = Math.max(0, area.getWidth() - getWidth());
        double maxY = Math.max(0, area.getHeight() - getHeight());
        boolean hit = nx < 0 || ny < 0 || nx > maxX || ny > maxY;
        if (hit && bounce) {
            direction = opposite(direction); updateAppearance();
            nx = getLayoutX(); ny = getLayoutY();
            switch (direction) {
                case UP -> ny -= speed; case DOWN -> ny += speed; case LEFT -> nx -= speed; case RIGHT -> nx += speed;
            }
        }
        setLayoutX(clamp(nx, 0, Math.max(0, area.getWidth() - getWidth())));
        setLayoutY(clamp(ny, 0, Math.max(0, area.getHeight() - getHeight())));
    }

    private Direction opposite(Direction d) {
        return switch (d) { case UP -> Direction.DOWN; case DOWN -> Direction.UP; case LEFT -> Direction.RIGHT; case RIGHT -> Direction.LEFT; };
    }

    private void updateAppearance() {
        if (direction == Direction.LEFT || direction == Direction.RIGHT) { setWidth(baseWidth + 15); setHeight(baseHeight); }
        else { setWidth(baseWidth); setHeight(baseHeight + 15); }
        setFill(automatic ? Color.ORANGE : Color.DODGERBLUE);
        setStroke(switch (direction) { case UP -> Color.DARKGREEN; case DOWN -> Color.PURPLE; case LEFT -> Color.CRIMSON; case RIGHT -> Color.DARKBLUE; });
    }

    private double clamp(double value, double min, double max) { return Math.max(min, Math.min(max, value)); }
}
