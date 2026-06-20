/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.WarehouseDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Zaw Min Htwe
 */
public class WarehouseDAO {
 private Connection conn = null;
    public WarehouseDAO() {
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    

    public boolean insertWarehouseDAO(WarehouseDTO warehouseDTO) {
        String insertsql = "INSERT INTO WAREHOUSES(warehouse_code,	warehouse_name,	address,	phone,	status) VALUES (?,?,?,?,?)";
        
        try(PreparedStatement ps = conn.prepareStatement(insertsql)) {
            ps.setString(1, warehouseDTO.getWarehouse_code());
            ps.setString(2, warehouseDTO.getWarehouse_name());
            ps.setString(3, warehouseDTO.getAddress());
            ps.setString(4, warehouseDTO.getPhone());
            ps.setString(5, warehouseDTO.getStatus());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    
    }
    
}
