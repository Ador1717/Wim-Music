package com.inholland.nl.wimmusic.Database;

import com.inholland.nl.wimmusic.Model.user;

import java.util.ArrayList;
import java.util.List;

public class userDatabase {
    private  final List<user> users ;


    public userDatabase() {
        this.users = new ArrayList<>();
        initializeUsers();
    }
    public void initializeUsers(){
        users.add(new user("Ador","Ador","Sales"));
        users.add(new  user("Wim","Wim","Manager"));
    }
    public  user validateUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))  // (2)
                .findFirst()
                .orElse(null);
    }
}
