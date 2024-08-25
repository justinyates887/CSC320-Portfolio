package interfaces;

import models.Automobile;

import java.util.List;

public interface IInventoryService {
    void addVehicle(Automobile automobile);
    void removeVehicle(String make, String model);
    List<Automobile> viewVehicles();
    void printToFile(List<Automobile> automobiles, String filePath);
}
