package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.HashSet;


public class Vertex {
    private double x;
    private double y;
    private Circle shape;
    private int index;
    public Color fillColor = Color.WHITE;
    public Color strokeColor = Color.BLACK;
    private boolean isColored = false;
    public HashSet<Color> forbiddenColors = new HashSet<Color>();

    public Vertex(double x, double y, Circle shape, int index) {
        this.x = x;
        this.y = y;
        this.shape = shape;
        this.index = index;
        this.shape.setCenterX(this.x);
        this.shape.setCenterY(this.y);
        this.shape.setRadius(20);
        this.shape.setStroke(javafx.scene.paint.Color.BLACK);
        this.shape.setFill(javafx.scene.paint.Color.WHITE);
    }

    public Circle getShape() {
        return shape;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        shape.setFill(fillColor);
    }

    public void setStrokeColor(Color strokeColor){
        this.strokeColor = strokeColor;
        shape.setStroke(strokeColor);
    }

    public void setIsColored(boolean b) {
        isColored = b;
    }

    public boolean isColored() {
        return isColored;
    }
}
