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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Breaze
 */
public class Listener extends Thread{
    private MulticastSocket socket; 
    private int port;
    private String multicastGroup;
    
    

    public Listener() {
        this.port = 8885;
        this.multicastGroup = "224.2.2.5";
    }
    
    
    
    @Override
    public void run(){
        try {
            InetAddress group = InetAddress.getByName(this.multicastGroup);
            MulticastSocket mSocket = new MulticastSocket(3456);
            mSocket.joinGroup(group);

            int i = 0;
            while(true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                mSocket.receive(packet);

                System.out.println(new String(buffer));
                i++;
            }
        } catch (Exception e) {e.printStackTrace();}
    }
 
    
    public void listen() throws IOException{
        // El mismo puerto que se uso en la parte de enviar.
        MulticastSocket escucha = new MulticastSocket(55557);

        // Nos ponemos a la escucha de la misma IP de Multicast que se uso en la parte de enviar.
        escucha.joinGroup(InetAddress.getByName("230.0.0.1"));

        // Un array de bytes con tamaño suficiente para recoger el mensaje enviado, 
        // bastaría con 4 bytes.
        byte [] dato = new byte [1024];

        // Se espera la recepción. La llamada a receive() se queda
        // bloqueada hasta que llegue un mesnaje.
        DatagramPacket dgp = new DatagramPacket(dato, dato.length);
        escucha.receive(dgp);

        // Obtención del dato ya relleno.
        dato = dgp.getData();
        System.out.println();
    }
    
}
