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
public class Cube {

    Color c = new Color(58, 134, 255);
//    Color c = Color.WHITE;
//    Color c = Color.BLACK;
    //Antiguos
    int[] xCubeOriginal = new int[8];
    int[] yCubeOriginal = new int[8];
    int[] zCubeOriginal = new int[8];
    int[] xCubeActual = new int[8];
    int[] yCubeActual = new int[8];
    int[] zCubeActual = new int[8];

    //Nuevos
    private Point3D[] originalPoints;
    private Point3D[] actualPoints;

    private boolean flag = false;

    int[][] transMatriz;
    float[][] escMatriz, rotMatriz;

    float[] rAngle = {0, 0, 0};

    int ubix, ubiy;
    Point3D movimiento;
    Point3D center;
    float sxyz = 1;

    public static final int rx = 0;
    public static final int ry = 1;
    public static final int rz = 2;

    int xp = 1;
    int yp = 1;
    int zp = 2;

    int xc = 0;
    int yc = 0;
    int zc = 0;

    int pCx = 0;
    int pCy = 0;
    int pCz = 0;

    public Cube(Point3D center, int ubx, int uby) {
        this.ubix = ubx;
        this.ubiy = uby;
        this.center = center;
    }

    public Cube() {
    }

    //Cubo a apartir del punto central
    public void drawCubeParallel(Graphics g, int x, int y, int z, int inc) {
        int x0r, y0r;
        Linea objLine = new Linea(xp, yp, zp, 'p', ubix, ubiy);
//        objLine.drawLine(g, 100, 100, 300, 300);

        x0r = objLine.projectionParallel(x, xp, z, zp);
        y0r = objLine.projectionParallel(y, yp, z, zp);

        objLine.putPixel(g, x0r, y0r, Color.RED);
        // z = 0
        //Izq Sup
        xCubeOriginal[0] = x - inc;
        yCubeOriginal[0] = y - inc;
        zCubeOriginal[0] = z + inc;

        //Der Sup
        xCubeOriginal[1] = x + inc;
        yCubeOriginal[1] = y - inc;
        zCubeOriginal[1] = z + inc;

        //Izq Inf
        xCubeOriginal[2] = x - inc;
        yCubeOriginal[2] = y + inc;
        zCubeOriginal[2] = z + inc;

        //Der Inf
        xCubeOriginal[3] = x + inc;
        yCubeOriginal[3] = y + inc;
        zCubeOriginal[3] = z + inc;

        // z = 100
        //Izq Sup z = 0;
        xCubeOriginal[4] = x - inc;
        yCubeOriginal[4] = y - inc;
        zCubeOriginal[4] = z - inc;

        //Der Sup z = 0
        xCubeOriginal[5] = x + inc;
        yCubeOriginal[5] = y - inc;
        zCubeOriginal[5] = z - inc;

        //Izq Inf z = 0;
        xCubeOriginal[6] = x - inc;
        yCubeOriginal[6] = y + inc;
        zCubeOriginal[6] = z - inc;

        //Der Inf z = 0
        xCubeOriginal[7] = x + inc;
        yCubeOriginal[7] = y + inc;
        zCubeOriginal[7] = z - inc;

//        for (int i = 0; i < 7; i++) {
//            System.out.println("xCube: " + xCube[i]);
//            System.out.println("yCube: " + yCube[i]);
//            System.out.println("zCube: " + zCube[i]);
//            x0r = objLine.ProjectionParallel(xCube[i], xp, zCube[i], zp);
//            y0r = objLine.ProjectionParallel(yCube[i], yp, zCube[i], zp);
//            objLine.putPixel(g, x0r, y0r, c);
//        }
        //Primer Cuadrado
        objLine.drawLine3DParallel(g, xCubeOriginal[0], yCubeOriginal[0], zCubeOriginal[0], xCubeOriginal[1], yCubeOriginal[1], zCubeOriginal[1]);
        objLine.drawLine3DParallel(g, xCubeOriginal[0], yCubeOriginal[0], zCubeOriginal[0], xCubeOriginal[2], yCubeOriginal[2], zCubeOriginal[2]);
        objLine.drawLine3DParallel(g, xCubeOriginal[1], yCubeOriginal[1], zCubeOriginal[1], xCubeOriginal[3], yCubeOriginal[3], zCubeOriginal[3]);
        objLine.drawLine3DParallel(g, xCubeOriginal[2], yCubeOriginal[2], zCubeOriginal[2], xCubeOriginal[3], yCubeOriginal[3], zCubeOriginal[3]);

        //Segundo Cuadrado
        objLine.drawLine3DParallel(g, xCubeOriginal[4], yCubeOriginal[4], zCubeOriginal[4], xCubeOriginal[5], yCubeOriginal[5], zCubeOriginal[5]);
        objLine.drawLine3DParallel(g, xCubeOriginal[4], yCubeOriginal[4], zCubeOriginal[4], xCubeOriginal[6], yCubeOriginal[6], zCubeOriginal[6]);
        objLine.drawLine3DParallel(g, xCubeOriginal[5], yCubeOriginal[5], zCubeOriginal[5], xCubeOriginal[7], yCubeOriginal[7], zCubeOriginal[7]);
        objLine.drawLine3DParallel(g, xCubeOriginal[6], yCubeOriginal[6], zCubeOriginal[6], xCubeOriginal[7], yCubeOriginal[7], zCubeOriginal[7]);

        //Lineas Union entre Cuadrados
        objLine.drawLine3DParallel(g, xCubeOriginal[0], yCubeOriginal[0], zCubeOriginal[0], xCubeOriginal[4], yCubeOriginal[4], zCubeOriginal[4]);
        objLine.drawLine3DParallel(g, xCubeOriginal[1], yCubeOriginal[1], zCubeOriginal[1], xCubeOriginal[5], yCubeOriginal[5], zCubeOriginal[5]);
        objLine.drawLine3DParallel(g, xCubeOriginal[2], yCubeOriginal[2], zCubeOriginal[2], xCubeOriginal[6], yCubeOriginal[6], zCubeOriginal[6]);
        objLine.drawLine3DParallel(g, xCubeOriginal[3], yCubeOriginal[3], zCubeOriginal[3], xCubeOriginal[7], yCubeOriginal[7], zCubeOriginal[7]);
    }

