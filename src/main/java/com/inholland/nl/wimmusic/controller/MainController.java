package com.inholland.nl.wimmusic.controller;

import com.inholland.nl.wimmusic.database.Data;
import com.inholland.nl.wimmusic.model.User;
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

    private Data database;
    private User users;

    public void navigateToDashboard(ActionEvent actionEvent) {
        loaderView("/com/inholland/nl/wimmusic/DashboardButton.fxml");
    }

    public void navigateToCreateOrder(ActionEvent actionEvent) {
        loaderView("/com/inholland/nl/wimmusic/createOrderButton.fxml");
    }

    public void navigateToProductInventory(ActionEvent actionEvent) {
        loaderView("/com/inholland/nl/wimmusic/productInventoryButton.fxml");

    }

    public void navigateToOrderHistory(ActionEvent actionEvent) {
        loaderView("/com/inholland/nl/wimmusic/orderHistoryButton.fxml");

    }
    public void setUser(User user){
        this.users = user;
        allowBasedOnRole();

    }
    public void setDatabase(Data database){
        this.database= database;
    }

    private  void allowBasedOnRole(){

        if (User.Role.Sales.equals(users.getRole())) {
            dashboardButton.setDisable(false);
            createOrderButton.setDisable(false);
            productInventoryButton.setDisable(true);
            orderHistoryButton.setDisable(false);
        } else if (User.Role.Manager.equals(users.getRole())) {
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
            } else if (controller instanceof CreateOrderButtonController) {
                CreateOrderButtonController createOrderBtn = (CreateOrderButtonController) controller;
                createOrderBtn.setDatabase(database);
            } else if (controller instanceof ProductInventoryButtonController) {
                ProductInventoryButtonController productInventoryButtonController = (ProductInventoryButtonController) controller;
                productInventoryButtonController.setDatabase(database);
            } else {
                OrderHistoryButtonController orderHistoryButtonController = (OrderHistoryButtonController) controller;
                orderHistoryButtonController.setDatabase(database);
            }
            DisplayNew.getChildren().setAll(navigationView);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
