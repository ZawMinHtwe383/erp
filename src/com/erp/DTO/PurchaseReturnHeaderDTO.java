/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DTO;

import java.util.Date;

/**
 *
 * @author Zaw Min Htwe
 */
public class PurchaseReturnHeaderDTO {

    
    private int SupplierId;
    private String Supplier;
    private Date ReturnDate;
    private String PurchaseReturnNo;
    private int PurchaseInvoiceNoId;
    private String PurchaseInvoiceNo;
    private String ReasonReturn;
    private String authorisedByTxt;

    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int SupplierId) {
        this.SupplierId = SupplierId;
    }

    public int getPurchaseInvoiceNoId() {
        return PurchaseInvoiceNoId;
    }

    public void setPurchaseInvoiceNoId(int PurchaseInvoiceNoId) {
        this.PurchaseInvoiceNoId = PurchaseInvoiceNoId;
    }
    
    
    
    
    public Date getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(Date ReturnDate) {
        this.ReturnDate = ReturnDate;
    }

    public String getPurchaseReturnNo() {
        return PurchaseReturnNo;
    }

    public void setPurchaseReturnNo(String PurchaseReturnNo) {
        this.PurchaseReturnNo = PurchaseReturnNo;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String Supplier) {
        this.Supplier = Supplier;
    }

    public String getPurchaseInvoiceNo() {
        return PurchaseInvoiceNo;
    }

    public void setPurchaseInvoiceNo(String PurchaseInvoiceNo) {
        this.PurchaseInvoiceNo = PurchaseInvoiceNo;
    }

    public String getReasonReturn() {
        return ReasonReturn;
    }

    public void setReasonReturn(String ReasonReturn) {
        this.ReasonReturn = ReasonReturn;
    }
         


    public String getAuthorisedByTxt() {
        return authorisedByTxt;
    }

    public void setAuthorisedByTxt(String authorisedByTxt) {
        this.authorisedByTxt = authorisedByTxt;
    }

    
    
    @Override
    public String toString() {
        return  "PurchaseReturnHeaderDTO{" +'\n' + 
                "returnNo=" + PurchaseReturnNo + '\n' + 
                "returnDate=" + ReturnDate +'\n' + 
                "supplierNameandId=" + Supplier +" and "+ SupplierId +'\n' + 
                "purchaseInvNoandId=" + PurchaseInvoiceNo +" and "+ this.PurchaseInvoiceNoId + '\n' + 
                 "ReasonReturn=" + ReasonReturn + '\n' + 
                 "AuthorisedBy=" + authorisedByTxt + '\n' + 
                '}';
    }
    
    

}
