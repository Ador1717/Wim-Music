package com.inholland.nl.wimmusic.Controller;

import com.inholland.nl.wimmusic.Model.user;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.inholland.nl.wimmusic.Database.userDatabase;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public TextField usernameField;
    public javafx.scene.control.PasswordField PasswordField;
    public Button loginButton;
    public Label errorMessage;
    private userDatabase userDatabase = new userDatabase();



    @FXML
    protected void onHelloButtonClick() {
        String username = usernameField.getText();
        String password = PasswordField.getText();

        user user1 = userDatabase.validateUser(username, password);

        loadScene(user1);
    }


    public void loadScene(user user1) {
        try {
            if (user1!=null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/inholland/nl/wimmusic/Main.fxml"));
                Parent root = loader.load();

                MainController mainViewController = loader.getController();
                mainViewController.setUser(user1);
                mainViewController.setDatabase(userDatabase);

                Stage view = new Stage();
                view.setScene(new Scene(root));
                view.setTitle("Wim's Music Store Dashboard");
                view.setResizable(false);
                view.show();
                ((Stage) loginButton.getScene().getWindow()).close();
            }
            else{
                errorMessage.setText("Invalid username or password");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}