/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.java.accessibility.util.AWTEventMonitor;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author alejopc
 */
public class Player implements KeyListener {
    private String name;
    private int x;
    private int y;

    public void setName(String name) {
        this.name = name;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private int height=50;
    private int width=100;
    private String status;

    public Player() {
        AWTEventMonitor.addKeyListener(this);
        this.status ="Not Playing";
        
        
        addMouseListener(new MouseAdapter() {

            public void mouseClick(MouseEvent e) { // cuando se presiona el mouse
                
            }

    });}

    public String getStatus() {
        return status;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setHeight(int height) {
        this.height=height;
    }

    public void setWidth(int width) {
        this.width=width;
    }

    public String isStatus() {
        return status;
    }

    @Override
    public void keyTyped(KeyEvent ke) {   
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int  key= ke.getExtendedKeyCode();
        if(key== KeyEvent.VK_DOWN){
            this.y+=5;
            
        }
        if(key==KeyEvent.VK_UP){
            this.y-=5;
        }
        if(key==KeyEvent.VK_RIGHT){
            this.x+=5;
        }
         if(key==KeyEvent.VK_LEFT){
            this.x-=5;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    
}


