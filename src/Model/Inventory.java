package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {


    private static ObservableList<Product> allProduct = FXCollections.observableArrayList();
    private static ObservableList<Part> allPart = FXCollections.observableArrayList();
    //Product section


    public static int getProductIDCount() {
        int productIDCount = 0;
        productIDCount = allProduct.size() + 1;
        return productIDCount;
    }

    public static void addProduct(Product product) {
        allProduct.add(product);
    }

    public static ObservableList<Product> getAllProduct() {
        return allProduct;
    }

    public static void deleteProduct(Product product) {
        allProduct.remove(product);
    }

    public static void updateProduct(int index, Product product) {
        allProduct.set(index, product);
    }

    public static int lookUpProduct(String search){
        boolean foundit = false;
        int index = 0;
        if (isInteger(search)){
            for (int i = 0; i<allProduct.size();i++){
                if (Integer.parseInt(search) == allProduct.get(i).getProductID()){
                    index = i;
                    foundit =  true;
                }
            }
        }
        else{
            for (int i = 0; i < allProduct.size(); i++ ){
                search = search.toLowerCase();
                if (search.equals(allProduct.get(i).getName().toLowerCase())){
                    index = i;
                    foundit = true;
                }
            }
        }
        if (foundit == true){
            return index;
        }
        else{
            return -1;
        }
    }

        //Part Section

        public static int getPartIDCount(){
            int partIDCount = 0;
            partIDCount = allPart.size() + 1;
            return partIDCount;
        }
        public static void addPart (Part part){
            allPart.add(part);
        }
        public static ObservableList<Part> getAllPart () {
            return allPart;
        }
        public static void deletePart (Part part){
            allPart.remove(part);
        }
        public static void updatePart ( int index, Part part){
            allPart.set(index, part);
        }
        public static int lookUpPart(String search){
            boolean foundit = false;
            int index = 0;
            if (isInteger(search)){
                for (int i = 0; i<allPart.size();i++){
                    if (Integer.parseInt(search) == allPart.get(i).getPartID()){
                        index = i;
                        foundit =  true;
                    }
                }
            }
            else{
                for (int i = 0; i < allPart.size(); i++ ){
                    search = search.toLowerCase();
                    if (search.equals(allPart.get(i).getPartName().toLowerCase())){
                        index = i;
                        foundit = true;
                    }
                }
            }
            if (foundit == true){
                return index;
            }
            else{
                return -1;
            }
        }

        public static boolean isInteger(String input){
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e){
            return false;
        }
        }
}




