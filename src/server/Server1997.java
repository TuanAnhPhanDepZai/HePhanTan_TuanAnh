/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Client;
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
public class Server1997 {


    OptionOpen op ;
    public Server1997() {
        try {
            ServerSocket serverSocket = new ServerSocket(1997);
            System.out.println("Server 1997  dang mo");
            Socket socket = new Socket("localhost", 1998);
            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();
            op = new OptionOpen(serverThread);
            op.setVisible(true);
            System.out.println("Da ket noi voi 1998 server");
            while (true) {
                Socket socketClient = serverSocket.accept();
                new ClientServerThread(serverThread, socketClient).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Server1997.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new Server1997();
    }
}
