package practicasp3;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Saul
 */
public class ProyeccionParalela extends JFrame {

    //Background Colors
    Color bgColor = new Color(109, 104, 117);

    public ProyeccionParalela() {
        setSize(500, 500);
        setVisible(true);
        setBackground(bgColor);
        setTitle("ProyeccionParalela");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        Cube objCube = new Cube();
        objCube.drawCubeParallel(g, 350, 350, 100, 50);
    }
}
