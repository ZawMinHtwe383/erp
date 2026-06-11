/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.CustomerDTO;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author Zaw Min Htwe
 */
public class CustomerDAO {
    
    
    public boolean addCustomerDAO(CustomerDTO customerDTO){
        try {
            ResultSet rs;
            PreparedStatement checkStmt;
            String checkSql = "Select customer_code from customers where customer_code=? ";
         
        } catch (Exception e) {
        }
        
        
        
        
        return true;
    }
    
    
    
    
}
