package practicasp3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Saul
 */
public class Linea {

    BufferedImage buffer;
    Color c = new Color(58, 134, 255);
//    Color c = Color.WHITE;
//    Color c = Color.BLACK;

    // Puntos de Proyeccion
    int xp = 0;
    int yp = 0;
    int zp = 0;

    //Puntos Centrales
    int xc = 0;
    int yc = 0;
    int zc = 0;

    byte contador = 0;
    private int ubicacionX, ubicacionY;

    public Linea(int xl, int yl, int zl, char type, int ubx, int uby) {
        if (type == 'p') {
            xp = xl;
            yp = yl;
            zp = zl;
        } else if (type == 'c') {
            xc = xl;
            yc = yl;
            zc = zl;
        }
        this.ubicacionX = ubx;
        this.ubicacionY = uby;
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void putPixel(Graphics g, int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        g.drawImage(buffer, x, y, null);
    }

    //Basicos
    public void drawLine3DParallel(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1) {
        int x0r, y0r, x1r, y1r;
        x0r = projectionParallel(x0, xp, z0, zp);
        y0r = projectionParallel(y0, yp, z0, zp);
        x1r = projectionParallel(x1, xp, z1, zp);
        y1r = projectionParallel(y1, yp, z1, zp);
        drawLine(g, x0r, y0r, x1r, y1r);
    }

    public void drawLine3DPerspective(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1) {
        int x0r, y0r, x1r, y1r;
        x0r = projectionPerspective(x0, xc, z0, zc);
        y0r = projectionPerspective(y0, yc, z0, zc);
        x1r = projectionPerspective(x1, xc, z1, zc);
        y1r = projectionPerspective(y1, yc, z1, zc);
        drawLine(g, x0r, y0r, x1r, y1r);
    }

    public int projectionParallel(int val, int valp, int z, int zp) {
        return (val - (valp * z) / zp);
    }

    public int projectionPerspective(int val, int valc, int z, int zc) {
        return (valc - (zc * (val - valc) / (z - zc)));
    }

    //Nuevo
    public void drawParallel3DLine(Graphics g, Point3D start3d, Point3D end3d) {;
        Point start;
        Point end;

        start = parallelProjection(start3d.x, start3d.y, start3d.z);
        end = parallelProjection(end3d.x, end3d.y, end3d.z);

        drawLine(g, start.x + ubicacionX, start.y + ubicacionY, end.x + ubicacionX, end.y + ubicacionY);
    }

    public Point parallelProjection(int x, int y, int z) {
        Point vertice = new Point();
        xp = 1;
        yp = 1;
        zp = 3;
        vertice.x = projectionParallel(x, xp, z, zp);
        vertice.y = projectionParallel(y, yp, z, zp);
        return vertice;
    }
//////////////////////NEW(OLD)//////////////////////////////////////////////////
//    //Rotacion  
//    public void rotationZ(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1, float angle) {
//        int x0r, y0r, x1r, y1r;
//        float sen = (float) Math.sin(1 + angle);
//        float cos = (float) Math.cos(1 + angle);
//        x0r = xc + Math.round(((x0 - xc) * (cos)) - ((y0 - yc) * (sen)));
//        y0r = yc + Math.round(((x0 - xc) * (sen)) + ((y0 - yc) * (cos)));
//        x1r = xc + Math.round(((x1 - xc) * (cos)) - ((y1 - yc) * (sen)));
//        y1r = yc + Math.round(((x1 - xc) * (sen)) + ((y1 - yc) * (cos)));
//        xp = 1;
//        yp = 1;
//        zp = 2;
//        drawLine3DParallel(g, x0r, y0r, z0, x1r, y1r, z1);
//    }
//
//    public void rotationX(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1, float angle) {
//        int y0r, z0r, y1r, z1r;
//        float sen = (float) Math.sin(1 + angle);
//        float cos = (float) Math.cos(1 + angle);
//
//        y0r = yc + Math.round(((y0 - yc) * (cos)) - ((z0 - zc) * (sen)));
//        z0r = zc + Math.round(((y0 - yc) * (sen)) + ((z0 - zc) * (cos)));
//        y1r = yc + Math.round(((y1 - yc) * (cos)) - ((z1 - zc) * (sen)));
//        z1r = zc + Math.round(((y1 - yc) * (sen)) + ((z1 - zc) * (cos)));
//        xp = 1;
//        yp = 1;
//        zp = 2;
//        drawLine3DParallel(g, x0, y0r, z0r, x1, y1r, z1r);
//    }
//
//    public void rotationY(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1, float angle) {
//        int x0r, z0r, x1r, z1r;
//        float sen = (float) Math.sin(1 + angle);
//        float cos = (float) Math.cos(1 + angle);
//
//        x0r = xc + Math.round(((x0 - xc) * (cos)) - ((z0 - zc) * (sen)));
//        z0r = zc + Math.round(((x0 - xc) * (sen)) + ((z0 - zc) * (cos)));
//        x1r = xc + Math.round(((x1 - xc) * (cos)) - ((z1 - zc) * (sen)));
//        z1r = zc + Math.round(((x1 - xc) * (sen)) + ((z1 - zc) * (cos)));
//        xp = 1;
//        yp = 1;
//        zp = 2;
//        drawLine3DParallel(g, x0r, y0, z0r, x1r, y1, z1r);
//    }

//    public void rotationX(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1, float angle) {
//        int y0r, z0r, y1r, z1r;
//        float sen = (float) Math.sin(1 + angle);
//        float cos = (float) Math.cos(1 + angle);
//
//        y0r = yc + Math.round(((y0 - yc) * (cos)) - ((z0 - zc) * (sen)));
//        z0r = zc + Math.round(((y0 - yc) * (sen)) + ((z0 - zc) * (cos)));
//        y1r = yc + Math.round(((y1 - yc) * (cos)) - ((z1 - zc) * (sen)));
//        z1r = zc + Math.round(((y1 - yc) * (sen)) + ((z1 - zc) * (cos)));
//        xp = 1;
//        yp = 1;
//        zp = 2;
//        drawLine3DParallel(g, x0, y0r, z0r, x1, y1r, z1r);
//    }
//
//    public void rotationY(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1, float angle) {
//        int x0r, z0r, x1r, z1r;
//        float sen = (float) Math.sin(1 + angle);
//        float cos = (float) Math.cos(1 + angle);
//
//        x0r = xc + Math.round(((x0 - xc) * (cos)) - ((z0 - zc) * (sen)));
//        z0r = zc + Math.round(((x0 - xc) * (sen)) + ((z0 - zc) * (cos)));
//        x1r = xc + Math.round(((x1 - xc) * (cos)) - ((z1 - zc) * (sen)));
//        z1r = zc + Math.round(((x1 - xc) * (sen)) + ((z1 - zc) * (cos)));
//        xp = 1;
//        yp = 1;
//        zp = 2;
//        drawLine3DParallel(g, x0r, y0, z0r, x1r, y1, z1r);
//    }
//    public void drawLine3DPerspectiveAll(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1, float[] angle) {
//        int x0r, y0r, z0r, x1r, y1r, z1r;
//        float sen = (float) Math.sin(1 + angle);
//        float cos = (float) Math.cos(1 + angle);
//
//        x0r = xc + Math.round(((x0 - xc) * (cos)) - ((y0 - yc) * (sen)));
//        y0r = yc + Math.round(((x0 - xc) * (sen)) + ((y0 - yc) * (cos)));
//        x1r = xc + Math.round(((x1 - xc) * (cos)) - ((y1 - yc) * (sen)));
//        y1r = yc + Math.round(((x1 - xc) * (sen)) + ((y1 - yc) * (cos)));
//        z0r = zc + Math.round(((y0 - yc) * (sen)) + ((z0 - zc) * (cos)));
//        z1r = zc + Math.round(((y1 - yc) * (sen)) + ((z1 - zc) * (cos)));
//
//
//        xp = 1;
//        yp = 1;
//        zp = 2;
//        drawLine3DParallel(g, x0r, y0r, z0r, x1r, y1r, z1r);
//    }
    /////////////////////////SURFACE////////////////////////////////////////////
    public void drawRotXYZ(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1, float angle, int opc) {

        int y0R = 0, y1R = 0, x0R = 0, x1R = 0, z0R = 0, z1R = 0;

        float sen = (float) Math.sin(1 + angle);
        float cos = (float) Math.cos(1 + angle);
        xp = 1;
        yp = 1;
        zp = 3;
        switch (opc) {
            case 1:
                y0R = yc + Math.round(((y0 - yc) * (cos)) - ((z0 - zc) * (sen)));
                z0R = zc + Math.round(((y0 - yc) * (sen)) + ((z0 - zc) * (cos)));
                y1R = yc + Math.round(((y1 - yc) * (cos)) - ((z1 - zc) * (sen)));
                z1R = zc + Math.round(((y1 - yc) * (sen)) + ((z1 - zc) * (cos)));
                drawLine3DParallel(g, x0, y0R, z0R, x1, y1R, z1R);

                break;
            case 2:
                x0R = xc + Math.round(((x0 - xc) * (cos)) - ((z0 - zc) * (sen)));
                z0R = zc + Math.round(((x0 - xc) * (sen)) + ((z0 - zc) * (cos)));
                x1R = xc + Math.round(((x1 - xc) * (cos)) - ((z1 - zc) * (sen)));
                z1R = zc + Math.round(((x1 - xc) * (sen)) + ((z1 - zc) * (cos)));
                drawLine3DParallel(g, x0R, y0, z0R, x1R, y1, z1R);
                break;
            case 3:
                x0R = xc + Math.round(((x0 - xc) * (cos)) - ((y0 - yc) * (sen)));
                y0R = yc + Math.round(((x0 - xc) * (sen)) + ((y0 - yc) * (cos)));
                x1R = xc + Math.round(((x1 - xc) * (cos)) - ((y1 - yc) * (sen)));
                y1R = yc + Math.round(((x1 - xc) * (sen)) + ((y1 - yc) * (cos)));
                drawLine3DParallel(g, x0R, y0R, z0, x1R, y1R, z1);
                break;
            default:
                break;
        }
    }

    public void drawRotAll(Graphics g, int x0, int y0, int z0, int x1, int y1, int z1, float angle) {

        int y0R = 0, y1R = 0, x0R = 0, x1R = 0, z0R = 0, z1R = 0;
        xp = 1;
        yp = 1;
        zp = 3;

        float sen = (float) Math.sin(1 + angle);
        float cos = (float) Math.cos(1 + angle);

        y0 = yc + Math.round(((y0 - yc) * (cos)) - ((z0 - zc) * (sen)));
        z0 = zc + Math.round(((y0 - yc) * (cos)) - ((z0 - zc) * (sen)));

        y1 = yc + Math.round(((y1 - yc) * (cos)) - ((z1 - zc) * (sen)));
        z1 = zc + Math.round(((y1 - yc) * (cos)) - ((z1 - zc) * (sen)));

        x0 = xc + Math.round(((x0 - xc) * (cos)) - ((z0 - zc) * (sen)));
        z0 = zc + Math.round(((x0 - xc) * (cos)) - ((z0 - zc) * (sen)));

        x1 = xc + Math.round(((x1 - xc) * (cos)) - ((z1 - zc) * (sen)));
        z1 = zc + Math.round(((x1 - xc) * (cos)) - ((z1 - zc) * (sen)));

        x0R = xc + Math.round(((x0 - xc) * (cos)) - ((y0 - yc) * (sen)));
        y0R = yc + Math.round(((x0 - xc) * (sen)) + ((y0 - yc) * (cos)));
        x1R = xc + Math.round(((x1 - xc) * (cos)) - ((y1 - yc) * (sen)));
        y1R = yc + Math.round(((x1 - xc) * (sen)) + ((y1 - yc) * (cos)));
        drawLine3DParallel(g, x0R, y0R, z0, x1R, y1R, z1);
    }

    public void drawLine(Graphics g, int x0, int y0, int x1, int y1) {
        int dx = (x1 - x0);
        int dy = (y1 - y0);

        int stepx, stepy;

        if (dy < 0) {
            dy = -dy;
            stepy = -1;
        } else {
            stepy = 1;
        }

        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        } else {
            stepx = 1;
        }

        int x = x0;
        int y = y0;

        if (dx > dy) {
            int p = 2 * dy - dx;
            int A = 2 * dy;
            int B = 2 * (dy - dx);

            while (x != x1) {
                x = x + stepx;
                if (p < 0) {
                    p = p + A;
                } else {
                    y = y + stepy;
                    p = p + B;
                }
                putPixel(g, x, y, c);
            }
        } else {
            int p = 2 * dx - dy;
            int A = 2 * dx;
            int B = 2 * (dx - dy);

            while (y != y1) {
                y = y + stepy;
                if (p < 0) {
                    p = p + A;
                } else {
                    x = x + stepx;
                    p = p + B;
                }
                putPixel(g, x, y, c);
            }
        }
    }

}
