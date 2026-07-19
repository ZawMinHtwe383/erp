/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Zaw Min Htwe
 */
public class PurchaseReturnDTO {
   // return_id	return_no	purchase_voucher_id	product_id	batch_no	qty	return_reason	return_date
    private String returnNo;
    private String purchaseVoucherNo;
    private int supplierId;
    private int apAccountId; // AP အကောင့် ID (General Ledger စာရင်းသွင်းရန်)
    private Date returnDate;
    private double grandTotal;
    private List<ItemDetail> returnItems = new ArrayList<>();

    public String getReturnNo() {
        return returnNo;
    }

    public void setReturnNo(String returnNo) {
        this.returnNo = returnNo;
    }

    public String getPurchaseVoucherNo() {
        return purchaseVoucherNo;
    }

    public void setPurchaseVoucherNo(String purchaseVoucherNo) {
        this.purchaseVoucherNo = purchaseVoucherNo;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getApAccountId() {
        return apAccountId;
    }

    public void setApAccountId(int apAccountId) {
        this.apAccountId = apAccountId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<ItemDetail> getReturnItems() {
        return returnItems;
    }

    public void setReturnItems(List<ItemDetail> returnItems) {
        this.returnItems = returnItems;
    }
    
    public static class ItemDetail {
        public int itemId;
        public int returnQty;
        public double unitPrice;
        public double totalAmount;

        public ItemDetail(int itemId, int returnQty, double unitPrice, double totalAmount) {
            this.itemId = itemId;
            this.returnQty = returnQty;
            this.unitPrice = unitPrice;
            this.totalAmount = totalAmount;
        }
    }
    
    
    
    
    
    
}


