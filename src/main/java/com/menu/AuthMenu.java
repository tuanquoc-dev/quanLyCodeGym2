package com.menu;

import com.lib.Input;
import com.model.User;
import com.service.UserService;

public class AuthMenu {
    private final UserService userService;
    private static int nextId = 1; // Biến static để tự tăng id

    public AuthMenu(UserService userService) {
        this.userService = userService;
    }

    public void showAuthMenu() {
        int choice;
        do {
            UI.printBox("🔐 AUTH MENU 🔐", new String[]{
                    "1. 📝 Đăng ký",
                    "2. 🔑 Đăng nhập",
                    "0. ❌ Thoát"
            });

            System.out.print("👉 Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 0 -> System.out.println("👋 Tạm biệt!");
                default -> UI.printError("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // ================= Đăng ký =================
    private void register() {
        System.out.println("📝 Đăng ký tài khoản mới");

        String username = Input.inputString("Nhập username: ");
        String password = Input.inputString("Nhập password: ");

        System.out.print("👉 Chọn role (1-Admin, 2-User): ");
        int roleChoice = Input.inputInt();
        String role = (roleChoice == 1) ? "ADMIN" : "USER";

        // Tạo user mới với id tự tăng
        User newUser = new User(nextId++, username, password, role);

        if (userService.register(newUser)) {
            UI.printSuccess("Đăng ký thành công!");
        } else {
            UI.printError("❌ Username đã tồn tại, vui lòng chọn tên khác.");
        }
    }

    // ================= Đăng nhập =================
    private void login() {
        System.out.println("🔑 Đăng nhập hệ thống");

        String username = Input.inputString("Nhập username: ");
        String password = Input.inputString("Nhập password: ");

        User loggedInUser = userService.login(username, password);

        if (loggedInUser != null) {
            UI.printSuccess("✅ Đăng nhập thành công! Xin chào " + loggedInUser.getUsername());
            MainMenu mainMenu = new MainMenu(loggedInUser);
            mainMenu.showMainMenu();
        } else {
            UI.printError("❌ Sai username hoặc password!");
        }
    }
}
