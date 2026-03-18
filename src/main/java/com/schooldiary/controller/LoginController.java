package com.schooldiary.controller;

import com.schooldiary.model.User;
import com.schooldiary.repository.JdbcUserRepository;
import com.schooldiary.service.AuthService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/view/main.fxml")
                );

                Parent root = loader.load();

                MainController controller = loader.getController();
                controller.setUser(user);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("School Diary");
                stage.show();

                loginField.getScene().getWindow().hide();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            errorLabel.setText("Неверный логин или пароль");
        }
    }
}