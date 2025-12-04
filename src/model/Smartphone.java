package model;

public class Smartphone extends Product {
    private int cameraMP;
    private int batteryCapacity;

    public Smartphone(int id, String name, double price, int stock, int cameraMP, int batteryCapacity) {
        super(id, name, price, stock);
        this.cameraMP = cameraMP;
        this.batteryCapacity = batteryCapacity;
    }

    // POLYMORPHISM: Tampilan info HP beda dengan Laptop
    @Override
    public void displayInfo() {
        System.out.println("[Smartphone] " + getName() + " | Kamera: " + cameraMP + "MP | Baterai: " + batteryCapacity + "mAh");
        System.out.println("Harga: Rp" + getPrice() + " | Stok: " + getStock());
    }
    
    // Getter Setter khusus Smartphone
    public int getCameraMP() { return cameraMP; }
    public void setCameraMP(int cameraMP) { this.cameraMP = cameraMP; }

    public int getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(int batteryCapacity) { this.batteryCapacity = batteryCapacity; }
}