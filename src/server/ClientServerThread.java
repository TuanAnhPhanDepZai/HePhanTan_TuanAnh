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
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CreateNotMainToken;

/**
 *
 * @author phant
 */
public class ClientServerThread extends Thread {

    // 2 thuoc tinh la SocketClient va 1 thread server - server 
    private ServerThread serverThread;
    private Socket socketClient;

    public ServerThread getServerThread() {
        return serverThread;
    }

    public void setServerThread(ServerThread serverThread) {
        this.serverThread = serverThread;
    }

    public Socket getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(Socket socketClient) {
        this.socketClient = socketClient;
    }

    public ClientServerThread(ServerThread serverThread, Socket socketClient) {
        this.serverThread = serverThread;
        this.socketClient = socketClient;
    }

    public void run() {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.socketClient.getInputStream()));
            PrintStream printStream = new PrintStream(this.socketClient.getOutputStream());
            // ps  and br cho server
            BufferedReader brServer = new BufferedReader(new InputStreamReader(this.serverThread.getSocketMain().getInputStream()));
            PrintStream printServer = new PrintStream(this.serverThread.getSocketMain().getOutputStream());
            while (true) {
                String request = br.readLine();
                if (request != null) {
                    System.out.println(request);
                }

                if (request != null) {
                    // tach request thanh nhieu phan, va kiem chung xem client muon lam gi
                    String[] requestProcessed = request.split(":");
                    if (requestProcessed[0].equals("dangnhap")) {
                        String[] infodangnhap = requestProcessed[1].split("-");
                        String nameAccount = infodangnhap[0];
                        String mapin = infodangnhap[1];

                        String resultCheckUser = AccountDao.checkUser(nameAccount, mapin);
                        System.err.println(resultCheckUser);
                        printStream.println(resultCheckUser);
                        printStream.flush();

                    } else if (requestProcessed[0].equals("vantinsodu")) {
                        String accountName = requestProcessed[1];
                        String result = AccountDao.vanTinSoDuByAccountName(accountName);
                        String respones = "ketquavantinsodu:" + result;
                        printStream.println(respones);
                        printStream.flush();
                    } else if (requestProcessed[0].equals("guitien")) {
                        String infoguitien = requestProcessed[1];
                        String[] infoguitien_split = infoguitien.split("-");
                        String accountName = infoguitien_split[0];
                        String tiengui = infoguitien_split[1];
                        String response = AccountDao.napTienVaoTaiKhoan(accountName, tiengui);
                        printStream.println("guitien:" + response);
                        printStream.flush();

                    } else if (requestProcessed[0].equals("ruttien")) {
                        String infoguitien = requestProcessed[1];
                        String[] infoguitien_split = infoguitien.split("-");
                        String accountName = infoguitien_split[0];
                        String tienrut = infoguitien_split[1];
                        String response = AccountDao.rutTienTaiKhoan(accountName, tienrut);
                        printStream.println("ruttien:" + response);
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
                        printServer.println(request);
                        printServer.flush();
                        System.out.println("da gui request " + request);
                        int i = 0;
                        while (true) {
                            // doi server phan hoi
//                            String responseFromServer = this.getServerThread().getMessage();
//                            System.out.println("message vai : " + this.getServerThread().getMessage());
                            if (this.getServerThread().getFlag() == 1) {
                                break;
                            }
                            System.out.println("Flag: " + this.getServerThread().getFlag());
                            i++;
                            System.out.println("i:" + i);
                        }
                        String responseFromServer = this.getServerThread().getMessage();
                        System.out.println("response from server:" + responseFromServer);
                        if (!responseFromServer.equals("thatbai")) {
                            //neu thanh cong thi moi duoc tiep tuc thuc hien thao tac cap nhat vao file
                            //      String result = CreateNotMainToken.createNotMainToken(sothe, cmnt, username, percent);
                            String result = AccountDao.taoThePhu(sothe, cmnt, username, percent);
                            printStream.println("taothephu:" + result);
                            System.out.println("taothephu:" + result);
                            printStream.flush();
                        }
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ClientServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
