/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Account;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class GetUserFile {

//    public static Account getUserByAccountName(String accountName) {
//        Account account = new Account();
//
//        ObjectInputStream ois = ConnectFile.getObjectInPutStream();
//        try {
//            ArrayList<Account> listAccount = (ArrayList<Account>) ois.readObject();
//            for (int i = 0; i < listAccount.size(); i++) {
//                if (listAccount.get(i).getNameAccount().equals(accountName)) {
//                    return listAccount.get(i);
//                }
//            }
//            ois.close();
//        } catch (IOException ex) {
//            Logger.getLogger(GetUserFile.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(GetUserFile.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return account;
//
//    }
    
    
    public static ArrayList<Account> getListAccountFromFile(){
        ArrayList<Account> listAccount = new ArrayList<>();
        ObjectInputStream ois = ConnectFile.getObjectInPutStream();
        try {
            listAccount = (ArrayList<Account>)ois.readObject();
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(GetUserFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetUserFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAccount;
    }

    public static void main(String[] args) {
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        for (int i = 0 ; i < listAccount.size() ; i ++){
            System.out.println(listAccount.get(i).getUserName() +  " " + listAccount.get(i).getSothe()+" " + listAccount.get(i).getNameAccount());
        }
        

    }
}
