package model;

import dao.Product;

// INHERITANCE: Laptop adalah turunan dari Product
public class Laptop extends Product {
    private String processor;
    private int ram;

    public Laptop(int id, String name, double price, int stock, String processor, int ram) {
        // Menggunakan constructor dari parent (Product)
        super(id, name, price, stock); 
        this.processor = processor;
        this.ram = ram;
    }

    // POLYMORPHISM: Mengubah perilaku method displayInfo khusus untuk Laptop
    @Override
    public void displayInfo() {
        System.out.println("[Laptop] " + getName() + " | CPU: " + processor + " | RAM: " + ram + "GB");
        System.out.println("Harga: Rp" + getPrice() + " | Stok: " + getStock());
    }

    
    
    
    // Getter Setter khusus Laptop
    public String getProcessor() { return processor; }
    public void setProcessor(String processor) { this.processor = processor; }

    public int getRam() { return ram; }
    public void setRam(int ram) { this.ram = ram; }
}