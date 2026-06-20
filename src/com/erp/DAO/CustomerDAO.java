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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
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
  
    
    
    public DefaultTableModel getCustomerTableModel(){
        String query = "Select * from customers";
         try (Statement statement = conn.createStatement(); 
                 ResultSet resultSet = statement.executeQuery(query)) {
            return buildTableModel(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return new DefaultTableModel();
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

    public boolean deleteCustomerDAO(int cusId) {
      // System.out.println(cusId);
      String sql = "DELETE FROM customers WHERE id = ?";
        // ✅ prepareCall အစား prepareStatement သို့ ပြောင်းလဲပြင်ဆင်ထားပါသည်။
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cusId);
            int row = ps.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
      
      
      
      
       
    }

    public boolean updateUserDAO(CustomerDTO customerDTO) {
       String sql = "Update customers set customer_code=?, customer_name=?, phone=?, email=?, address=?, township=?, city=?, credit_limit=?, status=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            
            //ps (1 , is match sql ?
            ps.setString(1, customerDTO.getCustomer_code());
            ps.setString(2, customerDTO.getCustomer_name());
            ps.setString(3, customerDTO.getPhone());
            ps.setString(4, customerDTO.getEmail()); // UI က လှမ်းပေးလိုက်တဲ့ စာသားအလွတ် ဝင်သွားမည်
            ps.setString(5, customerDTO.getAddress());
            ps.setString(6, customerDTO.getTownship());
            ps.setString(7, customerDTO.getCity());
            ps.setBigDecimal(8, customerDTO.getCreditLimit());
            ps.setString(9, customerDTO.getStatus());
            ps.setInt(10, customerDTO.getId());
            int row = ps.executeUpdate();
            return row > 0;
        } catch (Exception e) {
             e.printStackTrace();
            return false;
        }
    }
    public void generateCustomerReports (){
         try {
        // ၁။ src/reports ထဲက .jasper ဖိုင်လမ်းကြောင်းကို သတ်မှတ်ခြင်း
        String reportPath = "src/com/reports/customers.jasper";
        
        // ၂။ .jasper ဖိုင်ကို Java က ဖတ်နိုင်အောင် Load လုပ်ခြင်း
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);
        
        
        // ၁။ Parameter မရှိလျှင် Map နေရာ၌ null ပေးလိုက်ရုံပါပဲ
        Map<String, Object> parameters = null; 

        // ၂။ .jasper ဖိုင်ကို Load လုပ်ပြီး Connection တစ်ခုတည်း လွှဲပေးလိုက်ခြင်း ဖြစ်ပါတယ်
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
        
        
        
        // ၃။ Jaspersoft Studio ထဲတွင် ဆောက်ခဲ့သော Parameter အမည်အတိုင်း ဒေတာထည့်ခြင်း
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("VoucherNoParam", voucherNo); // 💡 "VoucherNoParam" သည် Studio ထဲက Parameter အမည်အတိုင်း ဖြစ်ရမည်။
//
//        
//        // ၄။ Connection ကော၊ Parameter ပါ ပေါင်းစပ်ပြီး Report ဒေတာ ဖြည့်သွင်းခြင်း
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
        
        // ၅။ Voucher ပရင့်ထုတ်ရန် Preview ဝင်းဒိုးကို လှှမ်းဖွင့်ပြခြင်း
        JasperViewer viewer = new JasperViewer(jasperPrint, false); // false သည် မိခင် Form ကြီးပါ အတူပိတ်မသွားစေရန် ဖြစ်သည်
        viewer.setTitle("Purchase Voucher Print Preview");
        viewer.setVisible(true);
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Report Open Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    }
   
    
    
    
    
    
    
    
    
    
    
    

