/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.erp;

import com.erp.DAO.CategoryDAO;
import com.erp.DAO.ProductDAO;
import com.erp.DAO.UnitDAO;
import com.erp.DTO.CategoryDTO;
import com.erp.DTO.ProductDTO;
import com.erp.DTO.UnitDTO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Zaw Min Htwe
 */
public class ProductPage extends javax.swing.JPanel {

    public ProductPage() {
        initComponents();

        //to show combo box
        loadCategoryToCombo();
        loadUnitsToCombo();

    }
    private UnitDAO unitDAO = new UnitDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    private void loadCategoryToCombo() {
        List<CategoryDTO> categoryName = categoryDAO.getAllCategoryNames();
        // Combo Box တွေကို အရင် ရှင်းထုတ်ပစ်ပါမယ် (Item 1, Item 2 တွေ ပျောက်သွားအောင်လို့ပါ)
        categoryCombo.removeAllItems();
        for (CategoryDTO cat : categoryName) {
            categoryCombo.addItem(cat); // ဖာ၊ ဒါဇင်၊ ကတ်၊ ခု အားလုံး ဝင်သွားပါမယ်

        }
    }

    private void loadUnitsToCombo() {
        // ဒေတာဘေ့စ်ထဲက ယူနစ်နာမည်စာရင်းကို လှမ်းယူခြင်း
        List<UnitDTO> unitNames = unitDAO.getAllUnitNames();

        // Combo Box တွေကို အရင် ရှင်းထုတ်ပစ်ပါမယ် (Item 1, Item 2 တွေ ပျောက်သွားအောင်လို့ပါ)
        cmbUnitLevel1.removeAllItems();
        cmbUnitLevel2.removeAllItems();
        cmbUnitLevel3.removeAllItems();
        cmbUnitLevel4.removeAllItems();

        // Loop ပတ်ပြီး Combo Box (၄) ခုလုံးထဲကို ဒေတာဘေ့စ်က နာမည်တွေ လိုက်ထည့်ခြင်း
        for (UnitDTO name : unitNames) {
            cmbUnitLevel1.addItem(name); // ဖာ၊ ဒါဇင်၊ ကတ်၊ ခု အားလုံး ဝင်သွားပါမယ်
            cmbUnitLevel2.addItem(name);
            cmbUnitLevel3.addItem(name);
            cmbUnitLevel4.addItem(name);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        productCodeTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        barCodeTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        productNameTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        purchasePriceTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        salePriceTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        stockQtyTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        reorderLevelTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        statusCombo = new javax.swing.JComboBox<>();
        categoryCombo = new javax.swing.JComboBox();
        txtFactor1To2 = new javax.swing.JTextField();
        txtFactor2To3 = new javax.swing.JTextField();
        txtFactor3To4 = new javax.swing.JTextField();
        txtFactor4To5 = new javax.swing.JTextField();
        cmbUnitLevel1 = new javax.swing.JComboBox();
        cmbUnitLevel2 = new javax.swing.JComboBox();
        cmbUnitLevel3 = new javax.swing.JComboBox();
        cmbUnitLevel4 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("Delete");

        updateBtn.setText("Update");

        clearBtn.setText("Clear");

        jLabel1.setText("Product Code");

        jLabel2.setText("Bar Code");

        jLabel3.setText("Product Name");

        jLabel4.setText("Cagegory");

        jLabel5.setText("Unit");

        jLabel6.setText("Purchase Price");

        jLabel7.setText("Sale Price");

        jLabel8.setText("Stock Qty");

        jLabel9.setText("Reorder Level");

        jLabel10.setText("Status");

        statusCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        categoryCombo.setEditable(true);
        categoryCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtFactor4To5.setEditable(false);
        txtFactor4To5.setText("1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productCodeTxt))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(barCodeTxt))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productNameTxt))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salePriceTxt))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stockQtyTxt))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reorderLevelTxt))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(categoryCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbUnitLevel2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFactor2To3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbUnitLevel1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFactor1To2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(purchasePriceTxt)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbUnitLevel4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFactor4To5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbUnitLevel3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFactor3To4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productCodeTxt)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barCodeTxt)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productNameTxt)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFactor1To2)
                    .addComponent(cmbUnitLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFactor2To3)
                    .addComponent(cmbUnitLevel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFactor3To4)
                    .addComponent(cmbUnitLevel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFactor4To5)
                    .addComponent(cmbUnitLevel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stockQtyTxt)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(purchasePriceTxt)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(salePriceTxt)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reorderLevelTxt)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(deleteBtn)
                    .addComponent(updateBtn)
                    .addComponent(clearBtn))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.LINE_END);

