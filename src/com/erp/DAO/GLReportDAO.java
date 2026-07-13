/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.Database.ConnectionFactory;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Zaw Min Htwe
 */
public class GLReportDAO {

    private Connection conn = null;

    public GLReportDAO() {
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DefaultTableModel getAccountTransactionReport(int accountId, Date fromDate, Date toDate) {
        DefaultTableModel model = new DefaultTableModel();

        // JTable ခေါင်းစဉ်များ သတ်မှတ်ခြင်း
        model.addColumn("Date");
        model.addColumn("Voucher No");
        model.addColumn("Description");
        model.addColumn("Debit");
        model.addColumn("Credit");
        model.addColumn("Running Balance");

        // 🧮 Running Balance ပါ တစ်ခါတည်း တွက်ပေးမည့် Window Function SQL
        String sql = "SELECT gl.entry_date, gl.voucher_no, gl.description, gl.debit, gl.credit, "
                + "SUM(gl.debit - gl.credit) OVER (ORDER BY gl.entry_date ASC, gl.gl_id ASC) AS running_balance "
                + "FROM general_ledger gl "
                + "WHERE gl.account_id = ? AND gl.entry_date BETWEEN ? AND ? "
                + "ORDER BY gl.entry_date ASC, gl.gl_id ASC";

        try {
            if (conn == null || conn.isClosed()) {
                conn = new ConnectionFactory().getConn(); // Connection ပြန်ပွင့်ရန်
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, accountId);
                stmt.setDate(2, new java.sql.Date(fromDate.getTime()));
                stmt.setDate(3, new java.sql.Date(toDate.getTime()));

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(rs.getDate("entry_date"));
                        row.add(rs.getString("voucher_no"));
                        row.add(rs.getString("description"));
                        row.add(rs.getDouble("debit"));
                        row.add(rs.getDouble("credit"));
                        row.add(rs.getDouble("running_balance")); // Auto တွက်ပြီးသား လက်ကျန်ငွေ

                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public DefaultTableModel getTrialBalanceReport(Date fromDate, Date toDate) {
        DefaultTableModel model = new DefaultTableModel();

        // JTable to add column name 
        model.addColumn("Account Name");
        model.addColumn("Debit");
        model.addColumn("Credit");
        model.addColumn("Amount");

        // 🧮 Running Balance ပါ တစ်ခါတည်း တွက်ပေးမည့် Window Function SQL
        String sql = "SELECT gl.account_id, a.account_name, SUM(gl.debit) AS total_debit, SUM(gl.credit) AS total_credit, SUM(gl.debit) - SUM(gl.credit) AS net_balance FROM general_ledger gl "
                + "INNER JOIN chart_of_accounts a ON gl.account_id = a.account_id WHERE gl.entry_date "
                + "BETWEEN ? AND ? GROUP BY gl.account_id, a.account_name "
                + "ORDER BY gl.account_id;";

        double grandTotalDebit = 0.0;
        double grandTotalCredit = 0.0;
        double grandTotalAmount = 0.0;

        try {
            if (conn == null || conn.isClosed()) {
                conn = new ConnectionFactory().getConn(); // Connection test
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setDate(1, new java.sql.Date(fromDate.getTime()));
                stmt.setDate(2, new java.sql.Date(toDate.getTime()));

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();

                        double debit = rs.getDouble("total_debit");
                        double credit = rs.getDouble("total_credit");
                        double amount = rs.getDouble("net_balance");
                        
                        //to get add amount repeat
                        grandTotalDebit += debit;
                        grandTotalCredit += credit;
                        grandTotalAmount += amount;

                        row.add(rs.getString("account_name"));
                        row.add(String.format("%.2f", debit));  // two decimal
                        row.add(String.format("%.2f", credit));
                        row.add(String.format("%.2f", amount));

                        model.addRow(row);
                    }

                    if (model.getRowCount() > 0) { // custom Total Row to show in table last row
                        Vector<Object> totalRow = new Vector<>();
                        totalRow.add("Total"); // Account Name  "Total" 
                        totalRow.add(String.format("%.2f", grandTotalDebit));  // Total Debit
                        totalRow.add(String.format("%.2f", grandTotalCredit)); // Total Credit
                        totalRow.add(String.format("%.2f", grandTotalAmount)); // Total Amount

                        model.addRow(totalRow); // to show total row 
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

}
