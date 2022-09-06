/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mahdi
 */
public class mydb {

    private final String URL = "jdbc:mysql://127.0.0.1:3306/adrenaline";
    private final String userName = "root";
    private final String passWord = "";
    private Connection c;
    private static mydb instance;

    private mydb() {
        try {
            c = DriverManager.getConnection(URL, userName, passWord);
            System.out.println("Cnnected to the database successflly");
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    public static mydb getInstance() {
        if (instance == null) {
            instance = new mydb();
        }
        return instance;
    }

    public Connection getCnx() {
        return c;
    }

}
