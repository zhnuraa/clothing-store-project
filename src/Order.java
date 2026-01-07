import java.util.ArrayList;

public class Order {

    public enum Status { PENDING, COMPLETED, CANCELLED }

    private int orderId;
    private Customer customer;
    private ArrayList<OrderLine> lines;
    private Status status;

    public Order(int orderId, Customer customer) {
        setOrderId(orderId);
        setCustomer(customer);
        this.lines = new ArrayList<OrderLine>();
        this.status = Status.PENDING;
    }

    public Order() {
        this.orderId = 0;
        this.customer = null;
        this.lines = new ArrayList<OrderLine>();
        this.status = Status.PENDING;
    }

    // Getters
    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Status getStatus() { return status; }
    public ArrayList<OrderLine> getLines() { return lines; }

    // Setters with validation
    public void setOrderId(int orderId) {
        if (orderId < 0) {
            System.out.println("Invalid orderId. Setting orderId = 0.");
            this.orderId = 0;
        } else {
            this.orderId = orderId;
        }
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            System.out.println("Customer cannot be null.");
            this.customer = null;
        } else {
            this.customer = customer;
        }
    }

    // Core logic
    public boolean isPending() {
        return status == Status.PENDING;
    }

    public double calculateTotal() {
        double sum = 0.0;
        for (int i = 0; i < lines.size(); i++) {
            sum += lines.get(i).getLineTotal();
        }
        return sum;
    }

    public boolean addItem(ClothingItem item, int quantity) {
        if (status != Status.PENDING) {
            System.out.println("Cannot add items. Order is not PENDING.");
            return false;
        }
        if (customer == null) {
            System.out.println("Cannot add items without customer.");
            return false;
        }
        if (item == null) {
            System.out.println("Item cannot be null.");
            return false;
        }
        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return false;
        }

        // Reduce stock first (real store behavior)
        boolean reduced = item.reduceStock(quantity);
        if (!reduced) {
            return false;
        }

        // If item already exists in order, increase quantity
        for (int i = 0; i < lines.size(); i++) {
            OrderLine line = lines.get(i);
            if (line.getItem().getItemId() == item.getItemId()) {
                line.setQuantity(line.getQuantity() + quantity);
                return true;
            }
        }

        // Otherwise add a new order line
        lines.add(new OrderLine(item, quantity));
        return true;
    }

    public void complete() {
        if (status != Status.PENDING) {
            System.out.println("Order cannot be completed. Current status: " + status);
            return;
        }
        if (customer == null) {
            System.out.println("Cannot complete order without customer.");
            return;
        }
        if (lines.isEmpty()) {
            System.out.println("Cannot complete empty order.");
            return;
        }
        status = Status.COMPLETED;
    }

    public void cancel() {
        if (status != Status.PENDING) {
            System.out.println("Order cannot be cancelled. Current status: " + status);
            return;
        }

        for (int i = 0; i < lines.size(); i++) {
            OrderLine line = lines.get(i);
            line.getItem().increaseStock(line.getQuantity());
        }

        status = Status.CANCELLED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + (customer == null ? "null" : customer.getName()) +
                ", linesCount=" + lines.size() +
                ", total=" + calculateTotal() +
                ", status=" + status +
                '}';
    }
}
