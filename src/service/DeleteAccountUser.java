
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
public class DeleteAccountUser {
    
    public static String deleteAccount(String accountName){
        String result = "xoataikhoanthanhcong";
        if(CheckExistAccountUserFile.checkexistAccountUser(accountName)==0){
            return "khongtontaitaikhoan";
        }else{
            ArrayList<Account> listAccount = GetUserFile.getListAccountFromFile();
            for (int i = 0 ; i < listAccount.size() ; i ++){
                if (listAccount.get(i).getNameAccount().equals(accountName)){
                    listAccount.remove(i);
                }
            }
            ObjectOutputStream oos = ConnectFile.getObjectOutPutStream();
            try {
                oos.writeObject(listAccount);
            } catch (IOException ex) {
                return "khongxoaduoctaikhoannay";
            }
            
        }
        return result;
    }
    
    
    public static void main(String[] args) {
        String result = DeleteAccountUser.deleteAccount("01655538467");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        System.out.println(result);
    }
}
