import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final ArrayList<ClothingItem> items = new ArrayList<>();
    private static final ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Clothing Store Management System (Week 3) ===");

        seedTestData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> addClothingItem();
                case 2 -> viewAllItems();
                case 3 -> addCustomer();
                case 4 -> viewAllCustomers();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting... Bye!");
                }
                default -> System.out.println("Invalid option. Try again.");
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
        System.out.println("0) Exit");
    }

    private static void addClothingItem() {
        System.out.println("\n--- Add Clothing Item ---");

        int id = readInt("Item ID (>=0): ");
        String name = readNonEmptyString("Name: ");
        String size = readNonEmptyString("Size (e.g., M, L, 34): ");
        double price = readDouble("Price (KZT, >=0): ");
        String brand = readNonEmptyString("Brand: ");
        int stock = readInt("Stock quantity (>=0): ");

        ClothingItem item = new ClothingItem(id, name, size, price, brand, stock);
        items.add(item);

        System.out.println("Added: " + item);
        System.out.println("Premium? " + item.isPremium() + " | In stock? " + item.isInStock());
    }

    private static void viewAllItems() {
        System.out.println("\n--- All Items ---");
        if (items.isEmpty()) {
            System.out.println("No items yet.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            ClothingItem item = items.get(i);
            System.out.println((i + 1) + ") " + item +
                    " | premium=" + item.isPremium() +
                    " | inStock=" + item.isInStock());
        }
    }

    private static void addCustomer() {
        System.out.println("\n--- Add Customer ---");

        int id = readInt("Customer ID (>=0): ");
        String name = readNonEmptyString("Name: ");
        String preferredSize = readNonEmptyString("Preferred size: ");

        int points = readInt("Initial points (>=0, usually 0): ");

        Customer customer = new Customer(id, name, preferredSize, points);
        customers.add(customer);

        System.out.println("Added: " + customer);
        System.out.println("VIP? " + customer.isVIP());
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
                System.out.println("Please enter a valid number (double).");
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

    private static void seedTestData() {
        items.add(new ClothingItem(101, "Hoodie", "M", 42000.0, "Nike", 5));
        items.add(new ClothingItem(102, "Jeans", "34", 24000.0, "Levi's", 2));

        customers.add(new Customer(5001, "Aruzhan Bek", "M", 90));
        customers.add(new Customer(5002, "Dias Nur", "L", 120));
    }
}
