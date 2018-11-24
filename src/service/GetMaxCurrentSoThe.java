/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Account;
import java.util.ArrayList;

/**
 *
 * @author phant
 */
public class GetMaxCurrentSoThe {
    
    public static long getMaxCurrentMaThe(){
        // theo cai dat thi gia tri ma pin lon nhat se la gia tri ma pin cuoi cung
        int result = 0;
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        String mathe = listAccount.get(listAccount.size() - 1).getSothe();
        return Long.valueOf(mathe);
    }
}
