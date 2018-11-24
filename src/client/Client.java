/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phant
 */
public class Client {

    private PrintStream printStream;
    private BufferedReader br;
    private Socket socket;
    private int numberServer;
    public Client(int numberServer) {
        int port  = 1998;
        if (numberServer == 1){
            port = 1997;
        }

        try {
            socket = new Socket("localhost", port);
            printStream = new PrintStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getNumberServer() {
        return numberServer;
    }

    public void setNumberServer(int numberServer) {
        this.numberServer = numberServer;
    }

  

    public void close(){
        try {
            this.br.close();
            this.printStream.close();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendMessage(String string) {
        this.getPrintStream().println(string);
        this.getPrintStream().flush();
    }
}
