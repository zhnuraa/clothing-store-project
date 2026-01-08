public class ClothingItem {
    private int itemId;
    private String name;
    private String size;
    private double price;
    private String brand;
    private int stockQuantity;

    public ClothingItem(int itemId, String name, String size, double price, String brand, int stockQuantity) {
        setItemId(itemId);
        setName(name);
        setSize(size);
        setPrice(price);
        setBrand(brand);
        setStockQuantity(stockQuantity);
    }

    public ClothingItem() {
        this.itemId = 0;
        this.name = "Unknown Item";
        this.size = "N/A";
        this.price = 0.0;
        this.brand = "No Brand";
        this.stockQuantity = 0;
    }

    // Getters
    public int getItemId() { return itemId; }
    public String getName() { return name; }
    public String getSize() { return size; }
    public double getPrice() { return price; }
    public String getBrand() { return brand; }
    public int getStockQuantity() { return stockQuantity; }

    // Setters with validation
    public void setItemId(int itemId) {
        if (itemId < 0) {
            System.out.println("Invalid itemId. Setting itemId = 0.");
            this.itemId = 0;
        } else {
            this.itemId = itemId;
        }
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid name. Setting name = 'Unknown Item'.");
            this.name = "Unknown Item";
        } else {
            this.name = name.trim();
        }
    }

    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            System.out.println("Invalid size. Setting size = 'N/A'.");
            this.size = "N/A";
        } else {
            this.size = size.trim();
        }
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Invalid price. Setting price = 0.0.");
            this.price = 0.0;
        } else {
            this.price = price;
        }
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            System.out.println("Invalid brand. Setting brand = 'No Brand'.");
            this.brand = "No Brand";
        } else {
            this.brand = brand.trim();
        }
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            System.out.println("Invalid stock. Setting stockQuantity = 0.");
            this.stockQuantity = 0;
        } else {
            this.stockQuantity = stockQuantity;
        }
    }

    // Business logic (Week 3)
    public void applyDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            System.out.println("Invalid discount: " + percentage + "%. No changes applied.");
            return;
        }
        price = price * (1 - percentage / 100.0);
    }

    public boolean isPremium() {
        return price > 35000.0;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public boolean reduceStock(int amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return false;
        }
        if (amount > stockQuantity) {
            System.out.println("Not enough stock. Available: " + stockQuantity);
            return false;
        }
        stockQuantity -= amount;
        return true;
    }

    public void increaseStock(int amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        stockQuantity += amount;
    }

    // ===== Week 4: polymorphism methods (children will override) =====
    public String getType() {
        return "ClothingItem";
    }

    public String getCareInstructions() {
        return "Standard care: wash at 30°C, do not bleach.";
    }

    public String getDisplayInfo() {
        return "[" + getType() + "] " +
                "id=" + itemId +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", stock=" + stockQuantity;
    }

    @Override
    public String toString() {
        return "ClothingItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
