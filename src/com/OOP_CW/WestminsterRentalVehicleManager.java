package com.OOP_CW;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.jmkgreen.morphia.query.Query;
import org.bson.Document;
import java.util.Collections;
import java.util.Scanner;

public class WestminsterRentalVehicleManager implements RentalVehicleManager {
    private List <Car> carArrayList = new ArrayList<>();
    private List <Document> carList = new ArrayList<>();
    private List <Bike> bikeArrayList = new ArrayList<>();
    private List <Document> bikeList = new ArrayList<>();
    private List <Vehicle> vehicleArrayList = new ArrayList<>();
    private ConnectDB conn = new ConnectDB();
    Scanner sc = new Scanner(System.in);

    //code to display menu
    public void displayMenu() throws IOException {
        boolean check = true;

        while (check){
            System.out.println("<----------MENU---------->");
            System.out.println("1. Add a Vehicle. \n2. View all vehicles. \n3. Delete Vehicle. \n4. Save Vehicle. \n" +
                    "5. Open GUI. \n6. Exit Program");
            System.out.println("**** ENTER THE NUMBER OF YOUR CHOICE ****");
            while (!sc.hasNextInt()) {
                System.out.println("Incorrect input.\n Choose an input from 1 to 6");
                sc.next();
            }
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("You have chosen to add a vehicle");
                    addVehicle();
                    break;

                case 2:
                    System.out.println("You have chosen to view all the vehicles in the system.");
                    viewVehicles();
                    break;

                case 3:
                    System.out.println("You have chosen to delete a vehicle");
                    deleteVehicle();
                    break;

                case 4:
                    System.out.println("You have chosen to save the vehicle");
                    saveVehicle();
                    break;

                case 5:
                    System.out.println("You have chosen to open the GUI");
                    //code to open the GUI

                case 6:
                    System.out.println("You have chosen to exit the program. \nThank you and have a nice day");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Incorrect choice entered. Try again.");
            }
        }
    }

    /*public List <Car> carList() {
        //List <Document> carList = conn.db.getCollection("Car").find().into(new ArrayList<>());
        conn.db.getCollection("Car").find().into(carList);

        for(Document car : carList){
            Car cars = new Car(car.getString("plate"), car.getString("make"),car.getString("model"), car.getDouble("rentPerDay"),
                    car.getInteger("noOfDoors"), car.getInteger("numOfLuggage"), car.getInteger("numOfPassengers"), car.getBoolean("airCon"),
                    car.getBoolean("musicPlayer"));

            carArrayList.add(cars);
        }

        return carArrayList;
    }

    public List <Bike> bikeList(){
        conn.db.getCollection("Bike").find().into(bikeList);

        for(Document bike : bikeList){
            Bike bikes = new Bike(bike.getString("plate"), bike.getString("make"), bike.getString("model"),
                    bike.getDouble("ratePerDay"), bike.getString("type"), bike.getBoolean("helmet"),
                    bike.getBoolean("jacket"));

            bikeArrayList.add(bikes);
        }

        return bikeArrayList;

    }*/

    public List <Vehicle> listOfVehicles(){
        conn.db.getCollection("Car").find().into(carList);
        conn.db.getCollection("Bike").find().into(bikeList);

        if(carList.size() != 0) {

            for (Document car : carList) {
                Car objectCar = new Car(car.getString("plate"), car.getString("make"),
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
            System.out.println("There are no cars in the system.");
        }

        if(bikeList.size() != 0) {
            for (Document bike : bikeList) {
                Bike bikes = new Bike(bike.getString("plate"), bike.getString("make"),
                        bike.getString("model"),
                        bike.getDouble("ratePerDay"),
                        bike.getString("type"), bike.getBoolean("helmet"),
                        bike.getBoolean("jacket"));

                vehicleArrayList.add(bikes);
                bikeArrayList.add(bikes);
            }
            return vehicleArrayList;
        }else{
            System.out.println("No bikes");
        }

        return vehicleArrayList;
    }

    /*public List <Vehicle> vehicleList(){
        vehicleArrayList.addAll(carList());
        vehicleArrayList.addAll(bikeList());

        return vehicleArrayList;
    }*/

    public boolean checkParkingAvailable(){
        int vehiclesInParking = vehicleArrayList.size();

        if(vehiclesInParking < 50){
            return true;
        }else{
            return false;
        }
    }

    public WestminsterRentalVehicleManager() {
    }

    public void addCar(Car car){
        listOfVehicles().add(car);
    }

    public void addBike(Bike bike){
        listOfVehicles().add(bike);
    }

    public void deleteCar(Car car){
        //carList().remove(car);
        listOfVehicles().remove(car);

        int availableParkingLot = 50 - listOfVehicles().size();
        System.out.println("You can add " + availableParkingLot + " vehicles to the Parking Lot");

    }

    public void deleteBike(Bike bike) {
        //bikeList().remove(bike);
        listOfVehicles().remove(bike);

        int availableParkingLot = 50 - listOfVehicles().size();
        System.out.println("You can add " + availableParkingLot + " vehicles to the Parking Lot");

    }

    @Override
    public void addVehicle() throws IOException {
        Scanner sc1 = new Scanner(System.in);

        if(checkParkingAvailable()) {

            //String plate = checkForPlate();
            System.out.println("Enter the plate: ");
            String plate = sc.nextLine();

            System.out.println("Enter the make of the vehicle:");
            String make = sc.next();

            System.out.println("Enter the model of the vehicle:");
            String model = sc.next();

            System.out.println("Enter the cost per day of renting the vehicle:");
            while (!sc.hasNextDouble()) {
                System.out.println("Incorrect type of input. Enter the daily rate of the vehicle with the correct " +
                        "data type.");
                sc.next();
            }
            double ratePerDay = sc.nextDouble();

            System.out.println("Enter the type of Vehicle: \n1. Bike. \n2.Car");

            while (!sc.hasNextInt()) {
                System.out.println("Incorrect input.\n Choose an input from 1 to 6");
                sc.next();
            }
            int choiceVehicle = sc.nextInt();
            boolean bigCheck = true;

            while (bigCheck) {

                switch (choiceVehicle) {
                    case 1:
                        System.out.println("You have chosen to add a Bike");

                        System.out.println("Enter the type of Bike: ");
                        String bikeType = sc.next();

                        //Initializing a Bike object
                        Bike bike = new Bike(plate, make, model, ratePerDay, bikeType);

                        System.out.println("Enter if the bike comes with a helmet: (1 if Yes; 2 if No");

                        while (!sc.hasNextInt()) {
                            System.out.println("Incorrect type of input.");
                            sc.next();
                        }
                        int isHelmet = sc.nextInt();
                        boolean check = true;

                        while (check) {
                            switch (isHelmet) {
                                case 1:
                                    bike.setHelmet(true);
                                    check = false;
                                    break;

                                case 2:
                                    bike.setHelmet(false);
                                    check = false;
                                    break;

                                default:
                                    System.out.println("Incorrect option. Try again");
                                    check = true;
                            }
                        }

                        System.out.println("Enter if the bike comes with a jacket: ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Incorrect type of input.");
                            sc.next();
                        }
                        int isJacket = sc.nextInt();
                        boolean check2 = true;
                        while (check2) {
                            switch (isJacket) {
                                case 1:
                                    bike.setJacket(true);
                                    check2 = false;
                                    break;

                                case 2:
                                    bike.setJacket(false);
                                    check2 = false;
                                    break;

                                default:
                                    System.out.println("Incorrect option. Try again");
                                    check = true;
                            }
                        }

                        conn.datastore.save(bike);
                        listOfVehicles().add(bike);
                        System.out.println("The bike with the plate " + bike.getPlate() + " is added successfully");
                        bigCheck = false;
                        break;

                    case 2:
                        System.out.println("You have chosen to add a car");

                        System.out.println("Enter the number of doors in the car: ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Incorrect type. Enter the number of doors in the car");
                            sc.next();
                        }
                        int numOfDoors = sc.nextInt();

                        System.out.println("Enter the number of luggage the car can carry: ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Incorrect type. Enter the number of luggage that can be in the car");
                            sc.next();
                        }
                        int numOfLuggage = sc.nextInt();

                        System.out.println("Enter the number of passengers that can be in the car: ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Incorrect type. Enter the number of passengers that can be in the car");
                            sc.next();
                        }
                        int numOfPassengers = sc.nextInt();

                        //instantiating a new car object
                        Car car = new Car(plate, make, model, ratePerDay, numOfDoors, numOfLuggage, numOfPassengers);

                        check2 = true;
                        while (check2) {
                            System.out.println("Does the car has Air Conditioning \nPress 1 if \"yes\"\nPress 2 if" +
                                    " \"no\"");
                            while (!sc.hasNextInt()) {
                                System.out.println("Incorrect input. \nDoes the car has A/C? \nPress 1 if \"yes\"\nPress" +
                                        " 2 if \"no\" ");
                                sc.next();
                            }
                            int checkAC = sc.nextInt();
                            switch (checkAC) {
                                case 1:
                                    check2 = false;
                                    car.setAirCon(true);
                                    break;

                                case 2:
                                    check2 = false;
                                    car.setAirCon(false);
                                    break;

                                default:
                                    System.out.println("Incorrect choice. Try again");
                            }
                        }

                        boolean check3 = true;
                        while (check3) {
                            System.out.println("Does the car has Music Player \nPress 1 if \"yes\"\nPress 2 if \"no\"");
                            while (!sc.hasNextInt()) {
                                System.out.println("Incorrect input. \nDoes the car has Music Player? \nPress 1 if " +
                                        "\"yes\"\nPress 2 if \"no\" ");
                                sc.next();
                            }
                            int checkMusicPlayer = sc.nextInt();
                            switch (checkMusicPlayer) {
                                case 1:
                                    check3 = false;
                                    car.setMusicPlayer(true);
                                    break;

                                case 2:
                                    check3 = false;
                                    car.setMusicPlayer(false);
                                    break;

                                default:
                                    System.out.println("Incorrect choice. Try again");
                            }
                        }

                        conn.datastore.save(car);
                        addCar(car);
                        System.out.println("The car with the plate number " + car.getPlate() + " is added successfully");
                        bigCheck = false;
                        break;

                    default:
                        System.out.println("Incorrect choice. Please try again...");
                }
            }
        }
    }

    @Override
    public void deleteVehicle() {


    }

    @Override
    public void viewVehicles() {
        /*if(vehicleList().size() == 0){
            System.out.println("There are no cars/bikes in the viewing lot. \n" +
                    "Please add a Vehicle");
        }else{
            Collections.sort(bikeList());
            for(Bike bike: bikeList()){
                System.out.println("<-------Bikes in the System------->");
                if(bikeList().size() == 0){
                    System.out.println("There are no bikes added to the system");
                }else{
                    System.out.println("Plate Number \t\tMake");
                    System.out.println(bike.getPlate() + "\t\t"+bike.getMake());
                }
            }

            Collections.sort(carList());
            for(Car car: carList()){
                System.out.println("<------- Cars in the System ------->");
                if(carList().size() == 0){
                    System.out.println("There are no cars added to the system");
                }else{
                    System.out.println("Plate Number \t\tMake");
                    System.out.println(car.getPlate() + "\t\t" + car.getMake());
                }
            }
        }*/
    }

    @Override
    public void saveVehicle() {

    }

    public String checkForPlate(String plate){
        //Scanner sc3 = new Scanner(System.in);
        //boolean checker = true;
        while(true){
            //System.out.println("Enter the plate of the vehicle");
            //String plate = sc3.nextLine();
            for(Vehicle vehicle: listOfVehicles()){
                if(vehicle.getPlate().contains(plate)){
                    System.out.println("There is a vehicle with the same plate. \nTry again");
                    break;

                }else{
                    return plate;

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        WestminsterRentalVehicleManager manager = new WestminsterRentalVehicleManager();
        //manager.listOfVehicles();
        System.out.println(manager.listOfVehicles() + ", size of carslist: " + manager.carArrayList.size());
        manager.addVehicle();
    }
}
