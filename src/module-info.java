module Database_Project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Biar bisa connect database
    requires javafx.base; // Tambahkan ini juga jika belum ada
    requires javafx.graphics;
    
    // --- TAMBAHKAN BAGIAN INI ---
    
    // Memberi izin JavaFX untuk membaca class di dalam package 'model'
    opens model to javafx.base;
    
    // Memberi izin JavaFX untuk menjalankan aplikasi utama
    // (Sesuaikan 'application' dengan nama package tempat MainApp/DashboardWindow Anda berada)
    opens application to javafx.graphics, javafx.fxml;
    
    // Jika Anda memakai package 'com.gudang.model' sesuai tutorial awal saya, 
    // tulisnya: opens com.gudang.model to javafx.base;
    // Tapi melihat error log Anda, sepertinya package Anda bernama 'model'.
}