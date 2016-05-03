/**
 * Michael O'Keefe
 * 3D Computer Graphics - Project 2
 * 1/28/16
 *
 * TheFrame class is the view component of the MVC model. TheFrame
 * class deals with all of the necessary graphical user interface
 * components of the project. When constructed a frame is created with
 * six different buttons: "Click to terminate", "Draw Square", "Draw Triangle",
 * "Draw Circle", "Erase Last", and "Erase All". TheFrame class has the capability
 * to draw a draw each shape, erase one shape, and erase all shapes (thus it has
 * access to the Model class- Shape. The logical functionality of when to perform
 * each task is handled in the Controller class.
 *
 * Note:
 *  - There is an odd bug: when the frame is first initialized, only one button will be
 *    displayed ("Click to terminate"), but if you click offscreen (anywhere but
 *    the frame created by this program) and then go back to the frame - all buttons
 *    should be displayed (currently do not know why this happens)
 *  - You cannot resize the frame, default is 500 by 500
 *  - The background color is white
 *  - When a single shape is erased and it was previously overlapping with another shape,
 *    then the existing shape that was being overlapped will now have small gaps in its
 *    structure where the erased shape used to be (this was done on-purpose since recreating
 *    each shape on display after one is erased is a costly operation)
 *  - The most recently clicked/executed button will be shown as clicked down until a new
 *    button is clicked/executed
 *
 */


package views;

import model.Shape;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class TheFrame extends JFrame {

    // width of screen
    public static final int WIDTH = 500;

    // height of screen
    public static final int HEIGHT = 500;

    // button for drawing a triangle
    private JButton drawTriangleButton = new JButton("Draw Triangle");

    // button for drawing a circle
    private JButton drawCircleButton = new JButton("Draw Circle");

    // button for drawing a square
    private JButton drawSquareButton = new JButton("Draw Square");

    // button to terminate the program
    private JButton closeButton = new JButton("Click to terminate");

    // button to erase the most recently drawn shape
    private JButton eraseLastButton = new JButton("Erase Last");

    // button to erase all shapes from the display
    private JButton eraseAllButton = new JButton("Erase All");

    public TheFrame() { // initializes the GUI components
        super();
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBackground(Color.WHITE);
        setLayout(null);

        // sets bounds for each button, fixed layout
        drawCircleButton.setBounds(5, 420, 100, 20);
        closeButton.setBounds(5, 5, 150, 20);
        drawTriangleButton.setBounds(200, 420, 100, 20);
        drawSquareButton.setBounds(395, 420, 100, 20);
        eraseAllButton.setBounds(125, 460, 100, 20);
        eraseLastButton.setBounds(275, 460, 100, 20);

        // add each button
        add(closeButton);
        add(drawTriangleButton);
        add(drawCircleButton);
        add(drawSquareButton);
        add(eraseAllButton);
        add(eraseLastButton);

        // make visible
        setVisible(true);
    }


    // Action Listener Methods: add action listeners for each button

    public void addDrawTriangleButtonActionListener(ActionListener listener) {
        drawTriangleButton.addActionListener(listener);
    }

    public void addCloseButtonActionListener(ActionListener listener) {
        closeButton.addActionListener(listener);
    }

    public void addDrawSquareButtonActionListener(ActionListener listener) {
        drawSquareButton.addActionListener(listener);
    }

    public void addDrawCircleButtonActionListener(ActionListener listener) {
        drawCircleButton.addActionListener(listener);
    }

    public void addEraseLastButtonActionListener(ActionListener listener) {
        eraseLastButton.addActionListener(listener);
    }

    public void addEraseAllButtonActionListener(ActionListener listener) {
        eraseAllButton.addActionListener(listener);
    }


    // GUI Action Methods: Perform each specified drawing or erasing task

    // draws a triangle using the given graphics and triangle
    public void drawTriangle(Graphics g, Shape triangle) {
        g.drawPolygon(triangle.getTriangleX(), triangle.getTriangleY(), triangle.getEdges());
    }

    // draws a circle using the given graphics and circle
    public void drawCircle(Graphics g, Shape circle) {
        g.drawOval(circle.getX(), circle.getY(), circle.getXd(), circle.getYd());
    }

    // draws a square using the given graphics and square
    public void drawSquare(Graphics g, Shape square) {
        g.drawRect(square.getX(), square.getY(), square.getXd(), square.getYd());
    }

    // erases the given shape by overlapping the same shape with the background
    // color - white
    public void eraseLast(Graphics g, Shape shapeToErase) {
        g.setColor(Color.WHITE);
        String shapeType = shapeToErase.getType().toLowerCase();
        if (shapeType == "triangle") {
            g.drawPolygon(shapeToErase.getTriangleX(), shapeToErase.getTriangleY(),shapeToErase.getEdges());
        }
        else if(shapeType == "square") {
            g.drawRect(shapeToErase.getX(), shapeToErase.getY(), shapeToErase.getXd(), shapeToErase.getYd());
        }
        else if(shapeType == "circle") {
            g.drawOval(shapeToErase.getX(), shapeToErase.getY(), shapeToErase.getXd(), shapeToErase.getYd());
        }
        g.setColor(Color.BLACK);
    }

    // erases all shapes from the display
    public void eraseAll(Graphics g) {
        g.clearRect(50, 50, 400, 375);
        Shape.eraseAll = false;
    }

    // called when the frame is first initialized and when repaint() is called
    // by the controller - determines which drawing or erasing method needs
    // to be executed and with what shape
    @Override
    public void paint(Graphics g) {
        if (!Shape.shapesToDisplay.isEmpty()) {
            Shape shapeToDraw = Shape.shapesToDisplay.remove();
            if (shapeToDraw.getType().toLowerCase().equals("triangle")) {
                drawTriangle(g, shapeToDraw);
            }
            else if (shapeToDraw.getType().toLowerCase().equals("square")) {
                drawSquare(g, shapeToDraw);
            }
            else if (shapeToDraw.getType().toLowerCase().equals("circle")) {
                drawCircle(g, shapeToDraw);
            }
        }
        else if (Shape.eraseAll) {
            eraseAll(g);
        }
        else if (!Shape.shapesToErase.isEmpty()) {
            Shape shapeToErase = Shape.shapesToErase.remove();
            eraseLast(g, shapeToErase);
        }

    }
}
