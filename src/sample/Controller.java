package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class Controller {

    enum Mode {
        VERTEX,
        EDGE,
        DELETE
    }

    Mode currentMode = Mode.VERTEX;
    Graph graph = new Graph();
    Edge currentEdge;
    boolean isBuildingEdge = false;

    @FXML
    Pane pane;
    @FXML
    Pane menuPane;
    @FXML
    RadioButton rbVertex, rbEdge;
    @FXML
    Button btnShow, btnRemove;
    @FXML
    Label textVertex, textEdge, textColors;


    public void click(MouseEvent e) {
        if (graph.getNumOfVerts() < 20 && currentMode == Mode.VERTEX) {
            Circle circle = new Circle();
            Vertex vertex = new Vertex(e.getX(), e.getY(), circle, graph.getNumOfVerts() + 1);
            graph.addVertex(vertex);
            textVertex.setText("Число вершин: " + graph.getNumOfVerts());
            circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (currentMode == Mode.DELETE) {
                        pane.getChildren().remove(vertex.getShape());
                    } else if (currentMode == Mode.EDGE && isBuildingEdge && (graph.isConnected(currentEdge.getIndexOfFirstVertex(), vertex.getIndex()) ||
                            currentEdge.getIndexOfFirstVertex() == vertex.getIndex())) {
                        graph.getVertex(currentEdge.getIndexOfFirstVertex()).setStrokeColor(Color.BLACK);
                        isBuildingEdge = false;
                    } else if (currentMode == Mode.EDGE && !isBuildingEdge) {
                        circle.setStroke(Color.RED);
                        Line line = new Line();
                        currentEdge = new Edge(circle.getCenterX(), circle.getCenterY(), line);
                        currentEdge.setIndexOfFirstVertex(vertex.getIndex());
                        isBuildingEdge = true;
                    } else if (currentMode == Mode.EDGE && isBuildingEdge && currentEdge.getIndexOfFirstVertex() != vertex.getIndex()) {
                        currentEdge.setEnd(vertex.getX(), vertex.getY());
                        currentEdge.setIndexOfSecondVertex(vertex.getIndex());
                        graph.getVertex(currentEdge.getIndexOfFirstVertex()).setStrokeColor(Color.BLACK);
                        isBuildingEdge = false;
                        graph.addEdge(currentEdge.getIndexOfFirstVertex(), currentEdge.geyIndexOfSecondVertex(), currentEdge);
                        textEdge.setText("Число ребер: " + graph.getNumOfEdges());
                        pane.getChildren().add(currentEdge.getShape());
                        currentEdge.getShape().toBack();
                    }
                }
            });
            vertex.getShape().toFront();
            pane.getChildren().add(vertex.getShape());
            menuPane.toFront();
            circle.setAccessibleText("341");
        } else {
            return;
        }

    }

    public void removeGraph(ActionEvent e) {
        graph.clearGraph(pane);
        textVertex.setText("Число вершин: 0");
        textEdge.setText("Число ребер: 0");
        textColors.setText("Кол-во цветов: 0");
    }


    public void showMenu(ActionEvent e) {
        menuPane.styleProperty().set("-fx-background-color: #e6e6e6");
        menuPane.setVisible(true);
        menuPane.toFront();
        btnShow.setVisible(false);
    }

    public void hideMenu(ActionEvent e) {
        menuPane.setVisible(false);
        menuPane.toFront();
        btnShow.setVisible(true);
    }

    public void getMode(ActionEvent e) {
        if (rbEdge.isSelected()) {
            currentMode = Mode.EDGE;
        } else if (rbVertex.isSelected()) {
            currentMode = Mode.VERTEX;
        }
    }

    public void colorVertexes(ActionEvent e) {
        graph.colorVertexes();
        textColors.setText("Кол-во цветов: " + graph.getNumOfColors());
    }

}
