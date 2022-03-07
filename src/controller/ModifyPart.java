package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Part;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**This is the Modify Part class.  This class is the controller for the Modify Part screen.*/
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

    /** This is the initialize Method.  This method displays the initial values for the various input textfields
     */
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

    /** This is the Cancel Clicked Method.  This method handles closing the add part window when cancel is clicked.
     @param actionEvent is the button being clicked
     */
    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /** This is the inHouse Clicked Method.  This method changes the text of the machineID and Company label to display the required text.
     @param actionEvent is the button being clicked
     */
    public void inHouseClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("Machine-ID");
        inHouseSelected = true;
    }

    /** This is the outSource Clicked Method.  This method changes the text of the machineID and Company label to display the required text.
     @param actionEvent is the button being clicked
     */
    public void outsourcedClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("Company Name");
        inHouseSelected = false;
    }

    /** This is the Save Clicked Method.  This method handles checking valid inputs and saving the Part then closing the add part window.
     @param actionEvent is the button being clicked
     */
    public void saveClicked(ActionEvent actionEvent) {
        boolean saveAble = true;
        String name = modifyPartNameTextField.getText();
        int partID = Integer.parseInt(modifyPartIDTextField.getText());
        int inventory = 0;
        int max = 0;
        int min = 0;
        int machineID = 0;
        String company = "";
        double price = 0;
        ObservableList<Part> partsIDSearch = Inventory.getAllParts();

        if (name.length() < 1) {
            displayError("Invalid Value", "Enter a part name.");
            saveAble = false;
        }

        try {
            inventory = Integer.parseInt(modifyPartInvTextField.getText());
            max = Integer.parseInt(modifyPartMaxTextField.getText());
            min = Integer.parseInt(modifyPartMinTextField.getText());
            if (inHouseSelected) {
                machineID = Integer.parseInt(modifyPartMachineCompanyTextField.getText());
            }
        } catch (NumberFormatException numberFormatException) {
            displayError("Invalid Value", "Ensure Proper Values.  Inventory, Min, Max and Machine ID must be integer values.");
            saveAble = false;
        }
        if (!inHouseSelected) {
            company = modifyPartMachineCompanyTextField.getText();
            if (company.length() < 1) {
                displayError("Invalid Company Name", "Enter a company name.");
                saveAble = false;
            }
        }

        try {
            price = Double.parseDouble(modifyPartPriceTextField.getText());
        } catch (NumberFormatException numberFormatException) {
            displayError("Invalid Price", "Price must be a number value");
            saveAble = false;
        }
        if (max < min) {
            displayError("Check minimum and maximum fields", "Minimum value cannot exceed maximum value");
            saveAble = false;
        }
        if (inventory > max || inventory < min) {
            displayError("Check inventory levels", "Inventory must be between the minimum and maximum values");
            saveAble = false;
        }


        if (saveAble) {
            if (inHouseSelected == true) {
                InHouse inHousePart = new InHouse(partID, name, price, inventory, min, max, machineID);
                Inventory.updatePart(index, inHousePart);
            } else {
                OutSourced outSourcedPart = new OutSourced(partID, name, price, inventory, min, max, company);
                Inventory.updatePart(index, outSourcedPart);
            }
            Scene currentScene;
            Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /** This is the displayError Method.  This method displays an error when incorrect data is being entered.
     @param header desired header text
     @param body desired body text
     */
    public void displayError(String header, String body) {
        Alert errorMessage = new Alert(Alert.AlertType.ERROR);
        errorMessage.setHeaderText(header);
        errorMessage.setContentText(body);
        errorMessage.showAndWait();
    }
}
