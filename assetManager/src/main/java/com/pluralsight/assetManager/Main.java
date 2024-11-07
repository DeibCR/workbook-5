package com.pluralsight.assetManager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Asset> assets = new ArrayList<>();

        House vacationHouse = new House("Beautiful family home", "2021-05-15", 250000.00, "123 Elm St", 1, 2000, 5000);
        House deibHouse = new House("Cozy cottage", "2020-03-10", 180000.00, "456 Oak St", 2, 1500, 3000);


        Vehicle workCar = new Vehicle("Sedan with great mileage", "2022-06-01", 30000.00, "Ford Fusion", 2018, 105000);
        Vehicle deibCar = new Vehicle("Sporty coupe", "2023-01-20", 45000.00, "Chevrolet Camaro", 2020, 25000);


        assets.add(vacationHouse);
        assets.add(deibHouse);
        assets.add(workCar);
        assets.add(deibCar);

        for (Asset asset : assets) {
            System.out.println("Description: " + asset.getDescription());
            System.out.println("Date Acquired: " + asset.getDateAcquired());
            System.out.println("Original Cost: $" + asset.getOriginalCost());
            System.out.println("Current Value: $" + asset.getValue());


            if (asset instanceof House) {
                House house = (House) asset;
                System.out.println("Address: " + house.getAddress());
                System.out.println("---------------------------");
            } else if (asset instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) asset;
                System.out.println("Year and Make/Model: " + vehicle.getYear() + " " + vehicle.getMakeModel());
                System.out.println("---------------------------");
            }
        }


    }
}
