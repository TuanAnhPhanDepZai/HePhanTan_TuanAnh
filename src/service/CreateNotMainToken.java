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
public class CreateNotMainToken {

    public static String createNotMainToken(String mathe, String cmt, String username, int percent) {
        String result = "";
        String nameaccount = getAccountNameBySothe(mathe);
        float monney = GetCurrentMonneyUserFile.getMonnneyBySoThe(mathe);
        long  maxcurrentMathe = GetMaxCurrentSoThe.getMaxCurrentMaThe();
        String newmathe = String.valueOf(maxcurrentMathe  + 1);
        
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        
        // tao account moi va add no vao danh sach thoi
        Account account = new Account(listAccount.size() + 1, username, nameaccount, monney, 0, newmathe, cmt, percent, newmathe);
        listAccount.add(account);
        
        // ghi du lieu nay xuong file
        ObjectOutputStream oos = ConnectFile.getObjectOutPutStream();
        try {
            oos.writeObject(listAccount);
            return newmathe;
        } catch (IOException ex) {
            Logger.getLogger(CreateNotMainToken.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static String getAccountNameBySothe(String sothe) {
        // tao 1 the phu
        String result = "";
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        for (int i = 0; i < listAccount.size(); i++) {
            if (listAccount.get(i).getSothe().equals(sothe)) {
                return listAccount.get(i).getNameAccount();
            }
        }
        return result;
    }
    
    
    public static void main(String[] args) {
        String response = CreateNotMainToken.createNotMainToken("20150157", "125639874", "Le Nguyen", 10);
        System.out.println(response);
    }

}
