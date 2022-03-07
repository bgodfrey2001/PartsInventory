package model;

/**This is the InHouse class.
 This is for parts that are sourced from in-house inventories.  It provides an additional field for storing the machineID.
 */
public class InHouse extends Part{
    private int machineID;

    /**
     * This is the constructor method.  It will take all the parameters and assign them to a newly created object
     *
     * @param id partID
     * @param name partName
     * @param price part Price
     * @param stock inventory level
     * @param min minimum inventory level
     * @param max maximum inventory level
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**This is the ID getter.
     @param machineID is  the Machine ID asked for with in-house products
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    /**This is the ID getter.
     @return machineID returns the machine ID. */
    public int getMachineID() {
        return machineID;
    }
}
