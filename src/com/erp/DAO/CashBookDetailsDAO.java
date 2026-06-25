/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;
import com.erp.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    
    public String[] getAccountNamesFromDB() {
    java.util.List<String> accountList = new java.util.ArrayList<>();
    
    // to use active account filter in SQL database
    String sql = "SELECT account_name FROM chart_of_accounts WHERE is_active = TRUE";
    
    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
         
        while (rs.next()) {
            accountList.add(rs.getString("account_name")); // List ထဲလှမ်းထည့်မယ်
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    // Java Swing ရဲ့ Dialog မှာ သုံးနိုင်အောင် List ကို String Array [] ပုံစံ ပြောင်းလဲပေးလိုက်ခြင်း
    return accountList.toArray(new String[0]);
}
    
}