    //Cubo a apartir del punto central
    public void drawCubePerspective(Graphics g, int x, int y, int z, int inc) {
        Linea objLine = new Linea(175, 100, 300, 'c', ubix, ubiy);

        // z = 0
        //Izq Sup
        xCubeOriginal[0] = x - inc;
        yCubeOriginal[0] = y - inc;
        zCubeOriginal[0] = z + inc;

        //Der Sup
        xCubeOriginal[1] = x + inc;
        yCubeOriginal[1] = y - inc;
        zCubeOriginal[1] = z + inc;

        //Izq Inf
        xCubeOriginal[2] = x - inc;
        yCubeOriginal[2] = y + inc;
        zCubeOriginal[2] = z + inc;

        //Der Inf
        xCubeOriginal[3] = x + inc;
        yCubeOriginal[3] = y + inc;
        zCubeOriginal[3] = z + inc;

        // z = 100
        //Izq Sup z = 0;
        xCubeOriginal[4] = x - inc;
        yCubeOriginal[4] = y - inc;
        zCubeOriginal[4] = z - inc;

        //Der Sup z = 0
        xCubeOriginal[5] = x + inc;
        yCubeOriginal[5] = y - inc;
        zCubeOriginal[5] = z - inc;

        //Izq Inf z = 0;
        xCubeOriginal[6] = x - inc;
        yCubeOriginal[6] = y + inc;
        zCubeOriginal[6] = z - inc;

        //Der Inf z = 0
        xCubeOriginal[7] = x + inc;
        yCubeOriginal[7] = y + inc;
        zCubeOriginal[7] = z - inc;

//        for (int i = 0; i < 7; i++) {
//            System.out.println("xCube: " + xCube[i]);
//            System.out.println("yCube: " + yCube[i]);
//            System.out.println("zCube: " + zCube[i]);
//            x0r = objLine.ProjectionParallel(xCube[i], xp, zCube[i], zp);
//            y0r = objLine.ProjectionParallel(yCube[i], yp, zCube[i], zp);
//            objLine.putPixel(g, x0r, y0r, c);
//        }
        //Primer Cuadrado
        objLine.drawLine3DPerspective(g, xCubeOriginal[0], yCubeOriginal[0], zCubeOriginal[0], xCubeOriginal[1], yCubeOriginal[1], zCubeOriginal[1]);
        objLine.drawLine3DPerspective(g, xCubeOriginal[0], yCubeOriginal[0], zCubeOriginal[0], xCubeOriginal[2], yCubeOriginal[2], zCubeOriginal[2]);
        objLine.drawLine3DPerspective(g, xCubeOriginal[1], yCubeOriginal[1], zCubeOriginal[1], xCubeOriginal[3], yCubeOriginal[3], zCubeOriginal[3]);
        objLine.drawLine3DPerspective(g, xCubeOriginal[2], yCubeOriginal[2], zCubeOriginal[2], xCubeOriginal[3], yCubeOriginal[3], zCubeOriginal[3]);

        //Segundo Cuadrado
        objLine.drawLine3DPerspective(g, xCubeOriginal[4], yCubeOriginal[4], zCubeOriginal[4], xCubeOriginal[5], yCubeOriginal[5], zCubeOriginal[5]);
        objLine.drawLine3DPerspective(g, xCubeOriginal[4], yCubeOriginal[4], zCubeOriginal[4], xCubeOriginal[6], yCubeOriginal[6], zCubeOriginal[6]);
        objLine.drawLine3DPerspective(g, xCubeOriginal[5], yCubeOriginal[5], zCubeOriginal[5], xCubeOriginal[7], yCubeOriginal[7], zCubeOriginal[7]);
        objLine.drawLine3DPerspective(g, xCubeOriginal[6], yCubeOriginal[6], zCubeOriginal[6], xCubeOriginal[7], yCubeOriginal[7], zCubeOriginal[7]);

        //Lineas Union entre Cuadrados
        objLine.drawLine3DPerspective(g, xCubeOriginal[0], yCubeOriginal[0], zCubeOriginal[0], xCubeOriginal[4], yCubeOriginal[4], zCubeOriginal[4]);
        objLine.drawLine3DPerspective(g, xCubeOriginal[1], yCubeOriginal[1], zCubeOriginal[1], xCubeOriginal[5], yCubeOriginal[5], zCubeOriginal[5]);
        objLine.drawLine3DPerspective(g, xCubeOriginal[2], yCubeOriginal[2], zCubeOriginal[2], xCubeOriginal[6], yCubeOriginal[6], zCubeOriginal[6]);
        objLine.drawLine3DPerspective(g, xCubeOriginal[3], yCubeOriginal[3], zCubeOriginal[3], xCubeOriginal[7], yCubeOriginal[7], zCubeOriginal[7]);
    }

