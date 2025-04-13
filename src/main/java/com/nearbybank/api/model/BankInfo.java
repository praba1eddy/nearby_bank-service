package com.nearbybank.api.model;

public class BankInfo {
    private String name;
    private String address;
    private double distanceInMiles;

    public BankInfo() {
        // No-arg constructor
    }

    public BankInfo(String name, String address, double distanceInMiles) {
        this.name = name;
        this.address = address;
        this.distanceInMiles = distanceInMiles;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistanceInMiles() {
        return distanceInMiles;
    }

    public void setDistanceInMiles(double distanceInMiles) {
        this.distanceInMiles = distanceInMiles;
    }
}
