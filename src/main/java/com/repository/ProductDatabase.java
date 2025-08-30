package com.repository;

import com.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private final File file;

    public ProductDatabase() {
        this.file = new File("database/product.csv");
        createFileIfNotExists();
    }

    // Đảm bảo file và folder tồn tại
    private void createFileIfNotExists() {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo file: " + file.getPath(), e);
        }
    }

    // Ghi đè toàn bộ danh sách sản phẩm
    public void writeData(List<Product> listProduct) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) {
            StringBuilder sb = new StringBuilder();
            for (Product product : listProduct) {
                sb.append(product.getId()).append(",")
                        .append(product.getName()).append(",")
                        .append(product.getPrice()).append(",")
                        .append(product.getQuantity()).append(",")
                        .append(product.getProductTypeId()).append("\n");
            }
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thêm 1 sản phẩm (append vào cuối file)
    public void writeData(Product product) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, true))) {
            bw.write(product.getId() + "," +
                    product.getName() + "," +
                    product.getPrice() + "," +
                    product.getQuantity() + "," +
                    product.getProductTypeId() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lấy toàn bộ file dưới dạng String
    public String getStringData() {
        StringBuilder output = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = br.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    // Đọc file thành List<Product>
    public List<Product> readData() {
        List<Product> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length >= 5) {
                    Long id = Long.valueOf(arr[0]);
                    String name = arr[1];
                    double price = Double.parseDouble(arr[2]);
                    int quantity = Integer.parseInt(arr[3]);
                    Long typeId = Long.valueOf(arr[4]);
                    list.add(new Product(id, name, price, quantity, typeId));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
