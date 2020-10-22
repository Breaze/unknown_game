/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import static java.lang.System.in;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Breaze
 */
public class ChatServer{
    private int port;
    private String multicastGroup;
    
    public ChatServer(){
        this.port = 8885;
        this.multicastGroup = "224.2.2.5";
    }
    
    public void run(){
        try {
            MulticastSocket server = new MulticastSocket(this.port);
            InetAddress group = InetAddress.getByName(this.multicastGroup);
            //getByName - returns IP address of the given host
            server.joinGroup(group);
            while(true) {
                byte buf[] = new byte[1024];
                DatagramPacket data = new DatagramPacket(buf, buf.length);
                System.out.println("waiting");
                server.receive(data);
                String msg = new String(data.getData()).trim();
                DatagramPacket dgp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, group, this.port);
                //System.out.println(data.getAddress().getHostAddress()+","+dgp.getAddress().getHostAddress());
                if(!(data.getAddress().getHostAddress().equals(dgp.getAddress().getHostAddress()))){
                    System.out.println("distributed");
                    server.send(data);
                }
                    
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
