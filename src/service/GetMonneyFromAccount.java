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
public class GetMonneyFromAccount {
    
    public static String getSoduFromAccount(String sothe){
        String result = "";
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        for (int i = 0; i< listAccount.size() ; i++){
            if (listAccount.get(i).getSothe().equals(sothe)){
                String username = listAccount.get(i).getUserName();
                result = result + username + "-";
                String accountname = listAccount.get(i).getNameAccount();
                result = result + accountname + "-";
                String monney = String.valueOf(listAccount.get(i).getMonney());
                result = result + monney;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(GetMonneyFromAccount.getSoduFromAccount("20150157"));
    }
}
