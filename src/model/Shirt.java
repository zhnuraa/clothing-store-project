public class Shirt extends ClothingItem {

    public enum SleeveType { SHORT, LONG }

    private SleeveType sleeveType;
    private String material;

    public Shirt(int itemId, String name, String size, double price, String brand, int stockQuantity,
                 SleeveType sleeveType, String material) {
        super(itemId, name, size, price, brand, stockQuantity);
        setSleeveType(sleeveType);
        setMaterial(material);
    }

    public Shirt() {
        super();
        this.sleeveType = SleeveType.SHORT;
        this.material = "Cotton";
    }

    public SleeveType getSleeveType() { return sleeveType; }
    public String getMaterial() { return material; }

    public void setSleeveType(SleeveType sleeveType) {
        if (sleeveType == null) {
            System.out.println("Invalid sleeveType. Setting SHORT.");
            this.sleeveType = SleeveType.SHORT;
        } else {
            this.sleeveType = sleeveType;
        }
    }

    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty()) {
            System.out.println("Invalid material. Setting 'Cotton'.");
            this.material = "Cotton";
        } else {
            this.material = material.trim();
        }
    }

    // Polymorphic behavior (overrides)
    @Override
    public String getType() {
        return "Shirt";
    }

    @Override
    public String getCareInstructions() {
        if (material.equalsIgnoreCase("wool")) {
            return "Wool care: hand wash cold, air dry.";
        }
        return "Shirt care: wash at 30°C, iron low heat.";
    }

    @Override
    public String getDisplayInfo() {
        return super.getDisplayInfo() +
                ", sleeveType=" + sleeveType +
                ", material='" + material + "'";
    }

    // Child-only method (for instanceof + casting demo)
    public void foldSleeves() {
        System.out.println("Shirt action: folding sleeves (" + sleeveType + ").");
    }

    @Override
    public String toString() {
        return "Shirt{" +
                "base=" + super.toString() +
                ", sleeveType=" + sleeveType +
                ", material='" + material + '\'' +
                '}';
    }
}
