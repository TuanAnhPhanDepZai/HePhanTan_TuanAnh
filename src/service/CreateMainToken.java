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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class CreateMainToken {

    public static String createMainTokenUser(String username, String nameAccount, float tienbandau, String cmt) {
        // dau vao chi la 2 gia tri nay thoi
        if(checkExistUser(nameAccount) == 1){
            return "datontaitaikhoan";
        }
        
        String result = "";
        long maxsothe = GetMaxCurrentSoThe.getMaxCurrentMaThe();
        String mapin = String.valueOf(maxsothe + 1);
        String sothe = mapin;
        Account account = new Account(0, username, nameAccount, tienbandau, 1, mapin, cmt, 100, sothe);

        // lay tat ca cac phan tu tu file 
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        listAccount.add(account);
        ObjectOutputStream oos = ConnectFile.getObjectOutPutStream();
        try {
            oos.writeObject(listAccount);
            // gia tri tra ve la so the moi
            result = sothe;
        } catch (IOException ex) {
            Logger.getLogger(CreateMainToken.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int checkExistUser(String accountName){
        int result = 0;
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        for (int i = 0 ; i < listAccount.size() ; i ++){
            if(listAccount.get(i).getNameAccount().equals(accountName)){
                return 1;
            }
        }
        return result ;
    }
    
    public static void main(String[] args) {
        String result = CreateMainToken.createMainTokenUser("Hadfd", "1234fdg", 10000, "1236258746");
//        System.out.println(Long.valueOf(result));

        System.out.println(Long.valueOf(2015015678 + 1));

        long x = 2015015789;
        System.out.println(String.valueOf(x));
    }
}
