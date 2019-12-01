package com.OOP_CW;

import com.vehicleRentalGUI.*;

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

    public List<Car> getCarArrayList() {
        return carArrayList;
    }

    public void setCarArrayList(List<Car> carArrayList) {
        this.carArrayList = carArrayList;
    }

    public List<Document> getCarList() {
        return carList;
    }

    public void setCarList(List<Document> carList) {
        this.carList = carList;
    }

    public List<Bike> getBikeArrayList() {
        return bikeArrayList;
    }

    public void setBikeArrayList(List<Bike> bikeArrayList) {
        this.bikeArrayList = bikeArrayList;
    }

    public List<Document> getBikeList() {
        return bikeList;
    }

    public void setBikeList(List<Document> bikeList) {
        this.bikeList = bikeList;
    }

    public List<Vehicle> getVehicleArrayList() {
        return vehicleArrayList;
    }

    public void setVehicleArrayList(List<Vehicle> vehicleArrayList) {
        this.vehicleArrayList = vehicleArrayList;
    }

    private Scanner sc = new Scanner(System.in);
    private List <String> addedPlates = new ArrayList<>();

    public List<Document> vehicleDocument(){
        conn.db.getCollection("Car").find().into(carList);
        conn.db.getCollection("Bike").find().into(bikeList);

        List <Document> vehicleDocuments = new ArrayList<>();
        vehicleDocuments.addAll(carList);
        vehicleDocuments.addAll(bikeList);

        return vehicleDocuments;
    }

    //get all the plates of te vehicles in the database
    public List <String> getPlates(){
        addedPlates.clear();
        for(Vehicle vehicle: vehicleArrayList){
            String plate = vehicle.getPlate();
            addedPlates.add(plate);
        }
        return addedPlates;
    }

    //check if there is an existing plate of the vehicle
    public Boolean checkPlate(String plate){
        for(String str : getPlates()){
            if(str.equals(plate)){
                System.out.println("The plate is already present in the system.");
                return true;
            }
        }
        return false;
    }

    //code to display menu
    public void displayMenu(){
        Scanner menuChoice = new Scanner(System.in);

        while (true){
            System.out.println("<----------MENU---------->");
            System.out.println("1. Add a Vehicle. \n2. View all vehicles. \n3. Delete Vehicle. \n4. Save Vehicle. \n" +
                    "5. Open GUI. \n6. Exit Program");
            System.out.println("**** ENTER THE NUMBER OF YOUR CHOICE ****");
            while (!menuChoice.hasNextInt()) {
                System.out.println("Incorrect input.\n Choose an input from 1 to 6");
                menuChoice.next();

            }
            int choice = menuChoice.nextInt();

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
                    String[] args = new String[0];
                    Main.main(args);
//                    Main.main(args);
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


    public void getListOfVehicles(){
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

    }

    public boolean checkParkingAvailable(){
        int vehiclesInParking = vehicleArrayList.size();

        return vehiclesInParking < 50;
    }

    public void getParkingSpace(){
        int remainingSpace = 50 - vehicleArrayList.size();
        System.out.println("There are " + remainingSpace + " parking spaces available.");
    }

    public WestminsterRentalVehicleManager() {
    }

    public void addCar(Car car){
        vehicleArrayList.add(car);
        carArrayList.add(car);
    }

    public void addBike(Bike bike){
        vehicleArrayList.add(bike);
        bikeArrayList.add(bike);
    }

    public void removeVehicle(Vehicle vehicle){
        vehicleArrayList.remove(vehicle);
        carArrayList.remove(vehicle);
        bikeArrayList.remove(vehicle);

        String deletedPlate = vehicle.getPlate();
        getPlates().remove(deletedPlate);

        getParkingSpace();
    }

    @Override
    public void addVehicle() {
        Scanner sc1 = new Scanner(System.in);

        if(checkParkingAvailable()) {
            getParkingSpace();

            System.out.println("Enter the plate: ");
            String plate = sc1.nextLine();
            while (checkPlate(plate)) {
                System.out.println("The plate is already there. Try again.");
                plate = sc1.nextLine();
            }

            System.out.println("Enter the make of the vehicle:");
            String make = sc1.nextLine();

            System.out.println("Enter the model of the vehicle:");
            String model = sc1.nextLine();

            System.out.println("Enter the cost per day of renting the vehicle:");
            while (!sc1.hasNextDouble()) {
                System.out.println("Incorrect type of input. Enter the daily rate of the vehicle with the correct " +
                        "data type.");
                sc1.next();
            }
            double ratePerDay = sc1.nextDouble();

            System.out.println("Enter the type of Vehicle: \n1. Bike. \n2.Car");

            while (!sc1.hasNextInt()) {
                System.out.println("Incorrect input.\n Choose an input from 1 to 6");
                sc1.next();
            }
            int choiceVehicle = sc1.nextInt();
            boolean bigCheck = true;
            Scanner sc_2 = new Scanner(System.in);
            while (bigCheck) {

                switch (choiceVehicle) {
                    case 1:
                        System.out.println("You have chosen to add a Bike");

                        System.out.println("Enter the type of Bike: ");
                        String bikeType = sc_2.nextLine();

                        //Initializing a Bike object
                        Bike bike = new Bike(plate, make, model, ratePerDay, bikeType);

                        System.out.println("Enter if the bike comes with a helmet: (1 if Yes; 2 if No");

                        while (!sc_2.hasNextInt()) {
                            System.out.println("Incorrect type of input.");
                            sc_2.next();
                        }
                        int isHelmet = sc_2.nextInt();
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
                        while (!sc_2.hasNextInt()) {
                            System.out.println("Incorrect type of input.");
                            sc_2.next();
                        }
                        int isJacket = sc_2.nextInt();
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
                        addBike(bike);
                        System.out.println("The bike with the plate " + bike.getPlate() + " is added successfully");
                        bigCheck = false;
                        break;

                    case 2:
                        System.out.println("You have chosen to add a car");

                        System.out.println("Enter the number of doors in the car: ");
                        while (!sc_2.hasNextInt()) {
                            System.out.println("Incorrect type. Enter the number of doors in the car");
                            sc_2.next();
                        }
                        int numOfDoors = sc_2.nextInt();

                        System.out.println("Enter the number of luggage the car can carry: ");
                        while (!sc_2.hasNextInt()) {
                            System.out.println("Incorrect type. Enter the number of luggage that can be in the car");
                            sc_2.next();
                        }
                        int numOfLuggage = sc_2.nextInt();

                        System.out.println("Enter the number of passengers that can be in the car: ");
                        while (!sc_2.hasNextInt()) {
                            System.out.println("Incorrect type. Enter the number of passengers that can be in the car");
                            sc_2.next();
                        }
                        int numOfPassengers = sc_2.nextInt();

                        //instantiating a new car object
                        Car car = new Car(plate, make, model, ratePerDay, numOfDoors, numOfLuggage, numOfPassengers);

                        check2 = true;
                        while (check2) {
                            System.out.println("Does the car has Air Conditioning \nPress 1 if \"yes\"\nPress 2 if" +
                                    " \"no\"");
                            while (!sc_2.hasNextInt()) {
                                System.out.println("Incorrect input. \nDoes the car has A/C? \nPress 1 if \"yes\"\nPress" +
                                        " 2 if \"no\" ");
                                sc_2.next();
                            }
                            int checkAC = sc_2.nextInt();
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
                            while (!sc_2.hasNextInt()) {
                                System.out.println("Incorrect input. \nDoes the car has Music Player? \nPress 1 if " +
                                        "\"yes\"\nPress 2 if \"no\" ");
                                sc_2.next();
                            }
                            int checkMusicPlayer = sc_2.nextInt();
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
                        System.out.println("The car with the plate number " + car.getPlate() + " is added " +
                                "successfully");
                        bigCheck = false;
                        break;

                    default:
                        System.out.println("Incorrect choice. Please try again...");
                        break;
                }
            }
        } else{
            System.out.println("The parking is full with 50 vehicles. \n Further vehicles cannot be added");
        }
    }

    @Override
    public void deleteVehicle() {
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter the plate number of the vehicle: ");
        String toDelete = sc2.nextLine();
        int sizeBeforeDelete = vehicleArrayList.size();

        for (Vehicle vehicle : vehicleArrayList) {
            if (vehicle.getPlate().equals(toDelete)) {
                removeVehicle(vehicle);

                for (Document docVehicle : vehicleDocument()) {

                    if (docVehicle.getString("_id").equals(toDelete)) {
                        String type = docVehicle.getString("className");

                        //to obtain the collection name of the vehicle with the number plate, in the database
                        String [] typeList = type.split("\\.");
                        String collectionName = typeList[typeList.length - 1];

                        conn.db.getCollection(collectionName).deleteOne(new Document("_id", toDelete));
                        break;
                    }
                }
                System.out.println("The vehicle with the number plate " + toDelete + " is successfully deleted");
                break;
            }
        }

        if(vehicleArrayList.size() == sizeBeforeDelete){
            System.out.println("There are no vehicles with the number plate " + toDelete + " in the system.");
        }
    }

    @Override
    public void viewVehicles() {
        if(vehicleArrayList.size() != 0) {
            displayTable();
        }else{
            System.out.println(" ********* There are no vehicles added to the system ********* ");
        }
    }

    public void displayTable(){
        String leftAlignFormat = "|  %12s  | %13s  |  %13s  |%n";
        System.out.format("+----------------+----------------+-----------------+%n");
        System.out.format("|----------------|----VEHICLES----|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|                       CARS                        |%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|  Plate Number  |      Make      |        Model    |%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");

        for(Car car: carArrayList){
            System.out.format(leftAlignFormat,car.getPlate(),car.getMake(),car.getModel());
            System.out.format("|----------------|----------------|-----------------|%n");
        }
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|                      BIKES                        |%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|  Plate Number  |      Make      |        Model    |%n");
        System.out.format("|----------------|----------------|-----------------|%n");
        System.out.format("|----------------|----------------|-----------------|%n");

        for(Bike bike: bikeArrayList ){
            System.out.format(leftAlignFormat,bike.getPlate(),bike.getMake(),bike.getModel());
            System.out.format("|----------------|----------------|-----------------|%n");
        }

        System.out.format("+----------------|----------------|-----------------+%n");
        System.out.format("+----------------+----------------+-----------------+%n");

    }

    @Override
    public void saveVehicle() {

    }

    public void begin() throws IOException {
        getListOfVehicles();
        displayMenu();
    }

    public static void main(String[] args) throws IOException {
        WestminsterRentalVehicleManager manager = new WestminsterRentalVehicleManager();
        manager.begin();
    }
}