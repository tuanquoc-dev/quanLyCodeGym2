package com.repository;

import com.model.ProductType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDatabase {
    private final File file;

    public ProductTypeDatabase() {
        this.file = new File("database/productType.csv");
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

    // Ghi đè toàn bộ list xuống file
    public void writeData(List<ProductType> listProductType) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) {
            for (ProductType productType : listProductType) {
                bw.write(toCsvLine(productType));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thêm 1 productType (append vào cuối file)
    public void writeData(ProductType productType) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, true))) {
            bw.write(toCsvLine(productType));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc file thành List<ProductType>
    public List<ProductType> readData() {
        List<ProductType> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = br.readLine()) != null) {
                ProductType productType = parseCsvLine(line);
                if (productType != null) {
                    list.add(productType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Trả về toàn bộ nội dung file dưới dạng String
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

    // ================== Private helpers ==================

    // Chuyển 1 ProductType thành dòng CSV
    private String toCsvLine(ProductType productType) {
        return productType.getId() + "," +
                escapeCsv(productType.getName()) + "," +
                escapeCsv(productType.getDescription());
    }

    // Parse 1 dòng CSV thành ProductType
    private ProductType parseCsvLine(String line) {
        String[] arr = line.split(",", -1); // giữ cả chuỗi rỗng
        if (arr.length < 3) return null;

        try {
            Long id = Long.valueOf(arr[0].trim());
            String name = arr[1].trim();
            String description = arr[2].trim();
            return new ProductType(id, name, description);
        } catch (Exception e) {
            System.err.println("Lỗi parse CSV: " + line);
            return null;
        }
    }

    // Xử lý trường hợp có dấu phẩy trong text
    private String escapeCsv(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }
}
