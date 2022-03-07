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

    /** This is the main method.  It is what Java looks for to begin the program.  It calls launch so JavaFX launches the app.
     I also use it to place initial data in the inventory
     */
    public static void main(String[] args) {
        InHouse part = new InHouse(1,"Bike Handle", 25.00, 5, 1, 20, 275);
        Inventory.addPart(part);
        InHouse part1 = new InHouse(2,"Brakes", 99.99, 12, 10, 20, 918);
        Inventory.addPart(part1);
        InHouse part2 = new InHouse(3,"Grips", 9.99, 23, 20, 50, 422);
        Inventory.addPart(part2);
        OutSourced part3 = new OutSourced(4,"Tires", 59.00, 25, 5, 50, "Giant");
        Inventory.addPart(part3);
        OutSourced part4 = new OutSourced(5,"Helmet", 34.99, 10, 1, 20, "Giant");
        Inventory.addPart(part4);
        OutSourced part5 = new OutSourced(6,"Knee Pads", 12.99, 5, 1, 10, "Giant");
        Inventory.addPart(part5);

        ObservableList<Part> p = FXCollections.observableArrayList();
        p.add(part);
        p.add(part2);
        p.add(part1);
        p.add(part3);
        Product prod = new Product(p, 1, "Full Bike", 1500.00, 20, 5, 30);
        Inventory.addProduct(prod);

        ObservableList<Part> p1 = FXCollections.observableArrayList();
        p1.add(part3);
        Product prod1 = new Product(p1, 2, "Wheels", 250.00, 5, 1, 8);
        Inventory.addProduct(prod1);

        ObservableList<Part> p2 = FXCollections.observableArrayList();
        p2.add(part);
        p2.add(part2);
        Product prod2 = new Product(p2, 3, "Front bar", 100.00, 5, 1, 10);
        Inventory.addProduct(prod2);

        ObservableList<Part> p3 = FXCollections.observableArrayList();
        p3.add(part4);
        p3.add(part5);
        Product prod3 = new Product(p3, 4, "Safety Equipment", 100.00, 10, 5, 20);
        Inventory.addProduct(prod3);


        launch(args);
    }
}
