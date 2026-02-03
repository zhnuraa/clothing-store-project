package model;

import exception.InvalidInputException;
import java.util.ArrayList;

public class Order {

    public enum Status { PENDING, COMPLETED, CANCELLED }

    private static class Line {
        private final ClothingItem item;
        private int quantity;

        Line(ClothingItem item, int quantity) {
            if (item == null) throw new InvalidInputException("item cannot be null");
            if (quantity <= 0) throw new InvalidInputException("quantity must be positive");
            this.item = item;
            this.quantity = quantity;
        }

        ClothingItem getItem() { return item; }
        int getQuantity() { return quantity; }

        void addQuantity(int add) {
            if (add <= 0) throw new InvalidInputException("add quantity must be positive");
            quantity += add;
        }

        double getLineTotal() {
            return item.getPrice() * quantity;
        }
    }

    private int orderId;
    private Customer customer;
    private final ArrayList<Line> lines = new ArrayList<Line>();
    private Status status;

    public Order(int orderId, Customer customer) {
        if (orderId < 0) throw new InvalidInputException("orderId must be >= 0");
        if (customer == null) throw new InvalidInputException("customer cannot be null");
        this.orderId = orderId;
        this.customer = customer;
        this.status = Status.PENDING;
    }

    public int getOrderId() { return orderId; }
    public Status getStatus() { return status; }

    public double calculateTotal() {
        double sum = 0.0;
        for (int i = 0; i < lines.size(); i++) sum += lines.get(i).getLineTotal();
        return sum;
    }

    public void addItem(ClothingItem item, int quantity) {
        if (status != Status.PENDING) throw new IllegalStateException("Order is not PENDING");
        if (item == null) throw new InvalidInputException("item cannot be null");
        if (quantity <= 0) throw new InvalidInputException("quantity must be positive");

        item.reduceStock(quantity);

        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            if (line.getItem().getItemId() == item.getItemId()) {
                line.addQuantity(quantity);
                return;
            }
        }
        lines.add(new Line(item, quantity));
    }

    public void complete() {
        if (status != Status.PENDING) throw new IllegalStateException("Order cannot be completed");
        if (lines.isEmpty()) throw new IllegalStateException("Order is empty");
        status = Status.COMPLETED;
    }

    public void cancel() {
        if (status != Status.PENDING) throw new IllegalStateException("Order cannot be cancelled");

        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            line.getItem().increaseStock(line.getQuantity());
        }
        status = Status.CANCELLED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", lines=" + lines.size() +
                ", total=" + calculateTotal() +
                ", status=" + status +
                '}';
    }
}
