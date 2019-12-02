package com.vehicleRentalGUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.OOP_CW.*;

public class VehicleListController {
    private WestminsterRentalVehicleManager vehicleManager = new WestminsterRentalVehicleManager();
    @FXML
    private ComboBox vehicleChoice;
    @FXML
    private ListView<Vehicle> vehicleListView;
    @FXML
    private Label vehicleDetailsLabel;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Button bookNow;

    public void initialize(){
        vehicleManager.getListOfVehicles();
        vehicleListView.getItems().setAll(vehicleManager.getVehicleArrayList());
        vehicleListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void listVehicleTypes(){

        List<String> vehicleTypes = new ArrayList<>();
        vehicleTypes.add("Bike");
        vehicleTypes.add("Car");
        vehicleChoice.getItems().setAll(vehicleTypes);

    }

    @FXML
    public void handleClickListView(){
        Vehicle vehicle = vehicleListView.getSelectionModel().getSelectedItem();
        System.out.println(vehicleListView.getSelectionModel().getSelectedItem());
        StringBuilder sb = new StringBuilder("Make\t : \t"+vehicle.getMake());
        sb.append("\n\n");
        sb.append("Model\t : \t"+vehicle.getModel());
        sb.append("\n\nPlate\t\t : \t"+vehicle.getPlate());
        vehicleDetailsLabel.setText(sb.toString());
    }

    @FXML
    public void onBookNowClicked(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 500, 275));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

//            stage.show();
//            ((Node)(event.getSource())).getScene().getWindow().hide();
        }catch (IOException e){
            System.out.println("Could not load Login");
        }

    }

    @FXML
    public void handleClickVehicleChoice(){
        System.out.println(vehicleChoice.getSelectionModel().getSelectedItem());
        if(vehicleChoice.getSelectionModel().getSelectedItem().equals("Bike")){
            vehicleListView.getItems().setAll(vehicleManager.getBikeArrayList());
        }else if(vehicleChoice.getSelectionModel().getSelectedItem().equals("Car")){
            vehicleListView.getItems().setAll(vehicleManager.getCarArrayList());
        }
    }

    @FXML
    public void onSignupClicked(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/signup.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Sigup");
            stage.setScene(new Scene(root, 500, 275));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (IOException e){
            System.out.println("Could not load Signup");
        }
    }

    @FXML
    public void onLoginClicked(){
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
}
