package model;

/**This is the Part Class.  The part class is an abstract class that InHouse and Outsourced will inherit from.*/
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**This is the constructor method.  It will take all the parameters and assign them to a newly created object*/
    Part(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**This is the ID setter.*/
    public void setId(int id) {
        this.id = id;
    }
    /**This is the name setter.*/
    public void setName(String name) {
        this.name = name;
    }
    /**This is the price setter.*/
    public void setPrice(double price) {
        this.price = price;
    }
    /**This is the stock setter.*/
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**This is the min setter.*/
    public void setMin(int min) {
        this.min = min;
    }
    /**This is the max setter.*/
    public void setMax(int max) {
        this.max = max;
    }
    /**This is the ID getter.*/
    public int getId() {
        return id;
    }
    /**This is the name getter.*/
    public String getName() {
        return name;
    }
    /**This is the price getter.*/
    public double getPrice() {
        return price;
    }
    /**This is the stock getter.*/
    public int getStock() {
        return stock;
    }
    /**This is the min getter.*/
    public int getMin() {
        return min;
    }
    /**This is the max getter.*/
    public int getMax() {
        return max;
    }
}
