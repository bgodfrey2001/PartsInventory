package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

/**This is the Add Product class.  This class is the controller for the Add Product screen.
 Logical Error encountered was having the partsRequiredList be = the product.getallassociatedparts().  This caused the product itself to be updated rather than
 the partsrequiredlist, meaning even if you hit cancel, it still updated the list.  I fixed that problem by changing the partsRequiredList to be filled by adding one
 part at a time from the list in a for loop.*/
public class ModifyProduct implements Initializable {

    ObservableList<Part> partsRequiredList = FXCollections.observableArrayList();
    int productID;
    @FXML private TextField partSearchTextField;

    @FXML private TextField modifyProductIDTextField;
    @FXML private TextField modifyProductNameTextField;
    @FXML private TextField modifyProductInvTextField;
    @FXML private TextField modifyProductPriceTextField;
    @FXML private TextField modifyProductMaxTextField;
    @FXML private TextField modifyProductMinTextField;

    @FXML private TableView<Part> modifyProductAvailablePartsTableView;
    @FXML private TableColumn<Part, Integer> availablePartIDCol;
    @FXML private TableColumn<Part, String> availablePartNameCol;
    @FXML private TableColumn<Part, Integer> availablePartInvLevelCol;
    @FXML private TableColumn<Part, Double> availablePartPriceCol;

    @FXML private TableView<Part> modifyProductRequiredPartsTableView;
    @FXML private TableColumn<Part, Integer> requiredPartIDCol;
    @FXML private TableColumn<Part, String> requiredPartNameCol;
    @FXML private TableColumn<Part, Integer> requiredInvLevelCol;
    @FXML private TableColumn<Part, Double> requiredPartPriceCol;

    /** This is the initialize Method.  This method displays the initial available parts tableview as well as placing the data into the respective textfields and required
     parts tableview
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyProductAvailablePartsTableView.setPlaceholder(new Label("Part not found"));
        displayAvailableParts(Inventory.getAllParts());
        productID = MainForm.productID;
        Product product = Inventory.lookupProduct(productID); //Gets the productID from the selected product on the MainForm
        //partsRequiredList = product.getAllAssociatedParts();
        for (Part parts : product.getAllAssociatedParts()) {
            partsRequiredList.add(parts);
        }
        modifyProductIDTextField.setText(String.valueOf(product.getId()));
        modifyProductNameTextField.setText(product.getName());
        modifyProductInvTextField.setText(String.valueOf(product.getStock()));
        modifyProductPriceTextField.setText(String.valueOf(product.getPrice()));
        modifyProductMaxTextField.setText(String.valueOf(product.getMax()));
        modifyProductMinTextField.setText(String.valueOf(product.getMin()));
        displayRequiredParts(partsRequiredList);
    }

    /** This is the Save Clicked Method.  This method handles checking valid inputs and saving the Product then closing the add Product window.
     @param actionEvent is the button being clicked
     */
    public void saveClicked(ActionEvent actionEvent) {
        boolean saveAble = true;
        String name = modifyProductNameTextField.getText();
        int inventory = 0;
        int max = 0;
        int min = 0;
        double price = 0;
        ObservableList<Part> partsIDSearch = Inventory.getAllParts();

        if (name.length() < 1) {
            displayError("Invalid Value", "Enter a part name.");
            saveAble = false;
        }

        try {
            inventory = Integer.parseInt(modifyProductInvTextField.getText());
            max = Integer.parseInt(modifyProductMaxTextField.getText());
            min = Integer.parseInt(modifyProductMinTextField.getText());
        } catch (NumberFormatException numberFormatException) {
            displayError("Invalid Value", "Ensure Proper Values.  Inventory, Min, Max and Machine ID must be integer values.");
            saveAble = false;
        }


        try {
            price = Double.parseDouble(modifyProductPriceTextField.getText());
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
            Product product = new Product(partsRequiredList, productID, name, price, inventory, min, max);
            int productIndex = Inventory.getAllProducts().indexOf(Inventory.lookupProduct(productID));
            Inventory.updateProduct(productIndex, product);
            Scene currentScene;
            Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }


    }


    /** This is the partSearch Typed Method.  This method handles the search bar to find if parts match what is being typed.
     @param keyEvent activates on a key being typed into the bar
     */
    public void partSearchTextFieldTyped(KeyEvent keyEvent) {
        ObservableList<Part> partsList = FXCollections.observableArrayList();
        try {
            int partID = Integer.parseInt(partSearchTextField.getText());
            Part part = Inventory.lookupPart(partID);

            try {
                partsList.set(0, part);
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                partsList.add(part);
            }

        } catch (NumberFormatException numberFormatException){
            String partID = partSearchTextField.getText();
            partsList = Inventory.lookupPart(partID);
        }
        displayAvailableParts(partsList);
    }

    /** This is the addPart clicked Method.  This method handles getting the selected part from the available table into the required parts list
     @param actionEvent detects the button press
     */
    public void addPartClicked(ActionEvent actionEvent) {
        Product product = Inventory.lookupProduct(productID);
        partsRequiredList.add(modifyProductAvailablePartsTableView.getSelectionModel().getSelectedItem());
        displayRequiredParts(partsRequiredList);
    }

    /** This is the remove associated part clicked Method.  This method handles removing the selected part from the required parts list.
     @param actionEvent detects the button press
     */
    public void removeAssociatedPartClicked(ActionEvent actionEvent) {
        Part part = modifyProductRequiredPartsTableView.getSelectionModel().getSelectedItem();
        Alert errorMessage = new Alert(Alert.AlertType.CONFIRMATION);
        errorMessage.setContentText("Do you wish to remove this part?");
        errorMessage.showAndWait();
        boolean cancelButton = errorMessage.getResult().getButtonData().isCancelButton();
        if (cancelButton) {
        } else {
            partsRequiredList.remove(part);
        }
        displayRequiredParts(partsRequiredList);
    }

    /** This is the Cancel Clicked Method.  This method handles closing the add Product window.
     @param actionEvent is the button being clicked
     */
    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /** This is the display available parts table Method.  This method handles displaying the available parts in the inventory.
     @param parts is the list of available parts from the inventory or the search method.
     */
    public void displayAvailableParts(ObservableList<Part> parts) {
        modifyProductAvailablePartsTableView.setItems(parts);
        availablePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        availablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        availablePartInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availablePartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This is the display required parts table Method.  This method handles loading and displaying the required parts for the product.
     @param parts is the list of available parts from the required parts list to be displayed
     */
    public void displayRequiredParts(ObservableList<Part> parts) {
        modifyProductRequiredPartsTableView.setItems(parts);
        requiredPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        requiredPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        requiredInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        requiredPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
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
