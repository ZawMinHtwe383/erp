/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;

import com.erp.DTO.UnitDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zaw Min Htwe
 */
public class UnitDAO {

    private Connection conn = null;

    public UnitDAO() {
        try {
            conn = new ConnectionFactory().getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UnitDTO> getAllUnitNames() {
        List<UnitDTO> list = new ArrayList<>();
        String sql = "SELECT unit_id, unit_name FROM units ORDER BY unit_id ASC";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            UnitDTO cat = new UnitDTO();
            cat.setUnit_id(rs.getInt("unit_id"));     // ID ထည့်သည်
            cat.setUnit_name(rs.getString("unit_name")); // နာမည်ထည့်သည်
            list.add(cat); // Object လိုက် List ထဲထည့်သည်
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
