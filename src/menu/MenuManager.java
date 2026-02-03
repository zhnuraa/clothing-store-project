package menu;

import model.*;
import exception.InvalidInputException;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);

    private final ArrayList<ClothingItem> items = new ArrayList<ClothingItem>();
    private final ArrayList<Customer> customers = new ArrayList<Customer>();
    private final ArrayList<Order> orders = new ArrayList<Order>();

    @Override
    public void displayMenu() {
        System.out.println("\n===============================");
        System.out.println(" CLOTHING STORE SYSTEM");
        System.out.println("===============================");
        System.out.println("1) Add Shirt");
        System.out.println("2) Add Pants");
        System.out.println("3) View All Items");
        System.out.println("4) Add Customer");
        System.out.println("5) View All Customers");
        System.out.println("6) Create Order");
        System.out.println("7) Add Item To Order");
        System.out.println("8) View All Orders");
        System.out.println("9) Complete Order");
        System.out.println("10) Cancel Order");
        System.out.println("0) Exit");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        seedTestData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readIntLine();

            switch (choice) {
                case 1: addShirt(); break;
                case 2: addPants(); break;
                case 3: viewAllItems(); break;
                case 4: addCustomer(); break;
                case 5: viewAllCustomers(); break;
                case 6: createOrder(); break;
                case 7: addItemToOrder(); break;
                case 8: viewAllOrders(); break;
                case 9: completeOrder(); break;
                case 10: cancelOrder(); break;
                case 0:
                    running = false;
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    // ===================== ADD SHIRT =====================
    private void addShirt() {
        System.out.println("\n--- ADD SHIRT ---");
        try {
            int id = readInt("Item ID (>=0): ");
            String name = readNonEmpty("Name: ");
            String size = readNonEmpty("Size (M/L/XL...): ");
            double price = readDouble("Price (>=0): ");
            String brand = readNonEmpty("Brand: ");
            int stock = readInt("Stock (>=0): ");

            int sleeveChoice = readInt("Sleeve (1=SHORT, 2=LONG): ");
            Shirt.SleeveType sleeve = (sleeveChoice == 2) ? Shirt.SleeveType.LONG : Shirt.SleeveType.SHORT;

            String material = readNonEmpty("Material: ");

            Shirt shirt = new Shirt(id, name, size, price, brand, stock, sleeve, material);
            items.add(shirt);

            System.out.println("Added: " + shirt.getDisplayInfo());

        } catch (InvalidInputException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ===================== ADD PANTS =====================
    private void addPants() {
        System.out.println("\n--- ADD PANTS ---");
        try {
            int id = readInt("Item ID (>=0): ");
            String name = readNonEmpty("Name: ");
            String size = readNonEmpty("Size (e.g., 34): ");
            double price = readDouble("Price (>=0): ");
            String brand = readNonEmpty("Brand: ");
            int stock = readInt("Stock (>=0): ");

            int fitChoice = readInt("Fit (1=SLIM, 2=REGULAR, 3=OVERSIZED): ");
            Pants.FitType fit;
            if (fitChoice == 1) fit = Pants.FitType.SLIM;
            else if (fitChoice == 3) fit = Pants.FitType.OVERSIZED;
            else fit = Pants.FitType.REGULAR;

            int waist = readInt("Waist (>0): ");
            int inseam = readInt("Inseam (>0): ");
            String material = readNonEmpty("Material: ");

            Pants pants = new Pants(id, name, size, price, brand, stock, fit, waist, inseam, material);
            items.add(pants);

            System.out.println("Added: " + pants.getDisplayInfo());

        } catch (InvalidInputException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllItems() {
        System.out.println("\n--- ALL ITEMS ---");
        if (items.isEmpty()) {
            System.out.println("No items yet.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            ClothingItem item = items.get(i);
            System.out.println((i + 1) + ") " + item.getDisplayInfo() +
                    " | premium=" + item.isPremium() +
                    " | inStock=" + item.isInStock());
        }
    }

    // ===================== CUSTOMERS =====================
    private void addCustomer() {
        System.out.println("\n--- ADD CUSTOMER ---");
        try {
            int id = readInt("Customer ID (>=0): ");
            String name = readNonEmpty("Name: ");
            String pref = readNonEmpty("Preferred size: ");
            int points = readInt("Points (>=0): ");

            Customer c = new Customer(id, name, pref, points);
            customers.add(c);
            System.out.println("Added: " + c.getProfile());

        } catch (InvalidInputException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllCustomers() {
        System.out.println("\n--- ALL CUSTOMERS ---");
        if (customers.isEmpty()) {
            System.out.println("No customers yet.");
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println((i + 1) + ") " + c.getProfile());
        }
    }

    // ===================== ORDERS =====================
    private void createOrder() {
        System.out.println("\n--- CREATE ORDER ---");
        try {
            int orderId = readInt("Order ID (>=0): ");
            int customerId = readInt("Customer ID: ");

            Customer customer = findCustomer(customerId);
            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            Order order = new Order(orderId, customer);
            orders.add(order);
            System.out.println("Created: " + order);

        } catch (InvalidInputException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addItemToOrder() {
        System.out.println("\n--- ADD ITEM TO ORDER ---");
        try {
            int orderId = readInt("Order ID: ");
            Order order = findOrder(orderId);
            if (order == null) {
                System.out.println("Order not found.");
                return;
            }

            int itemId = readInt("Item ID: ");
            ClothingItem item = findItem(itemId);
            if (item == null) {
                System.out.println("Item not found.");
                return;
            }

            int qty = readInt("Quantity (>0): ");

            // Order.addItem может бросить InvalidInputException/IllegalStateException
            order.addItem(item, qty);

            System.out.println("Added. Order: " + order);
            System.out.println("Item stock now: " + item.getStockQuantity());

        } catch (InvalidInputException | IllegalStateException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllOrders() {
        System.out.println("\n--- ALL ORDERS ---");
        if (orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            System.out.println((i + 1) + ") " + o);
            o.printLines();
        }
    }

    private void completeOrder() {
        System.out.println("\n--- COMPLETE ORDER ---");
        try {
            int orderId = readInt("Order ID: ");
            Order order = findOrder(orderId);
            if (order == null) {
                System.out.println("Order not found.");
                return;
            }
            order.complete();
            System.out.println("Updated: " + order);

        } catch (IllegalStateException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cancelOrder() {
        System.out.println("\n--- CANCEL ORDER ---");
        try {
            int orderId = readInt("Order ID: ");
            Order order = findOrder(orderId);
            if (order == null) {
                System.out.println("Order not found.");
                return;
            }
            order.cancel();
            System.out.println("Updated: " + order);

        } catch (IllegalStateException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ===================== FINDERS =====================
    private ClothingItem findItem(int itemId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemId() == itemId) return items.get(i);
        }
        return null;
    }

    private Customer findCustomer(int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == customerId) return customers.get(i);
        }
        return null;
    }

    private Order findOrder(int orderId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId() == orderId) return orders.get(i);
        }
        return null;
    }

    // ===================== INPUT HELPERS =====================
    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine().trim());
    }

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
            System.out.println("Value cannot be empty.");
        }
    }

    private int readIntLine() {
        String s = scanner.nextLine().trim();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ===================== TEST DATA =====================
    private void seedTestData() {
        // Items
        items.add(new Shirt(101, "Formal Shirt", "L", 26000, "Zara", 5, Shirt.SleeveType.LONG, "Cotton"));
        items.add(new Pants(102, "Jeans", "34", 24000, "Levis", 3, Pants.FitType.REGULAR, 34, 32, "Denim"));

        // Customers
        customers.add(new Customer(5001, "Aruzhan", "M", 90));
        customers.add(new Customer(5002, "Dias", "L", 120));

        // Orders empty by default
    }
}
