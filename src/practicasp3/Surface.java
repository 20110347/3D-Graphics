/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicasp3;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Saul
 */
public class Surface {

    int gx0 = 0, gy0 = 0, gz0 = 0;

    public void drawCurve(Graphics g, int xE, int yE, int zE,int sx, float angle, int opc) {
        Linea objLinea = new Linea(xE, yE, zE, 'c', 0, 0);

        Point3D point0 = new Point3D(0, 0, 0);
        Point3D point1 = new Point3D(0, 0, 0);
        int masx = xE, masy = yE;
        int tam = sx;
        int z = zE;
        boolean first = false;
        
        //LINEAS HORIZONTALES
        for (double x = -1; x < 1 + tam / 4; x = x + .5) {
            first = false;

            for (double y = 0; y < 2 + tam / 4; y = y + .5) {
                z = (int) (Math.pow(x, 2) + Math.pow(y, 2));
                if (!first) {
                    point0.x = (int) (y * tam + masx);
                    point0.y = (int) (x * tam + masy);
                    point0.z = z;

                    gx0 = point0.x;
                    gy0 = point0.y;
                    gz0 = point0.z;

                    first = true;
                } else {
                    point0.x = gx0;
                    point0.y = gy0;
                    point0.z = gz0;

                    point1.x = (int) (y * tam + masx);
                    point1.y = (int) (x * tam + masy);
                    point1.z = z;

                    objLinea.drawRotXYZ(g, point0.x, point0.y, point0.z, point1.x, point1.y, point1.z, angle, opc);
//                    objLinea.drawRotAll(g, point0.x, point0.y, point0.z, point1.x, point1.y, point1.z, angle);

                    gx0 = point1.x;
                    gy0 = point1.y;
                    gz0 = point1.z;
                }
            }
        }
        //LINEAS VERTICALES
        for (double y = 0; y < 2 + tam / 4; y = y + .5) {
            first = false;

            for (double x = -1; x < 1 + tam / 4; x = x + .5) {
                z = (int) (Math.pow(x, 2) + Math.pow(y, 2));
                if (!first) {
                    point0.x = (int) (y * tam + masx);
                    point0.y = (int) (x * tam + masy);
                    point0.z = z;

                    gx0 = point0.x;
                    gy0 = point0.y;
                    gz0 = point0.z;

                    first = true;
                } else {
                    point0.x = gx0;
                    point0.y = gy0;
                    point0.z = gz0;

                    point1.x = (int) (y * tam + masx);
                    point1.y = (int) (x * tam + masy);
                    point1.z = z;

                    objLinea.drawRotXYZ(g, point0.x, point0.y, point0.z, point1.x, point1.y, point1.z, angle, opc);
//                    objLinea.drawRotAll(g, point0.x, point0.y, point0.z, point1.x, point1.y, point1.z, angle);

                    gx0 = point1.x;
                    gy0 = point1.y;
                    gz0 = point1.z;
                }
            }
        }
    }
}
