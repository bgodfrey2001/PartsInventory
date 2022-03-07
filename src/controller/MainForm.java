package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the controller for the Main Form.
 It displays parts and products, as well as allows you to add, modify and delete them.
 A future enhancement I would add to this project would be to allow users to update the inventory from the MainForm.  This would allow for a faster way to
 update the inventory when changes occur.  An example of this would be the store sells a part, the person would then just go to the main screen, and click a "-" button
 to reduce the inventory by 1.
 */
public class MainForm implements Initializable {
    public MainForm() {

    }

    public static int partID;
    public static int productID;

    @FXML private TextField partsSearchTextField;
    @FXML private TextField productsSearchTextField;

    //Creates the Parts TableView
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partIDCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partInventoryLevelCol;
    @FXML private TableColumn<Part, Double> partPriceCol;

    //Creates the Products TableView
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> productIDCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, Integer> productInventoryLevelCol;
    @FXML private TableColumn<Product, Double> productPriceCol;

    /** This is the initialize Method.  This method displays the initial MainScreen with all tables filled out.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setPlaceholder(new Label("Part Not Found"));
        productsTable.setPlaceholder(new Label("Product Not Found"));
        displayPartsTable(Inventory.getAllParts());
        displayProductsTable(Inventory.getAllProducts());
    }

    /**This is the add parts method.  When the Add button is clicked this method is activated, causing it to open the add parts window.  It calls the newWindow method to do that.*/
    public void addPartsClicked(ActionEvent actionEvent) throws IOException {
        newWindow("/view/AddPart.fxml", "Add Part");
    }
    /**This is the add products method.  When the Add button is clicked this method is activated, causing it to open the add products window.  It calls the newWindow method to do that.*/
    public void addProductClicked(ActionEvent actionEvent) throws IOException{
        newWindow("/view/AddProduct.fxml", "Add Product");
    }
    /**This is the modify parts method.  When the modify button is clicked this method is activated, causing it to open the modify parts window.  It calls the newWindow method to do that.*/
    public void modifyPartClicked(ActionEvent actionEvent) throws IOException{
        partID = partsTable.getSelectionModel().getSelectedItem().getId();
        newWindow("/view/ModifyPart.fxml", "Modify Part");
    }

    /**This is the modify products method.  When the modify button is clicked this method is activated, causing it to open the modify products window.  It calls the newWindow method to do that.*/
    public void modifyProductClicked(ActionEvent actionEvent) throws IOException{
        productID = productsTable.getSelectionModel().getSelectedItem().getId();
        newWindow("/view/ModifyProduct.fxml", "Modify Product");
    }

    /** This is the exitClicked Method.  This method exits the app..
     */
    public void exitClicked(ActionEvent actionEvent) {
        System.exit(0);
    }

    /** This is the partLetter Typed Method.  This method handles the parts search bar to find if parts match what is being typed.
     @param keyEvent activates on a key being typed into the bar
     */
    public void partsLetterTyped(KeyEvent keyEvent) {
        ObservableList<Part> partList = FXCollections.observableArrayList();
        try {
            int partID = Integer.parseInt(partsSearchTextField.getText());
            Part part = Inventory.lookupPart(partID);
            try {
                partList.set(0,part);
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                partList.add(part);
            }
        } catch (NumberFormatException numberFormatException) {
            String partID = partsSearchTextField.getText();
            partList = Inventory.lookupPart(partID);
        }
        displayPartsTable(partList);
    }

    /** This is the productLetter Typed Method.  This method handles the product search bar to find if products match what is being typed.
     @param keyEvent activates on a key being typed into the bar
     */
    public void productsLetterTyped(KeyEvent keyEvent) {
        //displayProductsTable(Inventory.lookupProduct(productsSearchTextField.getText()));
        ObservableList<Product> productList = FXCollections.observableArrayList();
        try {
            int productID = Integer.parseInt(productsSearchTextField.getText());
            Product product = Inventory.lookupProduct(productID);

            try {
                productList.set(0, product);
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                productList.add(product);
            }

        } catch (NumberFormatException numberFormatException){
            String productID = productsSearchTextField.getText();
            productList = Inventory.lookupProduct(productID);
        }
        displayProductsTable(productList);
    }
    /**This is the new window method.  It is called to open new windows.
     @param viewAddress gives the address that the view is located in
     @param newTitle gives the title the new window will display
     */
    public void newWindow(String viewAddress, String newTitle) throws IOException{
        Stage openingWindow = new Stage();
        openingWindow.setTitle(newTitle);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewAddress));
        openingWindow.setScene(new Scene(loader.load()));
        openingWindow.show();
    }

    /** This is the display available parts table Method.  This method handles displaying the available parts in the inventory.
     @param parts is the list of available parts from the inventory or the search method.
     */
    public void displayPartsTable(ObservableList<Part> parts) {
        partsTable.setItems(parts);
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    /** This is the display available products table Method.  This method handles displaying the available products in the inventory.
     @param products is the list of available products from the inventory or the search method.
     */
    public void displayProductsTable(ObservableList<Product> products) {
        productsTable.setItems(products);
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This is the delete parts clicked Method.  This method handles deleting parts in the inventory.
     @param actionEvent is the clicked button actionEvent
     */
    public void deletePartClicked(ActionEvent actionEvent) {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        Alert errorMessage = new Alert(Alert.AlertType.CONFIRMATION);
        errorMessage.setContentText("Do you wish to delete this part?");
        errorMessage.showAndWait();
        boolean cancelButton = errorMessage.getResult().getButtonData().isCancelButton();
        if (cancelButton) {
        } else {
            Inventory.deletePart(part);
        }
    }

    /** This is the delete products clicked Method.  This method handles deleting products in the inventory.
     @param actionEvent is the clicked button actionEvent
     */
    public void deleteProductClicked(ActionEvent actionEvent) {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if(product.getAllAssociatedParts().isEmpty()) {
            Alert errorMessage = new Alert(Alert.AlertType.CONFIRMATION);
            errorMessage.setContentText("Do you wish to delete this product?");
            errorMessage.showAndWait();
            boolean cancelButton = errorMessage.getResult().getButtonData().isCancelButton();
            if (cancelButton) {
            } else {
                Inventory.deleteProduct(product);
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR);
            errorMessage.setContentText("Associated Parts list must be empty to delete");
            errorMessage.showAndWait();
        }
    }
}