    //Cubo a apartir del punto central ROTACION
    public void drawCubeRotation(Graphics g, Point3D point, int inc) {
        int x = point.x;
        int y = point.y;
        int z = point.z;
        Linea objLine = new Linea(x, y, z, 'c', ubix, ubiy);
        xc = x;
        yc = y;
        zc = z;
        int x0r, y0r;
        x0r = objLine.projectionParallel(x, xp, z, zp);
        y0r = objLine.projectionParallel(y, yp, z, zp);

        objLine.putPixel(g, x0r, y0r, Color.RED);
        if (!flag) {
            pCx = center.x;
            pCy = center.y;
            pCz = center.z;
            originalPoints = new Point3D[8];

            // z = 0
            //Izq Sup
            originalPoints[0] = new Point3D(x - inc, y - inc, z + inc);
            //Der Sup
            originalPoints[1] = new Point3D(x + inc, y - inc, z + inc);
            //Izq Inf
            originalPoints[2] = new Point3D(x - inc, y + inc, z + inc);
            //Der Inf
            originalPoints[3] = new Point3D(x + inc, y + inc, z + inc);

//            xCubeOriginal[0] = x - inc;
//            yCubeOriginal[0] = y - inc;
//            zCubeOriginal[0] = z + inc;
//
//            //Der Sup
//            xCubeOriginal[1] = x + inc;
//            yCubeOriginal[1] = y - inc;
//            zCubeOriginal[1] = z + inc;
//
//            //Izq Inf
//            xCubeOriginal[2] = x - inc;
//            yCubeOriginal[2] = y + inc;
//            zCubeOriginal[2] = z + inc;
//
//            //Der Inf
//            xCubeOriginal[3] = x + inc;
//            yCubeOriginal[3] = y + inc;
//            zCubeOriginal[3] = z + inc;
            // z = 100
            //Izq Sup
            originalPoints[4] = new Point3D(x - inc, y - inc, z - inc);
            //Der Sup
            originalPoints[5] = new Point3D(x + inc, y - inc, z - inc);
            //Izq Inf
            originalPoints[6] = new Point3D(x - inc, y + inc, z - inc);
            //Der Inf
            originalPoints[7] = new Point3D(x + inc, y + inc, z - inc);

//            xCubeOriginal[4] = x - inc;
//            yCubeOriginal[4] = y - inc;
//            zCubeOriginal[4] = z - inc;
//
//            //Der Sup z = 0
//            xCubeOriginal[5] = x + inc;
//            yCubeOriginal[5] = y - inc;
//            zCubeOriginal[5] = z - inc;
//
//            //Izq Inf z = 0;
//            xCubeOriginal[6] = x - inc;
//            yCubeOriginal[6] = y + inc;
//            zCubeOriginal[6] = z - inc;
//
//            //Der Inf z = 0
//            xCubeOriginal[7] = x + inc;
//            yCubeOriginal[7] = y + inc;
//            zCubeOriginal[7] = z - inc;
            flag = true;
//            xCubeActual = xCubeOriginal.clone();
//            yCubeActual = yCubeOriginal.clone();
//            zCubeActual = zCubeOriginal.clone();
            actualPoints = originalPoints.clone();
        }

//        for (int i = 0; i < 7; i++) {
//            System.out.println("xCube: " + xCube[i]);
//            System.out.println("yCube: " + yCube[i]);
//            System.out.println("zCube: " + zCube[i]);
//            x0r = objLine.ProjectionParallel(xCube[i], xp, zCube[i], zp);
//            y0r = objLine.ProjectionParallel(yCube[i], yp, zCube[i], zp);
//            objLine.putPixel(g, x0r, y0r, c);
//        }
        //Z
        //Primer Cuadrado/
        objLine.drawParallel3DLine(g, actualPoints[0], actualPoints[1]);
        objLine.drawParallel3DLine(g, actualPoints[1], actualPoints[3]);
        objLine.drawParallel3DLine(g, actualPoints[3], actualPoints[2]);
        objLine.drawParallel3DLine(g, actualPoints[2], actualPoints[0]);

        //Segundo Cuadrado/
        objLine.drawParallel3DLine(g, actualPoints[4], actualPoints[5]);
        objLine.drawParallel3DLine(g, actualPoints[5], actualPoints[7]);
        objLine.drawParallel3DLine(g, actualPoints[7], actualPoints[6]);
        objLine.drawParallel3DLine(g, actualPoints[6], actualPoints[4]);

        //Union
        objLine.drawParallel3DLine(g, actualPoints[0], actualPoints[4]);
        objLine.drawParallel3DLine(g, actualPoints[1], actualPoints[5]);
        objLine.drawParallel3DLine(g, actualPoints[2], actualPoints[6]);
        objLine.drawParallel3DLine(g, actualPoints[3], actualPoints[7]);

        //Primer Cuadrado
//            objLine.rotationZ(g, xCubeActual[0], yCubeActual[0], zCubeActual[0], xCubeActual[1], yCubeActual[1], zCubeActual[1], rAngle[0]);
//            objLine.rotationZ(g, xCubeActual[0], yCubeActual[0], zCubeActual[0], xCubeActual[2], yCubeActual[2], zCubeActual[2], rAngle[0]);
//            objLine.rotationZ(g, xCubeActual[1], yCubeActual[1], zCubeActual[1], xCubeActual[3], yCubeActual[3], zCubeActual[3], rAngle[0]);
//            objLine.rotationZ(g, xCubeActual[2], yCubeActual[2], zCubeActual[2], xCubeActual[3], yCubeActual[3], zCubeActual[3], rAngle[0]);
//
//            //Segundo Cuadrado
//            objLine.rotationZ(g, xCubeActual[4], yCubeActual[4], zCubeActual[4], xCubeActual[5], yCubeActual[5], zCubeActual[5], rAngle[0]);
//            objLine.rotationZ(g, xCubeActual[4], yCubeActual[4], zCubeActual[4], xCubeActual[6], yCubeActual[6], zCubeActual[6], rAngle[0]);
//            objLine.rotationZ(g, xCubeActual[5], yCubeActual[5], zCubeActual[5], xCubeActual[7], yCubeActual[7], zCubeActual[7], rAngle[0]);
//            objLine.rotationZ(g, xCubeActual[6], yCubeActual[6], zCubeActual[6], xCubeActual[7], yCubeActual[7], zCubeActual[7], rAngle[0]);
//
//            //Lineas Union entre Cuadrados
//            objLine.rotationZ(g, xCubeActual[0], yCubeActual[0], zCubeActual[0], xCubeActual[4], yCubeActual[4], zCubeActual[4], rAngle[0]);
//            objLine.rotationZ(g, xCubeActual[1], yCubeActual[1], zCubeActual[1], xCubeActual[5], yCubeActual[5], zCubeActual[5], rAngle[0]);
//            objLine.rotationZ(g, xCubeActual[2], yCubeActual[2], zCubeActual[2], xCubeActual[6], yCubeActual[6], zCubeActual[6], rAngle[0]);
//            objLine.rotationZ(g, xCubeActual[3], yCubeActual[3], zCubeActual[3], xCubeActual[7], yCubeActual[7], zCubeActual[7], rAngle[0]);
    }

