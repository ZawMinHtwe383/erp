/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.Database.ConnectionFactory;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author Zaw Min Htwe
 */
public class GLReportDAO {

    private Connection conn = null;
    
    
    public GLReportDAO() {
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    

    public DefaultTableModel getAccountTransactionReport(int accountId, Date fromDate, Date toDate) {
        DefaultTableModel model = new DefaultTableModel();
        
        // JTable ခေါင်းစဉ်များ သတ်မှတ်ခြင်း
        model.addColumn("Date");
        model.addColumn("Voucher No");
        model.addColumn("Description");
        model.addColumn("Debit");
        model.addColumn("Credit");
        model.addColumn("Running Balance");

        // 🧮 Running Balance ပါ တစ်ခါတည်း တွက်ပေးမည့် Window Function SQL
        String sql = "SELECT gl.entry_date, gl.voucher_no, gl.description, gl.debit, gl.credit, "
                   + "SUM(gl.debit - gl.credit) OVER (ORDER BY gl.entry_date ASC, gl.gl_id ASC) AS running_balance "
                   + "FROM general_ledger gl "
                   + "WHERE gl.account_id = ? AND gl.entry_date BETWEEN ? AND ? "
                   + "ORDER BY gl.entry_date ASC, gl.gl_id ASC";

        try {
            if (conn == null || conn.isClosed()) {
                conn = new ConnectionFactory().getConn(); // Connection ပြန်ပွင့်ရန်
            }
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, accountId);
                stmt.setDate(2, new java.sql.Date(fromDate.getTime()));
                stmt.setDate(3, new java.sql.Date(toDate.getTime()));
                
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(rs.getDate("entry_date"));
                        row.add(rs.getString("voucher_no"));
                        row.add(rs.getString("description"));
                        row.add(rs.getDouble("debit"));
                        row.add(rs.getDouble("credit"));
                        row.add(rs.getDouble("running_balance")); // Auto တွက်ပြီးသား လက်ကျန်ငွေ
                        
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
    

