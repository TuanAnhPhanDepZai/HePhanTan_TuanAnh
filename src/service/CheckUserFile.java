/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Account;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class CheckUserFile {

    public static int checkUser(String sothe, String mapin) {
        int result = 0;
        ObjectInputStream osi = ConnectFile.getObjectInPutStream();
        try {
            ArrayList<Account> listAccount = (ArrayList<Account>) osi.readObject();
            for (int i = 0; i < listAccount.size(); i++) {
                if (listAccount.get(i).getSothe().equals(sothe) && listAccount.get(i).getMapin().equals(mapin)) {
                    result = 1;
                }
            }
            osi.close();

        } catch (IOException ex) {
            Logger.getLogger(CheckUserFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckUserFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(CheckUserFile.checkUser("20150157", "1997"));
    }
}
