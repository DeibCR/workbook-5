package com.pluralsight.assetManager;

import java.time.LocalDate;

public class Vehicle extends Asset{
    private String makeModel;
    private int year;
    private int odometer;

    public Vehicle(String description, String dateAcquired, double originalCost, String makeModel, int year, int odometer) {
        super(description, dateAcquired, originalCost);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    @Override
    public double getValue() {
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year; // Calculate the age of the vehicle

        double value = getOriginalCost(); // Start with the original cost

        // Apply depreciation based on age
        if (age >= 0 && age <= 3) {
            value -= (originalCost * 0.03 * age); // 3% per year for 0-3 years old
        } else if (age >= 4 && age <= 6) {
            value -= (originalCost * 0.06 * age); // 6% per year for 4-6 years old
        } else if (age >= 7 && age <= 10) {
            value -= (originalCost * 0.08 * age); // 8% per year for 7-10 years old
        } else if (age > 10) {
            value = 1000.00; // Set value to $1000 for vehicles older than 10 years
        }

        // Adjust value based on odometer readings
        if (odometer > 100000) {
            // Check if makeModel contains "Honda" or "Toyota"
            if (!(makeModel.toLowerCase().contains("honda") || makeModel.toLowerCase().contains("toyota"))) {
                value *= 0.75; // Reduce final value by 25% if over 100,000 miles
            }
        }

        // Ensure that value does not fall below zero
        return Math.max(value, 0);
    }
}
