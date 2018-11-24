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
import service.ConnectFile;
import service.GetUserFile;
import sun.misc.ObjectInputFilter;

/**
 *
 * @author phant
 */
public class ChangeMaPinUserFile {
    
    public static int changeMaPinUser(String sothe, String mapinmoi){
        int result = 0 ;
        ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
        for (int i = 0; i< listAccount.size() ; i++){
            if (listAccount.get(i).getSothe().equals(sothe)){
                listAccount.get(i).setMapin(mapinmoi);
            }
        }
        ObjectOutputStream oos = ConnectFile.getObjectOutPutStream();
        try {
            oos.writeObject(listAccount);
            result = 1;
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(ChangeMaPinUserFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
