/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.CustomerDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
/**
 *
 * @author Zaw Min Htwe
 */
public class CustomerDAO {
    
    //sql connection
    private Connection conn = null;

    public CustomerDAO(){
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public boolean addCustomerDAO(CustomerDTO customerDTO){

        // 💡 Table နာမည်ကို customers လို့ အောက်ကကော အပေါ်ကကော ညီအောင် ညှိလိုက်ပါပြီ
    String checkSql = "SELECT customer_code FROM customers WHERE customer_code=?";
    String insertSql = "INSERT INTO customers(customer_code, customer_name, phone, email, address, township, city, credit_limit, status) VALUES (?,?,?,?,?,?,?,?,?)";
    
    try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
        checkStmt.setString(1, customerDTO.getCustomer_code());
        
        try (ResultSet rs = checkStmt.executeQuery()) {
            if (rs.next()) {
                return false; // Code ရှိပြီးသားဖြစ်လို့ false ပြန်မယ်
            }
        }
        
        // စစ်ဆေးပြီးလို့ မရှိတာ သေချာမှ Insert လုပ်မယ်
        try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setString(1, customerDTO.getCustomer_code());
            ps.setString(2, customerDTO.getCustomer_name());
            ps.setString(3, customerDTO.getPhone());
            ps.setString(4, customerDTO.getEmail()); // UI က လှမ်းပေးလိုက်တဲ့ စာသားအလွတ် ဝင်သွားမည်
            ps.setString(5, customerDTO.getAddress());
            ps.setString(6, customerDTO.getTownship());
            ps.setString(7, customerDTO.getCity());
            ps.setBigDecimal(8, customerDTO.getCreditLimit());
            ps.setString(9, customerDTO.getStatus());
            
            ps.executeUpdate();
            return true; // အောင်မြင်ရင် true ပြန်မယ်
        }
        
    } catch (Exception e) {
        // 💡 NetBeans ရဲ့ အောက်ခြေ Output Console မှာ ဘာ Error တက်လဲဆိုတာ အကဲခတ်လို့ရအောင် ပြပေးထားတာပါ
        System.out.println("--- Database Error Log ---");
        e.printStackTrace(); 
        return false;
    }
    }
    
    
    
    
}
