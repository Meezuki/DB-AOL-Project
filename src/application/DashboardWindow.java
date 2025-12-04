package application;

import dao.ProductDAO;
import model.Laptop;
import model.Product;
import model.Smartphone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardWindow {
    
    private ProductDAO productDAO = new ProductDAO();
    private TableView<Product> table = new TableView<>();
    private ObservableList<Product> productList;

    // Input Fields
    TextField txtName = new TextField();
    TextField txtPrice = new TextField();
    TextField txtStock = new TextField();
    TextField txtSpec1 = new TextField(); // Processor atau Camera
    TextField txtSpec2 = new TextField(); // RAM atau Battery
    ComboBox<String> cmbType = new ComboBox<>();

    public void show(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // 1. SETUP TABEL (READ)
        TableColumn<Product, String> colName = new TableColumn<>("Nama Produk");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Product, Double> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getColumns().addAll(colName, colPrice, colStock);
        
        // Load Data dari Database
        refreshTable();

        // 2. SETUP FORM INPUT (CREATE)
        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10);
        form.setPadding(new Insets(10));

        cmbType.getItems().addAll("Laptop", "Smartphone");
        cmbType.getSelectionModel().selectFirst();
        
        // Label dinamis berubah sesuai pilihan ComboBox
        Label lblSpec1 = new Label("Processor:");
        Label lblSpec2 = new Label("RAM (GB):");
        
        cmbType.setOnAction(e -> {
            if(cmbType.getValue().equals("Laptop")) {
                lblSpec1.setText("Processor:");
                lblSpec2.setText("RAM (GB):");
            } else {
                lblSpec1.setText("Camera (MP):");
                lblSpec2.setText("Battery (mAh):");
            }
        });

        form.addRow(0, new Label("Tipe:"), cmbType);
        form.addRow(1, new Label("Nama:"), txtName);
        form.addRow(2, new Label("Harga:"), txtPrice);
        form.addRow(3, new Label("Stok:"), txtStock);
        form.addRow(4, lblSpec1, txtSpec1);
        form.addRow(5, lblSpec2, txtSpec2);

        // 3. TOMBOL AKSI
        Button btnAdd = new Button("Tambah Barang");
        Button btnDelete = new Button("Hapus Barang");
        Button btnRefresh = new Button("Refresh Data");

        // Event Handling (Logika Tombol)
        btnAdd.setOnAction(e -> addProductAction());
        btnDelete.setOnAction(e -> deleteProductAction());
        btnRefresh.setOnAction(e -> refreshTable());

        HBox buttonBox = new HBox(10, btnAdd, btnDelete, btnRefresh);
        
        // Susun Layout
        VBox rightSide = new VBox(10, new Label("Form Input"), form, buttonBox);
        root.setCenter(table);
        root.setRight(rightSide);

        Scene scene = new Scene(root, 900, 500);
        stage.setScene(scene);
        stage.setTitle("Sistem Gudang Elektronik - Dashboard");
        stage.show();
    }

    private void refreshTable() {
        productList = FXCollections.observableArrayList(productDAO.getAllProducts());
        table.setItems(productList);
    }

    private void addProductAction() {
        try {
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int stock = Integer.parseInt(txtStock.getText());
            String type = cmbType.getValue();

            Product p;
            if (type.equals("Laptop")) {
                String proc = txtSpec1.getText();
                int ram = Integer.parseInt(txtSpec2.getText());
                p = new Laptop(0, name, price, stock, proc, ram);
            } else {
                int cam = Integer.parseInt(txtSpec1.getText());
                int bat = Integer.parseInt(txtSpec2.getText());
                p = new Smartphone(0, name, price, stock, cam, bat);
            }

            if(productDAO.addProduct(p)) {
                refreshTable();
                clearFields();
                showAlert("Sukses", "Data berhasil disimpan!");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Pastikan Harga/Stok/Angka diisi dengan benar!");
        }
    }

    private void deleteProductAction() {
        Product selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            productDAO.deleteProduct(selected.getId());
            refreshTable();
        } else {
            showAlert("Peringatan", "Pilih barang yang mau dihapus dulu!");
        }
    }
    
    private void clearFields() {
        txtName.clear(); txtPrice.clear(); txtStock.clear(); 
        txtSpec1.clear(); txtSpec2.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}