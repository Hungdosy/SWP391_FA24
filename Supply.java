package Model;

public class Supply {
    private int supplyId;
    private int projectId;
    private String itemName;
    private int quantityNeeded;
    private Integer quantityProvided;
    private String unit;

    // Constructors
    public Supply() {}

    public Supply(int supplyId, int projectId, String itemName, int quantityNeeded, Integer quantityProvided, String unit) {
        this.supplyId = supplyId;
        this.projectId = projectId;
        this.itemName = itemName;
        this.quantityNeeded = quantityNeeded;
        this.quantityProvided = quantityProvided;
        this.unit = unit;
    }

    // Getters and Setters
    public int getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantityNeeded() {
        return quantityNeeded;
    }

    public void setQuantityNeeded(int quantityNeeded) {
        this.quantityNeeded = quantityNeeded;
    }

    public Integer getQuantityProvided() {
        return quantityProvided;
    }

    public void setQuantityProvided(Integer quantityProvided) {
        this.quantityProvided = quantityProvided;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
