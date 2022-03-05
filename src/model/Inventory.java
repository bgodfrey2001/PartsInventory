package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This is the Product class.
 This class holds the inventory of parts and products.
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID){
                return part;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productID) {
        for(Product product : allProducts) {
            if(product.getId() == productID) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> selectedParts = FXCollections.observableArrayList();
        for (Part part : allParts){
            if(part.getName().contains(partName)){
                selectedParts.add(part);
            }
        }
        return selectedParts;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().contains(productName)){
                selectedProducts.add(product);
            }
        }
        return selectedProducts;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {

    }


    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    //public static Part lookupPart(int pardID){

    //}
}
