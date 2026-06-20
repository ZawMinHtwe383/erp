/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DTO;

import java.util.Date;

/**
 *
 * @author Zaw Min Htwe
 */
public class WarehouseDTO {
   // warehouse_id	warehouse_code	warehouse_name	address	phone	status	created_at	
    private int warehouse_id;
    private String warehouse_code;
    private String warehouse_name;
    private String address;
    private String phone;
    private String status;
    private Date created_at;

//    public WarehouseDTO(String warehouse_code, String warehouse_name, String address, String phone, String status, Date created_at) {
//        this.warehouse_code = warehouse_code;
//        this.warehouse_name = warehouse_name;
//        this.address = address;
//        this.phone = phone;
//        this.status = status;
//        this.created_at = created_at;
//    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_code() {
        return warehouse_code;
    }

    public void setWarehouse_code(String warehouse_code) {
        this.warehouse_code = warehouse_code;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
}
