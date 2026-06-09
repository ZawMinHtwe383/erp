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
    public void addUserDAO(UserDTO userDTO) {
        //username,password,full_name,usertype,status;
//          userType = (String) typeCombo.getSelectedItem();
//           status = (String) statusCombo.getSelectedItem();
//           userDTO.setUsername(userNameTxt.getText());
//           userDTO.setPassword(passTxt.getText());
//           userDTO.setFull_name(fullNameTxt.getText());
//           userDTO.setUsertype(userType);
//           userDTO.setStatus(status);
        
        
        
        
        try {
            String query = "SELECT * FROM users WHERE username ='"
                    +userDTO.getUsername()
                    +"' AND password='"
                    +userDTO.getPassword()
                    +"' AND full_name='"
                    +userDTO.getFull_name()
                    +"' AND usertype='"
                    +userDTO.getUsertype()
                     +"' AND status='"
                    +userDTO.getStatus()
                    +"'";
            resultSet = statement.executeQuery(query);
            if(resultSet.next())
                JOptionPane.showMessageDialog(null, "User already exists");
            else
                addFunction(userDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addFunction(UserDTO userDTO) {
         try {
            String username = null;
            String password = null;
            String oldUsername = null;
            String resQuery = "SELECT * FROM users";
            resultSet = statement.executeQuery(resQuery);

            if(!resultSet.next()){
                username = "ZMH";
                password = "123456";
            }
//            else {
//                String resQuery2 = "SELECT * FROM users ORDER BY id DESC";
//                resultSet = statement.executeQuery(resQuery2);
//
//                if(resultSet.next()){
//                    oldUsername = resultSet.getString("username");
//                    Integer uCode = Integer.parseInt(oldUsername.substring(4));
//                    uCode++;
//                    username = "user" + uCode;
//                    password = "user" + uCode;
//                }
//            }

            String query = "INSERT INTO users (username,password,full_name,usertype,status) " +
                    "VALUES(?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, userDTO.getUsername());
            preparedStatement.setString(2, userDTO.getPassword());
            preparedStatement.setString(3, userDTO.getFull_name());
            preparedStatement.setString(4, userDTO.getUsertype());
            preparedStatement.setString(5, userDTO.getStatus());
          
            preparedStatement.executeUpdate();

            if("Admin".equals(userDTO.getUsertype()))
                JOptionPane.showMessageDialog(null, "New administrator added.");
            else JOptionPane.showMessageDialog(null, "New employee added.");

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
    
}
