package services;

import interfaces.IInventoryService;
import interfaces.IMenuService;
import models.Automobile;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MenuService implements IMenuService {
    private final IInventoryService inventoryService;
    private final Scanner scanner;

    public MenuService(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("Menu Options:");
        System.out.println("1. View Vehicles");
        System.out.println("2. Add Vehicle");
        System.out.println("3. Remove Vehicle");
        System.out.println("4. Print to file (CSV)");
        System.out.println("0. END");
    }

    @Override
    public void handleMenuSelection(int choice) {
        switch (choice) {
            case 1:
                viewVehicles();
                break;
            case 2:
                addVehicle();
                break;
            case 3:
                removeVehicle();
                break;
            case 4:
                printToFile();
                break;
            case 0:
                System.out.println("Ending program...");
                break;
            default:
                System.out.println("Invalid choice, please select again.");
                break;
        }
    }

    private void viewVehicles() {
        List<Automobile> vehicles = inventoryService.viewVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in inventory.");
        } else {
            for (Automobile vehicle : vehicles) {
                for (String info : vehicle.listVehicle()) {
                    System.out.println(info);
                }
                System.out.println();
            }
        }
    }

    private void addVehicle() {
        System.out.println("Enter make:");
        String make = scanner.nextLine();
        System.out.println("Enter model:");
        String model = scanner.nextLine();
        System.out.println("Enter color:");
        String color = scanner.nextLine();
        System.out.println("Enter year:");
        int year = scanner.nextInt();
        System.out.println("Enter mileage:");
        int mileage = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Automobile automobile = new Automobile(make, model, color, year, mileage);
        inventoryService.addVehicle(automobile);
    }

    private void removeVehicle() {
        List<Automobile> vehicles = inventoryService.viewVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in inventory.");
            return;
        }

        System.out.println("Select a vehicle to remove:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i).listVehicle()[0] + " " + vehicles.get(i).listVehicle()[1]);
        }

        int index = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Enter the number corresponding to the vehicle you want to remove:");
            if (scanner.hasNextInt()) {
                index = scanner.nextInt() - 1;
                scanner.nextLine();

                if (index >= 0 && index < vehicles.size()) {
                    validInput = true;
                } else {
                    System.out.println("Invalid number. Please enter a number between 1 and " + vehicles.size());
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        
        Automobile removedVehicle = vehicles.get(index);
        inventoryService.removeVehicle(removedVehicle.getMake(), removedVehicle.getModel());
        System.out.println("Vehicle removed: " + removedVehicle.getMake() + " " + removedVehicle.getModel());
    }

    private void printToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save to file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }

            inventoryService.printToFile(inventoryService.viewVehicles(), filePath);
            System.out.println("File saved to: " + filePath);
        } else {
            System.out.println("Save operation canceled.");
        }
    }
}
