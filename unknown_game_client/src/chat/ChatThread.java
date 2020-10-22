/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Breaze
 */
public class ChatThread extends Thread 
{ 
    private MulticastSocket msocket;
    private DatagramPacket recv;

    public ChatThread(MulticastSocket msock ,InetAddress groupNo , int portNo)
    {
        msocket = msock;
        start();                // start calls run
    }

    @Override
    public void run()
    {
        byte[] buf = new byte[1000];
        String tmp;
        try
        {
            for(;;)
            {
               // Handle the incoming data and print it to stnd output.
               recv = new DatagramPacket(buf, buf.length);
               msocket.receive(recv);
               tmp = new String(recv.getData(),0,recv.getLength());
               System.out.println(tmp);
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
}
