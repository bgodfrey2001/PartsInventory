package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Part;

import java.awt.*;
import java.beans.Introspector;
import java.io.IOException;

/**This is the Add Part class.  This class is the controller for the Add Part screen.*/
public class AddPart {
    boolean inHouseSelected = true;

    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField invTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField minTextField;
    @FXML private TextField machineCompanyTextField;
    @FXML private Label machineCompanyLabel;

/** This is the Cancel Clicked Method.  This method handles closing the add part window when cancel is clicked.
 @param actionEvent is the button being clicked
 */
    public void cancelClicked(ActionEvent actionEvent) throws IOException {
        Scene currentScene;
        Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }

    /** This is the Save Clicked Method.  This method handles checking valid inputs and saving the Part then closing the add part window.
     @param actionEvent is the button being clicked
     */
    public void saveClicked(ActionEvent actionEvent) {
        boolean saveAble = true;
        int highestID = 0;
        String name = nameTextField.getText();
        int inventory = 0;
        int max = 0;
        int min = 0;
        int machineID = 0;
        String company = "";
        double price = 0;
        ObservableList<Part> partsIDSearch = Inventory.getAllParts();
        for (Part part : partsIDSearch) {
            if (part.getId() > highestID) {
                highestID = part.getId();
            }
        }

        if (name.length() < 1) {
            displayError("Invalid Value", "Enter a part name.");
            saveAble = false;
        }

        try {
            inventory = Integer.parseInt(invTextField.getText());
            max = Integer.parseInt(maxTextField.getText());
            min = Integer.parseInt(minTextField.getText());
            if (inHouseSelected) {
                machineID = Integer.parseInt(machineCompanyTextField.getText());
            }
        } catch (NumberFormatException numberFormatException) {
            displayError("Invalid Value", "Ensure Proper Values.  Inventory, Min, Max and Machine ID must be integer values.");
            saveAble = false;
        }
        if (!inHouseSelected) {
            company = machineCompanyTextField.getText();
            if (company.length() < 1) {
                displayError("Invalid Company Name", "Enter a company name.");
                saveAble = false;
            }
        }

        try {
            price = Double.parseDouble(priceTextField.getText());
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
                InHouse inHousePart = new InHouse(highestID + 1, name, price, inventory, min, max, machineID);
                Inventory.addPart(inHousePart);
            } else {

                OutSourced outSourcedPart = new OutSourced(highestID + 1, name, price, inventory, min, max, company);
                Inventory.addPart(outSourcedPart);
            }
            Scene currentScene;
            Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }

    }

    /** This is the inHouse Clicked Method.  This method changes the text of the machineID and Company label to display the required text.
     @param actionEvent is the button being clicked
     */
    public void inHouseClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("MachineID");
        inHouseSelected = true;
    }

    /** This is the outSource Clicked Method.  This method changes the text of the machineID and Company label to display the required text.
     @param actionEvent is the button being clicked
     */
    public void outsourceClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("Company");
        inHouseSelected = false;
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
