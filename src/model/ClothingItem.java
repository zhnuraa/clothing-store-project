package model;

import exception.InvalidInputException;

public abstract class ClothingItem {

    // Можно private (лучше по ООП), но Week4 иногда любит protected.
    // Я оставляю private — так инкапсуляция сильнее.
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
        this.name = "Unknown";
        this.size = "N/A";
        this.price = 0.0;
        this.brand = "No Brand";
        this.stockQuantity = 0;
    }

    // ===== abstract (Week 6 requirement) =====
    public abstract String getType();

    // ===== Getters =====
    public int getItemId() { return itemId; }
    public String getName() { return name; }
    public String getSize() { return size; }
    public double getPrice() { return price; }
    public String getBrand() { return brand; }
    public int getStockQuantity() { return stockQuantity; }

    // ===== Setters (Week 6: throw exceptions) =====
    public void setItemId(int itemId) {
        if (itemId < 0) throw new InvalidInputException("itemId must be >= 0");
        this.itemId = itemId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("name cannot be empty");
        this.name = name.trim();
    }

    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) throw new InvalidInputException("size cannot be empty");
        this.size = size.trim();
    }

    public void setPrice(double price) {
        if (price < 0) throw new InvalidInputException("price must be >= 0");
        this.price = price;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) throw new InvalidInputException("brand cannot be empty");
        this.brand = brand.trim();
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) throw new InvalidInputException("stockQuantity must be >= 0");
        this.stockQuantity = stockQuantity;
    }

    // ===== Extra methods (Week 2 / Week 3 logic) =====
    public boolean isPremium() {
        return price >= 35000.0;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void increaseStock(int amount) {
        if (amount <= 0) throw new InvalidInputException("amount must be positive");
        stockQuantity += amount;
    }

    public void reduceStock(int amount) {
        if (amount <= 0) throw new InvalidInputException("amount must be positive");
        if (amount > stockQuantity) throw new InvalidInputException("Not enough stock. Available: " + stockQuantity);
        stockQuantity -= amount;
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
                "type='" + getType() + '\'' +
                ", itemId=" + itemId +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
