
package com.OOP_CW;
/*
import org.bson.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static sun.awt.X11.XConstants.InputOutput;
*/

public class WestminsterRentalVehicleManagerTest {}
    /*
    @Mock
    WestminsterRentalVehicleManager manager = Mockito.mock(WestminsterRentalVehicleManager.class);
    @Mock
    ConnectDB conn = Mockito.mock(ConnectDB.class);


    @Before
    public void setup(){
        addMockData();
        manager.getListOfVehicles();
        System.out.println(manager.getVehicleArrayList());
        getVehicleList();
    }

    public void addMockData(){
        Car car1 = new Car("abcd","make","model",8000, 3,3,4,true,true);
        Car car2 = new Car("sdfg","make2","model2",9000,2,3,5,true,true);
        Bike bike1 = new Bike("dfgh","make3","model3",7800,"scooty",true,false);
        Bike bike2 = new Bike("fghj","make4","model4",980,"bike",false,true);

        conn.datastore.save(car1,car2,bike1,bike2);

    }

    public void getVehicleList(){
        List<Document> carList = new ArrayList<>();
        List <Document> bikeList = new ArrayList<>();
        conn.db.getCollection("Car").find().into(carList);
        conn.db.getCollection("Bike").find().into(bikeList);
        manager.setCarList(carList);
        manager.setBikeList(bikeList);

        List <Vehicle> vehicleArrayList = new ArrayList<>();
        List <Bike> bikeArrayList = new ArrayList<>();
        List <Car> carArrayList = new ArrayList<>();

        if(carList.size() != 0) {

            for (Document car : carList) {
                Car objectCar = new Car(car.getString("_id"), car.getString("make"),
                        car.getString("model"),
                        car.getDouble("ratePerDay"),
                        car.getInteger("noOfDoors"),
                        car.getInteger("numOfLuggage"),
                        car.getInteger("numOfPassengers"), car.getBoolean("airCon"),
                        car.getBoolean("musicPlayer"));

                vehicleArrayList.add(objectCar);
                carArrayList.add(objectCar);
            }
        }else{
            System.out.println("There are no cars added in the system.");
        }

        if(bikeList.size() != 0) {
            for (Document bike : bikeList) {
                Bike bikes = new Bike(bike.getString("_id"), bike.getString("make"),
                        bike.getString("model"),
                        bike.getDouble("ratePerDay"),
                        bike.getString("type"), bike.getBoolean("helmet"),
                        bike.getBoolean("jacket"));

                vehicleArrayList.add(bikes);
                bikeArrayList.add(bikes);
            }
        }else{
            System.out.println("There are no bikes added to the system.");
        }

        manager.setBikeArrayList(bikeArrayList);
        manager.setCarArrayList(carArrayList);
        manager.setVehicleArrayList(vehicleArrayList);
    }

    @org.junit.Test
    public void addVehicle() {
//        fail("This test is not implemented yet");
        System.out.println(manager.getBikeArrayList());

    }

    @org.junit.Test
    public void deleteVehicle() {
        fail("This test is not implemented yet");
    }

    @org.junit.Test
    public void viewVehicles() {
        fail("This test is not implemented yet");
    }

    @org.junit.Test
    public void saveVehicle() {
        fail("This test is not implemented yet");
    }

}
*/
