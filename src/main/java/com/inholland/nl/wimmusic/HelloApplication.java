package com.inholland.nl.wimmusic;

import com.inholland.nl.wimmusic.controller.LoginController;
import com.inholland.nl.wimmusic.database.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Data data = new Data();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        LoginController loginController = fxmlLoader.getController();
        loginController.setDatabase(data);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}