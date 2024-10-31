package com.pluralsight.assetManager;

public abstract class Asset {
    private final String description;
    private final String dateAcquired;
    public double originalCost;


    public Asset(String description, String dateAcquired, double originalCost) {
        this.description = description;
        this.dateAcquired = dateAcquired;
        this.originalCost = originalCost;
    }

    public String getDescription() {
        return description;
    }

    public String getDateAcquired() {
        return dateAcquired;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public double getValue() {
        return originalCost;
    }
}
