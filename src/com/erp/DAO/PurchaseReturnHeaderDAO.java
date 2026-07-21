/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.PurchaseReturnHeaderDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 *
 * @author Zaw Min Htwe
 */
public class PurchaseReturnHeaderDAO {
 //sql connection
    private Connection conn = null;

    public PurchaseReturnHeaderDAO() {
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean insertReturnHeaderDAO(PurchaseReturnHeaderDTO prhDTO) {
        
        String insertSql = "INSERT INTO purchase_returns(return_no,purchase_voucher_id,authorized,return_reason,return_date,sub_total,voucher_discount,grand_total) VALUES (?,?,?,?,?,?,?,?)";
 
           // After checking, we will insert it only if it is not there.

            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, prhDTO.getPurchaseReturnNo());
                ps.setInt(2, prhDTO.getPurchaseInvoiceNoId());
                ps.setString(3, prhDTO.getAuthorisedByTxt());
                ps.setString(4, prhDTO.getReasonReturn()); // The empty text provided by the UI will be entered.
               // ps.setDate(5, new java.sql.Date(prhDTO.getReturnDate()));
                 if (prhDTO.getReturnDate() != null) {
            ps.setDate(5, new java.sql.Date(prhDTO.getReturnDate().getTime()));
        } else {
            ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
        }
                 ps.setDouble(6, prhDTO.getSubTotal());
                 ps.setDouble(7, prhDTO.getVoucherDiscount());
                 ps.setDouble(8, prhDTO.getGrandTotal());
               
                 
                 
                 
                ps.executeUpdate();
                return true; // Returns true if successful
            }

       catch (Exception e) {
            System.out.println("--- Database Error Log ---");
            e.printStackTrace();
            return false;
        }

    }

    public String getNextVoucherNo() {
       
        PreparedStatement ps = null;
        ResultSet rs = null;
        String autoVoucherNo = "";

        try {

            String sql = "SELECT COUNT(*) FROM purchase_returns WHERE DATE(return_date) = CURDATE()";
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
            autoVoucherNo = String.format("PRV-%s-%03d", todayDate, count);

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
    }
    

