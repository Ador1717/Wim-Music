package com.inholland.nl.wimmusic.controller;


import com.inholland.nl.wimmusic.database.Data;
import com.inholland.nl.wimmusic.model.Order;
import com.inholland.nl.wimmusic.model.Product;
import com.inholland.nl.wimmusic.model.ProductSelectedListener;
import com.inholland.nl.wimmusic.model.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateOrderButtonController {

    public Button addProductBtn;
    public Button deleteProductBtn;
    public Button createProductBtn;
    @FXML
    private TableColumn  <Product, Double> totalPriceColumn;
    @FXML
    private TableColumn <Product, Integer>quantityColumn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TableView productTable;

    Product product;

    Data database;
    ProductSelectedListener listener;
    public List<Product> products = new ArrayList<>();
    public List<Order> orders = new ArrayList<>();

    public void initialize() {
        productTable.setItems(FXCollections.observableList(products));

        quantityColumn.setCellValueFactory(
                s -> new SimpleIntegerProperty(s.getValue().getQuantity()).asObject());
        totalPriceColumn.setCellValueFactory(
                s -> new SimpleDoubleProperty(s.getValue().getTotalPrice()).asObject());
    }
    public void setDatabase(Data database) {
        this.database = database;
    }
    public void setListener(ProductSelectedListener listener) {
        this.listener = listener;
    }
    public void deleteProductBtn(ActionEvent actionEvent) {
        Product product = (Product) productTable.getSelectionModel().getSelectedItem();
        products.remove(product);
        updateTableView();
    }

    public void createProductBtn(ActionEvent actionEvent) {
        User user = new User(
                firstName.getText(),
                lastName.getText(),
                email.getText(),
                Integer.parseInt(phone.getText()
                ));
        Order order = new Order(LocalDateTime.now(),user, products);
        database.addOrder(order);
       // product.setStock(Integer.parseInt(quantityColumn.getText()));
    }
    public void addProductBtn(ActionEvent actionEvent) {
       FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/inholland/nl/wimmusic/AddProduct.fxml"));
        try {
            Parent root = loader.load();
            AddProductController controller = loader.getController();
            controller.setDatabase(database);
            controller.setListener(product -> {
                products.add(product);
                updateTableView();
            });
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("App product to the order");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateTableView() {
        ObservableList<Product> observableProducts = FXCollections.observableArrayList(products);
        productTable.setItems(observableProducts);
    }
}

