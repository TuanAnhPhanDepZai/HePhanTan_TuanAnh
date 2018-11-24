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
public class GetCurrentMonneyUserFile {
    
    public static float getMonnneyBySoThe(String mathe){
        float result = 0;
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        for (int i  = 0 ; i < listAccount.size() ; i ++){
            if (listAccount.get(i).getSothe().equals(mathe)){
                result = listAccount.get(i).getMonney();
            }
        }
        return  result;
    }
}
