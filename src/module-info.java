module Database_Project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Biar bisa connect database
    requires javafx.base; 
    requires javafx.graphics;
    

    // Memberi izin JavaFX untuk membaca class di dalam package 'model'
    opens model to javafx.base;
    
    opens application to javafx.graphics, javafx.fxml;
   
}