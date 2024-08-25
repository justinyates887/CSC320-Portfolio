package services;

import interfaces.IInventoryService;
import models.Automobile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryService implements IInventoryService {
    private List<Automobile> inventory = new ArrayList<>();

    @Override
    public void addVehicle(Automobile automobile) {
        inventory.add(automobile);
        System.out.println("Vehicle added successfully.");
    }

    @Override
    public void removeVehicle(String make, String model) {
        inventory.removeIf(vehicle -> vehicle.getMake().equalsIgnoreCase(make)
                && vehicle.getModel().equalsIgnoreCase(model));
        System.out.println("Vehicle removed successfully.");
    }

    @Override
    public List<Automobile> viewVehicles() {
        return inventory;
    }

    @Override
    public void printToFile(List<Automobile> automobiles, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Make,Model,Color,Year,Mileage\n");
            for (Automobile automobile : automobiles) {
                writer.write(String.format("%s,%s,%s,%d,%d\n",
                        automobile.getMake(),
                        automobile.getModel(),
                        automobile.getColor(),
                        automobile.getYear(),
                        automobile.getMileage()));
            }
            System.out.println("Information printed to file successfully.");
        } catch (IOException ex) {
            System.out.println("Error printing to file: " + ex.getMessage());
        }
    }
}