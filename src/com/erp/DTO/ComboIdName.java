//to get id and name for combobox
package com.erp.DTO;

/**
 *
 * @author Zaw Min Htwe
 */
public class ComboIdName {
    private int id;
    private String name;

    public ComboIdName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    // to show name for combo
    @Override
    public String toString() {
        return name; 
    }
}
