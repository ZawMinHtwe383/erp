/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.erp;

import com.erp.DAO.ProductDAO;
import com.erp.DAO.PurchaseVoucherDAO;
import com.erp.DAO.SupplierDAO;
import com.erp.DAO.UnitDAO;
import com.erp.DTO.ProductDTO;
import com.erp.DTO.PurchaseDetailDTO;
import com.erp.DTO.PurchaseVoucherDTO;
import com.erp.DTO.SupplierDTO;
import com.erp.DTO.UnitDTO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zaw Min Htwe
 */
public class PurchasePage extends javax.swing.JPanel {

    /**
     * Creates new form PurchasePage
     */
    public PurchasePage() {
        initComponents();
        autoGenerateVoucherNo();
        loadSupplierToCombo();
        loadUnitsToCombo();
        loadProductToCombo();
    }

  private void autoGenerateVoucherNo() {
    // 1. DAO ကို လှမ်းခေါ်တယ်
    com.erp.DAO.PurchaseVoucherDAO dao = new com.erp.DAO.PurchaseVoucherDAO();
    
    // 2. နောက်ထပ်ဖြစ်မယ့် ဘောချာနံပါတ်ကို လှမ်းတောင်းတယ်
    String nextVoucherNo = dao.getNextVoucherNo();
    
    // 3. ရလာတဲ့ နံပါတ်ကို UI က TextBox ထဲ ထည့်ပေးတယ်
    voucherNoTxt.setText(nextVoucherNo);
    voucherNoTxt.setEditable(false); // လူက ဝင်ပြင်လို့မရအောင် ပိတ်ထားခြင်း
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dateTxt = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        supplierCmb = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        voucherNoTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        productNameCmb = new javax.swing.JComboBox<>();
        unitCmb = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        priceTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        qtyTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        focTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        disTxt = new javax.swing.JTextField();
        addTableBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        purchaseTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        subTotalTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        discountTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        grandTxt = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();

        jLabel4.setText("Supplier");

        supplierCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        supplierCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                supplierCmbItemStateChanged(evt);
            }
        });
        supplierCmb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                supplierCmbKeyReleased(evt);
            }
        });

        jLabel5.setText("Voucher No");

        jLabel6.setText("Date");

        jLabel7.setText("Product Name");

        productNameCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        unitCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Unit");

        jLabel9.setText("Price");

        jLabel10.setText("Qty");

        jLabel11.setText("FOC");

        jLabel12.setText("Discount");

        disTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                disTxtKeyReleased(evt);
            }
        });

        addTableBtn.setText("Add to Table");
        addTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTableBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(supplierCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(voucherNoTxt))))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(productNameCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(unitCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(priceTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(disTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(qtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(focTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(addTableBtn)
                .addGap(294, 294, 294))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(supplierCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(productNameCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(qtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTableBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(voucherNoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(unitCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(focTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(disTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        purchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Product Code", "Product Name", "Product ID", "Unit", "Unit ID", "Qty", "FOC", "Price", "Discount", "Amount"
            }
        ));
        jScrollPane1.setViewportView(purchaseTable);
        if (purchaseTable.getColumnModel().getColumnCount() > 0) {
            purchaseTable.getColumnModel().getColumn(0).setMinWidth(50);
            purchaseTable.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );

        jLabel1.setText("Sub Total");

        jLabel2.setText("Delar Discount");

        discountTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discountTxtKeyReleased(evt);
            }
        });

        jLabel3.setText("Grand Total");

        addBtn.setText("Save ");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(addBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grandTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(discountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grandTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addBtn)
                    .addComponent(clearBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTableBtnActionPerformed
       try {
        // Validation: မဖြစ်မနေလိုအပ်တဲ့ ပစ္စည်း၊ အရေအတွက်နဲ့ ဈေးနှုန်း ပါမပါ စစ်ဆေးခြင်း
        if(productNameCmb.getSelectedIndex() == -1 || qtyTxt.getText().trim().isEmpty() || priceTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ကျေးဇူးပြု၍ လိုအပ်သော အချက်အလက်များ အကုန်ဖြည့်စွက်ပါ!", "သတိပေးချက်", JOptionPane.WARNING_MESSAGE);
            return;
        }

        
                //to get id from comboBox
        ProductDTO selectedPro = null;
        Object productId = productNameCmb.getSelectedItem();
        if (productId instanceof ProductDTO) {
                selectedPro = (ProductDTO) productId;
            }
         String prodName = productNameCmb.getSelectedItem().toString();
          int prodId = selectedPro.getProductId();
        if(selectedPro != null){
            
       
         //  System.out.println(prodId);
        }
       

        
        UnitDTO selectedUnit = null;
        Object unitID = unitCmb.getSelectedItem();
        if(unitID instanceof UnitDTO){
            selectedUnit =(UnitDTO) unitID;
        }           
        String unitName = unitCmb.getSelectedItem().toString();
         int unitId = selectedUnit.getUnit_id();
        if(selectedUnit != null){
             
        
        //   System.out.println(unitId);
        }
        
        
       
        
        int qty = Integer.parseInt(qtyTxt.getText().trim());
        int foc = focTxt.getText().trim().isEmpty() ? 0 : Integer.parseInt(focTxt.getText().trim());
        double price = Double.parseDouble(priceTxt.getText().trim());
        double lineDiscount = disTxt.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(disTxt.getText().trim());

        // လိုင်းအလိုက် ကျသင့်ငွေ တွက်ချက်ခြင်း: (Qty * Price) - Line Discount
        double amount = (qty * price) - lineDiscount;

        // JTable Model ထဲသို့ Row အသစ် လှမ်းထည့်ခြင်း
        DefaultTableModel model = (DefaultTableModel) purchaseTable.getModel(); // purchaseTable နေရာတွင် မိမိ JTable နာမည် အစားထိုးပါ
        int rowCount = model.getRowCount() + 1;
  
        
        model.addRow(new Object[]{
            rowCount,       // No
            "Code",         // Product Code (လိုအပ်ပါက ဖြည့်စွက်ရန်)
            prodName,       // Product Name
            prodId,
            unitName,       // Unit
            unitId,
            qty,            // Qty
            foc,            // FOC
            price,          // Price
            lineDiscount,   // Discount
            amount          // Amount
        });

        
        
        
        
        
        // အောက်ခြေက Footer စုစုပေါင်း金額တွေကို တွက်ခိုင်းခြင်း
        calculateTotals();
        
        // ပစ္စည်းတစ်ခု ထည့်ပြီးတိုင်း Input အကွက်များကို ပြန်ရှင်းပေးခြင်း
        qtyTxt.setText("");
        focTxt.setText("");
        priceTxt.setText("");
        disTxt.setText("");
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "အရေအတွက်နှင့် ဈေးနှုန်းများတွင် ဂဏန်းများသာ မှန်ကန်စွာ ရိုက်ထည့်ပေးပါရန်။", "Format မှားယွင်းမှု", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addTableBtnActionPerformed

    private void disTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_disTxtKeyReleased
        
    }//GEN-LAST:event_disTxtKeyReleased

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
       // Dropdown များကို အစဦးဆုံးနေရာ ပြန်ရွှေ့ခြင်း
    supplierCmb.setSelectedIndex(0);
    productNameCmb.setSelectedIndex(0);
    unitCmb.setSelectedIndex(0);
    
    // TextFields များကို ရှင်းလင်းခြင်း
    voucherNoTxt.setText("");
    qtyTxt.setText("");
    focTxt.setText("");
    priceTxt.setText("");
    disTxt.setText("");
    
    // JCalendar/JDateChooser သုံးထားပါက ရက်စွဲကို ယနေ့ရက်စွဲ ပြန်ပြောင်းခြင်း
    // (တကယ်လို့ dateTxt က JTextField ဖြစ်ရင် dateTxt.setText(""); လို့ ရေးပေးပါ)
    dateTxt.setDate(new java.util.Date()); 
    
    // ဇယားထဲက ဒေတာများ အကုန်ဖျက်ခြင်း
    DefaultTableModel model = (DefaultTableModel) purchaseTable.getModel();
    model.setRowCount(0);
    
    // Footer အကွက်များ Zero ပြန်ချခြင်း
    subTotalTxt.setText("0.0");
    discountTxt.setText("0.0");
    grandTxt.setText("0.0");
    }//GEN-LAST:event_clearBtnActionPerformed

    private void discountTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountTxtKeyReleased
       calculateTotals();
    }//GEN-LAST:event_discountTxtKeyReleased

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
                                   
    DefaultTableModel model = (DefaultTableModel) purchaseTable.getModel(); // မိမိ JTable နာမည်
 
    
    // ၁။ ဇယားထဲမှာ ပစ္စည်းရှိမရှိ အရင်စစ်မယ်
    if(model.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "ဘောချာထဲတွင် မည်သည့်ပစ္စည်းမှ မရှိသေးပါ!", "သတိပေးချက်", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // ၂။ ပင်မ Voucher DTO ဆောက်ပြီး Header ဒေတာများ ထည့်မယ်
        PurchaseVoucherDTO voucherDTO = new PurchaseVoucherDTO();
        voucherDTO.setVoucherNo(voucherNoTxt.getText().trim());
        
 
        
        //to get id from comboBox
        SupplierDTO selectedSup = null;
        Object catItem = supplierCmb.getSelectedItem();
        if (catItem instanceof SupplierDTO) {
                selectedSup = (SupplierDTO) catItem;
            }
        voucherDTO.setSupplierId(selectedSup.getId()); // (မိမိတို့ Supplier ID Map ပြန်လုပ်ရန်)
  
        voucherDTO.setPurchaseDate(dateTxt.getDate()); // JDateChooser သုံးထားပါက
        voucherDTO.setSubTotal(Double.parseDouble(subTotalTxt.getText()));
        
        double vDiscount = discountTxt.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(discountTxt.getText().trim());
        voucherDTO.setVoucherDiscount(vDiscount);
        voucherDTO.setGrandTotal(Double.parseDouble(grandTxt.getText()));

        // ၃။ JTable ထဲက အတန်းတွေကို ပတ်ပြီး Detail DTO ထဲထည့်ကာ Voucher DTO ထဲ စုထည့်မယ်
        for (int i = 0; i < model.getRowCount(); i++) {
            PurchaseDetailDTO itemDTO = new PurchaseDetailDTO();
            
           
            int productId = Integer.parseInt(model.getValueAt(i, 2).toString());
            itemDTO.setProductId(productId);
           
            int unitId = Integer.parseInt(model.getValueAt(i, 3).toString());
            itemDTO.setUnitId(unitId);
            
            itemDTO.setQty(Integer.parseInt(model.getValueAt(i, 4).toString()));       // Index 4 = Qty
            itemDTO.setFoc(Integer.parseInt(model.getValueAt(i, 5).toString()));       // Index 5 = FOC
            itemDTO.setPrice(Double.parseDouble(model.getValueAt(i, 6).toString()));    // Index 6 = Price
            itemDTO.setLineDiscount(Double.parseDouble(model.getValueAt(i, 7).toString())); // Index 7 = Discount
            itemDTO.setAmount(Double.parseDouble(model.getValueAt(i, 8).toString()));   // Index 8 = Amount

            // Main DTO ကြီးထဲသို့ လှမ်းထည့်ပစ်ခြင်း
            voucherDTO.addPurchaseItem(itemDTO);
        }

        // ၄။ DAO ကို ခေါ်ပြီး DTO တစ်ခုတည်းကိုပဲ Parameter အနေနဲ့ ပစ်ပေးလိုက်တော့မယ်
        com.erp.DAO.PurchaseVoucherDAO dao = new com.erp.DAO.PurchaseVoucherDAO();
        boolean isSuccess = dao.savePurchaseVoucher(voucherDTO); // 👈 ဒေတာထုပ်ကြီး သွားပြီ

        if(isSuccess) {
            JOptionPane.showMessageDialog(this, "အဝယ်ဘောချာ သိမ်းဆည်းခြင်း အောင်မြင်ပါသည်!", "အောင်မြင်မှု", JOptionPane.INFORMATION_MESSAGE);
            clearBtnActionPerformed(null); // Form ကို Reset ပြန်ချမယ်
        } else {
            JOptionPane.showMessageDialog(this, "ဒေတာဘေ့စ် သိမ်းဆည်းမှု လွဲချော်ခဲ့ပါသည်။", "အမှားအယွင်း", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "ဒေတာပြောင်းလဲမှု အမှားအယွင်းရှိနေပါသည်။: " + e.getMessage(), "အမှား", JOptionPane.ERROR_MESSAGE);
    }


    }//GEN-LAST:event_addBtnActionPerformed

    private void supplierCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_supplierCmbItemStateChanged
        
        
    }//GEN-LAST:event_supplierCmbItemStateChanged

    private void supplierCmbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supplierCmbKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierCmbKeyReleased
private void calculateTotals() {
    DefaultTableModel model = (DefaultTableModel) purchaseTable.getModel();
    double subTotal = 0.0;
    double disTotal =0.0;

    // ဇယားထဲရှိ အတန်းအားလုံးကို ပတ်ပြီး Amount ကော်လံ (နောက်ဆုံးကော်လံ Index 8) ကို ပေါင်းခြင်း
    for (int i = 0; i < model.getRowCount(); i++) {
        subTotal += (double) model.getValueAt(i, 10);
    }

    subTotalTxt.setText(String.valueOf(subTotal));

  // item discount total
  //    for (int i = 0; i < model.getRowCount(); i++) {
//          disTotal += (double) model.getValueAt(i, 7);
//    }
//    discountTxt.setText(String.valueOf(disTotal));
    
    
    // ဘောချာတစ်ခုလုံးစာ ပေးမယ့် Discount ကို ဖတ်ခြင်း
    double voucherDiscount = 0.0;
    try {
        if (!discountTxt.getText().trim().isEmpty()) {
            voucherDiscount = Double.parseDouble(discountTxt.getText().trim());
        }
    } catch (NumberFormatException e) {
        voucherDiscount = 0.0; // ဂဏန်းမဟုတ်တာ ရိုက်ထားရင် 0 လို့ပဲ ယူမယ်
    }

    // အပြီးသတ် ကျသင့်ငွေ = SubTotal - Voucher Discount
    double grandTotal = subTotal - voucherDiscount;
    grandTxt.setText(String.valueOf(grandTotal));
}

    private void loadUnitsToCombo(){
        unitCmb.removeAllItems();
        UnitDAO unitDAO = new UnitDAO();
        List<UnitDTO> units = unitDAO.getAllUnitNames();
       
        //System.out.println(units);
        for (UnitDTO unit : units) {
            unitCmb.addItem(unit);
            
        }
        
    }
    
    private void loadProductToCombo(){
        productNameCmb.removeAllItems();
        ProductDAO productDAO = new ProductDAO();
        List<ProductDTO> product = productDAO.getAllProductsName();
        for (ProductDTO pro : product) {
            productNameCmb.addItem(pro);
            
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton addTableBtn;
    private javax.swing.JButton clearBtn;
    private com.toedter.calendar.JDateChooser dateTxt;
    private javax.swing.JTextField disTxt;
    private javax.swing.JTextField discountTxt;
    private javax.swing.JTextField focTxt;
    private javax.swing.JTextField grandTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTextField priceTxt;
    private javax.swing.JComboBox<Object> productNameCmb;
    private javax.swing.JTable purchaseTable;
    private javax.swing.JTextField qtyTxt;
    private javax.swing.JTextField subTotalTxt;
    private javax.swing.JComboBox<Object> supplierCmb;
    private javax.swing.JComboBox<Object> unitCmb;
    private javax.swing.JTextField voucherNoTxt;
    // End of variables declaration//GEN-END:variables

    
    
    private void loadSupplierToCombo() {
         supplierCmb.removeAllItems();
         SupplierDAO supplierDAO = new SupplierDAO();
        List<SupplierDTO> suppliers = supplierDAO.getAllSupplierNames();
       
        for (SupplierDTO sup : suppliers) {
            supplierCmb.addItem(sup);

        }
    }
}