    public void moveCube(Point3D movement) {
        originalPoints[0] = Cube.this.translacion(originalPoints[0], movement);
        originalPoints[1] = Cube.this.translacion(originalPoints[1], movement);
        originalPoints[2] = Cube.this.translacion(originalPoints[2], movement);
        originalPoints[3] = Cube.this.translacion(originalPoints[3], movement);
        originalPoints[4] = Cube.this.translacion(originalPoints[4], movement);
        originalPoints[5] = Cube.this.translacion(originalPoints[5], movement);
        originalPoints[6] = Cube.this.translacion(originalPoints[6], movement);
        originalPoints[7] = Cube.this.translacion(originalPoints[7], movement);
        actualPoints = originalPoints.clone();
        resizeCube(1);
        rotateCube(0, 0);
        Point3D center = Cube.this.translacion(new Point3D(pCx, pCy, pCz), movement);
        pCx = center.x;
        pCy = center.y;
        pCz = center.z;
    }

    public void resizeCube(float resizeValue) {
        sxyz = sxyz * resizeValue;
        Point3D center = new Point3D(pCx, pCy, pCz);
        rotateCube(0, 0);
    }

    public void rotateCube(int rotationAngle, int direction) {
        Point3D centerC = new Point3D(pCx, pCy, pCz);

        if (direction == rx) {
            rAngle[0] += rotationAngle;
        } else if (direction == ry) {
            rAngle[1] += rotationAngle;
        } else if (direction == rz) {
            rAngle[2] += rotationAngle;
        }

        actualPoints[0] = rotacion(originalPoints[0], centerC, rAngle[0], rx);
        actualPoints[1] = rotacion(originalPoints[1], centerC, rAngle[0], rx);
        actualPoints[2] = rotacion(originalPoints[2], centerC, rAngle[0], rx);
        actualPoints[3] = rotacion(originalPoints[3], centerC, rAngle[0], rx);
        actualPoints[4] = rotacion(originalPoints[4], centerC, rAngle[0], rx);
        actualPoints[5] = rotacion(originalPoints[5], centerC, rAngle[0], rx);
        actualPoints[6] = rotacion(originalPoints[6], centerC, rAngle[0], rx);
        actualPoints[7] = rotacion(originalPoints[7], centerC, rAngle[0], rx);

        actualPoints[0] = rotacion(actualPoints[0], centerC, rAngle[1], ry);
        actualPoints[1] = rotacion(actualPoints[1], centerC, rAngle[1], ry);
        actualPoints[2] = rotacion(actualPoints[2], centerC, rAngle[1], ry);
        actualPoints[3] = rotacion(actualPoints[3], centerC, rAngle[1], ry);
        actualPoints[4] = rotacion(actualPoints[4], centerC, rAngle[1], ry);
        actualPoints[5] = rotacion(actualPoints[5], centerC, rAngle[1], ry);
        actualPoints[6] = rotacion(actualPoints[6], centerC, rAngle[1], ry);
        actualPoints[7] = rotacion(actualPoints[7], centerC, rAngle[1], ry);

        actualPoints[0] = rotacion(actualPoints[0], centerC, rAngle[2], rz);
        actualPoints[1] = rotacion(actualPoints[1], centerC, rAngle[2], rz);
        actualPoints[2] = rotacion(actualPoints[2], centerC, rAngle[2], rz);
        actualPoints[3] = rotacion(actualPoints[3], centerC, rAngle[2], rz);
        actualPoints[4] = rotacion(actualPoints[4], centerC, rAngle[2], rz);
        actualPoints[5] = rotacion(actualPoints[5], centerC, rAngle[2], rz);
        actualPoints[6] = rotacion(actualPoints[6], centerC, rAngle[2], rz);
        actualPoints[7] = rotacion(actualPoints[7], centerC, rAngle[2], rz);

        actualPoints[0] = escalacion(actualPoints[0], centerC, sxyz);
        actualPoints[1] = escalacion(actualPoints[1], centerC, sxyz);
        actualPoints[2] = escalacion(actualPoints[2], centerC, sxyz);
        actualPoints[3] = escalacion(actualPoints[3], centerC, sxyz);
        actualPoints[4] = escalacion(actualPoints[4], centerC, sxyz);
        actualPoints[5] = escalacion(actualPoints[5], centerC, sxyz);
        actualPoints[6] = escalacion(actualPoints[6], centerC, sxyz);
        actualPoints[7] = escalacion(actualPoints[7], centerC, sxyz);
    }

