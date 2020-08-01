package ViewController;

import Model.InHouse;
import Model.Inventory;
import Model.OutSource;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPart implements Initializable {
    public RadioButton addPartInHouse;
    public TextField partIDTxtbox;
    public TextField partNameTxtbox;
    public TextField invTxtbox;
    public TextField pricePerUnitTxtbox;
    public TextField maxQtyTxtbox;
    public TextField minQtyTxtbox;
    public Button addPartSaveButton;
    public Button addPartCancelButton;
    public RadioButton addPartOutSource;
    public Label companyNameLabel;
    public TextField companyNamePrompt;


    public void addPartInHouse(ActionEvent actionEvent) {
        companyNameLabel.setText("Machine ID");
        companyNamePrompt.setPromptText("Machine ID");
        addPartOutSource.setSelected(false);

    }

    public void addPartOutsource(ActionEvent actionEvent) {
        companyNameLabel.setText("Company Name");
        companyNamePrompt.setPromptText("Company Name");
        addPartInHouse.setSelected(false);
    }

    public void addPartSaveButton(ActionEvent e) throws IOException {

        if (addPartInHouse.isSelected()) {
            Part partadd = new InHouse(0, "", 0.0, 0,0,0,0);
            if(partIDTxtbox.getText().isEmpty()){
                partadd.setPartID(Inventory.getPartIDCount());
            }
            if (!partNameTxtbox.getText().isEmpty()){
                partadd.setPartName(partNameTxtbox.getText());
            }
            if (!pricePerUnitTxtbox.getText().isEmpty()){
                partadd.setPartPrice(Double.parseDouble(pricePerUnitTxtbox.getText()));
            }
            if (!invTxtbox.getText().isEmpty()){
                partadd.setPartInStock(Integer.parseInt(invTxtbox.getText()));
            }
            if(!minQtyTxtbox.getText().isEmpty()){
                partadd.setMin(Integer.parseInt(minQtyTxtbox.getText()));
            }
            if(!maxQtyTxtbox.getText().isEmpty()){
                partadd.setMax(Integer.parseInt(maxQtyTxtbox.getText()));
            }
            if(!companyNamePrompt.getText().isEmpty()){
                ((InHouse)partadd).setMachineID(Integer.parseInt(companyNamePrompt.getText()));
            }
            Inventory.addPart(partadd);
        }
        else{
            Part partaddout = new OutSource(0, "", 0.0, 0,0,0,"");
            if(partIDTxtbox.getText().isEmpty()){
                partaddout.setPartID(Inventory.getPartIDCount());
            }
            if (!partNameTxtbox.getText().isEmpty()){
                partaddout.setPartName(partNameTxtbox.getText());
            }
            if (!pricePerUnitTxtbox.getText().isEmpty()){
                partaddout.setPartPrice(Double.parseDouble(pricePerUnitTxtbox.getText()));
            }
            if (!invTxtbox.getText().isEmpty()){
                partaddout.setPartInStock(Integer.parseInt(invTxtbox.getText()));
            }
            if(!minQtyTxtbox.getText().isEmpty()){
                partaddout.setMin(Integer.parseInt(minQtyTxtbox.getText()));
            }
            if(!maxQtyTxtbox.getText().isEmpty()){
                partaddout.setMax(Integer.parseInt(maxQtyTxtbox.getText()));
            }
            if(!companyNamePrompt.getText().isEmpty()){
                ((OutSource)partaddout).setCompanyName((companyNamePrompt.getText()));
            }
            Inventory.addPart(partaddout);
        }
        Parent addPartSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(addPartSave);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void addPartCancelButton(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel add part");
        alert.setHeaderText("You are about to return to the Main screen!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Parent addPartCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(addPartCancel);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
