/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.ComboIdName;
import com.erp.DTO.CustomerDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    public CustomerDAO() {
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addCustomerDAO(CustomerDTO customerDTO) {

       // The table name has been adjusted to be customers at the bottom and top.
        String checkSql = "SELECT customer_code FROM customers WHERE customer_code=?";
        String insertSql = "INSERT INTO customers(customer_code, customer_name, phone, email, address, township, city, credit_limit, status) VALUES (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, customerDTO.getCustomer_code());

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    return false; // Returns false because the code already exists.
                }
            }

           // After checking, we will insert it only if it is not there.
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, customerDTO.getCustomer_code());
                ps.setString(2, customerDTO.getCustomer_name());
                ps.setString(3, customerDTO.getPhone());
                ps.setString(4, customerDTO.getEmail()); // The empty text provided by the UI will be entered.
                ps.setString(5, customerDTO.getAddress());
                ps.setString(6, customerDTO.getTownship());
                ps.setString(7, customerDTO.getCity());
                ps.setBigDecimal(8, customerDTO.getCreditLimit());
                ps.setString(9, customerDTO.getStatus());

                ps.executeUpdate();
                return true; // Returns true if successful
            }

        } catch (Exception e) {
            System.out.println("--- Database Error Log ---");
            e.printStackTrace();
            return false;
        }
    }

    public DefaultTableModel getCustomerTableModel() {
        String query = "Select * from customers";
        try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
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
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

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

    //to show jasper reports 
    public void generateCustomerReports() {

        try {

            // 1. Set the path to the .jasper file in src/reports
            String reportPath = "src/com/reports/customers.jasper";

// 2. Load the .jasper file so that it can be read by Java
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

// 1. If there is no parameter, just pass null in the Map field
            Map<String, Object> parameters = null;

// 2. Load the .jasper file and pass a single Connection
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

// 3. Insert data according to the Parameter name created in Jaspersoft Studio
// Map<String, Object> parameters = new HashMap<>();
// parameters.put("VoucherNoParam", voucherNo); // 💡 "VoucherNoParam" should be the same as the Parameter name in Studio.
//
//
// // 4. Connection, Parameter and Report Data Filling
// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
// 5. Open the Preview window to print the voucher
            JasperViewer viewer = new JasperViewer(jasperPrint, false); // false is to prevent the parent form from closing
            viewer.setTitle("Purchase Voucher Print Preview");
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Report Open Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<ComboIdName> getCustomersNamesFromDB() {
//    java.util.List<String> customerList = new java.util.ArrayList<>();
//    
//    // to use active account filter in SQL database
//    String sql = "SELECT customer_name FROM customers WHERE status = 'Active'";
//    
//    try (PreparedStatement ps = conn.prepareStatement(sql);
//         ResultSet rs = ps.executeQuery()) {
//         
//        while (rs.next()) {
//            customerList.add(rs.getString("customer_name")); // List 
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    
//    // Java Swing ရဲ့ Dialog မှာ သုံးနိုင်အောင် List ကို String Array [] ပုံစံ ပြောင်းလဲပေးလိုက်ခြင်း
//    return customerList.toArray(new String[0]);

        List<ComboIdName> list = new ArrayList<>();
        String sql = "SELECT id,customer_name FROM customers WHERE status = 'Active'";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Create an Object by combining the ID and name and add it to the List
                list.add(new ComboIdName(rs.getInt("id"), rs.getString("customer_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

}
