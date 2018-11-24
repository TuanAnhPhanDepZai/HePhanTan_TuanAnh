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
public class MinusMonneyToAccount {

    public static int MinusMoneyToAccountName(String accountName, String monney) {
        int j = 0;

        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        for (int i = 0; i < listAccount.size(); i++) {
            if (listAccount.get(i).getNameAccount().equals(accountName)) {
                Float monneyAccount = listAccount.get(i).getMonney();
                if (monneyAccount - Float.valueOf(monney) <  0 ){
                    return j;
                }else{
                    // truong hop co the tru tien vao trong tai khoan
                    listAccount.get(i).setMonney(monneyAccount - Float.valueOf(monney));
                }
            }
        }
        ObjectOutputStream oos = ConnectFile.getObjectOutPutStream();
        try {
            oos.writeObject(listAccount);
            oos.close();
            j = 1;
        } catch (IOException ex) {
            return 2;
        }
        return j;
    }

   
}
