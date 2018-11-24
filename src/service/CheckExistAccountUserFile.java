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
public class CheckExistAccountUserFile {

    public static int checkexistAccountUser(String accountName) {
        int result = 0;
        // neu ton tai return 1
        // neu khong tra lai 0
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        for (int i = 0; i < listAccount.size(); i++) {
            if (listAccount.get(i).getNameAccount().equals(accountName)) {
                return 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(checkexistAccountUser("01655538467"));
    }
}
