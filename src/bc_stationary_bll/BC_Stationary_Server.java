/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya
 */
public class BC_Stationary_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        try(ServerSocket ss = new ServerSocket(5000,10,inet)){
            while(true){
            Socket s = ss.accept();
            Thread clientThread = new Thread(new ClientServicer(s));
            clientThread.start();}
        } catch (IOException ex) {
            Logger.getLogger(BC_Stationary_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
