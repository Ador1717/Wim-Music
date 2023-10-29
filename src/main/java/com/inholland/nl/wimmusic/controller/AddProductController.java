package com.inholland.nl.wimmusic.controller;

import com.inholland.nl.wimmusic.database.Data;
import com.inholland.nl.wimmusic.model.Product;
import com.inholland.nl.wimmusic.model.ProductSelectedListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddProductController{

    @FXML
    private TableView productTable;
    @FXML
    private TextField quantityField;
    @FXML
    private Button addProductBtn;
    @FXML
    private Button cancelProductBtn;
    Data database;
    ProductSelectedListener listener;
    private ObservableList<Product> products;


    public void setDatabase(Data database) {
        this.database = database;
        loadProducts();
    }
    public void loadProducts() {
        products = FXCollections.observableArrayList(database.getProducts());
        productTable.setItems(products);
    }
    public void setListener(ProductSelectedListener listener) {
        this.listener = listener;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        productTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    public void AddToOrder(ActionEvent actionEvent) {
        Product selectedProduct = (Product)productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null && listener != null) {
            int quantity = Integer.parseInt(quantityField.getText());
            selectedProduct.setNewStock(quantity);
            selectedProduct.getTotalPrice();
            selectedProduct.setQuantity(quantity);
            listener.onProductSelected(selectedProduct);
        }
        Stage stage = (Stage) addProductBtn.getScene().getWindow();
        stage.close();
    }

    public void CancelProduct(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelProductBtn.getScene().getWindow();
        stage.close();
    }
}
