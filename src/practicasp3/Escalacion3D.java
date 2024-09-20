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
public class Escalacion3D extends JFrame implements Runnable {

    public static Image Fondo;
    public static Graphics gFondo;
    Cube objCube = new Cube();
    private Thread thr;
    int[] rAngle = {0,0,0};
    float angulo = 0;

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
                ty = ty - 1;
            }
            if (e.VK_S == e.getKeyCode()) {
                System.out.println("Down");
                ty = ty + 1;
            }
            if (e.VK_A == e.getKeyCode()) {
                System.out.println("Left");
                tx = tx - 1;
            }
            if (e.VK_D == e.getKeyCode()) {
                System.out.println("Right");
                tx = tx + 1;
            }
            if (e.VK_Q == e.getKeyCode()) {
                System.out.println("Q");
                tz = tz - 1;
            }
            if (e.VK_E == e.getKeyCode()) {
                System.out.println("E");
                tz = tz + 1;
            }
            if (e.VK_Z == e.getKeyCode()) {
                System.out.println("Z");
                sx = sx - 1;
            }
            if (e.VK_X == e.getKeyCode()) {
                System.out.println("X");
                sx = sx + 1;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // code.
        }

    };

    public Escalacion3D() {
        setSize(800, 800);
        setVisible(true);
        setBackground(bgColor);
        setTitle("Escalacion3D");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        thr = new Thread(this);
        thr.start();

        this.addKeyListener(escuchaTeclado);
    }

    public void run() {
        while (true) {
            try {
                thr.sleep(50);
                repaint();
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
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
        objCube.drawCubeParallel(gFondo, 200 + tx, 250 + ty, 50 + tz, 50 + sx);
        objCube.drawCubePerspective(gFondo, 350 + tx, 300 + ty, 50 + tz, 50 + sx);
        g.drawImage(Fondo, 0, 0, this);
    }
}
