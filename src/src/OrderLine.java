public class OrderLine {
    private ClothingItem item;
    private int quantity;

    public OrderLine(ClothingItem item, int quantity) {
        this.item = item;
        setQuantity(quantity);
    }

    public ClothingItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Setting quantity = 1.");
            this.quantity = 1;
        } else {
            this.quantity = quantity;
        }
    }

    public double getLineTotal() {
        return item.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "itemId=" + item.getItemId() +
                ", name='" + item.getName() + '\'' +
                ", qty=" + quantity +
                ", unitPrice=" + item.getPrice() +
                ", lineTotal=" + getLineTotal() +
                '}';
    }
}
