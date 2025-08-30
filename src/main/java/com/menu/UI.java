package com.menu;

public class UI {

    // In menu dạng box
    public static void printBox(String title, String[] options) {
        System.out.println();

        // Tính chiều rộng lớn nhất (title và option dài nhất)
        int maxLength = title.length();
        for (String opt : options) {
            if (opt.length() > maxLength) {
                maxLength = opt.length();
            }
        }

        int boxWidth = maxLength + 6; // padding trái/phải

        // In khung trên
        System.out.print("╔");
        for (int i = 0; i < boxWidth; i++) System.out.print("═");
        System.out.println("╗");

        // In tiêu đề căn giữa
        String centeredTitle = centerText(title, boxWidth);
        System.out.println("║" + centeredTitle + "║");

        // In đường phân cách
        System.out.print("╠");
        for (int i = 0; i < boxWidth; i++) System.out.print("═");
        System.out.println("╣");

        // In các lựa chọn
        for (String opt : options) {
            String paddedOpt = padRight(opt, boxWidth);
            System.out.println("║" + paddedOpt + "║");
        }

        // In khung dưới
        System.out.print("╚");
        for (int i = 0; i < boxWidth; i++) System.out.print("═");
        System.out.println("╝");
    }

    // Căn giữa chuỗi
    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(text);
        while (sb.length() < width) sb.append(" ");
        return sb.toString();
    }

    // Thêm khoảng trắng bên phải để căn đều
    private static String padRight(String text, int width) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < width) {
            sb.append(" ");
        }
        return sb.toString();
    }

    // In lỗi
    public static void printError(String msg) {
        System.out.println("⚠️ " + msg);
    }

    // In thông báo thành công
    public static void printSuccess(String msg) {
        System.out.println("✅ " + msg);
    }

    // In cảnh báo
    public static void printWarning(String msg) {
        System.out.println("⚠️ " + msg);
    }
}
