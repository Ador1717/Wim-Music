package com.inholland.nl.wimmusic.Controller;

import com.inholland.nl.wimmusic.Database.userDatabase;
import com.inholland.nl.wimmusic.Model.user;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardButtonController {
    @FXML
    private Label userName;
    @FXML
    private Label userRole;
    @FXML
    private Label dateAndTime;

    public userDatabase database ;
    public user user1;

    public void setUser(user user) {
        this.user1 = user;
        getUser();
    }
    public void setDatabase(userDatabase database) {
        this.database = database;

    }

    public void getUser(){
        userName.setText("Welcome" + user1.getUsername()+"!");
        userRole.setText("Your role is: "+ user1.getRole());

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        dateAndTime.setText("It is now: " + formattedDateTime);
    }

}
