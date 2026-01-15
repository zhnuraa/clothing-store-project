package model;

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
        this.name = "Unknown Customer";
        this.preferredSize = "N/A";
        this.points = 0;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPreferredSize() { return preferredSize; }
    public int getPoints() { return points; }

    public void setCustomerId(int customerId) {
        if (customerId < 0) {
            System.out.println("Invalid customerId. Setting customerId = 0.");
            this.customerId = 0;
        } else {
            this.customerId = customerId;
        }
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid name. Setting name = 'Unknown Customer'.");
            this.name = "Unknown Customer";
        } else {
            this.name = name.trim();
        }
    }

    public void setPreferredSize(String preferredSize) {
        if (preferredSize == null || preferredSize.trim().isEmpty()) {
            System.out.println("Invalid preferredSize. Setting preferredSize = 'N/A'.");
            this.preferredSize = "N/A";
        } else {
            this.preferredSize = preferredSize.trim();
        }
    }

    public void setPoints(int points) {
        if (points < 0) {
            System.out.println("Invalid points. Setting points = 0.");
            this.points = 0;
        } else {
            this.points = points;
        }
    }

    public void addPoints(int points) {
        if (points <= 0) {
            System.out.println("Points must be positive. No changes applied.");
            return;
        }
        this.points += points;
    }

    public boolean isVIP() {
        return this.points > 100;
    }

    public String getProfile() {
        return "Customer#" + customerId + " " + name +
                " | preferredSize=" + preferredSize +
                " | points=" + points;
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
