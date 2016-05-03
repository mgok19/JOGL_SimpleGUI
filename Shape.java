/**
 * Michael O'Keefe
 * 3D Computer Graphics - Project 2
 * 1/28/16
 *
 * Shape class is the model component of MVC. The Shape model stores the type,
 * and position attributes for any of the three shapes - square, circle, and triangle.
 * The shape class also keeps track of what shapes need to be created and what shapes
 * need to be deleted (for communication between the controller and the view, works like
 * a messaging queue).
 *
 */

package model;

import java.util.LinkedList;
import java.util.Queue;

public class Shape {

    public static final Queue<Shape> shapesToDisplay = new LinkedList<Shape>();

    public static final Queue<Shape> shapesToErase = new LinkedList<Shape>();

    public static boolean eraseAll = false;

    private int x;
    private int y;
    private int xd;
    private int yd;
    private int[] triangleX;
    private int[] triangleY;
    private int edges;
    private String type;

    public Shape(String type, int[] triangleX, int[] triangleY, int edges) {
        if (type.toLowerCase().equals("triangle")) {
            this.type = type;
            this.triangleX = triangleX;
            this.triangleY = triangleY;
            this.edges = edges;
            shapesToDisplay.add(this);
        }
    }

    public Shape(String type, int x, int y, int xd, int yd) {
        if (type.toLowerCase().equals("circle") || type.toLowerCase().equals("square")) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.xd = xd;
            this.yd = yd;
            shapesToDisplay.add(this);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXd() {
        return xd;
    }

    public void setXd(int xd) {
        this.xd = xd;
    }

    public int getYd() {
        return yd;
    }

    public void setYd(int yd) {
        this.yd = yd;
    }

    public int[] getTriangleX() {
        return triangleX;
    }

    public void setTriangleX(int[] triangleX) {
        this.triangleX = triangleX;
    }

    public int[] getTriangleY() {
        return triangleY;
    }

    public void setTriangleY(int[] triangleY) {
        this.triangleY = triangleY;
    }

    public int getEdges() {
        return edges;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
