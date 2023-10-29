package com.inholland.nl.wimmusic.database;

import com.inholland.nl.wimmusic.model.Order;
import com.inholland.nl.wimmusic.model.Product;
import com.inholland.nl.wimmusic.model.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Data{
    private  User user;
    private  Product product;
    private  final List<User> Users;
    private  List <Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Data() {
        this.Users = new ArrayList<>();
        initializeUsers();
        initializeProducts();
        initializeOrders();
    }

    public void initializeUsers(){
        Users.add(new User("Ador","Ador123!", User.Role.Sales,"Ador","Negash","ador@gmail.com",+316234567));
        Users.add(new User("Wim","Wim123!", User.Role.Manager,"Wim","Kok","wim@gmail.com@",+316123678));
    }
    public void initializeProducts(){
        File file = new File("Newproducts.txt");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                products= (List<Product>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            Product product1= new Product(4, "White", "Strings", 2000.3, "New");
            products.add(product1);
            Product product2= new Product(4,"wed","dwd",100.20, "wwd");
            products.add(product2);
        }
    }
    public void initializeOrders(){
        File file = new File("orders.txt");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                orders= (List<Order>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


    public User validateUser(String username, String password) {
        return Users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))  // (2)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product){
        products.add(product);
        productsSaveInFile();
    }
    public void removeProduct(Product product){
        products.remove(product);
        productsSaveInFile();
    }

    public void addOrder(Order order) {
        orders.add(order);
        ordersSaveInFile();
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        ordersSaveInFile();
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void updateProductQuantity(Product selectedProduct) {
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        productsSaveInFile();
    }


    public void deleteProduct(Product selectedProduct) {
        products.remove(selectedProduct);
        productsSaveInFile();
    }


    public void updateProduct(Product updatedProduct) {
        int productIndex = -1;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(updatedProduct.getProductName())) {
                productIndex = i;
                break;
            }
        }

        if (productIndex != -1) {
            products.set(productIndex, updatedProduct);
            productsSaveInFile();
        } else {
            // Product not found, handle this case if needed
        }
    }

    public void productsSaveInFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Newproducts.txt"))){
            oos.writeObject(products);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void ordersSaveInFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("orders.txt"))){
            oos.writeObject(orders);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
