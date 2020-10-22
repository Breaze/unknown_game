/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import gui.ChatGUI;
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
    private ChatGUI gui;

    public ChatThread(MulticastSocket msock ,InetAddress groupNo , int port, ChatGUI gui)
    {
        msocket = msock;
        this.gui = gui;
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
                byte[] buf = new byte[1024];
               // Handle the incoming data and print it to stnd output.
               recv = new DatagramPacket(buf, buf.length);
               msocket.receive(recv);
               tmp = new String(recv.getData());
               //System.out.println(tmp);
               this.gui.updateChat(tmp);
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
