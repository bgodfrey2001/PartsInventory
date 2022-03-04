package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the controller for the Main Form.
 It displays parts and products, as well as allows you to add, modify and delete them.*/
public class MainForm implements Initializable {
    public MainForm() {

    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
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
        newWindow("/view/ModifyPart.fxml", "Modify Part");
    }

    /**This is the modify products method.  When the modify button is clicked this method is activated, causing it to open the modify products window.  It calls the newWindow method to do that.*/
    public void modifyProductClicked(ActionEvent actionEvent) throws IOException{
        newWindow("/view/ModifyProduct.fxml", "Modify Product");
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

    public void exitClicked(ActionEvent actionEvent) {
        System.exit(0);
    }
}
