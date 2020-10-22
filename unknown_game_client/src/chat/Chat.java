/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Breaze
 */
public class Chat {
    private String multicastAddress;
    private int port;
    private String name;
    private String message;
    private MulticastSocket socket;
    private InetAddress group;
    public Chat(String name){
        this.port = 8888;
        this.multicastAddress = "224.1.1.5";
        this.name = name;
        this.message = null;
        this.configureChat();
    }
    
    public final void configureChat(){
        try {
            this.group = InetAddress.getByName(this.multicastAddress);
            this.socket = new MulticastSocket(this.port);
            this.socket.setBroadcast(false);
            this.socket.setLoopbackMode(false);
            this.socket.joinGroup(this.group);
            new ChatThread(this.socket,group,port);
            
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean newMessage(String msg) throws UnknownHostException{

        boolean status = false;

        try 
        {
            msg = this.name+": "+msg;
            
            if ( msg==null)
                return status;
            DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(),this.group, this.port);
            this.socket.send(dp);
            
            //System.out.println("Leaving the Group...");
            //s.leaveGroup(group);
            //s.close();
            status = true;
        } 
        catch (Exception err)
        {
            System.err.println("ERR: Can not join the group " + err);
            err.printStackTrace();
            System.exit(1);
        }
        return status;
    }
    
}
