package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Stack;

public class Graph {
    private Vertex[] verts = new Vertex[20];
    private Edge[] edges = new Edge[400];

    private final int MAX_NUM = 20;
    private int numOfVerts = 0;
    private int numOfEdges = 0;
    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN,
            Color.INDIGO, Color.TOMATO, Color.GREY, Color.BROWN, Color.BLACK, Color.DARKVIOLET, Color.WHEAT,
            Color.PURPLE, Color.LIME, Color.TAN, Color.DEEPSKYBLUE, Color.CHOCOLATE, Color.CORAL};
    private int nCol = 0;
    private int adjMatrix[][] = new int[20][20];
    private HashSet<Color> usedColors = new HashSet<Color>();

    public void addVertex(Vertex vertex) {
        verts[numOfVerts] = vertex;
        numOfVerts++;
        for (int j = 0; j < MAX_NUM; j++) {
            adjMatrix[vertex.getIndex() - 1][j] = 0;
        }
    }

    public int getUncoloredVertex(int i) {
        for (int j = 0; j < MAX_NUM; j++) {
            if (adjMatrix[i][j] == 1 && verts[j].isColored() == false) return j;
        }
        return -1;
    }

    public Vertex getVertex(int index) {
        return verts[index - 1];
    }

    public int getNumOfVerts() {
        return numOfVerts;
    }

    public void addEdge(int indexOfFirst, int indexOfSecond, Edge edge) {
        adjMatrix[indexOfFirst - 1][indexOfSecond - 1] = 1;
        adjMatrix[indexOfSecond - 1][indexOfFirst - 1] = 1;
        edges[numOfEdges] = edge;
        numOfEdges++;
    }

    public boolean isConnected(int indexOfFirst, int indexOfSecond) {
        if (adjMatrix[indexOfFirst - 1][indexOfSecond - 1] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public void clearGraph(Pane p) {
        for (int i = 0; i < numOfVerts; i++) {
            verts[i].forbiddenColors.clear();
            p.getChildren().remove(verts[i].getShape());
            verts[i] = null;
        }

        for (int i = 0; i < numOfEdges; i++) {
            p.getChildren().remove(edges[i].getShape());
            edges[i] = null;
        }

        for (int i = 0; i < MAX_NUM; i++) {
            for (int j = 0; j < MAX_NUM; j++) {
                adjMatrix[i][j] = 0;
            }
        }
        numOfVerts = 0;
        numOfEdges = 0;
    }


    public Color findColor(Vertex v) {
        for (int i = 0; i < 20; i++) {
            if (!v.forbiddenColors.contains(colors[i])) return colors[i];
        }
        return Color.RED;
    }

    public void setForbbidenColor(Color color, Vertex v) {
        for (int i = 0; i <MAX_NUM; i++) {
            if (adjMatrix[v.getIndex() - 1][i] == 1) verts[i].forbiddenColors.add(color);
        }
    }

    public void colorVertexes() {
        usedColors.clear();
        Stack<Vertex> vertStack = new Stack<Vertex>();
        vertStack.push(verts[0]);
        verts[0].setFillColor(Color.RED);
        setForbbidenColor(Color.RED, verts[0]);
        verts[0].forbiddenColors.add(Color.RED);
        usedColors.add(Color.RED);
        verts[0].setIsColored(true);

        while (!vertStack.empty()) {
            int v = getUncoloredVertex(vertStack.peek().getIndex() - 1);
            if (v == -1) {
                vertStack.pop();
            } else if (v != -1) {
                Color c = findColor(verts[v]);
                verts[v].forbiddenColors.add(c);
                setForbbidenColor(c, verts[v]);
                usedColors.add(c);
                verts[v].setFillColor(c);
                verts[v].setIsColored(true);
                vertStack.push(verts[v]);
            }
        }

        for (int i = 0; i < numOfVerts; i++) {
            if (!verts[i].isColored()) verts[i].setFillColor(Color.RED);
            verts[i].setIsColored(false);
            verts[i].forbiddenColors.clear();
        }
    }

    public int getNumOfColors() {
        return usedColors.size();
    }
}
