package com.vehicleRentalGUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void onLoginClicked(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username + " " + password);
    }

    @FXML
    public void onCancelClicked(){
        System.exit(0);
    }
}
