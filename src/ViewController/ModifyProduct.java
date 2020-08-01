package ViewController;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import static ViewController.MainScreen.modifyProductIndex;
import static Model.Inventory.getAllProduct;

public class ModifyProduct implements Initializable {
    public Button modifyProductSearchButton;
    public Button ModifyDeleteProductButton;
    public Button modifyCancelProductButton;
    public Button modifySaveProductButton;
    public Button modifyAddProductButton;
    public TextField modifyProductMaxTxt;
    public TextField modifyProductMinTxt;
    public TextField modifyProductPriceTxt;
    public TextField modifyProductInvTxt;
    public TextField modifyProductNameTxt;
    public TextField modifyProductIDTxt;
    public TextField modifyProductSearchTxt;
    public TableView<Part> modifyProductPartAddTable;
    public TableColumn<Part, Integer> modifyProductAddPartIDColumn;
    public TableColumn<Part, String> modifyProductAddPartNameColumn;
    public TableColumn<Part, Integer> modifyProductAddPartInvColumn;
    public TableColumn<Part, Double> modifyProductAddPartPriceColumn;
    public TableView<Part> modifyProductPartTable;
    public TableColumn<Part, Integer> modifyProductPartIDColumn;
    public TableColumn<Part, String> modifyProductPartNameColumn;
    public TableColumn<Part, Integer> modifyProductPartInvColumn;
    public TableColumn<Part, Double> modifyProductPartPriceColumn;
    int prodIndx = modifyProductIndex();
    private int prodID;

    public void modifyProductSearchButton(MouseEvent mouseEvent) {
        String partSearch = modifyProductSearchTxt.getText();
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
            modifyProductPartAddTable.setItems(tempList);
        }
    }

    public void ModifyDeleteProductButton(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you wish to Delete this Part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Part part = modifyProductPartTable.getSelectionModel().getSelectedItem();
            availablePart.remove(part);
            modifyProductPartTable.setItems(availablePart);
        }
    }

    public void modifyCancelProductButton(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel modify product");
        alert.setHeaderText("You are about to return to the Main screen!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Parent modifyProductCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(modifyProductCancel);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else{
            return;
        }
    }

    public void modifySaveProductButton(MouseEvent mouseEvent) throws IOException {
        Product productmodify = new Product(0,"",0.0,0,0,0);
        if (!modifyProductIDTxt.getText().isEmpty()){
            productmodify.setProductID(Integer.parseInt(modifyProductIDTxt.getText()));
        }
        if (!modifyProductNameTxt.getText().isEmpty()){
            productmodify.setName(modifyProductNameTxt.getText());
        }
        if (!modifyProductPriceTxt.getText().isEmpty()){
            productmodify.setPrice(Double.parseDouble(modifyProductPriceTxt.getText()));
        }
        if (!modifyProductInvTxt.getText().isEmpty()){
            productmodify.setInStock(Integer.parseInt(modifyProductInvTxt.getText()));
        }
        if (!modifyProductMinTxt.getText().isEmpty()){
            productmodify.setMin(Integer.parseInt(modifyProductMinTxt.getText()));
        }
        if (!modifyProductMaxTxt.getText().isEmpty()){
            productmodify.setMax(Integer.parseInt(modifyProductMaxTxt.getText()));
        }
        if (!availablePart.isEmpty()){
            productmodify.setPart(availablePart);
            System.out.println(Product.getPart());
        }
        if(availablePart.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("All Products must contain at least 1 part!");
            alert.showAndWait();
        }
        else{
        Inventory.updateProduct(prodIndx, productmodify);
        Parent addProductSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(addProductSave);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        }
    }

    public void modifyAddProductButton(MouseEvent mouseEvent) {
        Part part = modifyProductPartAddTable.getSelectionModel().getSelectedItem();
        availablePart.add(part);
        modifyProductPartTable.setItems(availablePart);
    }
    private ObservableList<Part> availablePart = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb){
        modifyProductPartAddTable.setItems(Inventory.getAllPart());
        modifyProductAddPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        modifyProductAddPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyProductAddPartInvColumn.setCellValueFactory((new PropertyValueFactory<>("partInStock")));
        modifyProductAddPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        modifyProductPartTable.setItems(Product.getPart());
        modifyProductPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        modifyProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyProductPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        modifyProductPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        Product product = getAllProduct().get(prodIndx);
        prodID = getAllProduct().get(prodIndx).getProductID();
        modifyProductIDTxt.setText(Integer.toString(product.getProductID()));
        modifyProductNameTxt.setText(product.getName());
        modifyProductInvTxt.setText(Integer.toString(product.getInStock()));
        modifyProductPriceTxt.setText(Double.toString(product.getPrice()));
        modifyProductMinTxt.setText(Integer.toString(product.getMin()));
        modifyProductMaxTxt.setText(Integer.toString(product.getMax()));
        //modifyProductPartTable.setItems(Product.getPart());
        availablePart = Product.getPart();
    }

}





