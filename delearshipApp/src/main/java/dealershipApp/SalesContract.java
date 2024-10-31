package dealershipApp;

import java.util.ResourceBundle;

public class SalesContract extends Contract{
    private  final double salesTax;
    private final double recordingFee;
    private final double processingFee;
    private final boolean isFinance;
    private static final ResourceBundle resourceBundle= ResourceBundle.getBundle("contract_config");// Using resource bundle to provide flexibility and maintainability

    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, double salesTax, double recordingFee, double processingFee, boolean isFinance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTax = Double.parseDouble(resourceBundle.getString("sales.tax"));
        this.recordingFee = Double.parseDouble(resourceBundle.getString("recording.fee"));
        this.processingFee = vehicleSold.getPrice()>10000 ? //the processingFee get his value depending on or not of the condition
                Double.parseDouble(resourceBundle.getString("processing.fee.under10000")) :
                Double.parseDouble(resourceBundle.getString("processing.fee.over10000"));

        this.isFinance = isFinance;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }


    public double getProcessingFee() {
        return processingFee;
    }


    public boolean isFinance() {
        return isFinance;
    }



    @Override
    public double getTotalPrice() {
        double basePrice= getVehicleSold().getPrice();
        double total= basePrice + ( basePrice * salesTax )+ recordingFee+processingFee;
        return total;





    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinance) {
            return 0; // No monthly payment if not financed
        }
        double interestRate = getVehicleSold().getPrice() >= 10000
                ? Double.parseDouble(resourceBundle.getString("monthly.payment.interest.over10000"))
                : Double.parseDouble(resourceBundle.getString("monthly.payment.interest.under10000"));
        int termMonths = getVehicleSold().getPrice() >= 10000
                ? Integer.parseInt(resourceBundle.getString("monthly.payment.term.over10000"))
                : Integer.parseInt(resourceBundle.getString("monthly.payment.term.under10000"));

        return (getVehicleSold().getPrice() * (1 + interestRate)) / termMonths; // Monthly payment formula for financing
    }


    @Override
    public String getRepresentation() {
        return String.format("SALE|%s|%s|%s|%d|%s|%d|%.2f|%.2f|%.2f|%.2f|%b",
                getDateOfContract(), getCustomerName(), getCustomerEmail(),
                getVehicleSold().getVin(), getVehicleSold().getMake(),
                getVehicleSold().getYear(), salesTax, recordingFee, processingFee, getTotalPrice(), isFinance);
    }
}
