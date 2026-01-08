public class Shirt extends ClothingItem {

    public enum SleeveType { SHORT, LONG }

    private SleeveType sleeveType;
    private String material;

    public Shirt(int itemId, String name, String size, double price, String brand, int stockQuantity,
                 SleeveType sleeveType, String material) {
        super(itemId, name, size, price, brand, stockQuantity); // super() -> parent constructor
        setSleeveType(sleeveType);
        setMaterial(material);
    }

    public Shirt() {
        super();
        this.sleeveType = SleeveType.SHORT;
        this.material = "Cotton";
    }

    public SleeveType getSleeveType() {
        return sleeveType;
    }

    public void setSleeveType(SleeveType sleeveType) {
        if (sleeveType == null) {
            System.out.println("Invalid sleeveType. Setting SHORT.");
            this.sleeveType = SleeveType.SHORT;
        } else {
            this.sleeveType = sleeveType;
        }
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty()) {
            System.out.println("Invalid material. Setting 'Cotton'.");
            this.material = "Cotton";
        } else {
            this.material = material.trim();
        }
    }

    // ===== Overrides (polymorphic behavior) =====
    @Override
    public String getType() {
        return "Shirt";
    }

    @Override
    public String getCareInstructions() {
        // simple logic based on material
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

    @Override
    public String toString() {
        return "Shirt{" +
                "base=" + super.toString() +
                ", sleeveType=" + sleeveType +
                ", material='" + material + '\'' +
                '}';
    }

    // ===== method only for Shirt (for instanceof + casting demo) =====
    public void foldSleeves() {
        System.out.println("Shirt action: folding sleeves (" + sleeveType + ").");
    }
}
