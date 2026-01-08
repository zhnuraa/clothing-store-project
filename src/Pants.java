public class Pants extends ClothingItem {

    public enum FitType { SLIM, REGULAR, OVERSIZED }

    private FitType fitType;
    private int waist;
    private int inseam;
    private String material;

    public Pants(int itemId, String name, String size, double price, String brand, int stockQuantity,
                 FitType fitType, int waist, int inseam, String material) {
        super(itemId, name, size, price, brand, stockQuantity);
        setFitType(fitType);
        setWaist(waist);
        setInseam(inseam);
        setMaterial(material);
    }

    public Pants() {
        super();
        this.fitType = FitType.REGULAR;
        this.waist = 32;
        this.inseam = 32;
        this.material = "Denim";
    }

    public FitType getFitType() { return fitType; }
    public int getWaist() { return waist; }
    public int getInseam() { return inseam; }
    public String getMaterial() { return material; }

    public void setFitType(FitType fitType) {
        if (fitType == null) {
            System.out.println("Invalid fitType. Setting REGULAR.");
            this.fitType = FitType.REGULAR;
        } else {
            this.fitType = fitType;
        }
    }

    public void setWaist(int waist) {
        if (waist <= 0) {
            System.out.println("Invalid waist. Setting 32.");
            this.waist = 32;
        } else {
            this.waist = waist;
        }
    }

    public void setInseam(int inseam) {
        if (inseam <= 0) {
            System.out.println("Invalid inseam. Setting 32.");
            this.inseam = 32;
        } else {
            this.inseam = inseam;
        }
    }

    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty()) {
            System.out.println("Invalid material. Setting 'Denim'.");
            this.material = "Denim";
        } else {
            this.material = material.trim();
        }
    }

    // Polymorphic behavior (overrides)
    @Override
    public String getType() {
        return "Pants";
    }

    @Override
    public String getCareInstructions() {
        if (material.equalsIgnoreCase("denim")) {
            return "Denim care: wash inside-out, cold water, air dry.";
        }
        return "Pants care: wash at 30°C, do not bleach.";
    }

    @Override
    public String getDisplayInfo() {
        return super.getDisplayInfo() +
                ", fitType=" + fitType +
                ", waist=" + waist +
                ", inseam=" + inseam +
                ", material='" + material + "'";
    }

    // Child-only method (for instanceof + casting demo)
    public void sizeAdvice() {
        System.out.println("Pants advice: fit=" + fitType + ", waist=" + waist + ", inseam=" + inseam);
    }

    @Override
    public String toString() {
        return "Pants{" +
                "base=" + super.toString() +
                ", fitType=" + fitType +
                ", waist=" + waist +
                ", inseam=" + inseam +
                ", material='" + material + '\'' +
                '}';
    }
}
