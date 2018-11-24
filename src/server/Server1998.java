/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.mysql.jdbc.Buffer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class Server1998 {

    private OptionOpen2 optionOpen2;
    public Server1998() {
        try {
            ServerSocket serverSocket = new ServerSocket(1998);
            System.out.println("Server 1998  dang mo");

            Socket socketServer = serverSocket.accept();
            ServerThread2 serverThread = new ServerThread2(socketServer);
            serverThread.start();
            optionOpen2  = new OptionOpen2(serverThread);
            optionOpen2.setVisible(true);
            System.out.println("Da ket noi voi 1997 server");
            while (true) {
                Socket socketClient = serverSocket.accept();
                new ClientServerThread2(serverThread, socketClient).start();
            }
             
        } catch (IOException ex) {
            Logger.getLogger(Server1997.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        new Server1998();
    }
}
