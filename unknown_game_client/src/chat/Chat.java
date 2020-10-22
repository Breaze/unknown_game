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
    
    public Chat(){
        this.port = 8888;
        this.multicastAddress = "224.1.1.5";
    }
    /*public void send() throws IOException{
        Scanner in = new Scanner(System.in);
        MulticastSocket chat = new MulticastSocket(8885);
        InetAddress group = InetAddress.getByName("224.2.2.5");
        chat.joinGroup(group);

        String msg = "";
        System.out.println("Type a message for the server:");
        msg = in.next();
        DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, 8885);
        chat.send(data);
        chat.close();
    }
    
    public void read(){
        Listener l = new Listener();
        l.start();
    }*/
    
    public void run() throws UnknownHostException{
        String line;

        
        InetAddress group = InetAddress.getByName(this.multicastAddress);

        try 
        {
            MulticastSocket s = new MulticastSocket(port);

            // Enable/disable SO_BROADCAST.
            s.setBroadcast(false);
            //System.out.println("Broadcast is: " + s.getBroadcast());

            // Disable/Enable local loopback of multicast datagrams The 
            // option is used by the platform's networking code as a hint 
            // for setting whether multicast data will be looped back to the 
            // local socket.
            s.setLoopbackMode(false);
            //System.out.println("LoopbackMode is: " + s.getLoopbackMode());

            // Set the default time-to-live for multicast packets sent out on 
            // this MulticastSocket in order to control the scope of the 
            // multicasts.
            //s.setTimeToLive(2);
            //System.out.println("TimeToLive is: " + s.getTimeToLive());

            s.joinGroup(group);

            //System.out.println("Joined Group: " + multicastAddress + " Port:" + port);

            // ChatThread will handle the incoming Data and print it out to STDN output.
            new ChatThread(s,group,port);

            // Now read from STDN input and send the Data to the Group.

            BufferedReader myinput = new BufferedReader(
                           new InputStreamReader(System.in));  

            System.out.println("Type anything followed by RETURN, or Ctrl+D to  terminate the program.");
            while(true)
            {
                //Read from the STDN input
                line=myinput.readLine();

                if ( line==null) break; // User Hit Ctrl+D
                DatagramPacket dp = new DatagramPacket(line.getBytes(), line.length(),group, port);

                s.send(dp);
            }

            System.out.println("Leaving the Group...");
            s.leaveGroup(group);
            s.close();
        } 
        catch (Exception err)
        {
            System.err.println("ERR: Can not join the group " + err);
            err.printStackTrace();
            System.exit(1);
        }
    }
    
}
