/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Seif Bejaoui
 */
public class Session {

    private static int id = 0;
    private static String username;
    private static String email;
    private static String role;
    private static int id_Lo;

    public static int getId_Lo() {
        return id_Lo;
    }

    public static void setId_Lo(int id_Lo) {
        Session.id_Lo = id_Lo;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        Session.role = role;
    }

  
}
