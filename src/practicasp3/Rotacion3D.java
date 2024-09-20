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
public class Rotacion3D extends JFrame implements Runnable {

    public static Image Fondo;
    private final int width;
    private final int height;
    public static Graphics gFondo;
    public static Point3D center = new Point3D(0, 0, 0);
    private Thread thr;
    public Cube objCube;
    byte opcion = 1;

    //Translacion
//    int tx = 0, ty = 0, tz = 0;
//    //Escalacion
//    int sx = 0;
    //Rotacion
    float[] rAngle = {0, 0, 0};

    //Background Colors
    Color bgColor = new Color(109, 104, 117);

    public Rotacion3D() {
        setSize(500, 500);
        width = this.getWidth();
        height = this.getHeight();
        objCube = new Cube(center, width / 2, height / 2);
        setVisible(true);
        setBackground(bgColor);
        setTitle("Rotacion3D");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        thr = new Thread(this);
        thr.start();

        this.addKeyListener(escuchaTeclado);
    }

    KeyListener escuchaTeclado = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.VK_W == e.getKeyCode()) {
                System.out.println("Up");
                objCube.moveCube(new Point3D(0, -2, 0));
                objCube.ubiy -= 2;
            }
            if (e.VK_S == e.getKeyCode()) {
                System.out.println("Down");
                objCube.moveCube(new Point3D(0, 2, 0));
                objCube.ubiy += 2;
            }
            if (e.VK_A == e.getKeyCode()) {
                System.out.println("Left");
                objCube.moveCube(new Point3D(-2, 0, 0));
                objCube.ubix -= 2;
            }
            if (e.VK_D == e.getKeyCode()) {
                System.out.println("Right");
                objCube.moveCube(new Point3D(2, 0, 0));
                objCube.ubix += 2;
            }
            if (e.VK_Q == e.getKeyCode()) {
                System.out.println("Q");
                objCube.moveCube(new Point3D(0, 0, -2));
            }
            if (e.VK_E == e.getKeyCode()) {
                System.out.println("E");
                objCube.moveCube(new Point3D(0, 0, 2));
            }
            if (e.VK_Z == e.getKeyCode()) {
                System.out.println("Z");
                objCube.resizeCube((float) 0.9);
            }
            if (e.VK_X == e.getKeyCode()) {
                System.out.println("X");
                objCube.resizeCube((float) 1.1);
            }
            //Eje X
            if (e.VK_1 == e.getKeyCode()) {
                System.out.println("1");
                objCube.rotateCube(10, objCube.rx);
            }
            if (e.VK_2 == e.getKeyCode()) {
                System.out.println("2");
                objCube.rotateCube(-10, objCube.rx);
            }
            //Ejes Y
            if (e.VK_3 == e.getKeyCode()) {
                System.out.println("3");
                objCube.rotateCube(10, objCube.ry);
            }
            if (e.VK_4 == e.getKeyCode()) {
                System.out.println("4");
                objCube.rotateCube(-10, objCube.ry);
            }
            //Eje Z
            if (e.VK_5 == e.getKeyCode()) {
                System.out.println("3");
                objCube.rotateCube(10, objCube.rz);
            }
            if (e.VK_6 == e.getKeyCode()) {
                System.out.println("4");
                objCube.rotateCube(-10, objCube.rz);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // code.
        }

    };

    public void run() {
        while (true) {
            try {
                thr.sleep(10);
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
//        objCube.drawCubeParallel(gFondo, 200 + tx, 250 + ty, 50 + tz, 50 + sx);
//        objCube.drawCubePerspective(gFondo, 300 + tx, 300 + ty, 100 + tz, 50 + sx);
        objCube.drawCubeRotation(gFondo, center, 50);
        g.drawImage(Fondo, 0, 0, this);
    }
}
