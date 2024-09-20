/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicasp3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Saul
 */
public class Superficie extends JFrame implements Runnable {

    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    public static Image Fondo;
    public static Graphics gFondo;
    Surface surface = new Surface();
    private Thread thr;
    int opc = 1;
    float angle = 0;

    //Translacion
    int tx = 0, ty = 0, tz = 0;
    //Escalacion
    int sx = 0;

    //Background Colors
    Color bgColor = new Color(109, 104, 117);

    KeyListener escuchaTeclado = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.VK_W == e.getKeyCode()) {
                System.out.println("Up");
                ty = ty - 3;
            }
            if (e.VK_S == e.getKeyCode()) {
                System.out.println("Down");
                ty = ty + 3;
            }
            if (e.VK_A == e.getKeyCode()) {
                System.out.println("Left");
                tx = tx - 3;
            }
            if (e.VK_D == e.getKeyCode()) {
                System.out.println("Right");
                tx = tx + 3;
            }
            if (e.VK_Q == e.getKeyCode()) {
                System.out.println("Q");
                tz = tz - 3;
            }
            if (e.VK_E == e.getKeyCode()) {
                System.out.println("E");
                tz = tz + 3;
            }
            if (e.VK_Z == e.getKeyCode()) {
                System.out.println("Z");
                sx = sx - 1;
            }
            if (e.VK_X == e.getKeyCode()) {
                System.out.println("X");
                sx = sx + 1;
            }
            if (e.VK_1 == e.getKeyCode()) {
                System.out.println("1");
                opc = 1;
            }
            if (e.VK_2 == e.getKeyCode()) {
                System.out.println("2");
                opc = 2;
            }
            if (e.VK_3 == e.getKeyCode()) {
                System.out.println("3");
                opc = 3;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            // code.
        }

    };

    public Superficie() {
        setSize(800, 800);
        setVisible(true);
        setBackground(bgColor);
        setTitle("Superficie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        thr = new Thread(this);
        thr.start();

        this.addKeyListener(escuchaTeclado);
    }

    public void run() {
        while (true) {
            try {
                thr.sleep(10);
                repaint();
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
            angle = angle + (float) (.02);
        }

    }

    public void paint(Graphics g) {
        update(g);
    }

    public void update(Graphics g) {
        Fondo = createImage(getWidth(), getHeight());
        gFondo = Fondo.getGraphics();
        gFondo.setColor(bgColor);
        gFondo.setClip(0, 0, getWidth(), getHeight());
        gFondo.drawImage(Fondo, 0, 0, this);
        surface.drawCurve(gFondo, 300 + tx, 400 + ty, 60 + tz, 30+sx, angle, opc);
        g.drawImage(Fondo, 0, 0, this);
    }
}
