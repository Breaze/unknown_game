package gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Clase Paint
 * @author alvaro
 */
public class Paint extends JComponent  {

    private Point inicioArrastre;
    private Point finArrastre;
    private ImageIcon img;
    private ArrayList<Shape> lineas = new ArrayList<Shape>();

    public Paint() {
        super();
        JFrame ventana = new JFrame("world");
        ventana.setSize(1000, 800);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(new Paint());
        ventana.setVisible(true);
        
        
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) { // cuando se presiona el mouse
                inicioArrastre = new Point(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) { // cuando se deja de presionar el mouse
                finArrastre = new Point(e.getX(), e.getY());
                Shape linea = crearLinea(inicioArrastre.x, inicioArrastre.y, finArrastre.x, finArrastre.y);
                lineas.add(linea);
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) { // cuando se esta arrastrando el mouse
                finArrastre = new Point(e.getX(), e.getY());
                Shape linea = crearLinea(inicioArrastre.x, inicioArrastre.y, finArrastre.x, finArrastre.y);
                lineas.add(linea);
                inicioArrastre = new Point(finArrastre.x, finArrastre.y);
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        img = new ImageIcon(getClass().getResource("/Img/book.jpg"));
        g.drawImage(img.getImage(),0,0,2937,2056,null);
        super.paintComponents(g);
        setOpaque(false);
        

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        for (Shape linea : lineas) { // dibuja todos las elipses
            g2.draw(linea);
        }
        
    }

    private Line2D.Float crearLinea(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1, y1, x2, y2);
    }

}