package sample;

import javafx.scene.shape.Line;

public class Edge {
    private double startX;
    private double endX;
    private double startY;
    private double endY;
    private Line shape;
    private int indexOfFirstVertex;
    private int indexOfSecondVertex;

    public Edge(double startX, double startY, Line shape) {
        this.startX = startX;
        this.startY = startY;
        shape.setStartX(this.startX);
        shape.setStartY(this.startY);
        this.shape = shape;
    }

    public void setEnd(double endX, double endY) {
        this.endX = endX;
        this.endY = endY;
        shape.setEndX(this.endX);
        shape.setEndY(this.endY);
    }
    public void setIndexOfFirstVertex(int indexOfFirstVertex){
        this.indexOfFirstVertex = indexOfFirstVertex;
    }

    public void setIndexOfSecondVertex(int indexOfSecondVertex){
        this.indexOfSecondVertex = indexOfSecondVertex;
    }
    public int getIndexOfFirstVertex(){
        return indexOfFirstVertex;
    }

    public int geyIndexOfSecondVertex(){
        return indexOfSecondVertex;
    }

    public Line getShape() {
        return shape;
    }

}
