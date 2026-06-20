/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DTO;

/**
 *
 * @author Zaw Min Htwe
 */
public class StockLedgerDTO {
    private int ledgerId;
    private int productId;
    private String batchNo;
    private String sourceType;        // PURCHASE, SALE, DAMAGE စသည်ဖြင့်
    private String sourceVoucherNo;   // ဘယ်ဘောက်ချာနံပါတ်လဲ
    private int qtyIn;                // အဝင် Qty (မရှိလျှင် 0)
    private int qtyOut;               // အထွက် Qty (မရှိလျှင် 0)
    private double costPrice;         // ထိုအချိန်က ဖြစ်ပေါ်ခဲ့သော ဝယ်ဈေးရင်း
    private String createdAt;

    public StockLedgerDTO(int productId, String batchNo, String sourceType, String sourceVoucherNo, int qtyIn, int qtyOut, double costPrice, String createdAt) {
        this.productId = productId;
        this.batchNo = batchNo;
        this.sourceType = sourceType;
        this.sourceVoucherNo = sourceVoucherNo;
        this.qtyIn = qtyIn;
        this.qtyOut = qtyOut;
        this.costPrice = costPrice;
        this.createdAt = createdAt;
    }

    public int getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(int ledgerId) {
        this.ledgerId = ledgerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceVoucherNo() {
        return sourceVoucherNo;
    }

    public void setSourceVoucherNo(String sourceVoucherNo) {
        this.sourceVoucherNo = sourceVoucherNo;
    }

    public int getQtyIn() {
        return qtyIn;
    }

    public void setQtyIn(int qtyIn) {
        this.qtyIn = qtyIn;
    }

    public int getQtyOut() {
        return qtyOut;
    }

    public void setQtyOut(int qtyOut) {
        this.qtyOut = qtyOut;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
