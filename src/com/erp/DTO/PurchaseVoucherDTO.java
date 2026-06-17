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
public class PurchaseVoucherDTO {
    private int voucherId;
    private String voucherNo;
    private int supplierId;
    private Date purchaseDate;
    private double subTotal;
    private double voucherDiscount;
    private double grandTotal;
    
    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getVoucherDiscount() {
        return voucherDiscount;
    }

    public void setVoucherDiscount(double voucherDiscount) {
        this.voucherDiscount = voucherDiscount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
    
     private List<PurchaseDetailDTO> purchaseItems = new ArrayList<>();
    public List<PurchaseDetailDTO> getPurchaseItems() {
        return purchaseItems;
    }

    // Details Item  List to store
    public void setPurchaseItems(List<PurchaseDetailDTO> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
     
  

    public void addPurchaseItem(PurchaseDetailDTO itemDTO) {
       
        this.purchaseItems.add(itemDTO);
    }
}
