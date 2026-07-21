/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.erp;

import com.erp.DAO.PurchaseReturnDetailsDAO;
import com.erp.DAO.PurchaseReturnHeaderDAO;
import com.erp.DAO.SupplierDAO;
import com.erp.DTO.ComboIdName;
import com.erp.DTO.PurchaseDetailDTO;
import com.erp.DTO.PurchaseReturnDetailsDTO;
import com.erp.DTO.PurchaseReturnHeaderDTO;
import com.erp.DTO.SupplierDTO;
import com.erp.DTO.UserSessionDTO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zaw Min Htwe
 */
public class PurchaseReturnPage extends javax.swing.JPanel {

    /**
     * Creates new form PurchaseReturnPage
     */
    public PurchaseReturnPage() {
        initComponents();
        loadSupplierToCombo();

        String currentUser = UserSessionDTO.getLoggedInUserName();
        authorisedByTxt.setText(currentUser);

        // 🔒 User က လက်ရှိ Authorised By နာမည်ကို လျှောက်ပြင်လို့မရအောင် Lock ချထားခြင်း
        authorisedByTxt.setEditable(false);

        // 🎨 နောက်ခံအရောင်လေးကိုပါ အနည်းငယ်မှိန်ပြီး ပြင်မရကြောင်း visual ပြသချင်ပါက (Optional)
        authorisedByTxt.setBackground(new java.awt.Color(240, 240, 240));
        authorisedByTxt.setForeground(java.awt.Color.BLACK);
        
        subTotal();
        autoGenerateVoucherNo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        supplierCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        referenceCombo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        returnQtyTxt = new javax.swing.JTextField();
        returnFocTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        itemCombo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        issueDateCombo = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        authorisedByTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        priceTxt = new javax.swing.JTextField();
        discountTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        purchaseReturnNoTxt = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        purchaseReturnReasonTxt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        subTotalTxt = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        purchaseReturnTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Supplier");

        supplierCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierComboActionPerformed(evt);
            }
        });

        jLabel2.setText("Reference No");

        referenceCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                referenceComboActionPerformed(evt);
            }
        });

        jLabel4.setText("Return Qty");

        returnFocTxt.setText("0");

        jLabel5.setText("Return FOC");

        jLabel6.setText("Item");

        jLabel7.setText("Issue Date");

        jLabel8.setText("Authorised By");

        jLabel9.setText("Price");

        discountTxt.setText("0");

        jLabel10.setText("Discount %");

        jLabel13.setText("Return No");

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setText("Delete Row");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 255, 0));
        jButton1.setText("Add Row");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Reason");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supplierCombo, 0, 138, Short.MAX_VALUE)
                    .addComponent(referenceCombo, 0, 138, Short.MAX_VALUE)
                    .addComponent(itemCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(returnQtyTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(returnFocTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(issueDateCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(discountTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(authorisedByTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchaseReturnNoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchaseReturnReasonTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(purchaseReturnNoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(supplierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(returnQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(purchaseReturnReasonTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(discountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(referenceCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(returnFocTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(itemCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(issueDateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(authorisedByTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)
                        .addComponent(jButton1)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jLabel3.setText("Sub Total");

        jButton2.setBackground(new java.awt.Color(0, 255, 0));
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setText("Grand Total");

        jLabel12.setText("Delear Discount");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 641, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(subTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(subTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        purchaseReturnTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Item", "Qty", "Foc", "Price", "Discount", "Amount"
            }
        ));
        jScrollPane1.setViewportView(purchaseReturnTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        newRow();
        subTotal();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = purchaseReturnTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) purchaseReturnTable.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Select Row");
        }
        subTotal();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void supplierComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierComboActionPerformed
        if (supplierCombo.getSelectedItem() == null) {
            return;
        }

        //to get id from comboBox
        SupplierDTO selectedSup = null;
        Object catItem = supplierCombo.getSelectedItem();
        if (catItem instanceof SupplierDTO) {
            selectedSup = (SupplierDTO) catItem;
        }

        int supplierId = selectedSup.getId();

        // Reference (Voucher No) Combo ကို အရင်ရှင်းထုတ်ခြင်း
        referenceCombo.removeAllItems();
        itemCombo.removeAllItems(); // Item ပြားကိုပါ တစ်ခါတည်း ရှင်းပစ်မည်

        // Database မှ ဒေတာခေါ်ပြီး ပြန်ထည့်ခြင်း
        PurchaseReturnDetailsDAO dao = new PurchaseReturnDetailsDAO();
        List<ComboIdName> vouchers = dao.getVouchersBySupplier(supplierId);

        for (ComboIdName vNo : vouchers) {
            referenceCombo.addItem(vNo);
        }
    }//GEN-LAST:event_supplierComboActionPerformed

    private void referenceComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_referenceComboActionPerformed
        if (referenceCombo.getSelectedItem() == null) {
            return;
        }

        // ရွေးလိုက်သော Voucher No စာသားကို ယူခြင်း
        String selectedVoucherNo = referenceCombo.getSelectedItem().toString();

        // Item Combo ကို ရှင်းထုတ်ခြင်း
        itemCombo.removeAllItems();

        // Database မှ အဲ့ဒီ Voucher ထဲက ဆေးဝါးများကို ယူခြင်း
        PurchaseReturnDetailsDAO dao = new PurchaseReturnDetailsDAO();
        List<ComboIdName> items = dao.getItemsByVoucher(selectedVoucherNo);

        for (ComboIdName item : items) {
            // ComboIdName သုံးပြီး အရင်တစ်ခေါက် နည်းလမ်း (၂) အတိုင်း သပ်သပ်ရပ်ရပ် ထည့်ခြင်း
            itemCombo.addItem(item);

            double originalPrice = item.getPrice();

            // ၃။ Textbox ထဲသို့ တိုက်ရိုက်ထည့်ခြင်း
            priceTxt.setText(String.format("%.2f", originalPrice)); // အစ်ကို့ Price Textbox အမည်ပြောင်းရန်
        }
    }//GEN-LAST:event_referenceComboActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if (purchaseReturnNoTxt.getText().trim().isEmpty() || 
                purchaseReturnReasonTxt.getText().trim().isEmpty() || 
                issueDateCombo.getDate().toString().isEmpty()
                
                ) {
        JOptionPane.showMessageDialog(this, "Please fill for reason");
        return; 
    }

    PurchaseReturnHeaderDTO prhDTO = new PurchaseReturnHeaderDTO();
    
         
//to get id from comboBox
        SupplierDTO selectedSup = null;
        Object catItem = supplierCombo.getSelectedItem();
        if (catItem instanceof SupplierDTO) {
            selectedSup = (SupplierDTO) catItem;
        }
        int supplierId = selectedSup.getId();
        prhDTO.setSupplierId(supplierId);
        prhDTO.setSupplier(supplierCombo.getSelectedItem().toString());
        
         if (itemCombo.getSelectedItem() != null) {
            // 🚀 ၁။ Selected Item ကို ComboIdName Object အဖြစ် ပြောင်းယူခြင်း
            ComboIdName selectedProduct = (ComboIdName) itemCombo.getSelectedItem();
            int vid = selectedProduct.getVid();
            prhDTO.setPurchaseInvoiceNoId(vid);
        }
        
        
       
       
        prhDTO.setPurchaseInvoiceNo(referenceCombo.getSelectedItem().toString());
    
         
         
    prhDTO.setPurchaseReturnNo(purchaseReturnNoTxt.getText().trim());
    prhDTO.setReturnDate(issueDateCombo.getDate());
    prhDTO.setReasonReturn(purchaseReturnReasonTxt.getText().trim());
    prhDTO.setAuthorisedByTxt(authorisedByTxt.getText().trim());

        
        System.out.println(prhDTO );
        System.out.println(supplierId);
    
    PurchaseReturnHeaderDAO prhDAO = new PurchaseReturnHeaderDAO();
    boolean success = prhDAO.insertReturnHeaderDAO(prhDTO);
    
    if (success) {
        //  loadDataSet();
        // clearFields();
        JOptionPane.showMessageDialog(this, "Purchase Return Header Filled");
    } else {
        // 💡 ခေါင်းစဉ်ကို ပြောင်းလိုက်ပါပြီ (ဒါမှ ဒေတာဘေ့စ်ဆာဗာ ဒေါင်းနေရင်လည်း သိနိုင်မှာပါ)
        JOptionPane.showMessageDialog(this, "Purchase Return Header Unfilled");
    }

        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void newRow() {
        DefaultTableModel model = (DefaultTableModel) purchaseReturnTable.getModel();

        // ၁။ ကွန်ပျူတာရဲ့ ယနေ့ရက်စွဲကို "2026-06-22" ပုံစံ စာသားအဖြစ် အော်တိုပြောင်းယူမယ်
        String todayDate = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());

        // ၂။ ရက်စွဲနေရာမှာ ယနေ့ရက်စွဲကို တစ်ခါတည်းထည့်ပြီး ဇယားထဲ လိုင်းအသစ်တစ်လိုင်း တိုးပေးလိုက်မယ်
        // [ Date | Voucher | Particular | Debit | Credit ] အစီအစဉ်အတိုင်း ဖြစ်ပါတယ်
        PurchaseReturnDetailsDTO purchaseReturnDTO = new PurchaseReturnDetailsDTO();

        String itemName = null;
        if (itemCombo.getSelectedItem() != null) {
            // 🚀 ၁။ Selected Item ကို ComboIdName Object အဖြစ် ပြောင်းယူခြင်း
            ComboIdName selectedProduct = (ComboIdName) itemCombo.getSelectedItem();

            // ၂။ ၎င်းထဲမှ ID, Name နှင့် Price တို့ကို စိတ်ကြိုက် ဆွဲထုတ်အသုံးပြုခြင်း
            int itemId = selectedProduct.getId();          // Item ID ရယူရန်
            itemName = selectedProduct.getName();    // Item Name ရယူရန်
            double itemPrice = selectedProduct.getPrice();  // Item Price (ဈေးနှုန်း) ရယူရန်
            int vid = selectedProduct.getVid();
            // စမ်းသပ်ကြည့်ရန် Console မှာ ထုတ်ပြခြင်း
            System.out.println("ID: " + itemId + ", Name: " + itemName + ", Price: " + itemPrice + ", Vid: " + vid);
        }

        int qty = Integer.parseUnsignedInt(returnQtyTxt.getText());
        int foc = Integer.parseUnsignedInt(returnFocTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int discount = Integer.parseUnsignedInt(discountTxt.getText());
        //int supid = purchaseReturnDTO.setSupplierId(Integer.parseInt(supplierCombo.getSelectedItem()));

        model.addRow(new Object[]{todayDate, itemName, qty, foc, price, discount, (qty * price)-(qty * price)* discount/100});

    }

    private void loadSupplierToCombo() {
        supplierCombo.removeAllItems();
        SupplierDAO supplierDAO = new SupplierDAO();
        List<SupplierDTO> suppliers = supplierDAO.getAllSupplierNames();
        enableAutoComplete(supplierCombo, suppliers);
    }

    private void enableAutoComplete(JComboBox comboBox, List<?> dataList) {
        // 🎯 ၁။ အစပိုင်းမှာ ဒေတာအားလုံးကို ComboBox ထဲ တန်းပေါ်နေအောင် အပြည့်ထည့်ထားခြင်း (ဒီလိုင်းလိုနေခဲ့တာပါဗျာ)
        DefaultComboBoxModel initialModel = new DefaultComboBoxModel();
        for (Object item : dataList) {
            initialModel.addElement(item);
        }
        comboBox.setModel(initialModel);
        comboBox.setSelectedIndex(-1); // အစပိုင်းမှာ ဘာမှမရွေးရသေးပဲ အလွတ်ပြထားရန်

        // ၂။ ComboBox ကို စာရိုက်လို့ရအောင် ပြုလုပ်ခြင်း
        comboBox.setEditable(true);
        JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();

        // 🎯 ၃။ [UX အပိုဆောင်း] စာရိုက်ရုံတင်မက အကွက်ကို Mouse နဲ့ ကလစ်နှိပ်လိုက်ရင်လည်း Dropdown တန်းပွင့်လာစေရန်
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    comboBox.showPopup();
                }
            }
        });

        // ၄။ စာရိုက်တိုင်း ဒေတာကို စစ်ထုတ်ပေးမည့် KeyListener
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                // အပေါ်၊ အောက်၊ Enter၊ Escape နှိပ်ပါက ရှာဖွေမှုကို ခေတ္တကျော်မည်
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP
                        || e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN
                        || e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER
                        || e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                    return;
                }

                String typedText = textField.getText();
                DefaultComboBoxModel model = new DefaultComboBoxModel();

                // ရိုက်ထားသော စာသားပါဝင်သည့် ဒေတာများကို လိုက်ရှာခြင်း
                for (Object item : dataList) {
                    if (item.toString().toLowerCase().contains(typedText.toLowerCase())) {
                        model.addElement(item);
                    }
                }

                // စစ်ထုတ်ထားသော ဒေတာအသစ်ကို ComboBox ထဲ ထည့်ခြင်း
                comboBox.setModel(model);
                textField.setText(typedText);

                if (!typedText.isEmpty()) {
                    comboBox.showPopup();
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorisedByTxt;
    private javax.swing.JTextField discountTxt;
    private com.toedter.calendar.JDateChooser issueDateCombo;
    private javax.swing.JComboBox itemCombo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField priceTxt;
    private javax.swing.JTextField purchaseReturnNoTxt;
    private javax.swing.JTextField purchaseReturnReasonTxt;
    private javax.swing.JTable purchaseReturnTable;
    private javax.swing.JComboBox referenceCombo;
    private javax.swing.JTextField returnFocTxt;
    private javax.swing.JTextField returnQtyTxt;
    private javax.swing.JTextField subTotalTxt;
    private javax.swing.JComboBox supplierCombo;
    // End of variables declaration//GEN-END:variables

    private void subTotal() {
        
        DefaultTableModel model = (DefaultTableModel) purchaseReturnTable.getModel();
        double subTotal = 0.0;
        

        for (int i = 0; i < model.getRowCount(); i++) {
            // --- Debit ကော်လံ (Index 5) ကို စနစ်တကျ ဖတ်ခြင်း ---
            Object subTotalVal = model.getValueAt(i, 6);
            if (subTotalVal != null) {
                try {
                    // Number အမျိုးအစားဖြစ်ဖြစ်၊ String အမျိုးအစားဖြစ်ဖြစ် အမှားကင်းအောင် ပြောင်းလဲခြင်း
                    subTotal += Double.parseDouble(subTotalVal.toString().trim());
                } catch (NumberFormatException e) {
                    // အကယ်၍ အကြောင်းအမျိုးမျိုးကြောင့် စာသားဖြစ်နေပါက ဂဏန်း 0 ဟုသာ သတ်မှတ်မည်
                    subTotal += 0.0;
                }
            }

         
           
        }

        subTotalTxt.setText(String.valueOf(subTotal));

        
    }
    
    private void autoGenerateVoucherNo() {
        // 1. DAO ကို လှမ်းခေါ်တယ်
        com.erp.DAO.PurchaseReturnHeaderDAO dao = new com.erp.DAO.PurchaseReturnHeaderDAO();

        // 2. နောက်ထပ်ဖြစ်မယ့် ဘောချာနံပါတ်ကို လှမ်းတောင်းတယ်
        String nextVoucherNo = dao.getNextVoucherNo();

        // 3. ရလာတဲ့ နံပါတ်ကို UI က TextBox ထဲ ထည့်ပေးတယ်
        purchaseReturnNoTxt.setText(nextVoucherNo);
        purchaseReturnNoTxt.setEditable(false);
    }
}
