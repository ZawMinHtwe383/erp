/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DTO;

/**
 *
 * @author Zaw Min Htwe
 */
public class UnitDTO {
    private int unit_id;
    private String 	unit_name,	description;
    

    
    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

   
    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    // UnitDTO.java ထဲတွင် အောက်ဆုံး၌ ထည့်ရန်
@Override
public String toString() {
    return this.unit_name; // Combo Box ထဲမှာ "ဖာ" ၊ "ဒါဇင်" စတဲ့ နာမည်ပဲ ပြပေးမည့် ကုဒ်
  
}


}
