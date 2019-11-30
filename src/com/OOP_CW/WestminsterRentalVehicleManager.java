package com.OOP_CW;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import java.util.Scanner;

public class WestminsterRentalVehicleManager implements RentalVehicleManager {
    private List <Car> carArrayList = new ArrayList<>();
    private List <Document> carList = new ArrayList<>();
    private List <Bike> bikeArrayList = new ArrayList<>();
    private List <Document> bikeList = new ArrayList<>();
    private List <Vehicle> vehicleArrayList = new ArrayList<>();
    private ConnectDB conn = new ConnectDB();
    private Scanner sc = new Scanner(System.in);

    public List<Document> vehicleDocument(){
        conn.db.getCollection("Car").find().into(carList);
        conn.db.getCollection("Bike").find().into(bikeList);

        List <Document> vehicleDocuments = new ArrayList<>();
        vehicleDocuments.addAll(carList);
        vehicleDocuments.addAll(bikeList);

        return vehicleDocuments;
    }

    //code to display menu
    public void displayMenu(){

        while (true){
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
                    break;

                case 6:
                    System.out.println("You have chosen to exit the program. \nThank you and have a nice day");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Incorrect choice entered. Try again.");
            }
        }
    }

    public List <Vehicle> getListOfVehicles(){
        vehicleArrayList.clear();
        conn.db.getCollection("Car").find().into(carList);
        conn.db.getCollection("Bike").find().into(bikeList);

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
            System.out.println("There are no cars in the system.");
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
            //return vehicleArrayList;
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

        return vehiclesInParking<50;
    }

    public WestminsterRentalVehicleManager() {
    }

    public void addCar(Car car){
        getListOfVehicles().add(car);
    }

    public void addBike(Bike bike){
        getListOfVehicles().add(bike);
    }

    public void deleteCar(Car car){
        //carList().remove(car);
        getListOfVehicles().remove(car);

        int availableParkingLot = 50 - getListOfVehicles().size();
        System.out.println("You can add " + availableParkingLot + " vehicles to the Parking Lot");

    }

    public void deleteBike(Bike bike) {
        //bikeList().remove(bike);
        getListOfVehicles().remove(bike);

        int availableParkingLot = 50 - getListOfVehicles().size();
        System.out.println("You can add " + availableParkingLot + " vehicles to the Parking Lot");

    }

    @Override
    public void addVehicle() {
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
                                    check2 = true;
                            }
                        }

                        conn.datastore.save(bike);
                        getListOfVehicles().add(bike);
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
//        vehicleArrayList = getListOfVehicles();
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter the plate number of the vehicle: ");
        String toDelete = sc2.nextLine();
        boolean checkIfExisted = false;


        for (Vehicle vehicle : vehicleArrayList) {
            System.out.println(vehicleArrayList);
            if (vehicle.getPlate().equals(toDelete)) {
                vehicleArrayList.remove(vehicle);
                checkIfExisted = true;

                for (Document docVehicle : vehicleDocument()) {

                    if (docVehicle.getString("_id").equals(toDelete)) {
                        String type = docVehicle.getString("className");

                        String [] typeList = type.split("\\.");

                        String collectionName = typeList[typeList.length - 1];

                        System.out.println(type + " " + conn.db.getCollection(type).find());
                        conn.db.getCollection(collectionName).deleteOne(new Document("_id", toDelete));
                        break;
                    }
                    else {
                        System.out.println("There is no vehicles with " + toDelete + " plate number in the " +
                                "database");
                    }
                }
            }

            if (checkIfExisted) {
                System.out.println("The vehicle is deleted successfully");
                break;
            } else {
                System.out.println("The vehicle is not in the database.");
            }
        }
    }

    @Override
    public void viewVehicles() {

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
            for(Vehicle vehicle: getListOfVehicles()){
                if(vehicle.getPlate().contains(plate)){
                    System.out.println("There is a vehicle with the same plate. \nTry again");
                    break;

                }else{
                    return plate;

                }
            }
        }
    }

//    public void begin() throws IOException {
//        vehicleArrayList = getListOfVehicles();
//        displayMenu();
//    }

    public static void main(String[] args) {
        WestminsterRentalVehicleManager manager = new WestminsterRentalVehicleManager();
        manager.getListOfVehicles();
        System.out.println(manager.vehicleArrayList);
        manager.deleteVehicle();
    }
}
