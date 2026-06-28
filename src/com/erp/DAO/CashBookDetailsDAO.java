/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.CashBookDetailsDTO;
import com.erp.DTO.ComboIdName;
import com.erp.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;

//CREATE TABLE cash_book_details (
//    id INT AUTO_INCREMENT PRIMARY KEY,
//    entry_date DATE NOT NULL,
//    
//    -- ၁။ Account Master Table သို့မဟုတ် Category ID နှင့် ချိတ်ရန် (ဥပမာ- Receivable, Payable, Expense)
//    account_id INT NOT NULL,                  
//    
//    -- ၂။ Customer ဖြစ်ခဲ့လျှင် customers table ရဲ့ ID ကို လှမ်းသိမ်းရန် (Null ခွင့်ပြုသည်)
//    customer_id INT DEFAULT NULL,              
//    
//    -- ၃။ Supplier ဖြစ်ခဲ့လျှင် suppliers table ရဲ့ ID ကို လှမ်းသိမ်းရန် (Null ခွင့်ပြုသည်)
//    supplier_id INT DEFAULT NULL,              
//    
//    -- ၄။ အကယ်၍ Expense တို့၊ အခြား Custom စာသားတို့ လက်နဲ့ ရိုက်ခဲ့လျှင် သိမ်းရန် စာသားကော်လံ
//    custom_name VARCHAR(150) DEFAULT NULL,    
//    
//    voucher_no VARCHAR(50),
//    description TEXT,
//    debit DECIMAL(15, 2) DEFAULT 0.00,
//    credit DECIMAL(15, 2) DEFAULT 0.00,
//    balance DECIMAL(15, 2) DEFAULT 0.00,
//    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    
//    -- 🔗 နိုင်ငံတကာ စံနှုန်းအတိုင်း Foreign Key Relationships များ သတ်မှတ်ခြင်း (Optional)
//    -- ဒါက တခြား Table က ID တွေ ဖျက်လိုက်ရင် စာရင်း လိုက်မပျက်အောင် ကာကွယ်ပေးပါတယ်
//    CONSTRAINT fk_cash_account FOREIGN KEY (account_id) REFERENCES chart_of_accounts(account_id),
//    CONSTRAINT fk_cash_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE SET NULL,
//    CONSTRAINT fk_cash_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE SET NULL
//);
public class CashBookDetailsDAO {

    private Connection conn;

    public CashBookDetailsDAO() {
        // သင့်၏ ConnectionFactory မှတစ်ဆင့် Connection ယူရန်
        conn = new ConnectionFactory().getConn();
    }

