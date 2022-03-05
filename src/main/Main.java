package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

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
        InHouse part = new InHouse(123,"Bike Handle", 25.00, 5, 1, 20, 275);
        Inventory.addPart(part);
        InHouse part1 = new InHouse(34,"Brakes", 99.99, 12, 1, 20, 918);
        Inventory.addPart(part1);
        InHouse part2 = new InHouse(12,"Grips", 9.99, 23, 1, 50, 422);
        Inventory.addPart(part2);
        OutSourced part3 = new OutSourced(3,"Tires", 59.00, 25, 1, 50, "Giant");
        Inventory.addPart(part3);

        ObservableList<Part> p = FXCollections.observableArrayList();
        p.add(part);
        Product prod = new Product(p, 246, "Full Bike", 1500.00, 20, 90, 8);
        Inventory.addProduct(prod);

        ObservableList<Part> p1 = FXCollections.observableArrayList();
        p.add(part);
        Product prod1 = new Product(p, 194, "Wheels", 750.00, 20, 90, 8);
        Inventory.addProduct(prod1);

        ObservableList<Part> p2 = FXCollections.observableArrayList();
        p.add(part);
        p.add(part3);
        Product prod2 = new Product(p, 712, "Front suspension", 100.00, 20, 90, 8);
        Inventory.addProduct(prod2);

        ObservableList<Part> p3 = FXCollections.observableArrayList();
        p.add(part2);
        p.add(part1);
        p.add(part3);
        Product prod3 = new Product(p, 42, "Rear Suspension", 100.00, 20, 90, 8);
        Inventory.addProduct(prod3);


        launch(args);
    }
}
