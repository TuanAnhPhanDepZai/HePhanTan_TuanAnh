/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dao.AccountDao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CreateMainToken;

/**
 *
 * @author phant
 */
public class ServerThread extends Thread {

    private Socket socketMain;
    private String message;
    private int flag;

    public ServerThread(Socket socket) {
        this.socketMain = socket;
        this.message = "";
    }

    public Socket getSocketMain() {
        return socketMain;
    }

    public void setSocketMain(Socket socketMain) {
        this.socketMain = socketMain;
    }

    public String getMessage() {
        this.setFlag(0);
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void run() {

        while (true) {
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(socketMain.getInputStream()));
                PrintStream printStream = new PrintStream(socketMain.getOutputStream());

                String request = br.readLine();
                if (request != null) {
                    System.out.println("daya la server thread:" + request);
                }

                if (request != null) {
                    // tach request thanh nhieu phan, va kiem chung xem client muon lam gi
                    System.out.println("Da nhan thong diep tu server 1998: " + request);
                    this.setMessage(request);
                    this.flag = 1;
                    String[] requestProcessed = request.split(":");
                    if (requestProcessed[0].equals("guitien")) {
                        String infoguitien = requestProcessed[1];
                        String[] infoguitien_split = infoguitien.split("-");
                        String sothe = infoguitien_split[0];
                        String tiengui = infoguitien_split[1];
                        String response = AccountDao.napTienVaoTaiKhoan(sothe, tiengui);
                        System.out.println("ket qua " + response);
                        printStream.println(response);
                        printStream.flush();
                        System.out.println("da gui thanh cong");
                    } else if (requestProcessed[0].equals("ruttien")) {
                        String infoguitien = requestProcessed[1];
                        String[] infoguitien_split = infoguitien.split("-");
                        String sothe = infoguitien_split[0];
                        String tienrut = infoguitien_split[1];
                        String response = AccountDao.rutTienTaiKhoan(sothe, tienrut);
                        printStream.println(response);
                        printStream.flush();
                    } else if (requestProcessed[0].equals("doimapin")) {
                        String infoguitien = requestProcessed[1];
                        String[] infoguitien_split = infoguitien.split("-");
                        String sothe = infoguitien_split[0];
                        String mapinmoi = infoguitien_split[1];

                        String response = AccountDao.changeMaPin(sothe, mapinmoi);

                        printStream.println(response);
                        printStream.flush();
                    } else if (requestProcessed[0].equals("taothephu")) {

                        String infoguitien = requestProcessed[1];
                        String[] infoguitien_split = infoguitien.split("-");
                        String sothe = infoguitien_split[0];
                        String infothephu = infoguitien_split[1];
                        String[] listinfo = infothephu.split("<>");
                        String username = listinfo[0];
                        String cmnt = listinfo[1];
                        int percent = Integer.valueOf(listinfo[2]);

                        String response = AccountDao.taoThePhu(sothe, cmnt, username, percent);

                        printStream.println(response);
                        printStream.flush();
                    } else if (requestProcessed[0].equals("taotaikhoanchinh")) {

                        String infotaikhoanchinh = requestProcessed[1];
                        String[] infotaikhoanchinh_split = infotaikhoanchinh.split("-");
                        // lay thong tin moi
                        String username = infotaikhoanchinh_split[0];
                        String cmt = infotaikhoanchinh_split[1];
                        String accountName = infotaikhoanchinh_split[2];
                        long tienbandau = Long.valueOf(infotaikhoanchinh_split[3]);
                        // add account moi vao file
                        // return lai ma so the moi neu thanh cong hoac return lai "" neu khong thanh cong
                        // String response = CreateMainToken.createMainTokenUser(username, accountName, tienbandau, cmt);
                        String response = AccountDao.createNewMainAccount(username, accountName, cmt, tienbandau);
                        printStream.println(response);
                        printStream.flush();
                        System.out.println("Da gui thong diep " + response);
                    }else if (requestProcessed[0].equals("xoataikhoan")) {
                        String accountName = requestProcessed[1];
                        String response = AccountDao.deleteAccount(accountName);
                        printStream.println(response);
                        printStream.flush();
                        System.out.println("Da gui thong diep " + response);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void sendMessageToServer(String message) {
        try {
            PrintStream ps = new PrintStream(this.getSocketMain().getOutputStream());
            ps.println(message);
            ps.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
