/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.PurchaseDetailDTO;
import com.erp.DTO.PurchaseVoucherDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseVoucherDAO {

    private Connection conn = null;

    public PurchaseVoucherDAO() {
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

    public boolean savePurchaseVoucher(PurchaseVoucherDTO voucherDTO) {
        String insertHeaderSql = "INSERT INTO purchase_vouchers (voucher_no, supplier_id, purchase_date, sub_total, voucher_discount, grand_total) VALUES (?, ?, ?, ?, ?, ?)";
        String insertLedgerSQL = "INSERT INTO general_ledger (journal_detail_id,purchase_detail_id, entry_date, account_id, voucher_no, description, debit, credit) VALUES (Null, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pstmt = null;
        //ResultSet rs = null;
        int insertedVoucherId = -1;
        try {

            // 🛡️ Transaction စတင်ခြင်း (Auto Commit ကို ပိတ်ထားပါမည်)
            conn.setAutoCommit(false);

            // Statement.RETURN_GENERATED_KEYS သုံးပြီး အော်တိုတိုးမည့် Voucher ID (Primary Key) ကို လှမ်းယူပါမည်
            pstmt = conn.prepareStatement(insertHeaderSql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, voucherDTO.getVoucherNo());
            pstmt.setInt(2, voucherDTO.getSupplierId());

            // java.util.Date မှ java.sql.Date သို့ ပြောင်းလဲထည့်သွင်းခြင်း
            if (voucherDTO.getPurchaseDate() != null) {
                pstmt.setDate(3, new java.sql.Date(voucherDTO.getPurchaseDate().getTime()));
            } else {
                pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // null ဖြစ်ပါက ယနေ့ရက်စွဲ ထည့်မည်
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
            
            try (PreparedStatement stmt = conn.prepareStatement(insertLedgerSQL)) {
           
                //purchase_detail_id, entry_date, account_id, voucher_no, description, debit, credit
            
            // Purchase Account (Debit)
            // =========================================================================
            stmt.setInt(1, insertedVoucherId); // အဝယ်ဘောက်ချာ ID
            stmt.setDate(2, new java.sql.Date(voucherDTO.getPurchaseDate().getTime()));
            stmt.setInt(3, voucherDTO.getPurchaseId()); // 👈 Purchase Account ID (ဥပမာ- 25)
            stmt.setString(4, voucherDTO.getVoucherNo());
            stmt.setString(5, "Gross Purchase for " + voucherDTO.getVoucherNo());
            stmt.setDouble(6, voucherDTO.getSubTotal()); // Debit တွင် ဝယ်ယူသည့် ပမာဏထည့်ရန်
            stmt.setDouble(7, 0.00); // Credit ကို သုညပေးရန်
            stmt.addBatch();
            
            
            
            //purchase dicount
           if(voucherDTO.getVoucherDiscount() > 0){
            stmt.setInt(1, insertedVoucherId);
            stmt.setDate(2, new java.sql.Date(voucherDTO.getPurchaseDate().getTime()));
            stmt.setInt(3, voucherDTO.getPurchaseDiscountId()); 
            stmt.setString(4, voucherDTO.getVoucherNo());
            stmt.setString(5, "Discount Received for " + voucherDTO.getVoucherNo());
            stmt.setDouble(6, 0.00); // Debit ကို သုညပေးရန်
            stmt.setDouble(7, voucherDTO.getVoucherDiscount()); // Credit တွင် ပမာဏထည့်ရန်
            stmt.addBatch();
           }
            
            // =========================================================================
            // 🔹 စာကြောင်း (၂) - Accounts Payable (AP) သို့မဟုတ် Cash (Credit)
            // =========================================================================
            stmt.setInt(1, insertedVoucherId);
            stmt.setDate(2, new java.sql.Date(voucherDTO.getPurchaseDate().getTime()));
            
            // 💡 အကြွေးဝယ်တာဆိုလျှင် AP Account ID ကိုသွင်းပြီး၊ လက်ငင်းဆိုလျှင် Cash Account ID ကိုသွင်းမည်
//            if (purchaseDTO.isCreditPurchase()) {
//                stmt.setInt(3, purchaseDTO.getApAccountId()); // 👈 Accounts Payable Account ID (ဥပမာ- 12)
//            } else {
//                stmt.setInt(3, purchaseDTO.getCashAccountId()); // 👈 Cash In Hand / Bank ID
//            }
            stmt.setInt(3, voucherDTO.getSupplierId()); 
            stmt.setString(4, voucherDTO.getVoucherNo());
            stmt.setString(5, "Net Payable for " + voucherDTO.getVoucherNo());
            stmt.setDouble(6, 0.00); // Debit ကို သုညပေးရန်
            stmt.setDouble(7, voucherDTO.getGrandTotal()); // Credit တွင် ပမာဏထည့်ရန်
            stmt.addBatch();
             // Ledger ထဲသို့ (၂) ကြောင်းလုံး ပြိုင်တူ မောင်းသွင်းလိုက်ခြင်း
            stmt.executeBatch();
        }
            
     
            
            // Header ဝင်သွားပြီဆိုလျှင် Auto Generate ဖြစ်လာသော Voucher ID ကို ဆွဲထုတ်မည်
            if (affectedRows > 0) {
               
                // 👈 ဒုတိယမြောက် table အတွက် သော့ချက် ID
            
                    // 🔄 [METHOD ၂ သို့ ချိတ်ဆက်ခြင်း] ရလာသော ID ဖြင့် Detail များကို Batch စနစ်ဖြင့် လှမ်းသိမ်းခိုင်းခြင်း
                    // (မှတ်ချက် - voucherDTO ထဲတွင် ၎င်း၏ list အား ပြန်ထုတ်ပေးမည့် getPurchaseItems() getter ရှိရပါမည်)
                    List<PurchaseDetailDTO> itemList = voucherDTO.getPurchaseItems();

                    boolean isDetailsSaved = savePurchaseDetails(conn, insertedVoucherId, itemList);
                    
                     StockDAO stockDAO = new StockDAO();
                    try {
                        // masterDTO ထဲမှ Voucher နံပါတ်နှင့် UI ထဲမှ ဝယ်ယူလိုက်သော ဆေးဝါး List ကို လွှဲပေးလိုက်ပါသည်
                        stockDAO.updateStockAndLedgerFromPurchase(conn, itemList,voucherDTO.getVoucherNo() );
                    } catch (Exception ex) {
                        Logger.getLogger(PurchaseVoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if (isDetailsSaved) {
                        conn.commit(); // 🎉 နှစ်ခုစလုံး အောင်မြင်မှ ဒေတာဘေ့စ်ထဲ တကယ် သိမ်းဆည်းမည်
                        System.out.println("🎯 [Voucher Saved Successfully with ID: " + insertedVoucherId + "]");
                        return true;
                    }
                }
     
            // Rollback all data if something goes wrong
            conn.rollback();
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            // Resource များကို စနစ်တကျ ပြန်ပိတ်ခြင်း
            try {
               
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
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
