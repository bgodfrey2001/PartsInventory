package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Part;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable{
    private  int index;
    private boolean inHouseSelected;
    @FXML private Label machineCompanyLabel;
    @FXML private TextField modifyPartIDTextField;
    @FXML private TextField modifyPartNameTextField;
    @FXML private TextField modifyPartInvTextField;
    @FXML private TextField modifyPartPriceTextField;
    @FXML private TextField modifyPartMaxTextField;
    @FXML private TextField modifyPartMinTextField;
    @FXML private TextField modifyPartMachineCompanyTextField;
    @FXML private RadioButton inHouseRB;
    @FXML private RadioButton outsourcedRB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Part part = Inventory.lookupPart(MainForm.partID);
        index = Inventory.getAllParts().indexOf(part);
        modifyPartIDTextField.setText(String.valueOf(part.getId()));
        modifyPartNameTextField.setText(part.getName());
        modifyPartInvTextField.setText(String.valueOf(part.getStock()));
        modifyPartPriceTextField.setText(String.valueOf(part.getPrice()));
        modifyPartMaxTextField.setText(String.valueOf(part.getMax()));
        modifyPartMinTextField.setText(String.valueOf(part.getMin()));
        boolean isInstance = InHouse.class.isAssignableFrom(part.getClass());
        if(isInstance == true) {
            machineCompanyLabel.setText("Machine-ID");
            inHouseRB.setSelected(true);
            inHouseSelected = true;
            modifyPartMachineCompanyTextField.setText(String.valueOf(((InHouse) part).getMachineID()));
        } else {
            machineCompanyLabel.setText("Company Name");
            modifyPartMachineCompanyTextField.setText(((OutSourced)part).getCompanyName());
            outsourcedRB.setSelected(true);
            inHouseSelected = false;
        }
    }

    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void inHouseClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("Machine-ID");
        inHouseSelected = true;
    }

    public void outsourcedClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("Company Name");
        inHouseSelected = false;
    }

    public void saveClicked(ActionEvent actionEvent) {
        int id = Inventory.getAllParts().get(index).getId();
        String name = modifyPartNameTextField.getText();
        int inventory = Integer.parseInt(modifyPartInvTextField.getText());
        double price = Double.parseDouble(modifyPartPriceTextField.getText());
        int max = Integer.parseInt(modifyPartMaxTextField.getText());
        int min = Integer.parseInt(modifyPartMinTextField.getText());

        if (inHouseSelected == true) {
            int machineID = Integer.parseInt(modifyPartMachineCompanyTextField.getText());
            InHouse inHousePart = new InHouse(id, name, price, inventory, min, max, machineID);
            Inventory.updatePart(index, inHousePart);
        } else {
            String company = modifyPartMachineCompanyTextField.getText();
            OutSourced outSourcedPart = new OutSourced(id, name, price, inventory, min, max, company);
            Inventory.updatePart(index, outSourcedPart);
        }
        Scene currentScene;
        Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


}
