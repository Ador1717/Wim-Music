package com.inholland.nl.wimmusic.controller;

import com.inholland.nl.wimmusic.database.Data;
import com.inholland.nl.wimmusic.model.Order;
import com.inholland.nl.wimmusic.model.Product;
import com.inholland.nl.wimmusic.model.ProductSelectedListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDateTime;

public class OrderHistoryButtonController {

    @FXML
    private TableColumn price;
    @FXML
    private TableColumn <Order, String>usernameColumn;
    @FXML
    private TableColumn <Order, Double>totalPriceColumn;
    @FXML
    private TableView <Order> orderHistoryTable;

    @FXML
    private TableView<Product> productHistoryTable;

    Data database;

    public void setDatabase(Data database) {
        this.database = database;
        loadOrderHistory();
        setupOrderTableClickListener();
    }

    private void loadOrderHistory() {
        orderHistoryTable.setItems(FXCollections.observableArrayList(database.getOrders()));
        orderHistoryTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        usernameColumn.setCellValueFactory(
                s -> new SimpleStringProperty(s.getValue().getUserName())
        );
        totalPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getProducts().stream()
                .mapToDouble(Product::getTotalPrice)
                .sum()).asObject());

    }

    private void setupOrderTableClickListener() {
        orderHistoryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadProductDetails(newValue);
            }
        });
    }

    private void loadProductDetails(Order order) {
        productHistoryTable.setItems(FXCollections.observableArrayList(order.getProducts()));


    }
}