    //Metodo Ejecutado en la clase cubo
    public Point3D translacion(Point3D movement) {
        transMatriz = new int[][]{{1, 0, 0, movement.x}, {0, 1, 0, movement.y}, {0, 0, 1, movement.z}, {0, 0, 0, 1}};

        int[] positionMatrix = {center.x, center.y, center.z, 1};
        int[] newPosition = matrizMulti(positionMatrix, transMatriz);
        center.x = newPosition[0];
        center.y = newPosition[1];
        center.z = newPosition[2];

        return center;
    }

    //Metodo en el Main Ligado al KeyListener
    public Point3D translacion(Point3D point, Point3D movement) {
        transMatriz = new int[][]{{1, 0, 0, movement.x}, {0, 1, 0, movement.y}, {0, 0, 1, movement.z}, {0, 0, 0, 1}};

        int[] positionMatrix = {point.x, point.y, point.z, 1};
        int[] newPosition = matrizMulti(positionMatrix, transMatriz);
        point.x = newPosition[0];
        point.y = newPosition[1];
        point.z = newPosition[2];

        return point;
    }

    public Point3D escalacion(Point3D point, float valorEsc) {
        escMatriz = new float[][]{
            {valorEsc, 0, 0, 0},
            {0, valorEsc, 0, 0},
            {0, 0, valorEsc, 0},
            {0, 0, 0, 1}
        };
        int[] pointMatrix = {center.x - point.x, center.y - point.y, center.z - point.z, 1};
        int[] newPoint = matrizMulti(pointMatrix, escMatriz);
        return new Point3D(newPoint[0], newPoint[1], newPoint[2]);
    }

