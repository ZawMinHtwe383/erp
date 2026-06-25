/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.erp;

import com.erp.DAO.CashBookDetailsDAO;
import com.erp.DAO.CustomerDAO;
import com.erp.DAO.SupplierDAO;
import com.erp.DTO.CashBookDetailsDTO;
import com.erp.DTO.ComboIdName;
import java.awt.Component;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zaw Min Htwe
 */
public class CashBookDetailsPage extends javax.swing.JPanel {

    private JComboBox<ComboIdName> accuontNameComboBox;
    private JComboBox<ComboIdName> customerComboBox;
    private JComboBox<ComboIdName> supplierComboBox;

    private CashBookDetailsDAO cbdDAO = new CashBookDetailsDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private SupplierDAO supplierDAO = new SupplierDAO();

    public CashBookDetailsPage() {
        initComponents();

        //to show cashbook table dropdownlist from database account name
        // မိမိပြောင်းလဲချင်သော Column နေရာကို ညွှန်းပေးရပါမယ် (Particular Column က နံပါတ် 1 နေရာမို့လို့ 1 လို့ ထည့်ထားတာပါ)
        javax.swing.table.TableColumn accountName = cashTable.getColumnModel().getColumn(1);

        // 💡 ၁။ ComboBox ကို String အစား ComboItem အမျိုးအစား ပြောင်းလဲကြေညာပါမည်
        javax.swing.JComboBox<ComboIdName> comboBox = new javax.swing.JComboBox<>();

        // 💡 ၂။ DB ကနေ id ကော name ကော ပါလာတဲ့ List ကို လှမ်းယူပြီး loop ပတ်ထည့်ပါမည်
        // (မှတ်ချက် - cbdDAO.getAccountNamesFromDB() သည် List<ComboItem> ပြန်ပေးသည်ဟု ယူဆပါသည်)
        List<ComboIdName> accountList = cbdDAO.getAccountNamesFromDB();
        for (ComboIdName item : accountList) {
            comboBox.addItem(item); // Object အလိုက် ထည့်သော်လည်း toString() ကြောင့် နာမည်ပဲ ပေါ်နေပါမည်
        }

        // 🚀 ၃။ ၎င်း ComboBox ကို JTable ၏ Editor အဖြစ် သတ်မှတ်လိုက်ခြင်း
        accountName.setCellEditor(new javax.swing.DefaultCellEditor(comboBox));

        setupDynamicDropdowns();

        cashTable.getModel().addTableModelListener(new javax.swing.event.TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                if (tme.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                    int col = tme.getColumn();
                    if (col == 5 || col == 6) {
                        updateRunningBalance();
                        debitCreditTotal();
                    }
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFromDate = new com.toedter.calendar.JDateChooser();
        txtFromDate1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAddRow = new javax.swing.JButton();
        btnDeleteRow = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        debitTotalTxt = new javax.swing.JTextField();
        creditTotalTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cashTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setText("From Date");

        jLabel2.setText("To Date");

        jButton1.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtFromDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(440, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFromDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1)))))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        btnAddRow.setText("New Row");
        btnAddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRowActionPerformed(evt);
            }
        });

        btnDeleteRow.setText("Delete Row");
        btnDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRowActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel3.setText("Total");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddRow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteRow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(debitTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(creditTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddRow)
                        .addComponent(btnDeleteRow)
                        .addComponent(btnSave)
                        .addComponent(debitTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(creditTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        cashTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Account Name", "Customer / Sypplier", "Voucher No", "Description", "Debit", "Credit", "Balance", "AccountID", "CustomerID", "SupplierID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cashTable.setRowHeight(30);
        cashTable.setShowGrid(true);
        cashTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashTableMouseClicked(evt);
            }
        });
        cashTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cashTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(cashTable);
        if (cashTable.getColumnModel().getColumnCount() > 0) {
            cashTable.getColumnModel().getColumn(8).setMinWidth(0);
            cashTable.getColumnModel().getColumn(8).setMaxWidth(0);
            cashTable.getColumnModel().getColumn(9).setMinWidth(0);
            cashTable.getColumnModel().getColumn(9).setMaxWidth(0);
            cashTable.getColumnModel().getColumn(10).setMinWidth(0);
            cashTable.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    //to click table to get db data for row 
    private void cashTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashTableMouseClicked

    }//GEN-LAST:event_cashTableMouseClicked

    private void btnAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRowActionPerformed
        newRow();

    }//GEN-LAST:event_btnAddRowActionPerformed

    private void newRow() {
        DefaultTableModel model = (DefaultTableModel) cashTable.getModel();

        // ၁။ ကွန်ပျူတာရဲ့ ယနေ့ရက်စွဲကို "2026-06-22" ပုံစံ စာသားအဖြစ် အော်တိုပြောင်းယူမယ်
        String todayDate = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());

        // ၂။ ရက်စွဲနေရာမှာ ယနေ့ရက်စွဲကို တစ်ခါတည်းထည့်ပြီး ဇယားထဲ လိုင်းအသစ်တစ်လိုင်း တိုးပေးလိုက်မယ်
        // [ Date | Voucher | Particular | Debit | Credit ] အစီအစဉ်အတိုင်း ဖြစ်ပါတယ်
        model.addRow(new Object[]{todayDate, "", "", "", "", 0, 0});
        updateRunningBalance();
        debitCreditTotal();
    }


    private void btnDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRowActionPerformed
        int selectedRow = cashTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) cashTable.getModel();
            model.removeRow(selectedRow);
            updateRunningBalance();

        } else {
            JOptionPane.showMessageDialog(this, "Select Row");
        }
    }//GEN-LAST:event_btnDeleteRowActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       DefaultTableModel model = (DefaultTableModel) cashTable.getModel();
    int rowCount = model.getRowCount();

    // cash book no data for return
    if (rowCount == 0) {
        JOptionPane.showMessageDialog(this, "There have no data in cash book", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 💡 သိမ်းမလားလို့ အရင်မေးပါမည်
    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to save?", "Confirm", JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        CashBookDetailsDAO cashBookDetailsDAO = new CashBookDetailsDAO();
        int successCount = 0; // အောင်မြင်စွာ သိမ်းနိုင်ခဲ့တဲ့ အရေအတွက်ကို မှတ်ထားရန်

        // 💡 Loop စတင်ပါပြီ
        for (int i = 0; i < rowCount; i++) {
            
            // 🚀 ပြင်ဆင်ချက် ၁: DTO Object ကို Loop ထဲမှာ ဆောက်မှသာ Row တစ်ကြောင်းအတွက် DTO အသစ်တစ်ခု ရပါမည်
            CashBookDetailsDTO cbdDTO = new CashBookDetailsDTO(); 

            try {
                Object dateObj = model.getValueAt(i, 0);
                if (dateObj != null) {
                    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date utilDate = format.parse(dateObj.toString().trim());
                    cbdDTO.setEntryDate(new java.sql.Date(utilDate.getTime()));
                }
            } catch (Exception e) {}

            Object accObj = model.getValueAt(i, 1);
            String accountName = accObj != null ? accObj.toString().trim() : "";
            if (accObj instanceof ComboIdName) {
                ComboIdName selectedAccount = (ComboIdName) accObj;
                int accountId = selectedAccount.getId();
                cashTable.setValueAt(accountId, i, 8);
                cbdDTO.setAccountId(accountId);
            } else if (accObj != null) {
                String accName = accObj.toString().trim();
            }

            Object cellValue = cashTable.getValueAt(i, 2);
            if (cellValue != null) {
                if (cellValue instanceof ComboIdName) {
                    if (accountName.equalsIgnoreCase("Receivable")) {
                        ComboIdName selectedCustomer = (ComboIdName) cellValue;
                        int customerId = selectedCustomer.getId();
                        cashTable.setValueAt(customerId, i, 9);
                        cbdDTO.setCustomerId(customerId); // 🚀 မှတ်ချက်- ဤနေရာတွင် DTO သို့ ထည့်ရန် ကျန်နေခဲ့၍ ဖြည့်ပေးထားပါသည်
                    } else if (accountName.equalsIgnoreCase("Payable")) {
                        ComboIdName selectedSupplier = (ComboIdName) cellValue;
                        int supplierId = selectedSupplier.getId();
                        cashTable.setValueAt(supplierId, i, 10);
                        cbdDTO.setSupplierId(supplierId); // 🚀 မှတ်ချက်- ဤနေရာတွင် DTO သို့ ထည့်ရန် ကျန်နေခဲ့၍ ဖြည့်ပေးထားပါသည်
                    }
                }
            }

            Object voucherObj = model.getValueAt(i, 3);
            cbdDTO.setVoucherNo(voucherObj != null ? voucherObj.toString().trim() : "");

            Object descObj = model.getValueAt(i, 4);
            cbdDTO.setDescription(descObj != null ? descObj.toString().trim() : "");

            Object debitObj = model.getValueAt(i, 5);
            cbdDTO.setDebit(debitObj != null && !debitObj.toString().isEmpty() ? Double.parseDouble(debitObj.toString().trim()) : 0.0);

            Object creditObj = model.getValueAt(i, 6);
            cbdDTO.setCredit(creditObj != null && !creditObj.toString().isEmpty() ? Double.parseDouble(creditObj.toString().trim()) : 0.0);

            Object balObj = model.getValueAt(i, 7);
            cbdDTO.setBalance(balObj != null && !balObj.toString().isEmpty() ? Double.parseDouble(balObj.toString().trim()) : 0.0);

            // 🚀 ပြင်ဆင်ချက် ၂: Row တစ်ကြောင်းစီရဲ့ Data အစုံ ဖမ်းမိတာနဲ့ Database ထဲကို ချက်ချင်း လှမ်းသိမ်းပါမည်
            boolean success = cashBookDetailsDAO.insertCashBookDetailsDAO(cbdDTO);
            if (success) {
                successCount++; // သိမ်းတာ အောင်မြင်ရင် ၁ တိုးမည်
            }
        } 
        // --- Loop ပြီးဆုံးပါပြီ ---

        // Loop အားလုံး ပြီးသွားတဲ့အခါ Message ပြပါမည်
        if (successCount > 0) {
            // loadDataSet();
            JOptionPane.showMessageDialog(this, "Successfully Saved (" + successCount + ") records.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Save Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cashTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cashTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cashTablePropertyChange

    private JTextField customTextField = new JTextField();

    public void setupDynamicDropdowns() {
        // ComboBox များကို Initialize လုပ်ခြင်း
        customerComboBox = new JComboBox<>();
        supplierComboBox = new JComboBox<>();

        // tableModel လည်း ရှိပြီးသားဖြစ်ရပါမည်
        // 💡 ဟောဒီမှာ 'new JTable' မဆောက်တော့ဘဲ ရှိပြီးသား cashTable ထဲကို တိုက်ရိုက် Editor ထည့်ပါမယ်
        cashTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(customerComboBox) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                // ဘေးနားက 'Particular' (Column Index 1) မှာ ဘာရွေးထားလဲ လှမ်းကြည့်မည်
                Object accountValue = table.getValueAt(row, 1);

                if (accountValue != null) {
                    String accountName = accountValue.toString().trim();

                    // (A) Receivable ဖြစ်လျှင် Customer List ကို DB မှဆွဲထုတ်ပြီး ပြမည်
                    if (accountName.equalsIgnoreCase("Receivable")) {
                        customerComboBox.removeAllItems();
                        List<ComboIdName> customers = customerDAO.getCustomersNamesFromDB();
                        for (ComboIdName name : customers) {
                            customerComboBox.addItem(name);
                        }
                        if (value != null) {
                            customerComboBox.setSelectedItem(value.toString());
                        }
                        return customerComboBox;

                    } // (B) Payable ဖြစ်လျှင် Supplier List ကို DB မှဆွဲထုတ်ပြီး ပြမည်
                    else if (accountName.equalsIgnoreCase("Payable")) {
                        supplierComboBox.removeAllItems();
                        List<ComboIdName> suppliers = supplierDAO.getSuppliersNamesFromDB();
                        for (ComboIdName name : suppliers) {
                            supplierComboBox.addItem(name);

                        }
//  to get id save for database
//                    int selectedRow = cashTable.getSelectedRow();
//                      Object cellValue = cashTable.getValueAt(selectedRow, 2);
//                    if (cellValue instanceof ComboIdName) {
//                    ComboIdName selectedCustomer = (ComboIdName) cellValue;
//    
//                    int customerId = selectedCustomer.getId();
//                        cashTable.setValueAt(customerId, 1, 10);
//                    }

                        // 💡 Supplier ComboBox ကို ဤနေရာတွင် တိုက်ရိုက် လဲလှယ်ပြသရန်
                        if (value != null) {
                            supplierComboBox.setSelectedItem(value.toString());
                        }
                        return supplierComboBox;
                    }
                }

                // Expense သို့မဟုတ် အခြားစာရင်းဆိုလျှင် နှိပ်ရုံနဲ့ ဘာ dropdown မှ မကျလာစေရန် Component အလွတ်တစ်ခု ပြန်ပေးမည်
                //return new JTextField();
                customTextField.setText(value != null ? value.toString() : "");
                return customTextField;
            }

            @Override
            public Object getCellEditorValue() {
                // လက်ရှိ သုံးနေတဲ့ Component က ကွန်ပျူတာနောက်ကွယ်မှာ ဘာဖြစ်နေလဲ စစ်ဆေးခြင်း
                if (customerComboBox.isShowing()) {
                    return customerComboBox.getSelectedItem();
                } else if (supplierComboBox.isShowing()) {
                    return supplierComboBox.getSelectedItem();
                } else {
                    // အကယ်၍ Dropdown မဟုတ်ဘဲ Text Field ဖြစ်နေလျှင် User ရိုက်ထားသော စာသား (Customize တန်ဖိုး) ကို သိမ်းမည်
                    return customTextField.getText();
                }
            }
        });

    }

    private void debitCreditTotal() {
        DefaultTableModel model = (DefaultTableModel) cashTable.getModel();
        double debitTotal = 0.0;
        double creditTotal = 0.0;

        for (int i = 0; i < model.getRowCount(); i++) {
            // --- Debit ကော်လံ (Index 5) ကို စနစ်တကျ ဖတ်ခြင်း ---
            Object debitVal = model.getValueAt(i, 5);
            if (debitVal != null) {
                try {
                    // Number အမျိုးအစားဖြစ်ဖြစ်၊ String အမျိုးအစားဖြစ်ဖြစ် အမှားကင်းအောင် ပြောင်းလဲခြင်း
                    debitTotal += Double.parseDouble(debitVal.toString().trim());
                } catch (NumberFormatException e) {
                    // အကယ်၍ အကြောင်းအမျိုးမျိုးကြောင့် စာသားဖြစ်နေပါက ဂဏန်း 0 ဟုသာ သတ်မှတ်မည်
                    debitTotal += 0.0;
                }
            }

            // --- Credit ကော်လံ (Index 6) ကို စနစ်တကျ ဖတ်ခြင်း ---
            Object creditVal = model.getValueAt(i, 6);
            if (creditVal != null) {
                try {
                    creditTotal += Double.parseDouble(creditVal.toString().trim());
                } catch (NumberFormatException e) {
                    creditTotal += 0.0;
                }
            }
        }

        debitTotalTxt.setText(String.valueOf(debitTotal));
        creditTotalTxt.setText(String.valueOf(creditTotal));

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRow;
    private javax.swing.JButton btnDeleteRow;
    private javax.swing.JButton btnSave;
    private javax.swing.JTable cashTable;
    private javax.swing.JTextField creditTotalTxt;
    private javax.swing.JTextField debitTotalTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser txtFromDate;
    private com.toedter.calendar.JDateChooser txtFromDate1;
    // End of variables declaration//GEN-END:variables

    private boolean isUpdating;

    private void updateRunningBalance() {

        // to close loop
        if (isUpdating) {
            return;
        }
        isUpdating = true;

        DefaultTableModel model = (DefaultTableModel) cashTable.getModel();
        double currentBalance = 10.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double debit = 0.0;
            double credit = 0.0;

            try {
                if (model.getValueAt(i, 5) != null) {
                    debit = Double.parseDouble(model.getValueAt(i, 5).toString());

                }
                if (model.getValueAt(i, 6) != null) {
                    credit = Double.parseDouble(model.getValueAt(i, 6).toString());
                }
            } catch (Exception e) {
            }

            currentBalance = currentBalance + debit - credit;

            model.setValueAt(currentBalance, i, 7);

        }
        isUpdating = false;
    }
}
