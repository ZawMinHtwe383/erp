/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.PurchaseVoucherDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.*;
import java.text.SimpleDateFormat;

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
        System.out.println(voucherDTO.getPurchaseDate());
        return true;
    }

}
