/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.PurchaseDetailDTO;
import com.erp.DTO.PurchaseHeaderDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseHeaderDAO {

    private Connection conn = null;

    public PurchaseHeaderDAO() {
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNextVoucherNo() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String autoVoucherNo = "";

        try {

            String sql = "SELECT COUNT(*) FROM purchase_vouchers WHERE DATE(purchase_date) = CURDATE()";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }

            count++; // နောက်ထွက်မည့် ဘောချာအတွက် +1 တိုး

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String todayDate = sdf.format(new java.util.Date());
            // Format ထုတ်ယူခြင်း (ဥပမာ - PV-20260615-001)
            autoVoucherNo = String.format("PV-%s-%03d", todayDate, count);

        } catch (Exception e) {
            e.printStackTrace();
            autoVoucherNo = "PV-ERROR"; // အမှားရှိပါက ဒါပြမည်
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }

        return autoVoucherNo; // ထွက်လာတဲ့ စာသားကို ပြန်ပေးလိုက်ခြင်း
    }

   
    public boolean savePurchaseVoucher(PurchaseHeaderDTO voucherDTO) {
    String insertHeaderSql = "INSERT INTO purchase_vouchers (voucher_no, supplier_id, purchase_date, sub_total, voucher_discount, grand_total) VALUES (?, ?, ?, ?, ?, ?)";
    // 💡 VALUES ထဲတွင် Null နှင့် ? စုစုပေါင်း ၈ ခု ကွက်တိ ဖြစ်စေရန် ပြင်ဆင်ထားပါသည်
    String insertLedgerSQL = "INSERT INTO general_ledger (journal_detail_id, purchase_detail_id, entry_date, account_id, voucher_no, description, debit, credit) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
    
    PreparedStatement pstmt = null;
    int insertedVoucherId = -1;
    
    try {
        // 🛡️ Transaction စတင်ခြင်း
        conn.setAutoCommit(false);

        pstmt = conn.prepareStatement(insertHeaderSql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, voucherDTO.getVoucherNo());
        pstmt.setInt(2, voucherDTO.getSupplierId());

        if (voucherDTO.getPurchaseDate() != null) {
            pstmt.setDate(3, new java.sql.Date(voucherDTO.getPurchaseDate().getTime()));
        } else {
            pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
        }

        pstmt.setDouble(4, voucherDTO.getSubTotal());
        pstmt.setDouble(5, voucherDTO.getVoucherDiscount());
        pstmt.setDouble(6, voucherDTO.getGrandTotal());

        int affectedRows = pstmt.executeUpdate();

        try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
                insertedVoucherId = rs.getInt(1);
            }
        }
        
        // =========================================================================
        // 🚀 General Ledger ထဲသို့ ဒေတာသွင်းခြင်း (Parameter စီစဉ်မှု အမှန်)
        // =========================================================================
        try (PreparedStatement stmt = conn.prepareStatement(insertLedgerSQL)) {
            
            // ၁။ Purchase Account (Debit)
            // ---------------------------------------------------------------------
            stmt.setInt(1, insertedVoucherId); // purchase_detail_id
            stmt.setDate(2, new java.sql.Date(voucherDTO.getPurchaseDate().getTime())); // entry_date
            stmt.setInt(3, voucherDTO.getPurchaseId()); // account_id (ဥပမာ- 25)
            stmt.setString(4, voucherDTO.getVoucherNo()); // voucher_no
            stmt.setString(5, "Gross Purchase for " + voucherDTO.getVoucherNo()); // description
            stmt.setDouble(6, voucherDTO.getSubTotal()); // debit
            stmt.setDouble(7, 0.00); // credit
            stmt.addBatch();
            
            // ၂။ Purchase Discount (Credit) - ရှိမှ သွင်းမည်
            // ---------------------------------------------------------------------
            if (voucherDTO.getVoucherDiscount() > 0) {
                stmt.setInt(1, insertedVoucherId);
                stmt.setDate(2, new java.sql.Date(voucherDTO.getPurchaseDate().getTime()));
                stmt.setInt(3, voucherDTO.getPurchaseDiscountId()); // account_id
                stmt.setString(4, voucherDTO.getVoucherNo());
                stmt.setString(5, "Discount Received for " + voucherDTO.getVoucherNo());
                stmt.setDouble(6, 0.00); // debit
                stmt.setDouble(7, voucherDTO.getVoucherDiscount()); // credit
                stmt.addBatch();
            }
            
            // ၃။ Accounts Payable - AP (Credit)
            // ---------------------------------------------------------------------
            stmt.setInt(1, insertedVoucherId);
            stmt.setDate(2, new java.sql.Date(voucherDTO.getPurchaseDate().getTime()));
            
            // 💡 ⚠️ အစ်ကို့ DTO ထဲတွင် AP Account ID ကို ယူသည့် getApAccountId() စသည်ဖြင့် ပြောင်းပေးရန် လိုအပ်နိုင်ပါသည်
            // (Supplier ID သည် account table ထဲက ID မဟုတ်ပါက စာရင်းလွဲတတ်ပါသည်)
            stmt.setInt(3, voucherDTO.getApAccountId()); // 👈 Accounts Payable Account ID အမှန်ကို သုံးရန်
            
            stmt.setString(4, voucherDTO.getVoucherNo());
            stmt.setString(5, "Net Payable for " + voucherDTO.getVoucherNo());
            stmt.setDouble(6, 0.00); // debit
            stmt.setDouble(7, voucherDTO.getGrandTotal()); // credit
            stmt.addBatch();
            
            // Ledger ထဲသို့ အားလုံး ပြိုင်တူ မောင်းသွင်းခြင်း
            stmt.executeBatch();
            
        } // 💡 ဤနေရာတွင် မူရင်းကုဒ်၌ ခတ်ခဲ့သော catch(SQLException e) အမှားကို ဖယ်ရှားပြီး အပြင်က catch သို့ စီးဆင်းစေပါသည်

        // Header နှင့် Ledger အားလုံး အဆင်ပြေမှ Detail နှင့် Stock ဘက်သို့ သွားမည်
        if (affectedRows > 0) {
            List<PurchaseDetailDTO> itemList = voucherDTO.getPurchaseItems();
            boolean isDetailsSaved = savePurchaseDetails(conn, insertedVoucherId, itemList);
            
            StockDAO stockDAO = new StockDAO();
            try {
                stockDAO.updateStockAndLedgerFromPurchase(conn, itemList, voucherDTO.getVoucherNo());
            } catch (Exception ex) {
                Logger.getLogger(PurchaseHeaderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (isDetailsSaved) {
                conn.commit(); // 🎉 အားလုံး အောင်မြင်မှ သတ်မှတ်ချက်အတိုင်း တစ်ခါတည်း Commit လုပ်မည်
                System.out.println("🎯 [Voucher Saved Successfully with ID: " + insertedVoucherId + "]");
                return true;
            }
        }

        conn.rollback();
        return false;

    } catch (SQLException e) {
        e.printStackTrace();
        if (conn != null) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return false;
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    
    
    
    
    private boolean savePurchaseDetails(Connection conn, int voucherId, List<PurchaseDetailDTO> itemList) throws SQLException {

        String insertDetailSql = "INSERT INTO purchase_details (voucher_id,product_id,batch_no,expiry_date,unit_id, qty, foc, price, line_discount, amount) VALUES (?, ?, ?, ?,?,?, ?, ?, ?, ?)";

        // Connection ကို အပေါ်က မခူးဘဲ ဒီတိုင်း လက်ဆင့်ကမ်း သုံးထားသည့်အတွက် ပိတ်စရာမလိုပါ (Transaction တစ်ခုတည်းမို့လို့ပါ)
        try (PreparedStatement psDetail = conn.prepareStatement(insertDetailSql)) {

            for (PurchaseDetailDTO item : itemList) {
                psDetail.setInt(1, voucherId); // အပေါ်ကရလာတဲ့ ပင်မ ဘောချာ ID
                psDetail.setInt(2, item.getProductId());
                psDetail.setString(3, item.getBatchNo());

                //string date to date format 
//                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy"); 
//                java.util.Date parsedDate = sdf.parse(item.getExpiryDate());
//                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                psDetail.setDate(4, new java.sql.Date(item.getExpiryDate().getTime()));

                psDetail.setInt(5, item.getUnitId());
                psDetail.setInt(6, item.getQty());
                psDetail.setInt(7, item.getFoc());
                psDetail.setDouble(8, item.getPrice());
                psDetail.setDouble(9, item.getLineDiscount());
                psDetail.setDouble(10, item.getAmount());

                psDetail.addBatch(); // 🚀 Memory ပေါ်တွင် အစုလိုက် သိမ်းရန် စုထားခြင်း (အလုပ်မြန်စေသည်)
            }

            // အားလုံးစုပြီးမှ Database ဆီ တစ်ခါတည်း ဒုန်းခနဲ ပစ်ထည့်ခြင်း
            int[] result = psDetail.executeBatch();

            // ပစ္စည်းအားလုံး အောင်မြင်စွာ ဝင်၊ မဝင် စစ်ဆေးခြင်း
            return result.length == itemList.size();
        }
    }

}
