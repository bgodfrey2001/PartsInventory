package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

/**
 This is the main class.  It launches the application.
 */
public class Main extends Application {
    /** This is the start method.  It sets the main stage up and displays it.*/
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Inventory");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** This is the main method.  It is what Java looks for to begin the program.  It calls launch so JavaFX launches the app.*/
    public static void main(String[] args) {
        InHouse part = new InHouse(1234,"Bike Handle", 6.99, 12, 1, 20, 275);
        Inventory.addPart(part);

        ObservableList<Part> p = FXCollections.observableArrayList();
        p.add(part);
        Product prod = new Product(p, 246, "Full Bike", 100.00, 20, 90, 8);
        Inventory.addProduct(prod);
        launch(args);
    }
}
