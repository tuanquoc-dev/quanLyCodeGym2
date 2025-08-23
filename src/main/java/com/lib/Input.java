package com.lib;

import java.util.Scanner;

public class Input {
    private static Scanner sc = new Scanner(System.in);

    public static String inputString() {
        String output = sc.nextLine();
        return output;
    }

    public static int inputInt() {
        do {
            try {
                int output = Integer.parseInt(sc.nextLine());
                return output;
            } catch (NumberFormatException e) {
                System.out.println("Sai định dạng vui lòng nhập lại");
            }

        }while (true);
    }

    public static double inputDouble() {
        do {
            try {
                double output = Double.parseDouble(sc.nextLine());
                return output;
            } catch (NumberFormatException e) {
                System.out.println("Sai định dạng vui lòng nhập lại");
            }

        }while (true);
    }

}
