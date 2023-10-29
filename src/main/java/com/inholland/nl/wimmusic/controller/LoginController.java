package com.inholland.nl.wimmusic.controller;

import com.inholland.nl.wimmusic.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.inholland.nl.wimmusic.database.Data;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    private static final int SET_DIGIT_BIT = 0b100;
    private static final int SET_LETTER_BIT = 0b010;
    private static final int SET_SPECIAL_BIT = 0b001;


    protected boolean isPasswordValid(String password) {
        byte values = 0b000;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                values = (byte) (values | SET_DIGIT_BIT);
            } else if (Character.isLetter(c)) {
                values = (byte) (values | SET_LETTER_BIT);
            } else {
                values = (byte) (values | SET_SPECIAL_BIT);
            }
        }
        return values == 7;
    }
    @FXML
    private Label errorMessage2;
    @FXML
    private TextField usernameField;
    @FXML
    private javafx.scene.control.PasswordField PasswordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorMessage;
    private Data Data;
    public  void setDatabase(Data database) {
       this.Data = database;
    }


    @FXML
    protected void onHelloButtonClick() {
        String username = usernameField.getText();
        String password = PasswordField.getText();

        if(!isPasswordValid(password)){
            errorMessage.setText("Password must contain at least one letter, one digit and one special character");
            return;
        }

        User user1 = Data.validateUser(username, password);
        loadScene(user1);
    }

    public void loadScene(User user1) {
        try {
            if (user1!=null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/inholland/nl/wimmusic/Main.fxml"));
                Parent root = loader.load();

                MainController mainViewController = loader.getController();
                mainViewController.setUser(user1);
                mainViewController.setDatabase(Data);


                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Wim's Music");
                stage.setResizable(false);
                stage.show();
                ((Stage) loginButton.getScene().getWindow()).close();
            }
            else{
                errorMessage2.setText("Invalid username or password");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}