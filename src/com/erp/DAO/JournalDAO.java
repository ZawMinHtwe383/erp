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

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

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

  

    public boolean insertJournalDetails(JournalDTO row1DTO, JournalDTO row2DTO) {
    
    String sql = "INSERT INTO journal_details (entry_date, account_id, customer_id, supplier_id, voucher_no, description, debit, credit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String insertLedgerSQL = "INSERT INTO general_ledger (cash_book_detail_id, journal_detail_id, entry_date, account_id, voucher_no, description, debit, credit) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";


    try {
        if (conn == null || conn.isClosed()) {
            return false;
        }
        conn.setAutoCommit(false); // 🚀 Transaction ကို သေချာ စတင်လိုက်ပါပြီ
        int generatedJournalId = -1;
            try (PreparedStatement stmt1 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt1.setDate(1, new java.sql.Date(row1DTO.getEntryDate().getTime()));
                stmt1.setInt(2, row1DTO.getAccountId());
                 if (row1DTO.getCustomerId() != null) {
                    stmt1.setInt(3, row1DTO.getCustomerId());
                } else {
                    stmt1.setNull(3, java.sql.Types.INTEGER);
                }
                if (row1DTO.getSupplierId() != null) {
                    stmt1.setInt(4, row1DTO.getSupplierId());
                } else {
                    stmt1.setNull(4, java.sql.Types.INTEGER);
                }
                stmt1.setString(5, row1DTO.getVoucherNo());
                stmt1.setString(6, row1DTO.getDescription());
                stmt1.setDouble(7, row1DTO.getDebit());
                stmt1.setDouble(8, row1DTO.getCredit());
                stmt1.executeUpdate();
                
                
                stmt1.setDate(1, new java.sql.Date(row2DTO.getEntryDate().getTime()));
                stmt1.setInt(2, row2DTO.getAccountId());
                 if (row2DTO.getCustomerId() != null) {
                    stmt1.setInt(3, row2DTO.getCustomerId());
                } else {
                    stmt1.setNull(3, java.sql.Types.INTEGER);
                }
                if (row2DTO.getSupplierId() != null) {
                    stmt1.setInt(4, row2DTO.getSupplierId());
                } else {
                    stmt1.setNull(4, java.sql.Types.INTEGER);
                }
                stmt1.setString(5, row2DTO.getVoucherNo());
                stmt1.setString(6, row2DTO.getDescription());
                stmt1.setDouble(7, row2DTO.getDebit());
                stmt1.setDouble(8, row2DTO.getCredit());
                stmt1.executeUpdate();
   

                // သတ်မှတ်လိုက်သော ID အား လှမ်းယူခြင်း
                try (ResultSet rs = stmt1.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedJournalId = rs.getInt(1);
                    }
                }
            }
            
        try (PreparedStatement stmt2 = conn.prepareStatement(insertLedgerSQL)) {

                // 🔹 ပထမစာကြောင်း (User ရွေးချယ်လိုက်သော Account အတွက်)
                stmt2.setInt(1, generatedJournalId);
                stmt2.setDate(2, new java.sql.Date(row1DTO.getEntryDate().getTime()));
                stmt2.setInt(3, row1DTO.getAccountId()); // 👈 User ရွေးလိုက်တဲ့အကောင့် (ဥပမာ - Receivable)
                stmt2.setString(4, row1DTO.getVoucherNo());
                stmt2.setString(5, row1DTO.getDescription());
                stmt2.setDouble(6, row1DTO.getDebit()); // Cash Account အတွက် Debit နေရာ၌ Cash Book ၏ Credit အား ထည့်ခြင်း
                stmt2.setDouble(7, row1DTO.getCredit());

                stmt2.addBatch(); // Batch ထဲသို့ ထည့်ခြင်း

                // 🔹 ဒုတိယစာကြောင်း (ငွေသားအဝင်အထွက်ဖြစ်၍ "Cash Account" သို့ စာရင်းပြန်လှည့်ခြင်း)
                stmt2.setInt(1, generatedJournalId);
                stmt2.setDate(2, new java.sql.Date(row2DTO.getEntryDate().getTime()));
                stmt2.setInt(3, row2DTO.getAccountId()); // 👈 ဤနေရာတွင် Cash In Hand သို့မဟုတ် Bank ID အမြဲဝင်မည်
                stmt2.setString(4, row2DTO.getVoucherNo());
                stmt2.setString(5, row2DTO.getDescription());

                // 🧮 Double Entry သဘောတရားအရ Debit နှင့် Credit နေရာ ပြောင်းပြန်လှန်ပေးရပါမည်
                // Cash Account အတွက် Credit နေရာ၌ Cash Book ၏ Debit အား ထည့်ခြင်း
                stmt2.setDouble(6, row2DTO.getDebit());
                stmt2.setDouble(7, row2DTO.getCredit());
                stmt2.addBatch();

                // Ledger ထဲသို့ နှစ်ကြောင်းလုံး ပြိုင်တူ သွင်းလိုက်ခြင်း
                stmt2.executeBatch();
            }
        conn.commit(); // 🚀 အကုန်လုံး အဆင်ပြေမှ Database ထဲ တစ်ပြိုင်တည်း အပြီးသတ် သိမ်းခိုင်းလိုက်ခြင်း!
        return true; 

    } catch (SQLException e) {
        try { 
            conn.rollback(); // ဘာမှမဝင်အောင် ပြန်ဖျက်ချခြင်း
        } catch (SQLException ex) { ex.printStackTrace(); }
        
        javax.swing.JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "SQL Error Details", javax.swing.JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); 
        return false;
    } finally {
        try { conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
    }
}
    
    
    
}
