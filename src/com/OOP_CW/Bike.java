package com.OOP_CW;

import com.github.jmkgreen.morphia.annotations.Entity;

import java.util.Objects;

@Entity
public class Bike extends Vehicle {
    private String type; //whether it is a scooter, racing bike, or general
    private boolean helmet;
    private boolean jacket;
    //private String vehicleType = "Bike";


    public Bike() {
    }

    public Bike(String plate, String make, String model, double ratePerDay, String type, boolean helmet,
                boolean jacket) {
        super(plate, make, model, ratePerDay);
        this.type = type;
        this.helmet = helmet;
        this.jacket = jacket;
    }

    public Bike(String plate, String make, String model, double ratePerDay, String type) {
        super(plate, make, model, ratePerDay);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHelmet() {
        return helmet;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public boolean isJacket() {
        return jacket;
    }

    public void setJacket(boolean jacket) {
        this.jacket = jacket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bike bike = (Bike) o;
        return helmet == bike.helmet &&
                jacket == bike.jacket &&
                Objects.equals(type, bike.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, helmet, jacket);
    }

    @Override
    public int compareTo(Vehicle o) {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
