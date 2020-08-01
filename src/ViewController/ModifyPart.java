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
import static ViewController.MainScreen.modifyPartIndex;
import static Model.Inventory.getAllPart;

public class ModifyPart  implements Initializable {
    public RadioButton modifyPartInHouse;
    public RadioButton modifyPartOutsourced;
    public Button modifyPartCancelButton;
    public Button modifyPartSaveButton;
    public Label companyNameLabel;
    public TextField companyNamePrompt;
    public boolean inHouse;
    public TextField partIDTxtBox;
    public TextField invTxtbox;
    public TextField priceTxtbox;
    public TextField minTxtbox;
    public TextField maxTxtbox;
    public TextField partNameTxtbox;
    int partIndx = modifyPartIndex();
    private int partID;

    public void modifyPartSaveButton(MouseEvent mouseEvent) throws IOException {
        if (modifyPartInHouse.isSelected()) {
            Part partmodify = new InHouse(0, "", 0.0, 0,0,0,0);
            if(!partIDTxtBox.getText().isEmpty()){
                partmodify.setPartID(Integer.parseInt(partIDTxtBox.getText()));
            }
            if (!partNameTxtbox.getText().isEmpty()){
                partmodify.setPartName(partNameTxtbox.getText());
            }
            if (!priceTxtbox.getText().isEmpty()){
                partmodify.setPartPrice(Double.parseDouble(priceTxtbox.getText()));
            }
            if (!invTxtbox.getText().isEmpty()){
                partmodify.setPartInStock(Integer.parseInt(invTxtbox.getText()));
            }
            if(!minTxtbox.getText().isEmpty()){
                partmodify.setMin(Integer.parseInt(minTxtbox.getText()));
            }
            if(!maxTxtbox.getText().isEmpty()){
                partmodify.setMax(Integer.parseInt(maxTxtbox.getText()));
            }
            if(!companyNamePrompt.getText().isEmpty()){
                ((InHouse)partmodify).setMachineID(Integer.parseInt(companyNamePrompt.getText()));
            }
            Inventory.updatePart(partIndx, partmodify);
        }
        else{
            Part partmodifyout = new OutSource(0, "", 0.0, 0,0,0,"");
            if(!partIDTxtBox.getText().isEmpty()){
                partmodifyout.setPartID(Integer.parseInt(partIDTxtBox.getText()));
            }
            if (!partNameTxtbox.getText().isEmpty()){
                partmodifyout.setPartName(partNameTxtbox.getText());
            }
            if (!priceTxtbox.getText().isEmpty()){
                partmodifyout.setPartPrice(Double.parseDouble(priceTxtbox.getText()));
            }
            if (!invTxtbox.getText().isEmpty()){
                partmodifyout.setPartInStock(Integer.parseInt(invTxtbox.getText()));
            }
            if(!minTxtbox.getText().isEmpty()){
                partmodifyout.setMin(Integer.parseInt(minTxtbox.getText()));
            }
            if(!maxTxtbox.getText().isEmpty()){
                partmodifyout.setMax(Integer.parseInt(maxTxtbox.getText()));
            }
            if(!companyNamePrompt.getText().isEmpty()){
                ((OutSource)partmodifyout).setCompanyName((companyNamePrompt.getText()));
            }
            Inventory.updatePart(partIndx, partmodifyout);
        }
        Parent modifyPartSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(modifyPartSave);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
    }

    public void modifyPartCancelButton(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel modify part");
        alert.setHeaderText("You are about to return to the Main screen!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Parent modifyPartCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(modifyPartCancel);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else{
            return;
        }
    }

    public void modifyPartInHouse(ActionEvent actionEvent) {
        inHouse = true;
        companyNameLabel.setText("Machine ID");
        companyNamePrompt.setPromptText("Machine ID");
        modifyPartOutsourced.setSelected(false);
    }

    public void modifyPartOutsourced(ActionEvent actionEvent) {
        inHouse = false;
        companyNameLabel.setText("Company Name");
        companyNamePrompt.setPromptText("Company Name");
        modifyPartInHouse.setSelected(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Part part = getAllPart().get(partIndx);
        partID = getAllPart().get(partIndx).getPartID();
        partIDTxtBox.setText(Integer.toString(part.getPartID()));
        partNameTxtbox.setText(part.getPartName());
        invTxtbox.setText(Integer.toString(part.getPartInStock()));
        priceTxtbox.setText(Double.toString(part.getPartPrice()));
        minTxtbox.setText(Integer.toString(part.getMin()));
        maxTxtbox.setText(Integer.toString(part.getMax()));

        if (part instanceof InHouse){
            companyNameLabel.setText("Machine ID");
            companyNamePrompt.setText(Integer.toString(getAllPart().get(partIndx).getPartID()));
            modifyPartInHouse.setSelected(true);
        }
        else{
            companyNamePrompt.setText(((OutSource)getAllPart().get(partIndx)).getCompanyName());
            modifyPartOutsourced.setSelected(true);
        }

        }

}





