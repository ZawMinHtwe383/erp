/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.UserDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zaw Min Htwe
 */
//to CRUD
public class UserDAO {

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public UserDAO() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to display data set in tabular form
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

    // Method to retrieve data set to display in table
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT * FROM users";
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

        // Methods to add new user
    public boolean addUserDAO(UserDTO userDTO) {

        try {
        ResultSet rs;
        PreparedStatement checkStmt ;
        String checkSql = "SELECT username FROM users WHERE username=?";

         checkStmt = conn.prepareStatement(checkSql);

        checkStmt.setString(1,userDTO.getUsername());

        rs = checkStmt.executeQuery();

        if(rs.next()){
            return false;
        }
             String insertSql = "INSERT INTO users(username,password,full_name,usertype,status) VALUES(?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(insertSql);

        ps.setString(1, userDTO.getUsername());
        ps.setString(2, userDTO.getPassword());
        ps.setString(3, userDTO.getFull_name());
        ps.setString(4, userDTO.getUsertype());
        ps.setString(5, userDTO.getStatus());

        ps.executeUpdate();

        return true;    
        } catch (Exception ex) {
            ex.printStackTrace();
              return false;
        }
    }
    public boolean deleteUserDAO(int userId){
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, userId);
            int row = ps.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateUserDAO(UserDTO userDTO) {

    try {

        String sql =
            "UPDATE users " +
            "SET username=?,password=?,full_name=?,usertype=?,status=? " +
            "WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, userDTO.getUsername());
        ps.setString(2, userDTO.getPassword());
        ps.setString(3, userDTO.getFull_name());
        ps.setString(4, userDTO.getUsertype());
        ps.setString(5, userDTO.getStatus());
        ps.setInt(6, userDTO.getId());

        int row = ps.executeUpdate();

        return row > 0;

    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
}
    
    
}
