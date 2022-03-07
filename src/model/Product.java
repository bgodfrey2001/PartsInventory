package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This is the Product class.
 This is for creating products that are a combination of Parts.
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
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

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param associatedParts the associated parts list to set
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the max
     */
    public int getMax() { return max; }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the associated parts list
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }

    /**
     * @param part the part to add
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * @param selectedAssociatedPart the part to remove
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        boolean removed = associatedParts.remove(selectedAssociatedPart);
        return removed;
    }
}
