package dao;

import db.ConnectDB;
import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CreateMainToken;

/**
 *
 * @author phant
 */
public class AccountDao {

    //thuc hien cac thao tac lien quan den truy xuat, cap nhat tai khoan
    public static String createNewMainAccount(String userName, String nameAccount, String socmt, long tienbandau) {
        int i = 0;
        long maxcurrentSoThe = AccountDao.getMaxNumberToken();
        long sothemoi = maxcurrentSoThe + 1;
        String stringmathemoi = String.valueOf(sothemoi);
        String mapinmoi = stringmathemoi;

        if(checkAccountNameExist(nameAccount) > 0 ){
            return "datontaitaikhoan";
        }
        String query = "insert into account(username,nameaccount,monney,isMainToken,mapin,cmt,percentowner,sothe) values(?,?,?,1,?,?,100,?)";

        try {
            Connection con = new ConnectDB().getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, nameAccount);
            preparedStatement.setLong(3, tienbandau);
            preparedStatement.setString(4, mapinmoi);
            preparedStatement.setString(5, socmt);
            preparedStatement.setString(6, stringmathemoi);
            i = preparedStatement.executeUpdate();

            preparedStatement.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        if (i == 0) {
            return "";
        } else {
            return stringmathemoi;
        }
    }

    public static String taoThePhu(String mathe, String cmt, String username, int percent) {
        // tao mot the moi trong ngan hang
        // lay dc so the lon nhat trong ngan hang
        int result = 0;
        long maxToken = AccountDao.getMaxNumberToken();
        long sothemoi = maxToken + 1;
        String sothe = String.valueOf(sothemoi);
        String mapin = sothe;
        String nameaccount = AccountDao.getNameAccountBySoThe(mathe);
        float monney = AccountDao.getMonneytBySoThe(mathe);

        String query = "insert into account(username, nameaccount , monney , isMainToken, mapin, cmt , percentowner, sothe) values(?,?,?,?,?,?,?,?)";
        Connection con = new ConnectDB().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, nameaccount);
            ps.setFloat(3, monney);
            ps.setInt(4, 0);
            ps.setString(5, mapin);
            ps.setString(6, cmt);
            ps.setInt(7, percent);
            ps.setString(8, sothe);
            result = ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        if (result == 0) {
            return "thatbai";
        } else {
            return sothe;
        }
    }

    public static long getMaxNumberToken() {
        long result = 0;
        String query = "select max(sothe) from account";

        try {
            Connection con = new ConnectDB().getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return Integer.valueOf(rs.getString(1));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return result;
    }

    public static String getAccountByAccountName(String accountName) {
        String result = "";
        String query = "select * from account where nameaccount = ? ";

        try {
            Connection con = new ConnectDB().getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, accountName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString(2);
                result = result + username + ":";
                String accountname = rs.getString(3);
                result = result + accountName + ":";
                String monney = String.valueOf(rs.getFloat(4));
                result = result + monney + ":";
                String isMainToken = String.valueOf(rs.getInt(5));
                result = result + isMainToken + ":";
                String mapin = rs.getString(6);
                result = result + mapin + ":";
                String cmt = rs.getString(7);
                result = result + cmt;

            }

            preparedStatement.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }

    public static String checkUser(String sothe, String mapin) {
        String result = "thanhcong";
        try {
            String query = "select mapin from account where sothe = ?";
            Connection con = new ConnectDB().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, sothe);
            ResultSet rs = ps.executeQuery();
            String ma = "";
            while (rs.next()) {
                ma = rs.getString("mapin");
            }
            if (ma.equals("")) {
                result = "Khong Ton Tai tai khoan nay trong he thong";
            } else if (!(ma.equals(mapin))) {
                result = "Ma pin khong chinh xac";
            }

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    
    public static int checkAccountNameExist(String accountName){
        int result = 0;
        try {
            String query = "select count(*) from account where nameaccount =  ?";
            Connection con = new ConnectDB().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, accountName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
    public static String vanTinSoDuByAccountName(String sothe) {
        String result = "";
        String query = "select * from account where sothe = ? ";

        try {
            Connection con = new ConnectDB().getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, sothe);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString(2);
                result = result + username + "-";
                String accountname = rs.getString(3);
                result = result + accountname + "-";
                String monney = String.valueOf(rs.getFloat(4));
                result = result + monney;

            }
            preparedStatement.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }

    public static String napTienVaoTaiKhoan(String sothe, String sotienNap) {
        int i = 0;
        String nameaccount = AccountDao.getNameAccountBySoThe(sothe);
        float currentMonney = AccountDao.getMonneytBySoThe(sothe);
        float newMonney = currentMonney + Float.valueOf(sotienNap);

        String query = "update account set monney = ? where nameaccount = ? ";
        try {
            Connection con = new ConnectDB().getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setFloat(1, newMonney);
            preparedStatement.setString(2, nameaccount);
            i = preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (i == 0) {
            return "thatbai";
        } else {
            return "thanhcong";
        }
    }

    public static String rutTienTaiKhoan(String sothe, String soTienRut) {
        int i = 0;
        int percentOwner = AccountDao.getPercentOwnerBySoThe(sothe);
        String nameaccount = AccountDao.getNameAccountBySoThe(sothe);
        float currentMonney = AccountDao.getMonneytBySoThe(sothe);
        float tisotienrut = Float.valueOf(soTienRut) / currentMonney;

        if (percentOwner == 100) {
            // truong hop nay la chu the chinh
            // test xem so tien co du hay khong
            if ((currentMonney - Float.valueOf(soTienRut)) < 0) {
                i = 2;
            } else {
                // so tien du de rut---> cho phep rut
                String query = "update account set monney = ? where nameaccount = ? ";
                Connection con = new ConnectDB().getConnection();
                try {
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    preparedStatement.setFloat(1, currentMonney - Float.valueOf(soTienRut));
                    preparedStatement.setString(2, nameaccount);
                    preparedStatement.executeUpdate();
                    i = 1;
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            // truong hop tai khoan nay la chu the phu, percentowner < 100%
            if (tisotienrut > Float.valueOf(percentOwner) / 100.0) {
                i = 3;
            } else {
                String query = "update account set monney = ? where nameaccount = ? ";
                Connection con = new ConnectDB().getConnection();
                try {
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    preparedStatement.setFloat(1, currentMonney - Float.valueOf(soTienRut));
                    preparedStatement.setString(2, nameaccount);
                    preparedStatement.executeUpdate();
                    i = 1;
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (i == 0) {
            return "thatbai";
        } else if (i == 1) {
            return "thanhcong";
        } else if (i == 2) {
            return "khongdutien";
        } else {
            return "khongdupercent";
        }
    }

    public static int getPercentOwnerBySoThe(String sothe) {
        int result = 100;
        String query = "select percentowner from account where sothe = ? ";
        Connection con = new ConnectDB().getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, sothe);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return rs.getInt("percentowner");
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static String getNameAccountBySoThe(String sothe) {
        String result = "";
        String query = "select nameaccount from account where sothe = ? ";
        Connection con = new ConnectDB().getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, sothe);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return rs.getString("nameaccount");
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static float getMonneytBySoThe(String sothe) {
        float result = 0;
        String query = "select monney from account where sothe = ? ";
        Connection con = new ConnectDB().getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, sothe);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return rs.getFloat("monney");
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int countAccountName(String acocuntName) {

        int result = 0;
        try {
            // dem so account trong he thong, gia tri nay chi co the la 0 hoac 1 ma thoi

            String query = "select count(*) from account where nameaccount = ?";
            Connection con = new ConnectDB().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, acocuntName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static String deleteAccount(String acocuntName) {
        String result = "";
        String query = "delete from account where nameaccount = ? ";
        Connection con = new ConnectDB().getConnection();
        
        int j  = checkAccountNameExist(acocuntName);
        if (j ==0){
            return "khongtontaitaikhoan";
        }
        
        try {

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, acocuntName);
            int i  = ps.executeUpdate();

            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            return "xoataikhoanthatbai";
        }
        return "xoataikhoanthanhcong";
    }

    public static String changeMaPin(String sothe, String mapinmoi) {
        int i = 0;
        String result = "";
        // do qua trinh dang nhap thanh cong, tai khoan nay chac chan ton tai
        String query = "update account set mapin = ? where sothe = ?";
        Connection connection = new ConnectDB().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, mapinmoi);
            ps.setString(2, sothe);
            i = ps.executeUpdate();

            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (i == 0) {
            return "thatbai";
        }
        return "thanhcong";
    }

    public static int isExisAccount(String nameAccount) {
        int result = 0;
        String query = "select count(*) from account where nameaccount = ?";
        Connection connection = new ConnectDB().getConnection();
        try {
            PreparedStatement ps = connection.prepareCall(query);
            ps.setString(1, nameAccount);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        
    }

    public static void main(String[] args) {
        System.out.println(AccountDao.checkAccountNameExist("01655538467"));
    }
}
