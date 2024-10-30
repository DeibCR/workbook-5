package com.pluralsight.vehicles;

public class MainApp {
    public static void main(String[] args) {
        Moped slowRide = new Moped();
        slowRide.setColor("Red");
        slowRide.setFuelCapacity(5);

        Car suv = new Car();
        suv.setColor("Gray");
        suv.setFuelCapacity(10);
    }
}
