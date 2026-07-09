/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.ChartOfAccountsDTO;
import com.erp.DTO.ComboIdName;
import com.erp.Database.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Zaw Min Htwe
 */
public class ChartOfAccountsDAO {

    private Connection conn;

    public ChartOfAccountsDAO() {
        conn = new ConnectionFactory().getConn();
    }

    // 🚀 ၁။ CREATE (Insert Record)
    public boolean insert(ChartOfAccountsDTO dto) {
        String sql = "INSERT INTO chart_of_accounts (account_code, account_name, account_type, is_active) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dto.getAccount_code());
            stmt.setString(2, dto.getAccount_name());
            stmt.setString(3, dto.getAccount_type());
            stmt.setString(4, dto.getIs_active());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🚀 ၂။ READ (Fetch All Records)
    public List<ChartOfAccountsDTO> getAllAccounts() {
        List<ChartOfAccountsDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chart_of_accounts";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ChartOfAccountsDTO dto = new ChartOfAccountsDTO();
                rs.getInt("account_id");
                rs.getString("account_code");
                rs.getString("account_name");
                rs.getString("account_type");
                rs.getString("is_active");
                list.add(dto);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

//    // 🚀 ၃။ UPDATE (Update Record)
    public boolean update(ChartOfAccountsDTO dto) {
        String sql = "UPDATE chart_of_accounts SET account_code = ?, account_name = ?, account_type = ?, is_active = ? WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dto.getAccount_code());
            stmt.setString(2, dto.getAccount_name());
            stmt.setString(3, dto.getAccount_type());
            stmt.setString(4, dto.getIs_active());
            stmt.setInt(5, dto.getAccount_id());
           // System.out.println(dto.hashCode());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//
//    // 🚀 ၄။ DELETE (Delete Record)

    public boolean delete(int accountId) {
        String sql = "DELETE FROM chart_of_accounts WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public TableModel getCOATableModel() {

        String query = "Select * from chart_of_accounts";
        try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            return buildTableModel(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return new DefaultTableModel();
        }

    }

    public DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int colCount = metaData.getColumnCount();

        for (int col = 1; col <= colCount; col++) {
            columnNames.add(metaData.getColumnName(col).toUpperCase(Locale.ROOT));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int col = 1; col <= colCount; col++) {
                vector.add(resultSet.getObject(col));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

      
     public List<ChartOfAccountsDTO> getAllCOANames() {
        List<ChartOfAccountsDTO> list = new ArrayList<>();
        String sql = "SELECT account_id,account_name FROM chart_of_accounts ORDER BY account_id ASC";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ChartOfAccountsDTO coa = new ChartOfAccountsDTO();
                coa.setAccount_id(rs.getInt("account_id"));     // ID ထည့်သည်
                coa.setAccount_name(rs.getString("account_name")); // နာမည်ထည့်သည်
                list.add(coa); // Object လိုက် List ထဲထည့်သည်
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
}
