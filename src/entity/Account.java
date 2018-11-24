/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author phant
 */
public class Account implements Serializable{
    private int idAccount;
    private String userName;
    private String nameAccount;
    private float monney;
    private int  isMainToken;
    private String mapin;
    private String cmt;
    private int percentOwner;
    private String sothe;
    public Account() {
    }

    public Account(int idAccount, String userName, String nameAccount, float monney, int isMainToken, String mapin, String cmt,int percentOwner,String sothe) {
        this.idAccount = idAccount;
        this.userName = userName;
        this.nameAccount = nameAccount;
        this.monney = monney;
        this.isMainToken = isMainToken;
        this.mapin = mapin;
        this.cmt = cmt;
        this.percentOwner = percentOwner;
        this.sothe = sothe;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public String getSothe() {
        return sothe;
    }

    public void setSothe(String sothe) {
        this.sothe = sothe;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public float getMonney() {
        return monney;
    }

    public void setMonney(float monney) {
        this.monney = monney;
    }

    public int getIsMainToken() {
        return isMainToken;
    }

    public void setIsMainToken(int isMainToken) {
        this.isMainToken = isMainToken;
    }

    public String getMapin() {
        return mapin;
    }

    public void setMapin(String mapin) {
        this.mapin = mapin;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public int getPercentOwner() {
        return percentOwner;
    }

    public void setPercentOwner(int percentOwner) {
        this.percentOwner = percentOwner;
    }

    public Account(int idAccount, String userName, String nameAccount, float monney, int isMainToken, String mapin) {
        this.idAccount = idAccount;
        this.userName = userName;
        this.nameAccount = nameAccount;
        this.monney = monney;
        this.isMainToken = isMainToken;
        this.mapin = mapin;
    }

   
    
  
}
