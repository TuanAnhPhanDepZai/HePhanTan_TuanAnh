/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class ConnectDB2 {

    private final String className = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://127.0.0.1:3306/mybank2";
    private final String user = "root";
    private final String password = "27121997";

    public ConnectDB2() {
    }

    
    
    public Connection getConnection() {
        Connection connection = null;
        try {

            try {
                Class.forName(className);
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi roi ");
        }
        return connection;
    }

    
    public static void main(String[] args) {
        Connection connection = new ConnectDB().getConnection();
        if(connection != null){
            System.out.println("thanh cong");
        }
    }
}
