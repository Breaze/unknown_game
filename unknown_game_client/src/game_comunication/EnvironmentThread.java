/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_comunication;

import gui.World1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Breaze
 */
public class EnvironmentThread extends Thread{
    private MulticastSocket msocket;
    private DatagramPacket recv;
    private World1 world;
    
    public EnvironmentThread(MulticastSocket msock ,InetAddress groupNo , int portNo, World1 world)
    {
        msocket = msock;
        this.world = world;
        start();                // start calls run
    }

    @Override
    public void run()
    {
        String tmp;
        try
        {
            while(true)
            {
                String data[];
                byte[] buf = new byte[1024];
               // Handle the incoming data and print it to stnd output.
               recv = new DatagramPacket(buf, buf.length);
               msocket.receive(recv);
               tmp = new String(recv.getData());
               data = tmp.split(":");
               this.trigger(data);
               
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Exit...");
            e.printStackTrace();
        } 
        finally 
        {
            msocket.close();
        }
    }
    
    public void trigger(String[] data){
        //order:index:x:y:name:status
        String order = data[0];
        switch(order){
            case "update_player_list":
                update_player_list(data);
                break;
            /*case "update_player_position":
                update_player_position(data);
                break;*/
                
        }
    }
    
    public void update_player_list(String[] data){
        int index = Integer.parseInt(data[1]);
        int x = Integer.parseInt(data[2]);
        int y = Integer.parseInt(data[3]);
        this.world.updatePlayer(index, data[4], x, y, data[5]);
    }
    
    /*public void update_player_position(String[] data){
        int index = Integer.parseInt(data[1]);
        int x = Integer.parseInt(data[2]);
        int y = Integer.parseInt(data[3]);
        this.world.updatePlayer(index, data[4], x, y, "Playing");
    }*/
}
