/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

/**
 *
 * @author Zaw Min Htwe
 */

import com.erp.DTO.PurchaseDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class StockDAO {

    // 🎯 အဝယ်စာရင်းသွင်းလိုက်သည်နှင့် Stock လက်ကျန်တိုးခြင်းနှင့် Ledger ရာဇဝင်မှတ်တမ်းကို တစ်ပြိုင်တည်းလုပ်ဆောင်မည့် Method
    public void updateStockAndLedgerFromPurchase(Connection conn, List<PurchaseDetailDTO> itemList, String voucherNo) throws Exception {
        
        // SQL Queries များ ကြိုတင်ပြင်ဆင်ခြင်း
        String checkStockSql = "SELECT stock_id, quantity FROM stocks WHERE product_id = ? AND batch_no = ?";
        String updateStockSql = "UPDATE stocks SET quantity = quantity + ?, last_purchase_price = ? WHERE stock_id = ?";
        String insertStockSql = "INSERT INTO stocks (product_id, batch_no, expiry_date, quantity, last_purchase_price) VALUES (?, ?, ?, ?, ?)";
        
        String insertLedgerSql = "INSERT INTO stock_ledger (product_id, batch_no, source_type, source_voucher_no, qty_in, qty_out, cost_price) "
                               + "VALUES (?, ?, 'PURCHASE', ?, ?, 0, ?)";

        // PreparedStatement များကို Connection တစ်ခုတည်းအောက်တွင် ဆောက်တည်ခြင်း
        try (PreparedStatement psCheck = conn.prepareStatement(checkStockSql);
             PreparedStatement psUpdate = conn.prepareStatement(updateStockSql);
             PreparedStatement psInsertStock = conn.prepareStatement(insertStockSql);
             PreparedStatement psInsertLedger = conn.prepareStatement(insertLedgerSql)) {

            //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // အစ်ကိုကြီး UI က သုံးထားသော Date Format အတိုင်း ပြင်ဆင်နိုင်ပါသည်

            // 🔄 ဝယ်ယူလိုက်သော ဆေးဝါးစာရင်းတစ်ခုချင်းစီကို Loop ပတ်၍ စာရင်းသွင်းခြင်း
            for (PurchaseDetailDTO item : itemList) {
                
                // ၀။ ရက်စွဲကို String မှ java.sql.Date သို့ ပြောင်းလဲခြင်း
              //  java.util.Date parsedDate = sdf.parse(item.getExpiryDate());
                java.sql.Date sqlExpiryDate = new java.sql.Date(item.getExpiryDate().getTime());
                
                

                // -------------------------------------------------------------
                // ၁။ STOCKS TABLE အား စစ်ဆေးပြီး လက်ကျန်ပြင်ဆင်ခြင်း (UPSERT LOGIC)
                // -------------------------------------------------------------
                psCheck.setInt(1, item.getProductId());
                psCheck.setString(2, item.getBatchNo());
                
                int stockId = -1;
                int currentQty = 0;
                
                try (ResultSet rs = psCheck.executeQuery()) {
                    if (rs.next()) {
                        stockId = rs.getInt("stock_id");
                        currentQty = rs.getInt("quantity");
                    }
                }

                if (stockId != -1) {
                    // 💡 ကော်လံဟောင်းရှိပြီးသားဖြစ်ပါက - လက်ကျန်ပေါင်းထည့်ပြီး ဝယ်ဈေးသစ်ကို Update လုပ်မည်
                    psUpdate.setInt(1, item.getQty());
                    psUpdate.setDouble(2, item.getPrice());
                    psUpdate.setInt(3, stockId);
                    psUpdate.executeUpdate();
                } else {
                    // 💡 Batch အသစ်ဖြစ်ပါက - စာရင်းသစ်အဖြစ် INSERT လုပ်မည်
                    psInsertStock.setInt(1, item.getProductId());
                    psInsertStock.setString(2, item.getBatchNo());
                    psInsertStock.setDate(3, sqlExpiryDate);
                    psInsertStock.setInt(4, item.getQty());
                    psInsertStock.setDouble(5, item.getPrice());
                    psInsertStock.executeUpdate();
                }

                // -------------------------------------------------------------
                // ၂။ STOCK_LEDGER TABLE ထဲသို့ AUDIT TRAIL ရာဇဝင်မှတ်တမ်းသွင်းခြင်း
                // -------------------------------------------------------------
                psInsertLedger.setInt(1, item.getProductId());
                psInsertLedger.setString(2, item.getBatchNo());
                psInsertLedger.setString(3, voucherNo);
                psInsertLedger.setInt(4, item.getQty());      // qty_in နေရာတွင် ဝယ်ယူသော အရေအတွက် ထည့်သွင်းခြင်း
                psInsertLedger.setDouble(5, item.getPrice());   // cost_price နေရာတွင် ဝယ်ဈေးရင်း ထည့်သွင်းခြင်း
                psInsertLedger.executeUpdate();
            }
            
        } catch (Exception e) {
            // တစ်ခုခုလွဲချော်ပါက အပြင်ဘက် ဆော့ဖ်ဝဲလ်ဆီသို့ Error ပေးပို့၍ Rollback ဖြစ်စေပါမည်
            throw new Exception("Stock Management System Error: " + e.getMessage());
        }
    }
}

