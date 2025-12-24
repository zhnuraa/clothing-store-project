public class ClothingItem {
    private int itemId;
    private String name;
    private String size;
    private double price;
    private String brand;

    public ClothingItem(int itemId, String name, String size, double price, String brand) {
        this.itemId = itemId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.brand = brand;
    }

    public ClothingItem() {
        this.itemId = 0;
        this.name = "Unknown Item";
        this.size = "N/A";
        this.price = 0.0;
        this.brand = "No Brand";
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    // Method 1: Apply discount to price
    public void applyDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            System.out.println("Invalid discount: " + percentage + "%. No changes applied.");
            return;
        }
        this.price = this.price * (1 - percentage / 100.0);
    }

    // Method 2: Premium check (YOUR rule: price > 35000 KZT)
    public boolean isPremium() {
        return this.price > 35000.0;
    }

    // 7) toString()
    @Override
    public String toString() {
        return "ClothingItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}
