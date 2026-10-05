package com.campusgreen.model;

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int studentId;
    private int slotId;
    private double distanceKm;
    private double co2SavedKg;
    private Timestamp bookedAt;

    // For joining with VehicleSlot in history
    private String vehicleType;
    private String route;
    private Timestamp slotTime;

    public Booking() {
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public double getCo2SavedKg() {
        return co2SavedKg;
    }

    public void setCo2SavedKg(double co2SavedKg) {
        this.co2SavedKg = co2SavedKg;
    }

    public Timestamp getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(Timestamp bookedAt) {
        this.bookedAt = bookedAt;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Timestamp getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(Timestamp slotTime) {
        this.slotTime = slotTime;
    }
}
