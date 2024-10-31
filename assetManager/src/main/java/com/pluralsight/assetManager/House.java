package com.pluralsight.assetManager;

public class House extends Asset{
    private String address;
    //condition constants
    public static final int EXCELLENT = 1;
    public static final int GOOD = 2;
    public static final int FAIR = 3;
    public static final int POOR = 4;

    private int condition;
    private int squareFoot;
    private int lotSize;

    public House(String description, String dateAcquired, double originalCost, String address, int condition, int squareFoot, int lotSize) {
        super(description, dateAcquired, originalCost);
        this.address = address;
        setCondition(condition);
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
        if (condition < EXCELLENT || condition > POOR) {
            throw new IllegalArgumentException("Condition must be between 1 (Excellent) and 4 (Poor).");
        }
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    @Override
    public double getValue() {
        double houseValuePerSquareFoot = switch (condition) {
            case EXCELLENT -> // Excellent
                    190.00;
            case GOOD -> // Good
                    130.00;
            case FAIR -> // Fair
                    90.00;
            case POOR -> // Poor
                    80.00;
            default -> throw new IllegalStateException("Unexpected condition: " + condition);
        };

        // Determine value per square foot based on condition

        //  value of the house based on square footage
        double houseValue = houseValuePerSquareFoot * squareFoot;

        //  additional lot size value
        double lotValue = 0.25 * lotSize; // 25 cents per square foot of lot size

        // Total value is the sum of house value and lot value
        return houseValue + lotValue;
    }
}
