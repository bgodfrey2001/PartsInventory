package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This is the Product class.
 This class holds the inventory of parts and products.
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**This is the adPart setter.
     @param part is  the part being added to the parts inventory
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**This is the adPart setter.
     @param product is  the product being added to the product inventory
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**This is the lookup parts method.
     @param partID receives the integer part ID being searched for
     @return returns the part if found or null if not
     */
    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID){
                return part;
            }
        }
        return null;
    }

    /**This is the lookup product method.
     @param productID receives the integer product ID being searched for
     @return returns the product if found or null if not
     */
    public static Product lookupProduct(int productID) {
        for(Product product : allProducts) {
            if(product.getId() == productID) {
                return product;
            }
        }
        return null;
    }

    /**This is the lookup parts method.
     @param partName receives the string part name being searched for
     @return returns a list of parts that contain the part name
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> selectedParts = FXCollections.observableArrayList();
        for (Part part : allParts){
            if(part.getName().contains(partName)){
                selectedParts.add(part);
            }
        }
        return selectedParts;
    }

    /**This is the lookup product method.
     @param productName receives the string part name being searched for
     @return returns a list of product that contain the product name
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().contains(productName)){
                selectedProducts.add(product);
            }
        }
        return selectedProducts;
    }

    /**This is the update Part method.
     @param index receives the index of the part needing update
     @param selectedPart is the new part to be placed in that index
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**This is the update product method.
     @param index receives the index of the product needing update
     @param newProduct is the new product to be placed in that index
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**This is the get all parts method.
     @return returns a list of all parts in the inventory.
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**This is the get all products method.
     @return returns a list of all parts in the inventory.
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**This is the update Part method.
     @param part receives the part to be removed.
     @return returns true when deleted
     */
    public static boolean deletePart(Part part) {
        getAllParts().remove(part);
        return true;
    }

    /**This is the update Part method.
     @param product receives the part to be removed.
     @return returns true when deleted
     */
    public static boolean deleteProduct(Product product){
        getAllProducts().remove(product);
        return true;
    }
}
