package com.schooldiary.controller;

import com.schooldiary.model.User;
import com.schooldiary.repository.JdbcUserRepository;
import com.schooldiary.service.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final AuthService authService =
            new AuthService(new JdbcUserRepository());

    @FXML
    private void handleLogin() {

        User user = authService.login(
                loginField.getText(),
                passwordField.getText()
        );

        if (user != null) {
            System.out.println("Успешный вход: " + user.getRole());
        } else {
            errorLabel.setText("Неверный логин или пароль");
        }
    }
}