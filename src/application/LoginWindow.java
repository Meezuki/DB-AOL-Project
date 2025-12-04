package application;

import dao.UserDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWindow {
    
    public void show(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        
        Label lblTitle = new Label("LOGIN GUDANG");
        TextField txtUser = new TextField();
        txtUser.setPromptText("Username");
        txtUser.setMaxWidth(200);
        
        PasswordField txtPass = new PasswordField();
        txtPass.setPromptText("Password");
        txtPass.setMaxWidth(200);
        
        Button btnLogin = new Button("Login");
        Label lblStatus = new Label();

        btnLogin.setOnAction(e -> {
            UserDAO dao = new UserDAO();
            if (dao.login(txtUser.getText(), txtPass.getText())) {
                // Jika sukses, pindah ke Dashboard
                new DashboardWindow().show(stage);
            } else {
                lblStatus.setText("Login Gagal! Coba lagi.");
            }
        });

        root.getChildren().addAll(lblTitle, txtUser, txtPass, btnLogin, lblStatus);
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}