/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unknown_game_client;

import chat.Chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Breaze
 */
public class Unknown_game_client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        BufferedReader reader = 
                   new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese su nombre");
        String name = reader.readLine();
        Chat chat = new Chat(name);
        String msg = null;
        do{
            //msg = in.next();
            try {
                msg = reader.readLine();
                
                chat.newMessage(msg);
                //chat.run();
            } catch (UnknownHostException ex) {
                Logger.getLogger(Unknown_game_client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(msg!=null);
                //chat.run();
        
        
    }
    
}
