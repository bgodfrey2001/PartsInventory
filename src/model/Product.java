package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This is the Product class.
 This is for creating products that are a combination of Parts.
 */
public class Product {
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**This is the Product constructor.
     This will build a new Product object.
     @param associatedParts is a list of Part objects that are required to create this product
     @param id is the product ID
     @param name is the name of the product
     @param stock is how many are on hand
     @param min is the minimum inventory level
     @param max is the maximum inventory level
     @param price is the price of the product
     */
    public Product(ObservableList<Part> associatedParts, int id, String name, double price, int stock, int min, int max){
        this.associatedParts = associatedParts;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() { return max; }

    public double getPrice() {
        return price;
    }

    public static ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public static void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        return true;  //CHANGE THIS!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
}
