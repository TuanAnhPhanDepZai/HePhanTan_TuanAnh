package server;

import dao.AccountDao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CreateMainToken;
import service.CreateNotMainToken;
import service.DeleteAccountUser;

/**
 *
 * @author phant
 */
public class ServerThread2 extends Thread {

    private Socket socket;
    private String message;
    private int flag;

    public ServerThread2(Socket socket) {
        this.socket = socket;
        this.message = "";
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void run() {
        System.out.print("Server dang chay . ... ...");
        while (true) {
            BufferedReader br;
            try {

                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream printStream = new PrintStream(socket.getOutputStream());

                String request = br.readLine();

                if (request != null && !(request.equals(""))) {
                    System.out.println("Re quest laf " + request);
                    // tach request thanh nhieu phan, va kiem chung xem client muon lam gi
                    this.setMessage(request);
                    this.flag = 1;
                    String[] requestProcessed = request.split(":");
                    if (requestProcessed[0].equals("guitien")) {
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
                    } else if (requestProcessed[0].equals("taotaikhoanchinh")) {

                        String infotaikhoanchinh = requestProcessed[1];
                        String[] infotaikhoanchinh_split = infotaikhoanchinh.split("-");
                        // lay thong tin moi
                        String username = infotaikhoanchinh_split[0];
                        String cmt = infotaikhoanchinh_split[1];
                        String accountName = infotaikhoanchinh_split[2];
                        float tienbandau = Float.valueOf(infotaikhoanchinh_split[3]);
                        // add account moi vao file
                        // return lai ma so the moi neu thanh cong hoac return lai "" neu khong thanh cong
                        String response = CreateMainToken.createMainTokenUser(username, accountName, tienbandau, cmt);

                        printStream.println(response);
                        printStream.flush();
                        System.out.println("Da gui thong diep " + response);
                    }else if (requestProcessed[0].equals("taothephu")) {
                        String infoguitien = requestProcessed[1];
                        String[] infoguitien_split = infoguitien.split("-");
                        String sothe = infoguitien_split[0];
                        String infothephu = infoguitien_split[1];
                        String[] listinfo = infothephu.split("<>");
                        String username = listinfo[0];
                        String cmnt = listinfo[1];
                        int percent = Integer.valueOf(listinfo[2]);

                        String response = CreateNotMainToken.createNotMainToken(sothe, cmnt, username, percent);
                        
                        printStream.println(response);
                        printStream.flush();
                    }else if (requestProcessed[0].equals("xoataikhoan")) {
                        String accountName = requestProcessed[1];
                        String response = DeleteAccountUser.deleteAccount(accountName);
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

    public String getMessage() {
        this.setFlag(0);
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendMessageToServer(String message) {
        try {
            PrintStream ps = new PrintStream(this.getSocket().getOutputStream());
            ps.println(message);
            ps.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
