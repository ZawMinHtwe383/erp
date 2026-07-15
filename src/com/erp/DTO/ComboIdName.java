//to get id and name for combobox
package com.erp.DTO;

/**
 *
 * @author Zaw Min Htwe
 */
public class ComboIdName {

    private int id;
    private String name;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ComboIdName(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public ComboIdName(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // to show name for combo
    @Override
    public String toString() {
        return name;
    }
}
