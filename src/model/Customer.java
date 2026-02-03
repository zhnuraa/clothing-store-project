package model;

import exception.InvalidInputException;

public class Customer {

    private int customerId;
    private String name;
    private String preferredSize;
    private int points;

    public Customer(int customerId, String name, String preferredSize, int points) {
        setCustomerId(customerId);
        setName(name);
        setPreferredSize(preferredSize);
        setPoints(points);
    }

    public Customer() {
        this.customerId = 0;
        this.name = "Unknown";
        this.preferredSize = "N/A";
        this.points = 0;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPreferredSize() { return preferredSize; }
    public int getPoints() { return points; }

    public void setCustomerId(int customerId) {
        if (customerId < 0) throw new InvalidInputException("customerId must be >= 0");
        this.customerId = customerId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("customer name cannot be empty");
        this.name = name.trim();
    }

    public void setPreferredSize(String preferredSize) {
        if (preferredSize == null || preferredSize.trim().isEmpty())
            throw new InvalidInputException("preferredSize cannot be empty");
        this.preferredSize = preferredSize.trim();
    }

    public void setPoints(int points) {
        if (points < 0) throw new InvalidInputException("points must be >= 0");
        this.points = points;
    }

    public boolean isVIP() {
        return points > 100;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", preferredSize='" + preferredSize + '\'' +
                ", points=" + points +
                '}';
    }
}
