/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Account;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class WriteObjectFile {

    public static int writeSingleObjectToFile(Account account) {
        int i = 0;
        try {

            ObjectOutputStream oos = ConnectFile.getObjectOutPutStream();
            oos.writeObject(account);
            oos.close();
            i = 1 ;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }

    public static int writeListObjectToFile(ArrayList<Account> listAccount) {
        int i = 0;
        try {

            ObjectOutputStream oos = ConnectFile.getObjectOutPutStream();
            oos.writeObject(listAccount);
            oos.close();
            i = 1 ;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    
    public static void main(String[] args) {
//        Account account = new Account(0, "fdfdf", "fdfdf", 0, 0, "fdfdf", "fdfdf");
//        ArrayList<Account> l = new ArrayList<>();
//        l.add(account);
//        WriteObjectFile.writeListObjectToFile(l);
    }
}
