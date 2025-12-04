package dao;



public abstract class Product {

		// encapsuulation
	 private int id;
	 private String name;
	 private double price;
	 private int stock;
	
	 
	 
	 // Constructor
	 public Product(int id, String name, double price, int stock) {
	     this.id = id;
	     this.name = name;
	     this.price = price;
	     this.stock = stock;
	 }
	
	 
	 
	 // Abstract Method (Harus di-override oleh anak class)
	 public abstract void displayInfo();
	
	 
	 // Getter dan Setter (Wajib untuk Encapsulation)
	 public int getId() { return id; }
	 public void setId(int id) { this.id = id; }
	
	 public String getName() { return name; }
	 public void setName(String name) { this.name = name; }
	
	 public double getPrice() { return price; }
	 public void setPrice(double price) { this.price = price; }
	
	 public int getStock() { return stock; }
	 public void setStock(int stock) { this.stock = stock; }
}
