/**
 * Michael O'Keefe
 * 3D Computer Graphics - Project 2
 * 1/28/16
 *
 * Controller class is the controller component of MVC. The program is initialized through
 * the creation a controller - which has access to both the model and the view. The controller
 * constantly listens to the action events created by the user their interaction with the
 * GUI (within the view instance - TheFrame). If the user clicks on any of the "Draw <Shape>"
 * buttons then the controller will create a new Shape with random position and give the
 * shape to the frame (view) to draw. If the user clicks on any of the "Erase <allOrLast>" buttons
 * then the controller will determine what shape or shapes should be erased then pass the
 * information to the view to update. Finally, if the user clicks on the "Click to terminate"
 * button, then the controller will terminate the program.
 *
 */

package controller;

import model.Shape;
import views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Stack;

public class Controller {

    // for random number generation
    private static final Random random = new Random(System.currentTimeMillis());

    // the frame for the GUI
    private static final TheFrame frame = new TheFrame();

    // for storing the shapes that are on display
    private static final Stack<Shape> shapesOnDisplay = new Stack<Shape>();

    public Controller()
    {
        // The following event listeners specify what do when each specified button is clicked

        // add a new triangle shape in a random position
        frame.addDrawTriangleButtonActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int xPosition = randomInt(50, 400);
                        int yPosition = randomInt(100, 400);
                        Shape triangle = new Shape("triangle",
                                new int[] {xPosition, xPosition + 25, xPosition + 50},
                                new int[] {yPosition, yPosition - 50, yPosition}, 3);
                        addShape(triangle);
                        frame.repaint();
                    }
                }
        );

        // terminate the program
        frame.addCloseButtonActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );

        // add a new square shape in a random position
        frame.addDrawSquareButtonActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int xPosition = randomInt(50, 400);
                        int yPosition = randomInt(100, 375);
                        Shape square = new Shape("square", xPosition,
                                yPosition, 50, 50);
                        addShape(square);
                        frame.repaint();
                    }
                }
        );

        // add a new circle shape in a random position
        frame.addDrawCircleButtonActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int xPosition = randomInt(50, 400);
                        int yPosition = randomInt(100, 375);
                        Shape circle = new Shape("circle", xPosition,
                                yPosition, 50, 50);
                        addShape(circle);
                        frame.repaint();
                    }
                }
        );

        // erase the last shape that was added to the display
        frame.addEraseLastButtonActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!noShapesOnDisplay()) {
                            Shape shapeToErase = shapesOnDisplay.pop();
                            Shape.shapesToErase.add(shapeToErase);
                            frame.repaint();
                        }
                    }
                }
        );

        // erase all shapes added to the display
        frame.addEraseAllButtonActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!noShapesOnDisplay()) {
                            Shape.eraseAll = true;
                            while(!shapesOnDisplay.isEmpty())
                                shapesOnDisplay.pop();
                            frame.repaint();
                        }
                    }
                }
        );

    }

    // constructs a new Controller thus starting the program
    public static void main(String args[]) {
        Controller app = new Controller();
    }

    // private method that adds the given shape to the shapes that are on display
    // storage, returns true when successful
    private boolean addShape(Shape shape) {
        if (shape.getType() == null)
            return false;
        shapesOnDisplay.push(shape);
        return true;
    }

    // private method that returns true if there are no shapes on the display
    private boolean noShapesOnDisplay() {
        return shapesOnDisplay.empty();
    }

    // private method that returns a random integer between the two specified values
    // (both inclusive)
    private static int randomInt(int minValue, int maxValue) {


        double val = random.nextDouble();

        val = (val * ((double) maxValue - (double) minValue)) + (double) minValue;

        return (int) val;

    }

}
