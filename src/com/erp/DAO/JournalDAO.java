/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.ComboIdName;
import com.erp.DTO.JournalDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zaw Min Htwe
 */
public class JournalDAO {

  
private Connection conn;
    public JournalDAO() {
         conn = new ConnectionFactory().getConn();
    }

    public List<ComboIdName> getAccountNamesFromDB() {
        List<ComboIdName> list = new ArrayList<>();
         String sql = "SELECT account_id , account_name FROM chart_of_accounts WHERE is_active = 'Active'";

        try (PreparedStatement stmt = conn.prepareStatement(sql); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // ID ရော နာမည်ရောကို တွဲပြီး Object ဆောက်ကာ List ထဲထည့်ခြင်း
                list.add(new ComboIdName(rs.getInt("account_id"), rs.getString("account_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    
    
    }
        //to update for account_id 
    public int getAccountIdByName(String accountName) {
    String sql = "SELECT account_id FROM chart_of_accounts WHERE account_name = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, accountName);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("account_id"); // ID ရှာတွေ့ရင် ပြန်ပေးမယ်
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // ရှာမတွေ့ရင် -1 ပြန်ပေးမယ်
}
    
      public boolean insertJournalDetails(JournalDTO journalDTO) {
        String sql = "INSERT INTO journal_details (entry_date, account_id, customer_id, supplier_id, voucher_no, description, debit, credit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
       String insertLedgerSQL = "INSERT INTO general_ledger ( cash_book_detail_id,journal_detail_id,entry_date, account_id, voucher_no, description, debit, credit) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            conn.setAutoCommit(false);
            int generatedJournalId = -1;
            
            try(PreparedStatement stmt1 = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
                stmt1.setDate(1, new java.sql.Date(journalDTO.getEntryDate().getTime()));
                stmt1.setInt(2, journalDTO.getAccountId());
                if (journalDTO.getCustomerId() != null) {
                    stmt1.setInt(3, journalDTO.getCustomerId());
                } else {
                    stmt1.setNull(3, java.sql.Types.INTEGER);
                }
                if (journalDTO.getSupplierId() != null) {
                    stmt1.setInt(4, journalDTO.getSupplierId());
                } else {
                    stmt1.setNull(4, java.sql.Types.INTEGER);
                }
                stmt1.setString(5, journalDTO.getVoucherNo());
                stmt1.setString(6, journalDTO.getDescription());
                stmt1.setDouble(7, journalDTO.getDebit());
                stmt1.setDouble(8, journalDTO.getCredit());
                stmt1.executeUpdate();
                
                try (ResultSet rs = stmt1.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedJournalId = rs.getInt(1);
                    }
                }
                
                
                
                
                
            } 
            
            try (PreparedStatement stmt = conn.prepareStatement(insertLedgerSQL)) {
            
            stmt.setInt(1, generatedJournalId);
            stmt.setDate(2, new java.sql.Date(journalDTO.getEntryDate().getTime()));
            stmt.setInt(3, journalDTO.getAccountId()); // Debit အကောင့် ID
            stmt.setString(4, journalDTO.getVoucherNo());
            stmt.setString(5, journalDTO.getDescription());
            stmt.setDouble(6, journalDTO.getDebit());  // Debit Amount
            stmt.setDouble(7, 0.00);                 // Credit နေရာတွင် 0.00 ထားမည်
            stmt.addBatch(); // Batch ထဲသို့ ပထမစာကြောင်း ထည့်ခြင်း

            stmt.setInt(1, generatedJournalId);
            stmt.setDate(2, new java.sql.Date(journalDTO.getEntryDate().getTime()));
            stmt.setInt(3, journalDTO.getAccountId()); // Credit အကောင့် ID
            stmt.setString(4, journalDTO.getVoucherNo());
            stmt.setString(5, journalDTO.getDescription());
            stmt.setDouble(6, 0.00);                 // Debit နေရာတွင် 0.00 ထားမည်
            stmt.setDouble(7, journalDTO.getCredit()); // Credit Amount
            stmt.addBatch(); // Batch ထဲသို့ ဒုတိယစာကြောင်း ထည့်ခြင်း

            // 🚀 စာကြောင်း နှစ်ကြောင်းလုံးအား ဂျာနယ် သဘောတရားအတိုင်း ပြိုင်တူ မောင်းသွင်းလိုက်ခြင်း
            stmt.executeBatch();
        }
     
                conn.commit();
                return true;
            
        } catch (SQLException e) {
                try {
                    conn.rollback(); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
                return false;
    } finally {
        try {
            conn.setAutoCommit(true); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
      }
    
}
