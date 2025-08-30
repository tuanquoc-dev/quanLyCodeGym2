package com;

import com.menu.AuthMenu;
import com.service.UserService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService userService = new UserService();
        AuthMenu authMenu = new AuthMenu(userService);
        authMenu.showAuthMenu();



    }
}