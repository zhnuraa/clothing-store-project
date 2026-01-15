import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final ArrayList<ClothingItem> items = new ArrayList<ClothingItem>();
    private static final ArrayList<Customer> customers = new ArrayList<Customer>();
    private static final ArrayList<Order> orders = new ArrayList<Order>();

    public static void main(String[] args) {
        System.out.println("=== Clothing Store Management System (Week 4) ===");

        seedTestData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose option: ");

            switch (choice) {
                case 1:
                    addClothingItem();
                    break;
                case 2:
                    viewAllItems();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    viewAllCustomers();
                    break;
                case 5:
                    createOrder();
                    break;
                case 6:
                    addItemToOrder();
                    break;
                case 7:
                    viewAllOrders();
                    break;
                case 8:
                    completeOrder();
                    break;
                case 9:
                    cancelOrder();
                    break;
                case 10:
                    demonstratePolymorphism();
                    break;
                case 11:
                    demonstrateInstanceofCasting();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting... Bye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1) Add Clothing Item");
        System.out.println("2) View All Items");
        System.out.println("3) Add Customer");
        System.out.println("4) View All Customers");
        System.out.println("5) Create Order");
        System.out.println("6) Add Item to Order");
        System.out.println("7) View All Orders");
        System.out.println("8) Complete Order");
        System.out.println("9) Cancel Order");
        System.out.println("10) Demonstrate Polymorphism (Week 4)");
        System.out.println("11) Demonstrate instanceof + Casting (Week 4)");
        System.out.println("0) Exit");
    }

    // ---------------- ITEMS ----------------
    private static void addClothingItem() {
        System.out.println("\n--- Add Clothing Item ---");
        System.out.println("Choose item type:");
        System.out.println("1) Generic ClothingItem");
        System.out.println("2) Shirt (child of ClothingItem)");
        System.out.println("3) Pants (child of ClothingItem)");

        int typeChoice = readInt("Type: ");

        int id = readInt("Item ID (>=0): ");
        String name = readNonEmptyString("Name: ");
        String size = readNonEmptyString("Size (e.g., M, L, 34): ");
        double price = readDouble("Price (KZT, >=0): ");
        String brand = readNonEmptyString("Brand: ");
        int stock = readInt("Stock quantity (>=0): ");

        ClothingItem item;

        if (typeChoice == 2) {
            int sleeve = readInt("Sleeve type (1=SHORT, 2=LONG): ");
            Shirt.SleeveType st = (sleeve == 2) ? Shirt.SleeveType.LONG : Shirt.SleeveType.SHORT;
            String material = readNonEmptyString("Material (e.g., Cotton, Wool): ");
            item = new Shirt(id, name, size, price, brand, stock, st, material);

        } else if (typeChoice == 3) {
            int fit = readInt("Fit type (1=SLIM, 2=REGULAR, 3=OVERSIZED): ");
            Pants.FitType ft;
            if (fit == 1) ft = Pants.FitType.SLIM;
            else if (fit == 3) ft = Pants.FitType.OVERSIZED;
            else ft = Pants.FitType.REGULAR;

            int waist = readInt("Waist (e.g., 32): ");
            int inseam = readInt("Inseam (e.g., 32): ");
            String material = readNonEmptyString("Material (e.g., Denim): ");
            item = new Pants(id, name, size, price, brand, stock, ft, waist, inseam, material);

        } else {
            item = new ClothingItem(id, name, size, price, brand, stock);
        }

        items.add(item);
        System.out.println("Added: " + item.getDisplayInfo());
    }

    private static void viewAllItems() {
        System.out.println("\n--- All Items ---");
        if (items.isEmpty()) {
            System.out.println("No items yet.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i).getDisplayInfo());
        }
    }

    // ---------------- CUSTOMERS ----------------
    private static void addCustomer() {
        System.out.println("\n--- Add Customer ---");
        int id = readInt("Customer ID (>=0): ");
        String name = readNonEmptyString("Name: ");
        String preferredSize = readNonEmptyString("Preferred size: ");
        int points = readInt("Initial points (>=0, usually 0): ");

        Customer customer = new Customer(id, name, preferredSize, points);
        customers.add(customer);

        System.out.println("Added: " + customer);
    }

    private static void viewAllCustomers() {
        System.out.println("\n--- All Customers ---");
        if (customers.isEmpty()) {
            System.out.println("No customers yet.");
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println((i + 1) + ") " + c + " | VIP=" + c.isVIP());
        }
    }

    // ---------------- ORDERS ----------------
    private static void createOrder() {
        System.out.println("\n--- Create Order ---");
        int orderId = readInt("Order ID (>=0): ");
        int customerId = readInt("Customer ID: ");

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        Order order = new Order(orderId, customer);
        orders.add(order);
        System.out.println("Created: " + order);
    }

    private static void addItemToOrder() {
        System.out.println("\n--- Add Item To Order ---");
        int orderId = readInt("Order ID: ");
        Order order = findOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        int itemId = readInt("Item ID: ");
        ClothingItem item = findItemById(itemId);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        int qty = readInt("Quantity: ");
        boolean ok = order.addItem(item, qty);

        if (ok) {
            System.out.println("Added successfully.");
            System.out.println("Order now: " + order);
            System.out.println("Item stock now: " + item.getStockQuantity());
        } else {
            System.out.println("Failed to add item (check stock/status/inputs).");
        }
    }

    private static void viewAllOrders() {
        System.out.println("\n--- All Orders ---");
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

    private static void completeOrder() {
        System.out.println("\n--- Complete Order ---");
        int orderId = readInt("Order ID: ");
        Order order = findOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        order.complete();
        System.out.println("Updated: " + order);
    }

    private static void cancelOrder() {
        System.out.println("\n--- Cancel Order ---");
        int orderId = readInt("Order ID: ");
        Order order = findOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        order.cancel();
        System.out.println("Updated: " + order);
    }

    // ---------------- WEEK 4 DEMO ----------------
    private static void demonstratePolymorphism() {
        System.out.println("\n--- Polymorphism Demo ---");
        System.out.println("Same method calls, different behavior because of @Override.\n");

        for (int i = 0; i < items.size(); i++) {
            ClothingItem item = items.get(i);
            System.out.println("Item #" + (i + 1));
            System.out.println("  getType(): " + item.getType());
            System.out.println("  getCareInstructions(): " + item.getCareInstructions());
            System.out.println("  getDisplayInfo(): " + item.getDisplayInfo());
            System.out.println();
        }
    }

    private static void demonstrateInstanceofCasting() {
        System.out.println("\n--- instanceof + Casting Demo ---");
        int itemId = readInt("Enter itemId to inspect: ");

        ClothingItem item = findItemById(itemId);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.println("Selected: " + item.getDisplayInfo());

        if (item instanceof Shirt) {
            Shirt s = (Shirt) item;
            System.out.println("This is a Shirt. Calling Shirt-only method:");
            s.foldSleeves();

        } else if (item instanceof Pants) {
            Pants p = (Pants) item;
            System.out.println("This is Pants. Calling Pants-only method:");
            p.sizeAdvice();

        } else {
            System.out.println("This is a generic ClothingItem.");
        }
    }

    // ---------------- FIND HELPERS ----------------
    private static ClothingItem findItemById(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemId() == id) return items.get(i);
        }
        return null;
    }

    private static Customer findCustomerById(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == id) return customers.get(i);
        }
        return null;
    }

    private static Order findOrderById(int id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId() == id) return orders.get(i);
        }
        return null;
    }

    // ---------------- INPUT HELPERS ----------------
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line != null && !line.trim().isEmpty()) {
                return line.trim();
            }
            System.out.println("This value cannot be empty.");
        }
    }

    // ---------------- TEST DATA ----------------
    private static void seedTestData() {
        items.add(new ClothingItem(101, "Hoodie", "M", 42000.0, "Nike", 5));
        items.add(new Shirt(102, "Formal Shirt", "L", 26000.0, "Zara", 4,
                Shirt.SleeveType.LONG, "Cotton"));
        items.add(new Pants(103, "Jeans", "34", 24000.0, "Levi's", 2,
                Pants.FitType.REGULAR, 34, 32, "Denim"));

        customers.add(new Customer(5001, "Aruzhan Bek", "M", 90));
        customers.add(new Customer(5002, "Dias Nur", "L", 120));
    }
}
