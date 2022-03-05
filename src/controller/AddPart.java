package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import model.InHouse;
import model.Inventory;
import model.OutSourced;

import java.awt.*;
import java.io.IOException;

/**This is the Add Part class.  This class is the controller for the Add Part screen.*/
public class AddPart {
    private static int partIDGenerator;
    boolean inHouseSelected = true;

    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField invTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField minTextField;
    @FXML private TextField machineCompanyTextField;
    @FXML private Label machineCompanyLabel;


    public void cancelClicked(ActionEvent actionEvent) throws IOException {
        Scene currentScene;
        Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }

    public void saveClicked(ActionEvent actionEvent) {
        partIDGenerator++;
        String name = nameTextField.getText();
        int inventory = Integer.parseInt(invTextField.getText());
        double price = Double.parseDouble(priceTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int min = Integer.parseInt(minTextField.getText());

        if (inHouseSelected == true) {
            int machineID = Integer.parseInt(machineCompanyTextField.getText());
            InHouse inHousePart = new InHouse(partIDGenerator, name, price, inventory, min, max, machineID);
            Inventory.addPart(inHousePart);
        } else {
            String company = machineCompanyTextField.getText();
            OutSourced outSourcedPart = new OutSourced(partIDGenerator, name, price, inventory, min, max, company);
            Inventory.addPart(outSourcedPart);
        }

        Scene currentScene;
        Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void inHouseClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("MachineID");
        inHouseSelected = true;
    }

    public void outsourceClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("Company");
        inHouseSelected = false;
    }
}
