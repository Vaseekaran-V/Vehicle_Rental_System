package com.vehicleRentalGUI.controllers;

import com.OOP_CW.Car;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.Binary;

import javafx.event.ActionEvent;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private List<User> userList = new ArrayList<>();
    private List <Document> userDocument = new ArrayList<>();

    @FXML
    public void onLoginClicked(ActionEvent event) throws NoSuchAlgorithmException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username + " " + password);

        if (username.isEmpty() || username.trim().isEmpty() || password.isEmpty() || password.trim().isEmpty()){
            System.out.println("One or more fields are empty");
        }else{
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);

            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            if(verifyPassword(username,hashedPassword)){
                Node source = (Node)  event.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
            }else {
                usernameField.clear();
                passwordField.clear();
            }

        }
    }

    public boolean verifyPassword(String username, byte[] hashedPassword){
        getExistingUserDetails();
        System.out.println(username + hashedPassword);
        for(User u : userList){
            if(u.getUsername().equals(username)){
                if(u.getPassword().equals(hashedPassword)){
                    System.out.println("Login success");
                    return true;
                }else {
                    System.out.println("Incorrect password");
                    return false;
                }
            }
        }
        System.out.println("User not found");
        return false;
    }

    public void getExistingUserDetails(){
        ConnectDB conn = new ConnectDB();
        System.out.println("connection success");
        conn.db.getCollection("User").find().into(userDocument);
        System.out.println("adding to collection success");
        if(userDocument.size() != 0) {
            for (Document user : userDocument) {
                String  uname = user.getString("username");
                Binary binary = user.get("password", Binary.class);
                byte[] pwd = binary.getData();
                User userObject = new User(uname, pwd);
                userList.add(userObject);
            }
        }else{
            System.out.println("There are no users added in the system.");
        }
    }

    @FXML
    public void onCancelClicked(){
        System.exit(0);
    }
}
