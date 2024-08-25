import interfaces.IInventoryService;
import interfaces.IMenuService;
import services.InventoryService;
import services.MenuService;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        IInventoryService inventoryService = new InventoryService();
        IMenuService menuService = new MenuService(inventoryService);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            menuService.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                running = false;
            }

            menuService.handleMenuSelection(choice);
        }

        scanner.close();
    }
}