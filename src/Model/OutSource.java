package Model;

public class OutSource extends Part {


    private String companyName;

    public OutSource(int partID, String partName, double partPrice, int partInStock, int min, int max, String companyName) {
        super(partID, partName, partPrice, partInStock, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
