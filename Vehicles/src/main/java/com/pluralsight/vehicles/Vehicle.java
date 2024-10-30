package com.pluralsight.vehicles;

public class Vehicle {
    String color;
    int numberOfPassengers;
    int cargoCapacity;
    int fuelCapacity;


    public String getColor() {
        return color;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }
}
