public class Order {
    public enum Status { PENDING, COMPLETED, CANCELLED }

    private int orderId;
    private String customerName;
    private double total;
    private Status status;

    public Order(int orderId, String customerName, double total, Status status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.total = total;
        this.status = status;
    }

    public Order() {
        this.orderId = 0;
        this.customerName = "Unknown";
        this.total = 0.0;
        this.status = Status.PENDING;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotal() {
        return total;
    }

    public Status getStatus() {
        return status;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addAmount(double amount) {
        if (amount < 0) {
            System.out.println("Amount cannot be negative. No changes applied.");
            return;
        }
        this.total += amount;
    }

    public void complete() {
        this.status = Status.COMPLETED;
    }

    public void cancel() {
        this.status = Status.CANCELLED;
    }

    public boolean isPending() {
        return this.status == Status.PENDING;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}
