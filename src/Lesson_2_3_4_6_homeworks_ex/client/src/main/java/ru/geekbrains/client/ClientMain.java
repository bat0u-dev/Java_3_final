package ru.geekbrains.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class ClientMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("JavaChat Client Window");
        primaryStage.setScene(new Scene(root, 410, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
