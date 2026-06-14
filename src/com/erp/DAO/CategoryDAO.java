/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DAO;
import com.erp.DTO.CategoryDTO;
import com.erp.Database.ConnectionFactory;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Zaw Min Htwe
 */
public class CategoryDAO {
    private Connection conn;
    
    
    public CategoryDAO() {
        // သင့်၏ ConnectionFactory မှတစ်ဆင့် Connection ယူရန်
        conn = new ConnectionFactory().getConn(); 
    }
    // ၁။ CREATE - Category အသစ်ထည့်ခြင်း (Code တူစစ်ဆေးပြီးမှ သွင်းမည်)
    public boolean addCategoryDAO(CategoryDTO dto) {
        String checkSql = "SELECT category_code FROM categories WHERE category_code=?";
        String insertSql = "INSERT INTO categories(category_code, category_name, description, is_active) VALUES (?,?,?,?)";
        
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, dto.getCategoryCode());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) return false; // Code ရှိပြီးသားဖြစ်နေပါက ငြင်းမည်
            }
            
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, dto.getCategoryCode());
                ps.setString(2, dto.getCategoryName());
                ps.setString(3, dto.getDescription());
                ps.setBoolean(4, dto.isIsActive());
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ၂။ READ - ဒေတာအားလုံး ဆွဲထုတ်ခြင်း
    public ResultSet getCategoryDataDAO() {
        String sql = "SELECT category_code, category_name, description, is_active FROM categories";
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ၃။ UPDATE - ဒေတာ ပြင်ဆင်ခြင်း
    public boolean updateCategoryDAO(CategoryDTO dto) {
        String sql = "UPDATE categories SET category_name=?, description=?, is_active=? WHERE category_code=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getCategoryName());
            ps.setString(2, dto.getDescription());
            ps.setBoolean(3, dto.isIsActive());
            ps.setString(4, dto.getCategoryCode());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ၄။ DELETE - ဒေတာ ဖျက်သိမ်းခြင်း
    public boolean deleteCategoryDAO(int code) {
        String sql = "DELETE FROM categories WHERE category_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, code);
            int row = ps.executeUpdate();
            return  row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ၅။ SEARCH - နာမည်ဖြင့် Live Search ရှာဖွေခြင်း
//    public ResultSet searchCategoryByNameDAO(String name) {
//        String sql = "SELECT category_code, category_name, description, is_active FROM categories WHERE category_name LIKE ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, "%" + name + "%");
//            return ps.executeQuery();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public DefaultTableModel getCustomerTableModel() {
         String query = "Select * from categories";
         try (Statement statement = conn.createStatement(); 
                 ResultSet resultSet = statement.executeQuery(query)) {
            return buildTableModel(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return new DefaultTableModel();
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
   //  💡 Search အတွက် သီးသန့် Connection ကို အထဲမှာတင် ဖွင့်/ပိတ် လုပ်မည့်နည်းလမ်း
    public List<CategoryDTO> searchCategoryByNameDAO(String name) {
        List<CategoryDTO> list = new ArrayList<>();
        String sql = "SELECT category_id,category_code, category_name, description, is_active,created_at FROM categories WHERE category_name LIKE ?";
        
        // Try-with-resources သုံးထားလို့ အလုပ်ပြီးတာနဲ့ Connection အလိုအလျောက် ပိတ်သွားပါမည်
        try (Connection conn = new ConnectionFactory().getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + name + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setCategoryId(rs.getInt("category_id"));
                    dto.setCategoryCode(rs.getString("category_code"));
                    dto.setCategoryName(rs.getString("category_name"));
                    dto.setDescription(rs.getString("description"));
                    dto.setIsActive(rs.getBoolean("is_active"));
                    dto.setCreatedAt(rs.getString("created_at"));
                    list.add(dto); // List ထဲသို့ ဒေတာသိမ်းဆည်းခြင်း
                }
            }
        } catch (Exception e) {
            System.out.println("--- Search Error Log ---");
            e.printStackTrace();
        }
        return list; // ဒေတာစာရင်း (List) ကိုပဲ UI ဘက်သို့ ပြန်ပေးလိုက်ပါမည်
    }
    
    
     public List<CategoryDTO> getAllCategoryNames() {
        List<CategoryDTO> list = new ArrayList<>();
        String sql = "SELECT category_id,category_name FROM categories ORDER BY category_id ASC";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CategoryDTO cat = new CategoryDTO();
                cat.setCategoryId(rs.getInt("category_id"));     // ID ထည့်သည်
                cat.setCategoryName(rs.getString("category_name")); // နာမည်ထည့်သည်
                list.add(cat); // Object လိုက် List ထဲထည့်သည်
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
    
    
    
    

    