    public List<ComboIdName> getAccountNamesFromDB() {
//    java.util.List<String> accountList = new java.util.ArrayList<>();
//    
//    // to use active account filter in SQL database
//    String sql = "SELECT account_name FROM chart_of_accounts WHERE is_active = TRUE";
//    
//    try (PreparedStatement ps = conn.prepareStatement(sql);
//         ResultSet rs = ps.executeQuery()) {
//         
//        while (rs.next()) {
//            accountList.add(rs.getString("account_name")); // List ထဲလှမ်းထည့်မယ်
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    
//    // Java Swing ရဲ့ Dialog မှာ သုံးနိုင်အောင် List ကို String Array [] ပုံစံ ပြောင်းလဲပေးလိုက်ခြင်း
//    return accountList.toArray(new String[0]);
//}

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
    
    public double  openingBalance(){
        String sql = "SELECT entry_date,voucher_no, debit, credit, SUM(debit - credit) OVER (ORDER BY entry_date, id) AS balance FROM cash_book_details";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("balance"); // ID ရှာတွေ့ရင် ပြန်ပေးမယ်
            }
        }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return -1; 
        }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public boolean insertCashBookDetailsDAO(CashBookDetailsDTO cbdDTO) {
        String sql = "INSERT INTO cash_book_details (entry_date, account_id, customer_id, supplier_id, voucher_no, description, debit, credit, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, (Date) cbdDTO.getEntryDate());
            stmt.setInt(2, cbdDTO.getAccountId());
            
            // Null စစ်ဆေးပြီး ထည့်ခြင်း
            if (cbdDTO.getCustomerId() != null) stmt.setInt(3, cbdDTO.getCustomerId());
            else stmt.setNull(3, Types.INTEGER);
            
            if (cbdDTO.getSupplierId() != null) stmt.setInt(4, cbdDTO.getSupplierId());
            else stmt.setNull(4, Types.INTEGER);
            
            stmt.setString(5, cbdDTO.getVoucherNo());
            stmt.setString(6, cbdDTO.getDescription());
            stmt.setDouble(7, cbdDTO.getDebit());
            stmt.setDouble(8, cbdDTO.getCredit());
            stmt.setDouble(9, cbdDTO.getBalance());
        
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<CashBookDetailsDTO> searchByDateRange(Date fromDate, Date toDate) {
        List<CashBookDetailsDTO> list = new ArrayList<>();
    
    // 💡 ရက်စွဲအလိုက် အစဉ်လိုက် (အဟောင်းမှ အသစ်) ပြန်ဆွဲထုတ်မည့် SQL Query
    //String sql = "SELECT * FROM cash_book_details WHERE entry_date BETWEEN ? AND ? ORDER BY entry_date ASC";
    String sql = "SELECT c.*, a.account_name AS acc_name, cu.customer_name AS cus_name, s.supplier_name AS sup_name " +
                 "FROM cash_book_details c " +
                 "LEFT JOIN chart_of_accounts a ON c.account_id = a.account_id " +
                 "LEFT JOIN customers cu ON c.customer_id = cu.id " +
                 "LEFT JOIN suppliers s ON c.supplier_id = s.id " +
                 "WHERE c.entry_date BETWEEN ? AND ? ORDER BY c.entry_date ASC";
    
    //SELECT c.*, a.account_name AS acc_name, cu.customer_name AS cus_name, s.supplier_name AS sup_name FROM cash_book_details c LEFT JOIN chart_of_accounts a ON c.account_id = a.account_id LEFT JOIN customers cu ON c.customer_id = cu.id LEFT JOIN suppliers s ON c.supplier_id = s.id WHERE c.entry_date BETWEEN '2026-06-25' AND '2026-06-25' ORDER BY c.entry_date ASC; 
    
    
    
    
    
    
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setDate(1, fromDate);
        stmt.setDate(2, toDate);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CashBookDetailsDTO dto = new CashBookDetailsDTO();
                //to get id
                dto.setId(rs.getInt("id"));
                
                
                dto.setEntryDate(rs.getDate("entry_date"));
               // dto.setAccountId(rs.getInt("account_id"));
                dto.setAccountName(rs.getString("acc_name"));
                
                // Integer ကို null ဖြစ်နိုင်သဖြင့် getObject ဖြင့်ယူပါ
                dto.setCustomerId((Integer) rs.getObject("customer_id")); 
                dto.setCustomerName( rs.getString("cus_name"));
                
                dto.setSupplierId((Integer) rs.getObject("supplier_id"));
                dto.setSuppplierName(rs.getString("sup_name"));
                 
                System.out.println("Account: " + dto.getAccountName());
                System.out.println("Customer: " + dto.getCustomerName());
                System.out.println("Supplier: " + dto.getSuppplierName());
                
                
                
                
                dto.setVoucherNo(rs.getString("voucher_no"));
                dto.setDescription(rs.getString("description"));
                dto.setDebit(rs.getDouble("debit"));
                dto.setCredit(rs.getDouble("credit"));
                dto.setBalance(rs.getDouble("balance"));
                
                list.add(dto);
            }
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }

    public boolean deleteRecord(int recordId) {
   String sql = "DELETE FROM cash_book_details WHERE id = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, recordId);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
        
        
    }

    public boolean updateRecord(CashBookDetailsDTO dto) {
    String sql = "UPDATE cash_book_details SET entry_date = ?, account_id = ?, customer_id = ?, " +
                 "supplier_id = ?, voucher_no = ?, description = ?, debit = ?, credit = ?, balance = ? " +
                 "WHERE id = ?";
                 
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setDate(1, new java.sql.Date(dto.getEntryDate().getTime()));
        stmt.setInt(2, dto.getAccountId());
        
        // Handle Integer objects that might be null (Customer or Supplier)
        if (dto.getCustomerId() != null) {
            stmt.setInt(3, dto.getCustomerId());
        } else {
            stmt.setNull(3, java.sql.Types.INTEGER);
        }
        
        if (dto.getSupplierId() != null) {
            stmt.setInt(4, dto.getSupplierId());
        } else {
            stmt.setNull(4, java.sql.Types.INTEGER);
        }
        
        stmt.setString(5, dto.getVoucherNo());
        stmt.setString(6, dto.getDescription());
        stmt.setDouble(7, dto.getDebit());
        stmt.setDouble(8, dto.getCredit());
        stmt.setDouble(9, dto.getBalance());
        
        // Use the ID to specify which row to update
        stmt.setInt(10, dto.getId()); 
    
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
}
