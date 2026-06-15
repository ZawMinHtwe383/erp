/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.SupplierDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Zaw Min Htwe
 */
public class SupplierDAO {

     //sql connection
    private Connection conn = null;

    public SupplierDAO(){
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
  

    public boolean addSupplierDAO(SupplierDTO supplierDTO) {
      // 💡 Table နာမည်ကို ဒေတာဘေ့စ်ထဲကအတိုင်း suppliers လို့ ပေးထားပါတယ်
    String checkSql = "SELECT supplier_code FROM suppliers WHERE supplier_code=?";
    String insertSql = "INSERT INTO suppliers(supplier_code, supplier_name, phone, email, address, township, city, status) VALUES (?,?,?,?,?,?,?,?)";
    
    try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
        checkStmt.setString(1, supplierDTO.getSupplier_code());
        
        try (ResultSet rs = checkStmt.executeQuery()) {
            if (rs.next()) {
                return false; // Supplier Code ရှိပြီးသားဖြစ်က false ပြန်မည်
            }
        }
        
        // Code အသစ်ဖြစ်ပါက အောက်ပါအတိုင်း Insert ဆက်လုပ်မည်
        try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setString(1, supplierDTO.getSupplier_code());
            ps.setString(2, supplierDTO.getSupplier_name());
            ps.setString(3, supplierDTO.getPhone());
            ps.setString(4, supplierDTO.getEmail());
            ps.setString(5, supplierDTO.getAddress());
            ps.setString(6, supplierDTO.getTownship());
            ps.setString(7, supplierDTO.getCity());
            ps.setString(8, supplierDTO.getStatus());
            
            ps.executeUpdate();
            return true; // အောင်မြင်စွာ သွင်းပြီးပါက true ပြန်မည်
        }
        
    } catch (Exception e) {
        System.out.println("--- Supplier Database Error Log ---");
        e.printStackTrace(); 
        return false;
    }
    }
    
    // ၁။ READ - Supplier ဒေတာအားလုံးကို ဇယားထဲပြရန် ဆွဲထုတ်ခြင်း
public ResultSet getSupplierDataDAO() {
    String sql = "SELECT supplier_code, supplier_name, phone, email, address, township, city, status FROM suppliers";
    try {
        // 💡 ဇယားကွက်ထဲပြဖို့အတွက် ResultSet ကို Return ပြန်ပေးပါမည်
        // (မှတ်ချက် - ဤနေရာတွင် Connection ကို ချက်ချင်းမပိတ်ရပါ၊ UI ဘက်က ဒေတာဖတ်ပြီးမှ ပိတ်ရပါမည်)
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

// ၂။ UPDATE - ဒေတာ ပြင်ဆင်ခြင်း
public boolean updateSupplierDAO(SupplierDTO supplierDTO) {
    // 💡 updated_at က MySQL ဘက်မှာ ON UPDATE စနစ်ခံထားလို့ SQL ထဲ ထည့်ရေးပေးစရာမလိုပါ
    String sql = "UPDATE suppliers SET supplier_name=?, phone=?, email=?, address=?, township=?, city=?, status=? WHERE supplier_code=?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, supplierDTO.getSupplier_name());
        ps.setString(2, supplierDTO.getPhone());
        ps.setString(3, supplierDTO.getEmail());
        ps.setString(4, supplierDTO.getAddress());
        ps.setString(5, supplierDTO.getTownship());
        ps.setString(6, supplierDTO.getCity());
        ps.setString(7, supplierDTO.getStatus());
        ps.setString(8, supplierDTO.getSupplier_code()); // WHERE ကန့်သတ်ချက်အတွက်
        
        int rows = ps.executeUpdate();
        return rows > 0; // ပြင်ဆင်မှု အောင်မြင်ရင် true ပြန်ပါမည်
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

// ၃။ DELETE - ဒေတာ ဖျက်သိမ်းခြင်း
public boolean deleteSupplierDAO(String supplierCode) {
    String sql = "DELETE FROM suppliers WHERE supplier_code=?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, supplierCode);
        
        int rows = ps.executeUpdate();
        return rows > 0; // ဖျက်တာ အောင်မြင်ရင် true ပြန်ပါမည်
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    // Method to display data set in tabular form
    public DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int colCount = metaData.getColumnCount();

        for (int col = 1; col <= colCount; col++) {
            columnNames.add(metaData.getColumnName(col).toUpperCase(Locale.ROOT));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int col = 1; col <= colCount; col++) {
                vector.add(resultSet.getObject(col));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }




    public DefaultTableModel getSupplierTableModel() {
       String query = "Select * from suppliers";
         try (Statement statement = conn.createStatement(); 
                 ResultSet resultSet = statement.executeQuery(query)) {
            return buildTableModel(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return new DefaultTableModel();
        }
    }

    public List<SupplierDTO> getAllSupplierNames() {
       List<SupplierDTO> list = new ArrayList<>();
       String sql = "select id, supplier_name from suppliers ORDER by supplier_name asc";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SupplierDTO sup = new SupplierDTO();
                sup.setId(rs.getInt("id"));     // ID ထည့်သည်
                sup.setSupplier_name(rs.getString("supplier_name")); // နာမည်ထည့်သည်
                list.add(sup); // Object လိုက် List ထဲထည့်သည်
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    
}
