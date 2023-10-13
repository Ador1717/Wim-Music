package com.inholland.nl.wimmusic.Controller;

import com.inholland.nl.wimmusic.Model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class createOrderButtonController {
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Button addProduct;

    @FXML
    private Button deleteProduct;

    @FXML
    private Button createOrder;
}
