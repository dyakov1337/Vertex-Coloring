package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(); //(Main.class.getResource("Main.fxml"));
        fxmlLoader.setLocation(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Раскраска вершин графа");
        primaryStage.getIcons().add(new Image("./resources/icon2.png"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
