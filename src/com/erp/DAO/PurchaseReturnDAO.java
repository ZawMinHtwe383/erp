/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.ComboIdName;
import com.erp.Database.ConnectionFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Zaw Min Htwe
 */
public class PurchaseReturnDAO {
    
    private Connection conn;

    public PurchaseReturnDAO() {
               conn = new ConnectionFactory().getConn();
    }
    

//    public boolean savePurchaseReturn(String returnNo, String pVoucherNo, int supplierId, Date date, double grandTotal, DefaultTableModel tableModel, int apAccountId, int purchaseAccountId) {
//        
//    }
    
    public List<String> getVouchersBySupplier(int supplierId) {
        List<String> vouchers = new ArrayList<>();
        String sql = "SELECT voucher_no FROM purchase_vouchers WHERE supplier_id = ? ORDER BY voucher_id DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vouchers.add(rs.getString("voucher_no"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vouchers;
    }

    // 🔍 ၂။ ရွေးချယ်လိုက်သော Voucher No ထဲတွင်ပါဝင်သည့် Item (ဆေးဝါး) များကို ရှာပေးရန်
    public List<ComboIdName> getItemsByVoucher(String voucherNo) {
        List<ComboIdName> itemList = new ArrayList<>();
         String sql = "SELECT pd.product_id,pd.price, m.product_name FROM purchase_details pd INNER JOIN products m ON pd.product_id = m.product_id INNER JOIN purchase_vouchers pv ON pd.voucher_id = pv.voucher_id WHERE pv.voucher_no = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, voucherNo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                     double price = rs.getDouble("price");
                    itemList.add(new ComboIdName(rs.getInt("product_id"), rs.getString("product_name"),price));
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
    
    

