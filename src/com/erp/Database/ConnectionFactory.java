/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.Database;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 *
 * @author Zaw Min Htwe
 */


public class ConnectionFactory {
    // ချိတ်ဆက်ရန် လိုအပ်သော အချက်အလက်များကို သိမ်းထားမည်
    private String url;
    private String user;
    private String pass;
    private String driver;
    
  public ConnectionFactory(){
          try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("src/db-config.xml");
            props.loadFromXML(fis); 
            fis.close();

            // XML ထဲမှ Data များကို Variable များထဲသို့ ထည့်သွင်းခြင်း
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.pass = props.getProperty("db.password");
            this.driver = props.getProperty("db.driver");

            // Driver ကို Load လုပ်ခြင်း (တစ်ကြိမ်သာ လုပ်ရန်လိုသည်)
            Class.forName(this.driver);

        } catch (Exception e) {
            System.out.println("XML ဖိုင်ဖတ်ရာတွင် အမှားအယွင်းဖြစ်နေပါသည်...");
            e.printStackTrace();
        }
    
    } 
  
      public Connection getConn() {
         Connection conn = null;
        try {
            // Constructor မှာ ဖတ်ထားတဲ့ အချက်အလက်တွေနဲ့ Database ချိတ်ဆက်ခြင်း
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Database Connection Succefully ကို အောင်မြင်စွာ ချိတ်ဆက်နိုင်ပါပြီ!");
        } catch (Exception e) {
            System.out.println("Database ချိတ်ဆက်ရာတွင် အမှားအယွင်းဖြစ်နေပါသည်...");
            e.printStackTrace();
        }
        return conn; // ချိတ်ဆက်ထားသော Connection ကို ပြန်ပို့ပေးမည်
    }
    
  
  
  
    //Login verification method
    public boolean checkLogin(String username, String password, String userType){
       // SQL Query တွင် ' ' (Single Quote) များအစား ? (Placeholder) ကို သုံးခြင်းက ပိုမုံလုံခြုံပါသည်
    String query = "SELECT * FROM users WHERE username = ? AND password = ? AND usertype = ? LIMIT 1";
    
    // try-with-resources ပုံစံကိုသုံးလျှင် connection များကို အလိုအလျောက် ပြန်ပိတ်ပေးပါသည်။
    try {
        // ၁။ အပေါ်က getConn() မက်သဒ်ကို လှမ်းခေါ်ပြီး connection ယူမည်
        Connection conn = this.getConn(); 
        
        if (conn != null) {
            // ၂။ statement ကို connection ထံမှတစ်ဆင့် တည်ဆောက်ပေးရပါမည် (ယခင်က ဤနေရာ လိုနေခြင်းဖြစ်ပါသည်)
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            // ? နေရာများတွင် တန်ဖိုးများ အစားထိုးထည့်သွင်းခြင်း
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, userType);
            
            // ၃။ Query ကို Run ပြီး Result ထုတ်ယူခြင်း
            ResultSet resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                // အကယ်၍ user ရှိပါက connection များကို ပိတ်ပြီး true ပြန်မည်
                resultSet.close();
                pstmt.close();
                conn.close();
                return true; 
            }
            
            // မရှိပါကလည်း ပြန်ပိတ်မည်
            resultSet.close();
            pstmt.close();
            conn.close();
        }
    } catch (Exception ex) {
        System.out.println("Login စစ်ဆေးစဉ် အမှားအယွင်း ဖြစ်ပွားခဲ့သည်...");
        ex.printStackTrace();
    }
    return false; // အကောင့်မရှိလျှင် သို့မဟုတ် Error တက်လျှင် false ပြန်မည်
    }
}