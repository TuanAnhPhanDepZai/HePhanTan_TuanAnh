/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Account;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class AddMonneyUserFile {

    public static int addMoneyToAccountName(String sothe, String monney) {
        int j = 0;
        String nameaccount = AddMonneyUserFile.getAccountNameBySothe(sothe);
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        for (int i = 0; i < listAccount.size(); i++) {
            if (listAccount.get(i).getNameAccount().equals(nameaccount)) {
                listAccount.get(i).setMonney(listAccount.get(i).getMonney() + Float.valueOf(monney));
            }
        }
        ObjectOutputStream oos = ConnectFile.getObjectOutPutStream();
        try {
            oos.writeObject(listAccount);
            oos.close();
            j = 1;
        } catch (IOException ex) {
            Logger.getLogger(AddMonneyUserFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }

    public static String getAccountNameBySothe(String sothe) {
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
        System.out.println(AddMonneyUserFile.addMoneyToAccountName("20150157", "1000000"));
    }

}
