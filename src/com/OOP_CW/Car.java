package com.OOP_CW;

import com.github.jmkgreen.morphia.annotations.Entity;

import java.util.Objects;

@Entity
public class Car extends Vehicle{
    private int noOfDoors;
    private int numOfLuggage;
    private int numOfPassengers;
    private boolean airCon;
    private boolean musicPlayer;
//    private String vehicleType = "Car";

    public Car() {
    }

    public Car(String plate, String make, String model, double ratePerDay, int noOfDoors, int numOfLuggage,
               int numOfPassengers, boolean airCon, boolean musicPlayer) {
        super(plate, make, model, ratePerDay);
        this.noOfDoors = noOfDoors;
        this.numOfLuggage = numOfLuggage;
        this.numOfPassengers = numOfPassengers;
        this.airCon = airCon;
        this.musicPlayer = musicPlayer;
        //this.vehicleType = vehicleType;
    }

    public Car(String plate, String make, String model, double ratePerDay, int noOfDoors, int numOfLuggage,
               int numOfPassengers) {
        super(plate, make, model, ratePerDay);
        this.noOfDoors = noOfDoors;
        this.numOfLuggage = numOfLuggage;
        this.numOfPassengers = numOfPassengers;
       // this.vehicleType = vehicleType;
    }

    public int getNoOfDoors() {
        return noOfDoors;
    }

    public void setNoOfDoors(int noOfDoors) {
        this.noOfDoors = noOfDoors;
    }

    public int getNumOfLuggage() {
        return numOfLuggage;
    }

    public void setNumOfLuggage(int numOfLuggage) {
        this.numOfLuggage = numOfLuggage;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public boolean isAirCon() {
        return airCon;
    }

    public void setAirCon(boolean airCon) {
        this.airCon = airCon;
    }

    public boolean isMusicPlayer() {
        return musicPlayer;
    }

    public void setMusicPlayer(boolean musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

//    public String getVehicleType() {
//        return vehicleType;
//    }

    @Override
    public String toString() {
        return "Car{} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return noOfDoors == car.noOfDoors &&
                numOfLuggage == car.numOfLuggage &&
                numOfPassengers == car.numOfPassengers &&
                airCon == car.airCon &&
                musicPlayer == car.musicPlayer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), noOfDoors, numOfLuggage, numOfPassengers, airCon, musicPlayer);
    }

    @Override
    public int compareTo(Vehicle o) {
        return 0;
    }
}
