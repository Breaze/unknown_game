/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import game_comunication.EnvironmentControl;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author alejopc
 */
public class World1 extends javax.swing.JPanel {

    private ImageIcon img;
    private int playerNumber;
    Player players[] = new Player[5];
    private EnvironmentControl ec;
    /**
     * Creates new form World1
     */
    public World1() {

        initComponents();
        setFocusable(true);
        for(int i = 0; i < this.players.length; i++){
            this.players[i] = new Player();
        }
        this.ec = new EnvironmentControl(this);

    }

    public void paint(Graphics g) {
        img = new ImageIcon(getClass().getResource("/Img/world.jpg"));
        g.drawImage(img.getImage(), 0, 0, 800, 600, null);
        super.paintComponents(g);
        setOpaque(false);

        //g.setColor(Color.red);
        
        //for(Player player:this.players){
        for(int i = 0; i < this.players.length; i++){
            Player player = this.players[i];
            if (player.getStatus().equals("Playing")) {
                g.setColor(Color.red);
                g.fillRect(player.getX(), player.getY(), player.getHeight(), player.getWidth());
                try {
                    ec.updatePlayerList(i, player.getX(), player.getY(), player.getName());
                } catch (UnknownHostException ex) {
                    Logger.getLogger(World1.class.getName()).log(Level.SEVERE, null, ex);
                }
                //repaint();
                
            }
           
        }
    }
    
    public void askPlayerInfo(){
        int playing = 0;
        for(Player player : this.players){
            playing = (player.getStatus().equals("Playing"))? playing+1:playing;
        }
        if(playing<=this.players.length){
            String name = JOptionPane.showInputDialog("¿Cual es tu nombre?");
            boolean create = false;
            do{
                int number = Integer.parseInt(JOptionPane.showInputDialog("Que Jugador Quiere Ser? (1 a 5)"));
                if(!this.players[number-1].getStatus().equals("Playing")){
                    create = true;
                    this.players[number-1].setName(name);
                    this.playerNumber = number;
                }else{
                    JOptionPane.showMessageDialog(this, "El jugador ya está siendo usado");
                }
            }while(!create);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(800, 600));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        
        this.players[playerNumber-1].keyPressed(evt);
        System.out.println(evt.getKeyCode());
        repaint();
    }//GEN-LAST:event_formKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if(evt.getButton()==1 && this.players[playerNumber-1].getStatus().equals("Not Playing")){
            this.players[playerNumber-1].setStatus("Playing");
            this.players[playerNumber-1].setX(evt.getX());
            this.players[playerNumber-1].setY(evt.getY());
            /*try {
                ec.updatePlayerList(this.playerNumber-1, evt.getX(), evt.getY(), this.players[playerNumber-1].getName());
            } catch (UnknownHostException ex) {
                Logger.getLogger(World1.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();*/
            repaint();
        }
    }//GEN-LAST:event_formMouseClicked

    public void updatePlayer(int index, String name, int x, int y, String status){
        status = status.replace("\u0000", ""); // removes NUL chars
        status = status.replace("\\u0000", ""); // removes backslash+u0000
        if(!(index==this.playerNumber-1)){
            this.players[index].setName(name);
            this.players[index].setX(x);
            this.players[index].setY(y);
            this.players[index].setStatus(status);
        }
        
        repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}