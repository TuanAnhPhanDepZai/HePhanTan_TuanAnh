/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Account;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class ConnectFile {
    
    
    private  String fileName = "D://data.dat";

    public ConnectFile() {
        
    }
    
    public static ObjectOutputStream getObjectOutPutStream(){
        try {
            FileOutputStream f2 = new FileOutputStream("D://data.dat");
            try {
                ObjectOutputStream oos = new ObjectOutputStream(f2);
                return oos;
            } catch (IOException ex) {
                Logger.getLogger(ConnectFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public static ObjectInputStream getObjectInPutStream(){
        try {
            FileInputStream f2 = new FileInputStream("D://data.dat");
            try {
                ObjectInputStream ois = new ObjectInputStream(f2);
                return ois;
            } catch (IOException ex) {
                Logger.getLogger(ConnectFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
       ObjectOutputStream ois = ConnectFile.getObjectOutPutStream();
        Account acc = new Account(1, "Phan Tuan Anh", "01655538467", 1000000, 1, "1997", "125820815",100,"20150157");
        Account acc2 = new Account(2, "Tran Tuan", "01655538467", 1000000, 0, "1998", "125678903", 20,"20150158");
        ArrayList<Account> listAccount = new ArrayList<Account>();
        listAccount.add(acc);
        listAccount.add(acc2);
        try {
            ois.writeObject(listAccount);
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
