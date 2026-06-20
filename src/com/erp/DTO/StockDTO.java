/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DTO;

/**
 *
 * @author Zaw Min Htwe
 */
public class StockDTO {
    private int stockId;
    private int productId;
    private String batchNo;
    private String expiryDate; // UI (JTable) နှင့် အလွယ်တကူ တွဲသုံးနိုင်ရန် String ဖြင့် ထားရှိခြင်းဖြစ်ပါသည်
    private int quantity;
    private double lastPurchasePrice; // Valuation တွက်ချက်ရန် ဝယ်ဈေးရင်း

    public StockDTO(int productId, String batchNo, String expiryDate, int quantity, double lastPurchasePrice) {
        this.productId = productId;
        this.batchNo = batchNo;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.lastPurchasePrice = lastPurchasePrice;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
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

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLastPurchasePrice() {
        return lastPurchasePrice;
    }

    public void setLastPurchasePrice(double lastPurchasePrice) {
        this.lastPurchasePrice = lastPurchasePrice;
    }
}
