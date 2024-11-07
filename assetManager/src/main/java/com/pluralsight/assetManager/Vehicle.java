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
        int age = currentYear - year;

        double value = getOriginalCost();


        if (age >= 0 && age <= 3) {
            value -= (originalCost * 0.03 * age);
        } else if (age >= 4 && age <= 6) {
            value -= (originalCost * 0.06 * age);
        } else if (age >= 7 && age <= 10) {
            value -= (originalCost * 0.08 * age);
        } else if (age > 10) {
            value = 1000.00;
        }


        if (odometer > 100000) {

            if (!(makeModel.toLowerCase().contains("honda") || makeModel.toLowerCase().contains("toyota"))) {
                value *= 0.75;
            }
        }


        return Math.max(value, 0);
    }
}