        productTable.setAutoCreateRowSorter(true);
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product Code", "Bar Code", "Name", "Category", "Unit", "Purchase Price", "Sale Price", "Stock Qty", "Reorder Level", "Status"
            }
        ));
        jScrollPane1.setViewportView(productTable);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Stock Information");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        try {
            // ၁။ လူရိုက်ထည့်လိုက်သော စာသားအချက်အလက်များကို ရယူခြင်း
            String prodCode = productCodeTxt.getText().trim();
            String barCode = barCodeTxt.getText().trim(); // (လိုအပ်ပါက သုံးရန်)
            String prodName = productNameTxt.getText().trim();

            // အချက်အလက် မပြည့်စုံပါက ရှေ့ဆက်မသွားရန် တားဆီးခြင်း
            if (prodCode.isEmpty() || prodName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ကျေးဇူးပြု၍ Product Code နှင့် နာမည်ကို မဖြစ်မနေ ဖြည့်စွက်ပေးပါဗျာ။", "သတိပေးချက်", JOptionPane.WARNING_MESSAGE);
                return;
            }

            CategoryDTO selectedCat = null;
            Object catItem = categoryCombo.getSelectedItem();
            if (catItem instanceof CategoryDTO) {
                selectedCat = (CategoryDTO) catItem;
            }

            // --- Unit 1 အတွက် ပြင်ရန် ---
            UnitDTO u1 = null;
            Object unit1Item = cmbUnitLevel1.getSelectedItem();
            if (unit1Item instanceof UnitDTO) {
                u1 = (UnitDTO) unit1Item;
            }

            // --- Unit 2 အတွက် ပြင်ရန် ---
            UnitDTO u2 = null;
            Object unit2Item = cmbUnitLevel2.getSelectedItem();
            if (unit2Item instanceof UnitDTO) {
                u2 = (UnitDTO) unit2Item;
            }

            // --- Unit 3 အတွက် ပြင်ရန် ---
            UnitDTO u3 = null;
            Object unit3Item = cmbUnitLevel3.getSelectedItem();
            if (unit3Item instanceof UnitDTO) {
                u3 = (UnitDTO) unit3Item;
            }

            // --- Unit 4 အတွက် ပြင်ရန် ---
            UnitDTO u4 = null;
            Object unit4Item = cmbUnitLevel4.getSelectedItem();
            if (unit4Item instanceof UnitDTO) {
                u4 = (UnitDTO) unit4Item;
            }


            int f1_2 = Integer.parseInt(txtFactor1To2.getText().trim()); // ဥပမာ- ၁ ဖာမှာ ၁၀ ဒါဇင်
            int f2_3 = Integer.parseInt(txtFactor2To3.getText().trim()); // ဥပမာ- ၁ ဒါဇင်မှာ ၁၂ ကတ်
            int f3_4 = Integer.parseInt(txtFactor3To4.getText().trim()); // ဥပမာ- ၁ ကတ်မှာ ၁၀ ခု
            int f4_5 = Integer.parseInt(txtFactor4To5.getText().trim()); // ဥပမာ- ၁ ကတ်မှာ ၁၀ ခု

            // ၄။ အဝယ်အရေအတွက်နှင့် ဈေးနှုန်းများကို ရယူခြင်း
            int inputQty = Integer.parseInt(stockQtyTxt.getText().trim()); // ဥပမာ- ၅ ဖာ ဝယ်တယ်
            double purchasePrice = Double.parseDouble(purchasePriceTxt.getText().trim()); // ၁ ခုစာ ဝယ်ဈေး
            double salePrice = Double.parseDouble(salePriceTxt.getText().trim());       // ၁ ခုစာ ရောင်းဈေး
            int reorderLevel = Integer.parseInt(reorderLevelTxt.getText().trim());

            // 🌟 ၅။ အရေးကြီးဆုံးအပိုင်း - အသေးဆုံး "ခု (Pcs)" အရေအတွက်ဖြစ်အောင် ဆင့်ကဲမြှောက်တွက်ခြင်း
            // ၅ ဖာ x ၁၀ ဒါဇင် x ၁၂ ကတ် x ၁၀ ခု = စုစုပေါင်း ဆေးပြား အရေအတွက်
            int totalPcs = inputQty * f1_2 * f2_3 * f3_4 * f4_5;

            // ၆။ အချက်အလက်အားလုံးကို DTO ထဲသို့ ထည့်သွင်းခြင်း
            ProductDTO dto = new ProductDTO();
            dto.setProductCode(prodCode);
            dto.setBarcode(barCode);
            dto.setProductName(prodName);
            
            dto.setCategoryId(selectedCat.getCategoryId());

            dto.setUnitLevel1Id(u1.getUnit_id());
            dto.setUnitLevel2Id(u2.getUnit_id());
            dto.setUnitLevel3Id(u3.getUnit_id());
            dto.setUnitLevel4Id(u4.getUnit_id());

            dto.setFactor1To2(f1_2);
            dto.setFactor2To3(f2_3);
            dto.setFactor3To4(f3_4);

            
            
            // အစောနက Level 1 ရွေးချယ်ထားတဲ့ ယူနစ် DTO (u1) ထဲက ID ကို ယူပြီး ထည့်ပေးရပါမယ်
            if (u1 != null) {
                dto.setUnitId(u1.getUnit_id()); 
            } else {
                dto.setUnitId(1); // တကယ်လို့ u1 က null ဖြစ်နေရင်တောင် DB ထဲမှာ တကယ်ရှိတဲ့ Unit ID တစ်ခုခုကို Default ပေးထားလိုက်ပါ
            }
            
                 
            dto.setStockQty(totalPcs); // မြှောက်လို့ရလာတဲ့ စုစုပေါင်း "ခု" အရေအတွက်ကို သိမ်းမည်
            dto.setPurchasePrice(purchasePrice);
            dto.setSalePrice(salePrice);
            dto.setReorderLevel(reorderLevel);
            dto.setIsActive(true);

            // ၇။ DAO ကို လှမ်းခေါ်ပြီး Database ထဲ ဒေတာထည့်ခြင်း
            ProductDAO dao = new ProductDAO();
            boolean isSuccess = dao.insertProductDAO(dto);

            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "ဆေးပစ္စည်းအသစ်ကို ဒေတာဘေ့စ်ထဲ အောင်မြင်စွာ ထည့်သွင်းပြီးပါပြီဗျာ။", "အောင်မြင်ပါသည်", JOptionPane.INFORMATION_MESSAGE);

                // loadTableData(); // ဘယ်ဘက်က JTable ဇယားကို ဒေတာအသစ်နဲ့ Refresh ဖြစ်အောင် ပြန်ခေါ်ပေးခြင်း
                //  clearFields();   // ရိုက်ထားတဲ့ စာသားကွက်လပ်တွေကို ရှင်းထုတ်ပေးခြင်း
            } else {
                JOptionPane.showMessageDialog(this, "ဒေတာဘေ့စ်ထဲ ထည့်သွင်းရာတွင် အဆင်မပြေမှု ဖြစ်ပွားခဲ့ပါသည်။", "မှားယွင်းမှု", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "ကျေးဇူးပြု၍ ပါဝင်မှုနှုန်း (Factor)၊ အရေအတွက်နှင့် ဈေးနှုန်းကွက်များတွင် ဂဏန်းများသာ မှန်ကန်စွာ ရိုက်ထည့်ပေးပါဗျာ။", "ဂဏန်းအမှား", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "မှားယွင်းမှု", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField barCodeTxt;
    private javax.swing.JComboBox categoryCombo;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox cmbUnitLevel1;
    private javax.swing.JComboBox cmbUnitLevel2;
    private javax.swing.JComboBox cmbUnitLevel3;
    private javax.swing.JComboBox cmbUnitLevel4;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField productCodeTxt;
    private javax.swing.JTextField productNameTxt;
    private javax.swing.JTable productTable;
    private javax.swing.JTextField purchasePriceTxt;
    private javax.swing.JTextField reorderLevelTxt;
    private javax.swing.JTextField salePriceTxt;
    private javax.swing.JComboBox<String> statusCombo;
    private javax.swing.JTextField stockQtyTxt;
    private javax.swing.JTextField txtFactor1To2;
    private javax.swing.JTextField txtFactor2To3;
    private javax.swing.JTextField txtFactor3To4;
    private javax.swing.JTextField txtFactor4To5;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
