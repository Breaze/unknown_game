/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_comunication;

import chat.Chat;
import chat.ChatThread;
import gui.World1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Breaze
 */
public class FoodControl {
    private String multicastAddress;
    private int port;
    private String name;
    private String message;
    private MulticastSocket socket;
    private InetAddress group;
    private World1 world;
    public FoodControl(World1 world){
        this.port = 8889;
        this.multicastAddress = "224.1.1.7";
        this.message = null;
        this.world = world;
        this.configureEnvironment();
    }
    
    public final void configureEnvironment(){
        try {
            this.group = InetAddress.getByName(this.multicastAddress);
            this.socket = new MulticastSocket(this.port);
            this.socket.setBroadcast(false);
            this.socket.setLoopbackMode(false);
            this.socket.joinGroup(this.group);
            new FoodThread(this.socket,this.group,this.port, this.world);
            
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public boolean updateFood(int index, int x, int y, int size) throws UnknownHostException{

        boolean status = false;

        try 
        {
            //order:index:x:y:name:status
            String info = "update_food:"+index+":"+x+":"+y+":"+size;
            //msg = this.name+": "+msg;
            
            if ( info==null)
                return status;
            DatagramPacket dp = new DatagramPacket(info.getBytes(), info.length(),this.group, this.port);
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
