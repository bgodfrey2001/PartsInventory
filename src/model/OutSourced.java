package model;
/**This is the OutSourced class.
 This is for parts that are outsourced to another vendor.  It provides an additional field for storing the company's name.
 */
public class OutSourced extends Part{
    String companyName;
    /**
     * This is the constructor method.  It will take all the parameters and assign them to a newly created object
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
    OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
}
