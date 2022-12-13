package Bots;

/**
 * @author Department of Data Science and Knowledge Engineering (DKE)
 * @version 2022.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

/**
 * This class takes care of all the graphics to display a certain state.
 * Initially, you do not need to modify (or event understand) this class in Phase 1. You will learn more about GUIs in Period 2, in the Introduction to Computer Science 2 course.
 */
public class UI extends JPanel
{
    private JFrame window;
    public static final int generalBound = 4;
    private int[][] state;
    private int size;
    public static final int xCenter = 450;
    public static final int yCenter = 430;
    int[][] board = new int[3][3];
    
    


    /**
     * Constructor for the GUI. Sets everything up
     * @param x x position of the GUI
     * @param y y position of the GUI
     * @param _size size of the GUI
     */
    public UI(int x, int y, int _size)
    {
        size = _size;
        setPreferredSize(new Dimension(x * size, y * size));

        window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(this);
        window.pack();
        window.setVisible(true);
    }

    /**
     * This function is called BY THE SYSTEM if required for a new frame, uses the state stored by the UI class.
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D localGraphics2D = (Graphics2D) g;
        localGraphics2D.setColor(Color.white);
        localGraphics2D.fill(getVisibleRect());

        //draw lines
        localGraphics2D.setColor(Color.black);
        localGraphics2D.setStroke(new BasicStroke(3));
        // center coords : 450, 430
        // x and y like on coord sys
        localGraphics2D.drawLine(300, 0, 300, 900);
        localGraphics2D.drawLine(600, 0, 600, 900);
        localGraphics2D.drawLine(0, 300, 900, 300);
        localGraphics2D.drawLine(0, 600, 900, 600);
        //localGraphics2D.setColor(Color.red);
        localGraphics2D.setStroke(new BasicStroke(15));
        if (board != null){
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if(board[i][j] == 1){
                        localGraphics2D.setColor(Color.red);
                        addX(j, i, localGraphics2D);
                        
                    }
                    else if(board[i][j] == 2){
                        localGraphics2D.setColor(Color.blue);
                        addO(j,i,localGraphics2D);
                    }                    
                }  
            }
        }
        
        
    }

    /**
     * This function should be called to update the displayed state (makes a copy)
     * @param _state information about the new state of the GUI
     */
    public void setState(int[][] board)
    {
        this.board = board;
        
        repaint();
    }
    
    public void addX(int x, int y, Graphics2D localGraphics2D){
        localGraphics2D.drawLine(x*300+25, y*300+25, (x+1)*300-25, (y+1)*300-25);
        localGraphics2D.drawLine((x+1)*300-25, y*300+25, (x*300)+25, (y+1)*300-25);
        //localGraphics2D.drawLine(300, 0, 300, 900);        
    }
    public void addO(int x, int y, Graphics2D localGraphics2D){
        localGraphics2D.drawOval((x * 300)+25 , y*300+25 , 250, 250);
        
        //localGraphics2D.drawLine(300, 0, 300, 900);        
    }

    
}
