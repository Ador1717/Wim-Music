package com.inholland.nl.wimmusic.Controller;

import com.inholland.nl.wimmusic.Database.userDatabase;
import com.inholland.nl.wimmusic.Model.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {
    @FXML
    public Button createOrderButton;
    @FXML
    public Button productInventoryButton;
    @FXML
    public Button orderHistoryButton;

    public VBox DisplayNew;
    @FXML
    private Button dashboardButton;

    private userDatabase database;
    private user users;

    public void navigateToDashboard(ActionEvent actionEvent) {
        loaderView("/com/inholland/nl/wimmusic/DashboardButton.fxml");
    }

    public void navigateToCreateOrder(ActionEvent actionEvent) {
        loaderView("/com/inholland/nl/wimmusic/productInventoryButton.fxml");
    }

    public void navigateToProductInventory(ActionEvent actionEvent) {
        loaderView("/com/inholland/nl/wimmusic/productInventoryButton.fxml");

    }

    public void navigateToOrderHistory(ActionEvent actionEvent) {
        loaderView("/com/inholland/nl/wimmusic/orderHistoryButton.fxml");

    }
    public void setUser(user user){
        this.users = user;
        allowBasedOnRole();

    }
    public void setDatabase(userDatabase database){
        this.database= database;
    }

    private  void allowBasedOnRole(){

        if ("sales".equals(users.getRole())) {
            dashboardButton.setDisable(false);
            createOrderButton.setDisable(false);
            productInventoryButton.setDisable(true);
            orderHistoryButton.setDisable(false);
        } else if ("manager".equals(users.getRole())) {
            dashboardButton.setDisable(false);
            createOrderButton.setDisable(true);
            productInventoryButton.setDisable(false);
            orderHistoryButton.setDisable(false);
        }
    }

    public void loaderView(String Path)  {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Path));
            VBox navigationView = fxmlLoader.load();
            Object controller = fxmlLoader.getController();
            if (controller instanceof DashboardButtonController) {
                DashboardButtonController dashboardButtonController = (DashboardButtonController) controller;
                dashboardButtonController.setUser(users);
                dashboardButtonController.setDatabase(database);
            } else if (controller instanceof createOrderButtonController) {
                createOrderButtonController createOrderBtn = (createOrderButtonController) controller;

            } else if (controller instanceof ProductInventoryButtonController) {
                ProductInventoryButtonController productInventoryButtonController = (ProductInventoryButtonController) controller;
            } else {
                OrderHistoryButtonController orderHistoryButtonController = (OrderHistoryButtonController) controller;
            }
            DisplayNew.getChildren().setAll(navigationView);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
