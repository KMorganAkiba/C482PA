package ViewController;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreen implements Initializable{

    public Button closeSystemButton;
    public Button partsSearchButton;
    public Button partsAddButton;
    public Button partsModifyButton;
    public Button partsDeleteButton;
    public Button productsSearchButton;
    public Button productsAddButton;
    public Button productsModifyButton;
    public Button productsDeleteButton;
    public TableView<Part> partsTableView;
    public TableView<Product> productTableView;
    public TableColumn<Product, Integer> productIDColumn;
    public TableColumn<Product, String> productNameColumn;
    public TableColumn<Product, Integer> productInventoryColumn;
    public TableColumn<Product, Double> productPriceColumn;
    public TableColumn<Part, Integer> partIDColumn;
    public TableColumn<Part, String > partNameColumn;
    public TableColumn<Part, Integer> partInventoryColumn;
    public TableColumn<Part, Double> partCostColumn;
    public TextField partSearchTxtbox;
    public TextField productSearchTxtbox;

    private static Part partModify;
    private static int partModifyIndx;
    private static Product productModify;
    private static int productModifyIndx;
    public static int modifyPartIndex(){
        return partModifyIndx;
    }
    public static int modifyProductIndex() { return productModifyIndx; }


   @Override
        public void initialize(URL url, ResourceBundle rb){
        partsTableView.setItems(Inventory.getAllPart());
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        productTableView.setItems(Inventory.getAllProduct());
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void closeSystemButton(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("You are about to Exit the Program");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
        else {
            return;
        }
    }

    //Part Section
    public void partsSearchButton(MouseEvent mouseEvent) {
       String partSearch = partSearchTxtbox.getText();
       int partIndx = -1;
       if (Inventory.lookUpPart(partSearch) == -1){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("Part not Found!");
           alert.showAndWait();
       }
       else{
           partIndx = Inventory.lookUpPart(partSearch);
           Part temp = Inventory.getAllPart().get(partIndx);
           ObservableList<Part> tempList = FXCollections.observableArrayList();
           tempList.add(temp);
           partsTableView.setItems(tempList);
       }

    }

    public void partsAddButton(MouseEvent mouseEvent) throws IOException {
        Parent AddPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartScene = new Scene(AddPartParent);
        Stage addPartStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    public void partsModifyButton(MouseEvent mouseEvent) throws IOException {

        partModify = partsTableView.getSelectionModel().getSelectedItem();
        partModifyIndx = Inventory.getAllPart().indexOf(partModify);
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);
        Stage modifyPartStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        modifyPartStage.setScene(modifyPartScene);
        modifyPartStage.show();
    }

    public void partsDeleteButton(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you wish to Delete this Part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Part part = partsTableView.getSelectionModel().getSelectedItem();
            Inventory.deletePart(part);
        }
    }

    //Product Section
    public void productsSearchButton(MouseEvent mouseEvent) {
        String productSearch = productSearchTxtbox.getText();
        int productIndx = -1;
        if (Inventory.lookUpProduct(productSearch) == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Product not Found!");
            alert.showAndWait();
        }
        else{
            productIndx = Inventory.lookUpProduct(productSearch);
            Product producttemp = Inventory.getAllProduct().get(productIndx);
            ObservableList<Product> tempListProduct = FXCollections.observableArrayList();
            tempListProduct.add(producttemp);
            productTableView.setItems(tempListProduct);
        }
    }

    public void productsAddButton(MouseEvent mouseEvent) throws IOException {
        Parent AddProductParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addProductScene = new Scene(AddProductParent);
        Stage addProductStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        addProductStage.setScene(addProductScene);
        addProductStage.show();
    }

    public void productsModifyButton(MouseEvent mouseEvent) throws IOException {

        productModify = productTableView.getSelectionModel().getSelectedItem();
        productModifyIndx = Inventory.getAllProduct().indexOf(productModify);
        Parent modifyProductParent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene modifyProductScene = new Scene(modifyProductParent);
        Stage modifyProductStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        modifyProductStage.setScene(modifyProductScene);
        modifyProductStage.show();
        System.out.println(Product.getPart());
    }

    public void productsDeleteButton(MouseEvent mouseEvent) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("Are you sure you wish to Delete this Product?");
       Optional<ButtonType> result = alert.showAndWait();
       if (result.get() == ButtonType.OK) {
           Product product = productTableView.getSelectionModel().getSelectedItem();
           Inventory.deleteProduct(product);
       }

    }
}
