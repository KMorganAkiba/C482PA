package ViewController;

import Model.Inventory;
import Model.Part;
import Model.Product;
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

public class AddProduct implements Initializable {
    public Button addProductSearchButton;
    public Button addProductAddButton;
    public Button addProductCancelButton;
    public Button addProductSaveButton;
    public Button addProductDeleteButton;
    public TextField productIDTxtbox;
    public TextField productNameTxtbox;
    public TextField productInvTxtbox;
    public TextField productMaxTxtField;
    public TextField productPriceTxtbox;
    public TextField productMinTxtbox;
    public TextField productSearchTxtbox;
    public TableView<Part> partSelectionTableview;
    public TableColumn<Part, Integer> partSelectionIDColumn;
    public TableColumn<Part, String> partSelectionNameColumn;
    public TableColumn<Part, Integer> partSelectionInvColumn;
    public TableColumn<Part, Double> partSelectionPriceColumn;
    public TableView<Part> productPartsTableview;
    public TableColumn<Part, Integer> productPartIDColumn;
    public TableColumn<Part, String> productPartNameColumn;
    public TableColumn<Part, Integer> productPartInvColumn;
    public TableColumn<Part, Double> productPartPriceColumn;

    private ObservableList<Part> availablePart = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle rb){
        partSelectionTableview.setItems(Inventory.getAllPart());
        partSelectionIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partSelectionNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partSelectionInvColumn.setCellValueFactory((new PropertyValueFactory<>("partInStock")));
        partSelectionPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        productPartsTableview.setItems(availablePart);
        productPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        productPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        productPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        productPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
    }

    public void addProductSearchButton(MouseEvent mouseEvent) {
        String partSearch = productSearchTxtbox.getText();
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
            partSelectionTableview.setItems(tempList);
        }
    }

    public void addProductAddButton(MouseEvent mouseEvent) {
        Part part = partSelectionTableview.getSelectionModel().getSelectedItem();
        availablePart.add(part);
        productPartsTableview.setItems(availablePart);
    }

    public void addProductCancelButton(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel add product");
        alert.setHeaderText("You are about to return to the Main screen!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Parent addProductCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(addProductCancel);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    public void addProductSaveButton(MouseEvent mouseEvent) throws IOException {
        Product productadd = new Product(0,"",0.0,0,0,0);
        if (productIDTxtbox.getText().isEmpty()){
            productadd.setProductID(Inventory.getProductIDCount());
        }
        if (!productNameTxtbox.getText().isEmpty()){
            productadd.setName(productNameTxtbox.getText());
        }
        if (!productPriceTxtbox.getText().isEmpty()){
            productadd.setPrice(Double.parseDouble(productPriceTxtbox.getText()));
        }
        if (!productInvTxtbox.getText().isEmpty()){
            productadd.setInStock(Integer.parseInt(productInvTxtbox.getText()));
        }
        if (!productMinTxtbox.getText().isEmpty()){
            productadd.setMin(Integer.parseInt(productMinTxtbox.getText()));
        }
        if (!productMaxTxtField.getText().isEmpty()){
            productadd.setMax(Integer.parseInt(productMaxTxtField.getText()));
        }
        if (!availablePart.isEmpty()) {
            productadd.setPart(availablePart);
        }

        if(availablePart.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("All products must contain at least 1 Part!");
            alert.showAndWait();
        }
        else {
            Inventory.addProduct(productadd);
            Parent addProductSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(addProductSave);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    public void addProductDeleteButton(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you wish to Delete this Part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Part part = productPartsTableview.getSelectionModel().getSelectedItem();
            availablePart.remove(part);
            productPartsTableview.setItems(availablePart);

        }
    }

}
