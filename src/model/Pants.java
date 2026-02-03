package model;

import exception.InvalidInputException;

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

    @Override
    public String getType() {
        return "Pants";
    }

    public FitType getFitType() { return fitType; }
    public int getWaist() { return waist; }
    public int getInseam() { return inseam; }
    public String getMaterial() { return material; }

    public void setFitType(FitType fitType) {
        if (fitType == null) throw new InvalidInputException("fitType cannot be null");
        this.fitType = fitType;
    }

    public void setWaist(int waist) {
        if (waist <= 0) throw new InvalidInputException("waist must be > 0");
        this.waist = waist;
    }

    public void setInseam(int inseam) {
        if (inseam <= 0) throw new InvalidInputException("inseam must be > 0");
        this.inseam = inseam;
    }

    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty()) throw new InvalidInputException("material cannot be empty");
        this.material = material.trim();
    }

    // extra logic methods (Week 2)
    public boolean isJeans() {
        return "denim".equalsIgnoreCase(material);
    }

    public String sizeAdvice() {
        if (fitType == FitType.SLIM) return "Slim fit: consider +1 size if you prefer comfort.";
        if (fitType == FitType.OVERSIZED) return "Oversized: true-to-size is usually loose.";
        return "Regular: true-to-size is recommended.";
    }

    @Override
    public String getDisplayInfo() {
        return super.getDisplayInfo() +
                ", fit=" + fitType +
                ", waist=" + waist +
                ", inseam=" + inseam +
                ", material='" + material + "'";
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
