package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the controller for the Main Form.
 It displays parts and products, as well as allows you to add, modify and delete them.*/
public class MainForm implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
}
