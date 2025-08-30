package com.repository;

import com.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();
    private int nextId = 1;

    public UserRepository() {
        // tạo sẵn 1 admin mặc định
        users.add(new User(nextId++, "admin", "admin123", "ADMIN"));
    }

    public User register(String username, String password) {
        // check username tồn tại chưa
        for (User u : users) {
            if (u.getUsername().equals(username)) return null;
        }
        User newUser = new User(nextId++, username, password, "USER");
        users.add(newUser);
        return newUser;
    }

    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
