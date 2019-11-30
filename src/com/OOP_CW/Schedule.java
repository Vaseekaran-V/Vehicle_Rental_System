package com.OOP_CW;

import java.sql.Date;
import java.util.Objects;

public class Schedule {
    private String scheduleId;
    private Date pickUpDate;
    private Date returnDate;
    private Customer customer;
    private Vehicle vehicle;

    public Schedule() {
    }

    public Schedule(String scheduleId, Date pickUpDate, Date returnDate, Customer customer, Vehicle vehicle) {
        this.scheduleId = scheduleId;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
        this.customer = customer;
        this.vehicle = vehicle;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", returnDate=" + returnDate +
                ", customer=" + customer +
                ", vehicle=" + vehicle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(scheduleId, schedule.scheduleId) &&
                Objects.equals(pickUpDate, schedule.pickUpDate) &&
                Objects.equals(returnDate, schedule.returnDate) &&
                Objects.equals(customer, schedule.customer) &&
                Objects.equals(vehicle, schedule.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, pickUpDate, returnDate, customer, vehicle);
    }
}
