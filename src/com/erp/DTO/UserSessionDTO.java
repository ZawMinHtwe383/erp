/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erp.DTO;

/**
 *
 * @author Zaw Min Htwe
 */
public class UserSessionDTO {
    private static String loggedInUserName = "";
    private static int loggedInUserId = 0;

    public static String getLoggedInUserName() {
        return loggedInUserName;
    }

    public static void setLoggedInUserName(String loggedInUserName) {
        UserSessionDTO.loggedInUserName = loggedInUserName;
    }

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void setLoggedInUserId(int loggedInUserId) {
        UserSessionDTO.loggedInUserId = loggedInUserId;
    }

    public UserSessionDTO() {
    }
    
    public static void setSession(int userId, String userName) {
        loggedInUserId = userId;
        loggedInUserName = userName;
    }
}
