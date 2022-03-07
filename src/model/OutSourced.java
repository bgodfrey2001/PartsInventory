package model;
/**This is the OutSourced class.
 This is for parts that are outsourced to another vendor.  It provides an additional field for storing the company's name.
 */
public class OutSourced extends Part{
    String companyName;
    /**
     * This is the constructor method.  It will take all the parameters and assign them to a newly created object
     *
     * @param id id number for the part
     * @param name name for the part
     * @param price price for the part
     * @param stock inventory of the part
     * @param min minimum inventory of the part
     * @param max maximum inventory of the part
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName() {return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}
