package model;

import exception.InvalidInputException;

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

    @Override
    public String getType() {
        return "SHIRT";
    }

    public SleeveType getSleeveType() { return sleeveType; }
    public String getMaterial() { return material; }

    public void setSleeveType(SleeveType sleeveType) {
        if (sleeveType == null) throw new InvalidInputException("sleeveType cannot be null");
        this.sleeveType = sleeveType;
    }

    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty()) throw new InvalidInputException("material cannot be empty");
        this.material = material.trim();
    }

    @Override
    public String getDisplayInfo() {
        return super.getDisplayInfo() + ", sleeve=" + sleeveType + ", material='" + material + "'";
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
