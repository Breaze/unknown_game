/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unknown_game_client;

import chat.Chat;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Breaze
 */
public class Unknown_game_client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Chat chat = new Chat();
            chat.run();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Unknown_game_client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
