public class Main {
    public static void main(String[] args) {
        System.out.println("=== Clothing Store Management System===\n");
        System.out.println("1) Creating Objects");
      
        ClothingItem item1 = new ClothingItem(101, "Hoodie", "M", 42000.0, "Nike");
        ClothingItem item2 = new ClothingItem(102, "Jeans", "32", 28000.0, "Levi's");
        ClothingItem item3 = new ClothingItem();

        Customer customer1 = new Customer(5001, "Aruzhan Bek", "M", 90);
        Customer customer2 = new Customer();

        Order order1 = new Order(9001, customer1.getName(), 0.0, Order.Status.PENDING);
        Order order2 = new Order();
        System.out.println("\n2) Initial Objects");
        System.out.println("item1: " + item1);
        System.out.println("item2: " + item2);
        System.out.println("item3: " + item3);
        System.out.println("customer1: " + customer1);
        System.out.println("customer2: " + customer2);
        System.out.println("order1: " + order1);
        System.out.println("order2: " + order2);
      
        System.out.println("\n3) Testing GETTERS");
        System.out.println("item1 getters -> id=" + item1.getItemId() + ", name=" + item1.getName() +
                ", size=" + item1.getSize() + ", price=" + item1.getPrice() + ", brand=" + item1.getBrand());

        System.out.println("item2 getters -> id=" + item2.getItemId() + ", name=" + item2.getName() +
                ", size=" + item2.getSize() + ", price=" + item2.getPrice() + ", brand=" + item2.getBrand());

        System.out.println("item3 getters -> id=" + item3.getItemId() + ", name=" + item3.getName() +
                ", size=" + item3.getSize() + ", price=" + item3.getPrice() + ", brand=" + item3.getBrand());

        System.out.println("customer1 getters -> id=" + customer1.getCustomerId() + ", name=" + customer1.getName() +
                ", preferredSize=" + customer1.getPreferredSize() + ", points=" + customer1.getPoints());

        System.out.println("customer2 getters -> id=" + customer2.getCustomerId() + ", name=" + customer2.getName() +
                ", preferredSize=" + customer2.getPreferredSize() + ", points=" + customer2.getPoints());

        System.out.println("order1 getters -> id=" + order1.getOrderId() + ", customerName=" + order1.getCustomerName() +
                ", total=" + order1.getTotal() + ", status=" + order1.getStatus());

        System.out.println("order2 getters -> id=" + order2.getOrderId() + ", customerName=" + order2.getCustomerName() +
                ", total=" + order2.getTotal() + ", status=" + order2.getStatus());

        System.out.println("\n4) Testing SETTERS");

        item2.setPrice(30000.0);
        item2.setSize("34");
        System.out.println("item2 updated with setters: " + item2);

        item3.setItemId(103);
        item3.setName("T-Shirt");
        item3.setSize("L");
        item3.setPrice(15000.0);
        item3.setBrand("Adidas");
        System.out.println("item3 updated with setters: " + item3);

        customer1.setPoints(105);
        System.out.println("customer1 updated with setter (points): " + customer1);

        customer2.setCustomerId(5002);
        customer2.setName("Dias Nur");
        customer2.setPreferredSize("L");
        customer2.setPoints(120);
        System.out.println("customer2 updated with setters: " + customer2);

        order1.setTotal(5000.0);
        System.out.println("order1 updated with setter (total): " + order1);

        order2.setOrderId(9002);
        order2.setCustomerName(customer2.getName());
        order2.setTotal(10000.0);
        order2.setStatus(Order.Status.PENDING);
        System.out.println("order2 updated with setters: " + order2);

        System.out.println("\n5) Testing ADDITIONAL METHODS");

      
        System.out.println("item1 isPremium (price > 35000): " + item1.isPremium());
        System.out.println("Applying 10% discount to item1...");
        item1.applyDiscount(10);
        System.out.println("item1 after discount: " + item1);
        System.out.println("item1 isPremium now: " + item1.isPremium());

        System.out.println("\nitem2 isPremium: " + item2.isPremium());
        System.out.println("Applying 20% discount to item2...");
        item2.applyDiscount(20);
        System.out.println("item2 after discount: " + item2);

        System.out.println("\ncustomer1 isVIP (points > 100): " + customer1.isVIP());
        System.out.println("Adding 30 points to customer1...");
        customer1.addPoints(30);
        System.out.println("customer1 after addPoints: " + customer1);
        System.out.println("customer1 isVIP now: " + customer1.isVIP());
        System.out.println("customer1 profile: " + customer1.getProfile());

        System.out.println("\ncustomer2 isVIP: " + customer2.isVIP());
        System.out.println("customer2 profile: " + customer2.getProfile());

        System.out.println("\norder1 pending? " + order1.isPending());
        System.out.println("Adding item3 price to order1 using addAmount...");
        order1.addAmount(item3.getPrice());
        System.out.println("order1 after addAmount: " + order1);

        System.out.println("Completing order1...");
        order1.complete();
        System.out.println("order1 after complete(): " + order1);

        System.out.println("\norder2 pending? " + order2.isPending());
        System.out.println("Cancelling order2...");
        order2.cancel();
        System.out.println("order2 after cancel(): " + order2);

        System.out.println("\n 6) Final State");
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println(order1);
        System.out.println(order2);

        System.out.println("\n=== Program Complete ===");
    }
}
