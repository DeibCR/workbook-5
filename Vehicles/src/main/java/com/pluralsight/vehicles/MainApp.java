package com.pluralsight.vehicles;

public class MainApp {
    public static void main(String[] args) {
        Moped slowRide = new Moped("yellow",2,1,2);
       // slowRide.setColor("Red");
        //slowRide.setFuelCapacity(5);

        Car suv = new Car("gray",4,3,10);
        //suv.setColor("Gray");
       // suv.setFuelCapacity(10);
        System.out.println(suv);
    }
}
