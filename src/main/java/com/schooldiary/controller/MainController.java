package com.schooldiary.controller;

import com.schooldiary.model.Role;
import com.schooldiary.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    @FXML private Label userLabel;
    @FXML private Button usersBtn;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        userLabel.setText(user.getLogin() + " (" + user.getRole() + ")");
        configureMenuByRole();
    }

    private void configureMenuByRole() {
        if (currentUser.getRole() != Role.ADMIN) {
            usersBtn.setVisible(false);
        }
    }

    @FXML
    private void handleLogout() {
        userLabel.getScene().getWindow().hide();
    }
}