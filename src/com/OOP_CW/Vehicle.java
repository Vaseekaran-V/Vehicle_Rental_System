package com.OOP_CW;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;

import java.util.Objects;

@Entity
public abstract class Vehicle implements Comparable<Vehicle> {
    @Id
    private String plate;

    private String make;
    private String model;
    private double ratePerDay;

    public Vehicle() {
    }

    public Vehicle(String plate, String make, String model, double ratePerDay) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.ratePerDay = ratePerDay;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getRatePerDay() {
        return ratePerDay;
    }

    public void setRatePerDay(double ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    @Override
    public String toString() {
        return make +" "+ model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.ratePerDay, ratePerDay) == 0 &&
                Objects.equals(plate, vehicle.plate) &&
                Objects.equals(make, vehicle.make) &&
                Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plate, make, model, ratePerDay);
    }


    /*@Override
    public int compareTo(Vehicle vehicle) {
        return this.getMake().compareTo(vehicle.getMake());
    }*/
}
