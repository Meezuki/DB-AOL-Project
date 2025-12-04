package dao;

import model.Laptop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // FITUR 1: CREATE (Menambah Barang)
    public boolean addProduct(Product p) {
        String sql = "INSERT INTO products (type, name, price, stock, spec_1, spec_2) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set data umum (dari parent class Product)
            stmt.setString(2, p.getName());
            stmt.setDouble(3, p.getPrice());
            stmt.setInt(4, p.getStock());

            // LOGIKA KHUSUS: Cek apakah ini Laptop atau HP?
            if (p instanceof Laptop) {
                Laptop laptop = (Laptop) p; // Casting
                stmt.setString(1, "Laptop");
                stmt.setString(5, laptop.getProcessor()); // spec_1 jadi Processor
                stmt.setString(6, String.valueOf(laptop.getRam())); // spec_2 jadi RAM
            } else if (p instanceof Smartphone) {
                Smartphone hp = (Smartphone) p; // Casting
                stmt.setString(1, "Smartphone");
                stmt.setString(5, String.valueOf(hp.getCameraMP())); // spec_1 jadi Camera
                stmt.setString(6, String.valueOf(hp.getBatteryCapacity())); // spec_2 jadi Baterai
            }

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // FITUR 2: READ (Mengambil Semua Data)
    
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String type = rs.getString("type");
                
                // Ambil data umum
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                String spec1 = rs.getString("spec_1");
                String spec2 = rs.getString("spec_2");

                // LOGIKA MAPPING: Ubah data SQL kembali menjadi Objek Java
                if ("Laptop".equalsIgnoreCase(type)) {
                    // Buat objek Laptop (spec2 diubah balik ke int untuk RAM)
                    list.add(new Laptop(id, name, price, stock, spec1, Integer.parseInt(spec2)));
                } else if ("Smartphone".equalsIgnoreCase(type)) {
                    // Buat objek Smartphone
                    list.add(new Smartphone(id, name, price, stock, Integer.parseInt(spec1), Integer.parseInt(spec2)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // FITUR 3: DELETE (Hapus Barang)
    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}