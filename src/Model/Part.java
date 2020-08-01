package Model;

public abstract class Part {

    private int partID;
    private String partName;
    private double partPrice;
    private int partInStock;
    private int min;
    private int max;

    //Part constructor
    public Part(int partID, String partName, double partPrice, int partInStock, int min, int max) {
        this.partID = partID;
        this.partName = partName;
        this.partPrice = partPrice;
        this.partInStock = partInStock;
        this.min = min;
        this.max = max;
    }

    //Getters and setters for Part class


    public String getPartName() {
        return partName;
    }

    public void setPartName(String name){
        this.partName = name;
    }

    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    public int getPartInStock() {
        return partInStock;
    }

    public void setPartInStock(int partInStock) {
        this.partInStock = partInStock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }
}


