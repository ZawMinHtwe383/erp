/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DTO;

/**
 *
 * @author Zaw Min Htwe
 */
public class SupplierDTO {
    private int id;
     private String supplier_code, supplier_name, phone, email, address, township, city, status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplier_code() {
        return supplier_code;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplier_code = supplier_code;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
        // SupplierDTO.java ထဲတွင် အောက်ဆုံး၌ ထည့်ရန်
@Override
public String toString() {
    return this.supplier_name; // Combo Box ထဲမှာ အမျိုးအစားနာမည်ပဲ ပြပေးမည့် ကုဒ်
}
    
    
   
}
    /*
    to show dataase data to combo box
    1// SupplierDTO.java ထဲတွင် အောက်ဆုံး၌ ထည့်ရန်
    @Override
    public String toString() {
        return this.supplier_name; // Combo Box ထဲမှာ အမျိုးအစားနာမည်ပဲ ပြပေးမည့် ကုဒ်
    }


    2// form load to call construction method loadSupplierToCombo
            private SupplierDAO supplierDAO = new SupplierDAO();

        private void loadSupplierToCombo() {
            List<SupplierDTO> supplierName = supplierDAO.getAllSupplierNames();
            supplierCmb.removeAllItems();
            for (SupplierDTO cat : supplierName) {
                supplierCmb.addItem(cat.toString());
            }


    3//DAO
     public List<SupplierDTO> getAllSupplierNames() {
           List<SupplierDTO> list = new ArrayList<>();
           String sql = "select id, supplier_name from suppliers ORDER by id asc";
            try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierDTO cat = new SupplierDTO();
                    cat.setId(rs.getInt("id"));     // ID ထည့်သည်
                    cat.setSupplier_name(rs.getString("supplier_name")); // နာမည်ထည့်သည်
                    list.add(cat); // Object လိုက် List ထဲထည့်သည်
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }


to get id from combo in DAO insert method
      SupplierDTO selectedCat = null;
            Object catItem = supplierCmb.getSelectedItem();
            if (catItem instanceof CategoryDTO) {
                selectedCat = (SupplierDTO) catItem;
            }
 SupplierDTO dto = new SupplierDTO();
           
            dto.setId(selectedCat.getSupplierId());




*/