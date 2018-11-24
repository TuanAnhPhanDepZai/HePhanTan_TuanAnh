/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Account;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class ReadObjectFile {

    public static ArrayList<Account> readListObjectFromFile() {
        ArrayList<Account> listAccount = new ArrayList<>();
        try {

            ObjectInputStream ois = ConnectFile.getObjectInPutStream();
            listAccount = (ArrayList<Account>) ois.readObject();
            ois.close();

            return listAccount;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return listAccount;
    }

    public static void main(String[] args) {
        ArrayList<Account> list = ReadObjectFile.readListObjectFromFile();
        for (int i = 0 ; i < list.size() ; i ++){
            System.out.println(list.get(i).getUserName());
        }
    }
    
    
}
