package dealershipApp;

import java.util.Scanner;

public class UserInterface {
    private final DealershipFileManager fileManager;
    private Dealership dealership;
    private final Scanner scanner;

    public UserInterface() {
        fileManager = new DealershipFileManager();
        scanner = new Scanner(System.in);
    }

    private void init() {
        dealership = fileManager.getDealership("./src/main/resources/inventory.csv", "D & B Used Cars", "111 Old Benbrook Rd", "817-555-5555");
    }

    public void display() {
        init();

        boolean exit = false;
        while (!exit) {


            displayMenu();
            int input = getUserInput();
            exit = processInput(input);
        }
    }

    private void displayMenu() {
        System.out.println("\n================================================");
        System.out.println("\n               Dealership Menu                  ");
        System.out.printf("%s-%s-%s\n%n", dealership.getName(), dealership.getAddress(), dealership.getPhone());
        System.out.println("\n================================================");
        System.out.printf("║ %-45s ║%n", "1. View all vehicles");
        System.out.printf("║ %-45s ║%n", "2. Add a vehicle");
        System.out.printf("║ %-45s ║%n", "3. Remove a vehicle");
        System.out.printf("║ %-45s ║%n", "4. Search vehicles by price");
        System.out.printf("║ %-45s ║%n", "5. Search vehicles by make and model");
        System.out.printf("║ %-45s ║%n", "6. Search vehicles by color");
        System.out.printf("║ %-45s ║%n", "7. Search vehicles by type");
        System.out.printf("║ %-45s ║%n", "8. Search vehicles by mileage");
        System.out.printf("║ %-45s ║%n", "9. Search vehicles by year");
        System.out.printf("║ %-45s ║%n", "10. Exit");
        System.out.print("Please type an option to continue:");
    }

    private int getUserInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 10.");
            return -1;
        }
    }


    private boolean processInput(int input) {
        switch (input) {
            case 1 -> {
                getAllVehicles();
                return false;
            }
            case 2 -> {
                addVehicleRequest();
                return false;
            }
            case 3 -> {
                removeVehicle();
                return false;
            }
            case 4 -> {
                searchByPrice();
                return false;
            }
            case 5 -> {
                searchByMakeAndModel();
                return false;
            }
            case 6 -> {
                searchByColor();
                return false;
            }
            case 7 -> {
                searchByType();
                return false;
            }
            case 8 -> {
                searchByMileage();
                return false;
            }
            case 9 -> {
                searchByYear();
                return false;
            }
            case 10 -> {
                System.out.println("Exiting application. Goodbye!");
                return true;
            }
            default -> {
                System.out.println("Invalid choice. Please try again.");
                return false;
            }
        }
    }

    public void getAllVehicles() {
        headerDisplay();
        dealership.getAllVehicles().forEach(System.out::println);
    }

    private void addVehicleRequest() {
        try {
            int id = promptForInt("Enter vehicle VIN: ");
            int year = promptForInt("Enter vehicle year: ");
            String make = promptForString("Enter vehicle make: ");
            String model = promptForString("Enter vehicle model: ");
            String type = promptForString("Enter vehicle type: ");
            String color = promptForString("Enter vehicle color: ");
            int mileage = promptForInt("Enter vehicle mileage: ");
            double price = promptForDouble("Enter vehicle price: ");

            Vehicle newVehicle = new Vehicle(id, year, make, model, type, color, mileage, price);
            dealership.addVehicle(newVehicle);
            System.out.println("A new vehicle has been added.");
            saveDealershipData();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter the correct data types.");
        }
    }


    private void removeVehicle() {
        try {
            int vin = promptForInt("Enter a vehicle VIN to remove: ");
            Vehicle vehicleToRemove = dealership.getAllVehicles().stream()
                    .filter(vehicle -> vehicle.getVin() == vin)
                    .findFirst()
                    .orElse(null);

            if (vehicleToRemove != null) {
                dealership.removeVehicle(vehicleToRemove);
                System.out.println("Vehicle with VIN " + vin + " has been removed.");
                saveDealershipData();
            } else {
                System.out.println("Vehicle not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid VIN. Please enter a valid number.");
        }
    }

    private void searchByPrice() {
        double minPrice = promptForDouble("Enter a minimum price: ");
        double maxPrice = promptForDouble("Enter a maximum price: ");
        headerDisplay();
        dealership.getVehiclesByPrice(minPrice, maxPrice).forEach(System.out::println);
    }


    private void searchByMakeAndModel() {
        String make=promptForString("Enter a vehicle make: ");
        String model=promptForString("Enter a vehicle model: ");
        headerDisplay();
        dealership.getVehiclesByMakeModel(make, model).forEach(System.out::println);
    }

    private void searchByColor() {
        String color=promptForString("Enter a vehicle color: ");
        headerDisplay();
        dealership.getVehiclesByColor(color).forEach(System.out::println);
    }

    private void searchByType() {
       String type=promptForString("Enter a vehicle type: ");
        headerDisplay();
        dealership.getVehiclesByType(type).forEach(System.out::println);
    }

    private void searchByMileage() {
        int minMileage=promptForInt("Enter a minimum mileage: ");
        int maxMileage=promptForInt("Enter a maximum mileage: ");
        headerDisplay();
        dealership.getVehiclesByMileage(minMileage, maxMileage).forEach(System.out::println);

    }

    private void searchByYear() {
        int year=promptForInt("Enter the year of the vehicles to search for: ");
        headerDisplay();
        dealership.getVehiclesByYear(year).forEach(System.out::println);
    }

    //helpers methods to clean the code:

    public void headerDisplay() {
        System.out.println("""
                --------------------------------------------------------------------------------------------
                                              All Vehicles
                Vin      Year     Make        Model        Type      Color     Mileage     Price
                --------------------------------------------------------------------------------------------
                """);
    }

    private int promptForInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    private String promptForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private double promptForDouble(String message) {
        System.out.print(message);
        return Double.parseDouble(scanner.nextLine());
    }

    private void saveDealershipData() {
        fileManager.saveDealership(dealership, "./src/main/resources/inventory.csv");
    }


}
