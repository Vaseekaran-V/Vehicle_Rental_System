package com.vehicleRentalGUI.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class SignupController {
    private ConnectDB conn = new ConnectDB();
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    public Button sigupButton;
    @FXML
    private Button cancelButton;


    @FXML
    public void onSignupClicked(ActionEvent event) throws NoSuchAlgorithmException {
        String  username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

//        final Stage stage = (Stage) sigupButton.getScene().getWindow();

        if (username.isEmpty() || username.trim().isEmpty() || password.isEmpty() || password.trim().isEmpty() || confirmPassword.isEmpty() || confirmPassword.trim().isEmpty()){
            System.out.println("One or more field/s is/are empty");
        }else if(!password.equals(confirmPassword)){
            System.out.println("Both passwords do not match each other.");
        }else {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);

            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            User user = new User(username, hashedPassword);
            conn.datastore.save(user);
            Node  source = (Node)  event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
            showLogin();
        }

    }
//    @FXML
    public void showLogin(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 500, 275));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (IOException e){
            System.out.println("Could not load Login");
        }
    }

    @FXML
    public void onCancelClicked(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