    public Point3D escalacion(Point3D targetPoint, Point3D referencia, float valorEsc) {
        escMatriz = new float[][]{{valorEsc, 0, 0, 0},{0, valorEsc, 0, 0},{0, 0, valorEsc, 0},{0, 0, 0, 1}
        };

        int[] pointMatrix = {targetPoint.x, targetPoint.y, targetPoint.z, 1};
        int[] newPoint = matrizMulti(pointMatrix, escMatriz);
        return new Point3D(newPoint[0], newPoint[1], newPoint[2]);
    }

    public Point3D rotacion(Point3D targetPoint, Point3D pivot, float rAngle, int dir) {
        float rotationValue = (float) rAngle / 360;
        switch (dir) {
            case rx:
                rotMatriz = new float[][]{
                    {1, 0, 0, 0},
                    {0, (float) Math.cos(rotationValue), -(float) Math.sin(rotationValue), 0},
                    {0, (float) Math.sin(rotationValue), (float) Math.cos(rotationValue), 0},
                    {0, 0, 0, 1}
                };
                break;
            case ry:
                rotMatriz = new float[][]{
                    {(float) Math.cos(rotationValue), 0, -(float) Math.sin(rotationValue), 0},
                    {0, 1, 0, 0},
                    {(float) Math.sin(rotationValue), 0, (float) Math.cos(rotationValue), 0},
                    {0, 0, 0, 1}
                };
                break;
            case rz:
                rotMatriz = new float[][]{
                    {(float) Math.cos(rotationValue), -(float) Math.sin(rotationValue), 0, 0},
                    {(float) Math.sin(rotationValue), (float) Math.cos(rotationValue), 0, 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 1}
                };
                break;
        }
        int[] targetMatriz = new int[]{targetPoint.x, targetPoint.y, targetPoint.z, 1};
        int[] newPoint = matrizMulti(targetMatriz, rotMatriz);
        return new Point3D(newPoint[0], newPoint[1], newPoint[2]);
    }

    private int[] matrizMulti(int[] matriz, int[][] operacionDeMatriz) {
        int[] res = {0, 0, 0, 0};
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                res[i] = res[i] + operacionDeMatriz[i][j] * matriz[j];
            }
        }
        return res;
    }

    private int[] matrizMulti(int[] matriz, float[][] operacionDeMatriz) {
        int[] res = {0, 0, 0, 0};
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                res[i] = res[i] + (int) (operacionDeMatriz[i][j] * matriz[j]);
            }
        }
        return res;
    }

}
