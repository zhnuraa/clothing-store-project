package model;

import exception.InvalidInputException;

/**
 * Parent class (Week 4) -> becomes abstract (Week 6).
 * Also implements an extra interface (Discountable) to satisfy Week 6.
 */
public abstract class ClothingItem implements Discountable {

    // Week 4 checklist often asks for protected fields (min 4).
    // We keep all core fields protected.
    protected int itemId;
    protected String name;
    protected String size;
    protected double price;
    protected String brand;
    protected int stockQuantity;

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

    // Setters (Week 6: throw exceptions instead of printing)
    public void setItemId(int itemId) {
        if (itemId < 0) {
            throw new InvalidInputException("itemId must be >= 0");
        }
        this.itemId = itemId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("name cannot be empty");
        }
        this.name = name.trim();
    }

    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new InvalidInputException("size cannot be empty");
        }
        this.size = size.trim();
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new InvalidInputException("price must be >= 0");
        }
        this.price = price;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidInputException("brand cannot be empty");
        }
        this.brand = brand.trim();
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new InvalidInputException("stockQuantity must be >= 0");
        }
        this.stockQuantity = stockQuantity;
    }

    // Week 3 store logic
    public boolean isPremium() {
        return price > 35000.0;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public boolean reduceStock(int amount) {
        if (amount <= 0) {
            throw new InvalidInputException("amount must be positive");
        }
        if (amount > stockQuantity) {
            throw new InvalidInputException("not enough stock. Available: " + stockQuantity);
        }
        stockQuantity -= amount;
        return true;
    }

    public void increaseStock(int amount) {
        if (amount <= 0) {
            throw new InvalidInputException("amount must be positive");
        }
        stockQuantity += amount;
    }

    // ===== Week 4 / Week 6 polymorphism =====
    // Week 6 requires at least one abstract method.
    public abstract String getType();

    public String getCareInstructions() {
        return "Standard care: wash at 30C, do not bleach.";
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

    // ===== Week 6 extra interface (Discountable) =====
    @Override
    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new InvalidInputException("discount percent must be between 0 and 100");
        }
        double multiplier = (100.0 - percent) / 100.0;
        this.price = this.price * multiplier;
    }

    @Override
    public double calculateDiscountedPrice(double percent) {
        if (percent < 0 || percent > 100) {
            throw new InvalidInputException("discount percent must be between 0 and 100");
        }
        double multiplier = (100.0 - percent) / 100.0;
        return this.price * multiplier;
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
