/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.ProductDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zaw Min Htwe
 */
public class ProductDAO {
    //sql connection

    private Connection conn = null;

    public ProductDAO() {
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertProductDAO(ProductDTO dto) throws SQLException {
        // ၄ ဆင့်ခွဲ ယူနစ် ID များနှင့် အချိုးအစားများအပြင် အသေးဆုံးဝယ်ဈေး၊ ရောင်းဈေးများကို သိမ်းပါမည်
        String sql = "INSERT INTO products (product_code,barcode,product_name,category_id,unit_level1_id,unit_level2_id,unit_level3_id,unit_level4_id,factor_1_to_2,factor_2_to_3,factor_3_to_4,unit_id,purchase_price,	sale_price,stock_qty,reorder_level,is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getProductCode());
            ps.setString(2, dto.getBarcode());
            ps.setString(3, dto.getProductName()); // Product Code (SKU)
            ps.setInt(4, dto.getCategoryId());

            // ယူနစ် ID များ
            ps.setInt(5, dto.getUnitLevel1Id());
            ps.setInt(6, dto.getUnitLevel2Id());
            ps.setInt(7, dto.getUnitLevel3Id());
            ps.setInt(8, dto.getUnitLevel4Id());

            // အချိုးအစား (Factors)
            ps.setInt(9, dto.getFactor1To2());
            ps.setInt(10, dto.getFactor2To3());
            ps.setInt(11, dto.getFactor3To4());

            // ဈေးနှုန်းနှင့် စတော့ခ် (အသေးဆုံး "ခု" ယူနစ်အလိုက် တန်ဖိုးများ)
            ps.setDouble(12, dto.getUnitId());
            ps.setDouble(13, dto.getPurchasePrice());
            ps.setDouble(14, dto.getSalePrice()); // နာရီနောက်ကွယ်က တွက်ထားသည့် စုစုပေါင်း "ခု" အရေအတွက်
            ps.setInt(15, dto.getStockQty());
            ps.setInt(16, dto.getReorderLevel());
            ps.setBoolean(17, dto.isIsActive());
            

            int result = ps.executeUpdate();
            return result > 0; // သိမ်းဆည်းမှု အောင်မြင်ပါက true ပြန်ပါမည်

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
