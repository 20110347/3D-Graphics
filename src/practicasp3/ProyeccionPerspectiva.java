/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicasp3;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Saul
 */
public class ProyeccionPerspectiva extends JFrame {

    //Background Colors
    Color bgColor = new Color(109, 104, 117);

    public ProyeccionPerspectiva() {
        setSize(500, 500);
        setVisible(true);
        setBackground(bgColor);
        setTitle("ProyeccionPerspectiva");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        Cube objCube = new Cube();
        objCube.drawCubePerspective(g, 300, 300, 50, 50);
    }
}
