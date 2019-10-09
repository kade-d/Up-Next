package bsu.edu.cs222;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends Application {

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));

        AnchorPane page = new AnchorPane();

        try{
            page = loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Scene scene = new Scene(page, 800, 600); //Controls size of window

        stage.setTitle("Project 1: Wiki");

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
