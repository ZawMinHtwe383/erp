/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DTO;
//product_id	product_code	barcode	product_name	category_id	unit_id	purchase_price	sale_price	stock_qty	reorder_level	is_active	created_at	
/**
 *
 * @author Zaw Min Htwe
 */
public class ProductDTO {
    
    // 🌟 ယူနစ် ၄ ဆင့်အတွက် Variable အသစ်များ
    private int unitLevel1Id; // ဖာ ID
    private int unitLevel2Id; // ဒါဇင် ID
    private int unitLevel3Id; // ကတ် ID
    private int unitLevel4Id; // ခု ID
    
    // 🌟 အချိုးအစား (Factors) များ
    private int factor1To2;
    private int factor2To3;
    private int factor3To4;
    
    // 🌟 UI Table မှာ နာမည်တိုက်ရိုက်ပြရန် String များ
    private String unitLevel1Name;
    private String unitLevel2Name;
    private String unitLevel3Name;
    private String unitLevel4Name;
    private String categoryName;

     // 1. Primary Key နှင့် Foreign Key များ (ကိန်းပြည့်)
    private int productId;
    private int categoryId;
    private int unitId;

    // 2. စာသား သက်သက် ဖြစ်သော အကွက်များ
    private String productCode;
    private String barcode;
    private String productName;
    private String createdAt; // ရက်စွဲကို UI ဇယားထဲမှာ ပြရုံသက်သက်မလို့ String က အရှင်းဆုံးပါဗျာ

    // 3. ဈေးနှုန်းတွက်ချက်ရန်အတွက် ဒသမကိန်း (Double)
    private double purchasePrice; // ဝယ်ဈေး
    private double salePrice;     // ရောင်းဈေး

    // 4. အရေအတွက်များအတွက် ကိန်းပြည့် (Integer)
    private int stockQty;         // ဆိုင်ကျန်အရေအတွက်
    private int reorderLevel;     // သတိပေးမည့်အရေအတွက်

    // 5. အခြေအနေ စစ်ဆေးရန် (True / False)
    private boolean isActive;     // Active ဖြစ်/မဖြစ်	created_at;
    
    
    
    
    public int getUnitLevel1Id() {
        return unitLevel1Id;
    }

    public void setUnitLevel1Id(int unitLevel1Id) {
        this.unitLevel1Id = unitLevel1Id;
    }

    public int getUnitLevel2Id() {
        return unitLevel2Id;
    }

    public void setUnitLevel2Id(int unitLevel2Id) {
        this.unitLevel2Id = unitLevel2Id;
    }

    public int getUnitLevel3Id() {
        return unitLevel3Id;
    }

    public void setUnitLevel3Id(int unitLevel3Id) {
        this.unitLevel3Id = unitLevel3Id;
    }

    public int getUnitLevel4Id() {
        return unitLevel4Id;
    }

    public void setUnitLevel4Id(int unitLevel4Id) {
        this.unitLevel4Id = unitLevel4Id;
    }

    public int getFactor1To2() {
        return factor1To2;
    }

    public void setFactor1To2(int factor1To2) {
        this.factor1To2 = factor1To2;
    }

    public int getFactor2To3() {
        return factor2To3;
    }

    public void setFactor2To3(int factor2To3) {
        this.factor2To3 = factor2To3;
    }

    public int getFactor3To4() {
        return factor3To4;
    }

    public void setFactor3To4(int factor3To4) {
        this.factor3To4 = factor3To4;
    }

    public String getUnitLevel1Name() {
        return unitLevel1Name;
    }

    public void setUnitLevel1Name(String unitLevel1Name) {
        this.unitLevel1Name = unitLevel1Name;
    }

    public String getUnitLevel2Name() {
        return unitLevel2Name;
    }

    public void setUnitLevel2Name(String unitLevel2Name) {
        this.unitLevel2Name = unitLevel2Name;
    }

    public String getUnitLevel3Name() {
        return unitLevel3Name;
    }

    public void setUnitLevel3Name(String unitLevel3Name) {
        this.unitLevel3Name = unitLevel3Name;
    }

    public String getUnitLevel4Name() {
        return unitLevel4Name;
    }

    public void setUnitLevel4Name(String unitLevel4Name) {
        this.unitLevel4Name = unitLevel4Name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
          
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
}
