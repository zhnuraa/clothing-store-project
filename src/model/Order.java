package model;

import exception.InvalidInputException;

import java.util.ArrayList;

public class Order {

    public enum Status { PENDING, COMPLETED, CANCELLED }

    // Внутренний класс — это НЕ новый файл, структуру не нарушает
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

        @Override
        public String toString() {
            return "Line{itemId=" + item.getItemId() +
                    ", name='" + item.getName() + '\'' +
                    ", qty=" + quantity +
                    ", unitPrice=" + item.getPrice() +
                    ", total=" + getLineTotal() + '}';
        }
    }

    private int orderId;
    private Customer customer;
    private final ArrayList<Line> lines = new ArrayList<Line>();
    private Status status;

    public Order(int orderId, Customer customer) {
        setOrderId(orderId);
        setCustomer(customer);
        this.status = Status.PENDING;
    }

    public Order() {
        this.orderId = 0;
        this.customer = null;
        this.status = Status.PENDING;
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Status getStatus() { return status; }

    public void setOrderId(int orderId) {
        if (orderId < 0) throw new InvalidInputException("orderId must be >= 0");
        this.orderId = orderId;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) throw new InvalidInputException("customer cannot be null");
        this.customer = customer;
    }

    // extra methods (Week 2)
    public boolean isPending() {
        return status == Status.PENDING;
    }

    public double calculateTotal() {
        double sum = 0.0;
        for (int i = 0; i < lines.size(); i++) sum += lines.get(i).getLineTotal();
        return sum;
    }

    public void addItem(ClothingItem item, int quantity) {
        if (status != Status.PENDING) throw new IllegalStateException("Order is not PENDING");
        if (item == null) throw new InvalidInputException("item cannot be null");
        if (quantity <= 0) throw new InvalidInputException("quantity must be positive");

        // уменьшаем склад
        item.reduceStock(quantity);

        // если товар уже есть в заказе — просто увеличиваем количество
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
        if (status != Status.PENDING) throw new IllegalStateException("Order cannot be completed (not PENDING)");
        if (customer == null) throw new IllegalStateException("Order has no customer");
        if (lines.isEmpty()) throw new IllegalStateException("Order is empty");
        status = Status.COMPLETED;
    }

    public void cancel() {
        if (status != Status.PENDING) throw new IllegalStateException("Order cannot be cancelled (not PENDING)");

        // возвращаем склад
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            line.getItem().increaseStock(line.getQuantity());
        }
        status = Status.CANCELLED;
    }

    public void printLines() {
        if (lines.isEmpty()) {
            System.out.println("   (empty)");
            return;
        }
        for (int i = 0; i < lines.size(); i++) {
            System.out.println("   - " + lines.get(i));
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + (customer == null ? "null" : customer.getName()) +
                ", lines=" + lines.size() +
                ", total=" + calculateTotal() +
                ", status=" + status +
                '}';
    }
}
