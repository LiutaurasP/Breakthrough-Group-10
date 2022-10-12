package Menu;

import GUI.BoardUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class SelectPositions extends JFrame implements MouseListener{

    private final JFrame frame;

    public final int FRAME_WIDTH = 700;
    public final int FRAME_HEIGHT = 700;

    private final Object lock = new Object();

    private final int SIZE_OF_BOARD = 11;
    private int totalSilverPieces;
    private int totalGoldPieces;

    private int choosing; // 1 - silver | 2 - gold
    public int[] click = new int[2];
    private int[][] board;
    private final String madeBoard = "src/DifferentKindOfBoards/MadeBoard.txt";

    public SelectPositions(){
        choosing = 2;

        totalSilverPieces = 20;
        totalGoldPieces = 12;

        frame = new JFrame("Manual Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(true);

        JLabel background = new JLabel();
        background.setLayout(new FlowLayout());
        frame.add(background);

        addMouseListener(this);

//        BoardUI bu = new BoardUI(lock);
//        background.add(bu);

        JButton silver = new JButton("Choose for SILVER " + totalSilverPieces);
        silver.setBackground(Color.WHITE);
        background.add(silver);

        JButton gold = new JButton("Choose for GOLD " + totalGoldPieces);
        gold.setBackground(Color.WHITE);
        background.add(gold);

        silver.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                gold.setBackground(Color.WHITE);
                silver.setBackground(Color.CYAN);
                choosing = 1;
            }
        });

        gold.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                silver.setBackground(Color.WHITE);
                gold.setBackground(Color.CYAN);
                choosing = 2;
            }
        });

        createBoard();
        //new Breakthru();

        frame.setVisible(true);
    }

    private void createBoard() {
        int[][] b = new int[SIZE_OF_BOARD][SIZE_OF_BOARD];
        writeBoard(b);
    }

    /**
     * Checking if you are allowed to place your piece in the clicked spot.
     * The gold pieces can only be placed inside the 5X5 square in the middle of the board.
     * @return true if you're placing the piece in the correct place.
     */
    private boolean checkIfAllowed() {
        if(choosing == 1) {
            // false if trying to place a silver piece in the gold square
            return (click[1] < 3 || click[1] > 7) || (click[2] < 3 || click[2] > 7);
        } else  {
            // false if trying to place a gold piece outside the gold square or in the middle
            return (click[1] > 3 && click[1] < 7) && (click[2] > 3 && click[2] < 7) && (click[1] != 5 || click[2] != 5);
        }
    }

    void writeBoard(int[][] board) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(madeBoard));

            for (int[] ints : board) {
                for (int anInt : ints) {
                    bw.write(anInt + " ");
                }
                bw.newLine();
            }

            bw.flush();
        } catch (IOException ignored) {
        }
    }

    public static void main(String[] args) {
        SelectPositions selectPositions = new SelectPositions();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX()+" "+e.getY());
        int x = (e.getX() - 3) / 50;
        int y = (e.getY() - 3) / 50;
        click[0] = x;
        click[1] = y;

        if(checkIfAllowed()){
            board[x][y] = choosing;
        }

        if(choosing == 1) {
            totalSilverPieces--;
        }

        if(choosing == 2) {
            totalGoldPieces--;
        }

        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX()+" "+e.getY());
        int x = (e.getX() - 3) / 50;
        int y = (e.getY() - 3) / 50;
        click[0] = x;
        click[1] = y;

        if(checkIfAllowed()){
            board[x][y] = choosing;
        }

        if(choosing == 1) {
            totalSilverPieces--;
        }

        if(choosing == 2) {
            totalGoldPieces--;
        }

        synchronized (lock) {
            lock.notifyAll();
        }

//        if () {
//            board[y][x].setCurrentPiece(new RegularPiece(Team.s,x,y));
//        }
//        if (Integer.parseInt(line[x]) == 2) {
//            board[y][x].setCurrentPiece(new RegularPiece(Team.g,x,y));
//        }
//        if (Integer.parseInt(line[x]) == 3) {
//            board[y][x].setCurrentPiece(new Flag(Team.g,x,y));
//        }

        // System.out.println("x:" + x + ", y:" + y + "//" + tiles[y][x].getSquare().getCurrentPiece());
//        synchronized (lock) {
//            lock.notifyAll();
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

