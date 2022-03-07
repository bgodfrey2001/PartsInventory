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
import model.*;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

/**This is the Add Product class.  This class is the controller for the Add Product screen.
 Logica*/
public class AddProduct implements Initializable {
    //Sets up the available parts TableView
    @FXML private TableView<Part> productAvailablePartsTableView;
    @FXML private TableColumn<Part, Integer> availablePartIDCOL;
    @FXML private TableColumn<Part, String> availablePartNameCol;
    @FXML private TableColumn<Part, Integer> availablePartInvLevelCol;
    @FXML private TableColumn<Part, Double> availablePartPriceCol;


    //Sets up the Required Parts for the product TableView
    @FXML private TableView<Part> productRequiredPartsTableView;
    @FXML private TableColumn<Part, Integer> requiredPartIDCol;
    @FXML private TableColumn<Part, String> requiredPartNameCol;
    @FXML private TableColumn<Part, Integer> requiredInvLevelCol;
    @FXML private TableColumn<Part, Double> requiredPartPriceCol;

    //Sets up the TextFields for saving a part
    @FXML private TextField productNameTextField;
    @FXML private TextField productInvTextField;
    @FXML private TextField productPriceTextField;
    @FXML private TextField productMaxTextField;
    @FXML private TextField productMinTextField;

    @FXML private TextField partSearchBar;

    //ObservableList for building the parts list for the product
    ObservableList<Part> requiredPartsList = FXCollections.observableArrayList();

    /** This is the initialize Method.  This method displays the initial available parts tableview.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayAvailablePartsTable(Inventory.getAllParts());
        productAvailablePartsTableView.setPlaceholder(new Label("Item Not Found"));

    }

    /** This is the Cancel Clicked Method.  This method handles closing the add Product window.
     @param actionEvent is the button being clicked
     */
    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /** This is the Save Clicked Method.  This method handles checking valid inputs and saving the Product then closing the add Product window.
     @param actionEvent is the button being clicked
     */
    public void saveClicked(ActionEvent actionEvent) {
        boolean saveAble = true;
        int highestID = 0;
        String name = productNameTextField.getText();
        int inventory = 0;
        int max = 0;
        int min = 0;
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
            inventory = Integer.parseInt(productInvTextField.getText());
            max = Integer.parseInt(productMaxTextField.getText());
            min = Integer.parseInt(productMinTextField.getText());
        } catch (NumberFormatException numberFormatException) {
            displayError("Invalid Value", "Ensure Proper Values.  Inventory, Min, Max and Machine ID must be integer values.");
            saveAble = false;
        }


        try {
            price = Double.parseDouble(productPriceTextField.getText());
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
            Product product = new Product(requiredPartsList, +highestID + 1 , name,price,inventory,min,max);
            Inventory.addProduct(product);
            Scene currentScene;
            Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }

    }

    /** This is the onSearchKey Typed Method.  This method handles the search bar to find if parts match what is being typed.
     @param keyEvent activates on a key being typed into the bar
     */
    public void onSearchKeyTyped(KeyEvent keyEvent) {
        try {//Checks to see if it's an int, if not it looks for a string
            int id = Integer.parseInt(partSearchBar.getText());
            Part part = Inventory.lookupPart(id);
            ObservableList<Part> foundPart = FXCollections.observableArrayList();
            try { //Checks to see if there is a part already at 0, if not it adds one
                foundPart.set(0, Inventory.lookupPart(id));
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                foundPart.add(Inventory.lookupPart(id));
            }
            foundPart.set(0, Inventory.lookupPart(id));
            displayAvailablePartsTable(foundPart);
        } catch (NumberFormatException numberFormatException) {
            String id = partSearchBar.getText();
            displayAvailablePartsTable(Inventory.lookupPart(id));
        }
    }

    /** This is the addPart clicked Method.  This method handles getting the selected part from the available table into the required parts list
     @param actionEvent detects the button press
     */
    public void partAddClicked(ActionEvent actionEvent) {
        Part part = productAvailablePartsTableView.getSelectionModel().getSelectedItem();
        requiredPartsList.add(part);
        displayRequiredPartsTable();
    }

    /** This is the remove associated part clicked Method.  This method handles removing the selected part from the required parts list.
     @param actionEvent detects the button press
     */
    public void removeAssociatedPartClicked(ActionEvent actionEvent) {
        Part part = productRequiredPartsTableView.getSelectionModel().getSelectedItem();
        Alert errorMessage = new Alert(Alert.AlertType.CONFIRMATION);
        errorMessage.setContentText("Do you wish to remove this part?");
        errorMessage.showAndWait();
        boolean cancelButton = errorMessage.getResult().getButtonData().isCancelButton();
        if (cancelButton) {
        } else {
            requiredPartsList.remove(part);
        }
        displayRequiredPartsTable();
    }

    /** This is the display available parts table Method.  This method handles displaying the available parts in the inventory.
     @param parts is the list of available parts from the inventory or the search method.
     */
    public void displayAvailablePartsTable(ObservableList<Part> parts) {
        productAvailablePartsTableView.setItems(parts);
        availablePartIDCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        availablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        availablePartInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availablePartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This is the display required parts table Method.  This method handles displaying the required parts for the product.
     */
    public void displayRequiredPartsTable() {
        productRequiredPartsTableView.setItems(requiredPartsList);
        requiredPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        requiredPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        requiredInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        requiredPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This is the get all parts Method.  This method handles getting the required parts from the tableview and putting them into the required parts list when saving.
     */
    public void getAllParts() {
        ObservableList<Part> requiredParts = FXCollections.observableArrayList();
        requiredParts = productAvailablePartsTableView.getItems();
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
