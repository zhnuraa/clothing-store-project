package menu;

import database.ClothingItemDAO;
import exception.InvalidInputException;
import model.ClothingItem;
import model.Pants;
import model.Shirt;

import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ClothingItemDAO dao = new ClothingItemDAO();

    @Override
    public void displayMenu() {
        System.out.println("\n===============================");
        System.out.println(" CLOTHING STORE - Week 8 CRUD");
        System.out.println("===============================");
        System.out.println("1) Add Shirt (INSERT)");
        System.out.println("2) Add Pants (INSERT)");
        System.out.println("3) View All Items (SELECT)");
        System.out.println("4) Update Item (UPDATE)");
        System.out.println("5) Delete Item (DELETE, confirm)");
        System.out.println("6) Search by Name (ILIKE)");
        System.out.println("7) Search by Price Range (BETWEEN)");
        System.out.println("8) Search by Min Price (>=)");
        System.out.println("9) View Item by ID");
        System.out.println("10) Increase Stock by ID");
        System.out.println("0) Exit");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readIntLine();

            switch (choice) {
                case 1: addShirt(); break;
                case 2: addPants(); break;
                case 3: viewAll(); break;
                case 4: updateItem(); break;
                case 5: deleteItemSafe(); break;
                case 6: searchByName(); break;
                case 7: searchByRange(); break;
                case 8: searchByMin(); break;
                case 9: viewById(); break;
                case 10: increaseStockById(); break;
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

    // ---------- INSERT ----------
    private void addShirt() {
        System.out.println("\n--- ADD SHIRT ---");
        try {
            int id = readInt("Item ID (>=0): ");
            String name = readNonEmpty("Name: ");
            String size = readNonEmpty("Size: ");
            double price = readDouble("Price: ");
            String brand = readNonEmpty("Brand: ");
            int stock = readInt("Stock: ");

            int sleeveChoice = readInt("Sleeve (1=SHORT, 2=LONG): ");
            Shirt.SleeveType sleeve = (sleeveChoice == 2) ? Shirt.SleeveType.LONG : Shirt.SleeveType.SHORT;

            String material = readNonEmpty("Material: ");

            Shirt s = new Shirt(id, name, size, price, brand, stock, sleeve, material);

            boolean ok = dao.insertShirt(s);
            System.out.println(ok ? "Inserted successfully ✅" : "Insert failed ❌");

        } catch (InvalidInputException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addPants() {
        System.out.println("\n--- ADD PANTS ---");
        try {
            int id = readInt("Item ID (>=0): ");
            String name = readNonEmpty("Name: ");
            String size = readNonEmpty("Size: ");
            double price = readDouble("Price: ");
            String brand = readNonEmpty("Brand: ");
            int stock = readInt("Stock: ");

            int fitChoice = readInt("Fit (1=SLIM, 2=REGULAR, 3=OVERSIZED): ");
            Pants.FitType fit;
            if (fitChoice == 1) fit = Pants.FitType.SLIM;
            else if (fitChoice == 3) fit = Pants.FitType.OVERSIZED;
            else fit = Pants.FitType.REGULAR;

            int waist = readInt("Waist: ");
            int inseam = readInt("Inseam: ");
            String material = readNonEmpty("Material: ");

            Pants p = new Pants(id, name, size, price, brand, stock, fit, waist, inseam, material);

            boolean ok = dao.insertPants(p);
            System.out.println(ok ? "Inserted successfully ✅" : "Insert failed ❌");

        } catch (InvalidInputException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------- SELECT ----------
    private void viewAll() {
        System.out.println("\n--- ALL ITEMS (FROM DB) ---");
        List<ClothingItem> items = dao.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No items in database.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i).getDisplayInfo());
        }
    }

    private void viewById() {
        System.out.println("\n--- VIEW BY ID ---");
        int id = readInt("Item ID: ");
        ClothingItem item = dao.getById(id);
        if (item == null) System.out.println("Not found.");
        else System.out.println(item.getDisplayInfo());
    }

    // ---------- UPDATE ----------
    private void updateItem() {
        System.out.println("\n--- UPDATE ITEM ---");
        int id = readInt("Enter item ID to update: ");

        ClothingItem existing = dao.getById(id);
        if (existing == null) {
            System.out.println("No item found with ID: " + id);
            return;
        }

        System.out.println("Current:");
        System.out.println(existing.getDisplayInfo());

        // Press Enter to keep current (как в гайде Week 8 flow)
        String newName = readOptional("New name [" + existing.getName() + "]: ");
        if (newName.isEmpty()) newName = existing.getName();

        String newSize = readOptional("New size [" + existing.getSize() + "]: ");
        if (newSize.isEmpty()) newSize = existing.getSize();

        String priceStr = readOptional("New price [" + existing.getPrice() + "]: ");
        double newPrice = priceStr.isEmpty() ? existing.getPrice() : Double.parseDouble(priceStr);

        String newBrand = readOptional("New brand [" + existing.getBrand() + "]: ");
        if (newBrand.isEmpty()) newBrand = existing.getBrand();

        String stockStr = readOptional("New stock [" + existing.getStockQuantity() + "]: ");
        int newStock = stockStr.isEmpty() ? existing.getStockQuantity() : Integer.parseInt(stockStr);

        try {
            if ("SHIRT".equalsIgnoreCase(existing.getType())) {
                Shirt old = (Shirt) existing; // не демонстрация, а нормальная логика update
                String sleeveStr = readOptional("New sleeve (SHORT/LONG) [" + old.getSleeveType() + "]: ");
                Shirt.SleeveType st = sleeveStr.isEmpty() ? old.getSleeveType() : Shirt.SleeveType.valueOf(sleeveStr.toUpperCase());

                String material = readOptional("New material [" + old.getMaterial() + "]: ");
                if (material.isEmpty()) material = old.getMaterial();

                Shirt updated = new Shirt(id, newName, newSize, newPrice, newBrand, newStock, st, material);
                boolean ok = dao.updateShirt(updated);
                System.out.println(ok ? "Updated ✅" : "Update failed ❌");

            } else {
                Pants old = (Pants) existing;
                String fitStr = readOptional("New fit (SLIM/REGULAR/OVERSIZED) [" + old.getFitType() + "]: ");
                Pants.FitType ft = fitStr.isEmpty() ? old.getFitType() : Pants.FitType.valueOf(fitStr.toUpperCase());

                String waistStr = readOptional("New waist [" + old.getWaist() + "]: ");
                int waist = waistStr.isEmpty() ? old.getWaist() : Integer.parseInt(waistStr);

                String inseamStr = readOptional("New inseam [" + old.getInseam() + "]: ");
                int inseam = inseamStr.isEmpty() ? old.getInseam() : Integer.parseInt(inseamStr);

                String material = readOptional("New material [" + old.getMaterial() + "]: ");
                if (material.isEmpty()) material = old.getMaterial();

                Pants updated = new Pants(id, newName, newSize, newPrice, newBrand, newStock, ft, waist, inseam, material);
                boolean ok = dao.updatePants(updated);
                System.out.println(ok ? "Updated ✅" : "Update failed ❌");
            }
        } catch (Exception e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    // ---------- DELETE (safe confirm) ----------
    private void deleteItemSafe() {
        System.out.println("\n--- DELETE ITEM ---");
        int id = readInt("Enter item ID to delete: ");

        ClothingItem existing = dao.getById(id);
        if (existing == null) {
            System.out.println("No item found with ID: " + id);
            return;
        }

        System.out.println("Item to delete:");
        System.out.println(existing.getDisplayInfo());

        String confirm = readNonEmpty("Are you sure? (yes/no): ");
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        boolean ok = dao.deleteItem(id);
        System.out.println(ok ? "Deleted ✅" : "Delete failed ❌");
    }

    // ---------- SEARCH ----------
    private void searchByName() {
        System.out.println("\n--- SEARCH BY NAME (ILIKE) ---");
        String q = readNonEmpty("Enter name part: ");
        List<ClothingItem> list = dao.searchByName(q);
        printList(list);
    }

    private void searchByRange() {
        System.out.println("\n--- SEARCH BY PRICE RANGE (BETWEEN) ---");
        double min = readDouble("Min price: ");
        double max = readDouble("Max price: ");
        List<ClothingItem> list = dao.searchByPriceRange(min, max);
        printList(list);
    }

    private void searchByMin() {
        System.out.println("\n--- SEARCH BY MIN PRICE (>=) ---");
        double min = readDouble("Min price: ");
        List<ClothingItem> list = dao.searchByMinPrice(min);
        printList(list);
    }

    // ---------- Extra (stock) ----------
    private void increaseStockById() {
        System.out.println("\n--- INCREASE STOCK ---");
        int id = readInt("Item ID: ");
        ClothingItem existing = dao.getById(id);
        if (existing == null) {
            System.out.println("Not found.");
            return;
        }
        int add = readInt("Add quantity: ");
        int newStock = existing.getStockQuantity() + add;

        // переиспользуем update: меняем только stock
        // оставим остальные поля теми же
        try {
            if ("SHIRT".equalsIgnoreCase(existing.getType())) {
                Shirt old = (Shirt) existing;
                Shirt updated = new Shirt(old.getItemId(), old.getName(), old.getSize(), old.getPrice(), old.getBrand(),
                        newStock, old.getSleeveType(), old.getMaterial());
                System.out.println(dao.updateShirt(updated) ? "Stock updated ✅" : "Failed ❌");
            } else {
                Pants old = (Pants) existing;
                Pants updated = new Pants(old.getItemId(), old.getName(), old.getSize(), old.getPrice(), old.getBrand(),
                        newStock, old.getFitType(), old.getWaist(), old.getInseam(), old.getMaterial());
                System.out.println(dao.updatePants(updated) ? "Stock updated ✅" : "Failed ❌");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printList(List<ClothingItem> list) {
        if (list.isEmpty()) {
            System.out.println("No results.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ") " + list.get(i).getDisplayInfo());
        }
    }

    // ---------- input helpers ----------
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

    private String readOptional(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine();
        return (s == null) ? "" : s.trim();
    }

    private int readIntLine() {
        String s = scanner.nextLine().trim();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
