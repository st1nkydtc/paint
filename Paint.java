import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import javax.swing.JColorChooser;
/**
 * Write a description of class DrawShape here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Paint
{
    // instance variables - replace the example below with your own
    private double startX, startY;
    private Color currentColor;
    private String drawMode = "line";
    private double size  = 50;
    
    
    
    
    /**
     * Constructor for objects of class LineDrawer
     */
    public Paint()
    {
        // initialise instance variables
        UI.initialise();
        UI.addButton("Quit", UI::quit);
    }

    /**
     * Colour Chooser
     */
    
    public void doChooseColor() {
        this.currentColor = JColorChooser.showDialog(null, "Choose Colour", this.currentColor);
        UI.setColor(this.currentColor);
        
    }
    
 
    
    public void doSelectOval()
    {
        drawMode = "oval";
    }
    
    public void doSelectRect()
    {
        drawMode = "rect";
    }
    
    public void doSelectLine()
    {
        drawMode = "line";
    }
    
    public void doSelectSize(double s)
    {
        size = s;
    }
    
    
    public void doSelectWidth(double w)
    {
        UI.setLineWidth(w);
    }
    
    /**
     * Mouse actioner
     */
    public void doMouse(String action, double x, double y) {
        if (drawMode.equals("line")) {
            if (action.equals("pressed")) {
                this.startX = x;
                this.startY = y;
            // put your code here
            
           } else if (action.equals("released")) {
               UI.drawLine(this.startX, this.startY, x, y);
            }
        }
        else if (drawMode.equals("oval"))
        {
            if (action.equals("clicked")) {
                UI.fillOval(x - (size/2), y - (size/2), size, size);
          }
        } 
        else {
           if (action.equals("clicked")) {
                UI.fillRect(x - (size/2), y - (size/2), size, size);
          }  
        }
    }
    
    /**
     * Main routine
     */
    public static void main(String[] args){
        Paint obj = new Paint();
        UI.setMouseListener(obj::doMouse);
        UI.addButton("Color",obj::doChooseColor);
        UI.addButton("Oval",obj::doSelectOval);
        UI.addButton("Rectangle",obj::doSelectRect);
        UI.addButton("Line",obj::doSelectLine);
        UI.addSlider("Shape Size", 1, 1000, 500, obj::doSelectSize);
        UI.addSlider("Shape Width", 1, 1000, 500, obj::doSelectWidth);
    }
 }