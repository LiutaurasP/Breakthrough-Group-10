import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;


//white - 1
//black - 2
public class Launcher extends JPanel {
    int xtiles = 11;
    JFrame frame;
    int ytiles = 11;
    public static int[][] playingBoard;
    int[] storage = new int[2];
    boolean playingState = false;
    public static boolean goldTurn = true;
    int px = 660;
    int py = 660;

    Launcher(){
        frame = new JFrame("Chess's not so cool brother");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(px+14,py+60);
        frame.add(this);
        frame.setVisible(true);
        frame.setResizable(false);

        JMenuBar selectionBar = new JMenuBar();
        JMenu button1 = new JMenu("Button1");
        JMenu button2 = new JMenu("Button2");
        JMenu button3 = new JMenu("Button3");
        JMenu button4 = new JMenu("Button4");
        JMenu button5 = new JMenu("Button5");
        selectionBar.add(button1);
        selectionBar.add(button2);
        selectionBar.add(button3);
        selectionBar.add(button4);
        selectionBar.add(button5);
        frame.setJMenuBar(selectionBar);
        playingBoard = new int[11][11];
        newBoard();
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int y = (e.getX()-5)/60;
                int x = (e.getY()-50)/60;
                System.out.println("x: " + x + ", y: " + y);
                if(goldTurn){
                    if(!playingState){
                        if(playingBoard[x][y] == 1){
                            playingState = true;
                            storage[0] = x;
                            storage[1] = y;
                            System.out.println("not in playingState");
                        }else{
                            System.out.println("pick a white piece");

                        }
                    }else{
                        System.out.println("in playingState");
                        if(playingBoard[storage[0]][storage[1]] == 1){
                            if(storage[1] == y && storage[0] == x){
                                playingState = false;
                            }
                            if(storage[1] == y && storage[0] - 1 == x){
                                whiteMoveForward(storage[0], storage[1]);
                                if(gameEnd()){
                                    System.out.println("game over");
                                }
                                goldTurn = false;
                                playingState = false;
                            }
                            else if(storage[1] + 1 == y && storage[0] - 1 == x){
                                whiteMoveRight(storage[0], storage[1]);
                                if(gameEnd()){
                                    System.out.println("game over");
                                }
                                goldTurn = false;
                                playingState = false;
                            }
                            else if(storage[1] - 1 == y && storage[0] - 1 == x){
                                whiteMoveLeft(storage[0], storage[1]);
                                if(gameEnd()){
                                    System.out.println("game over");
                                }
                                goldTurn = false;
                                playingState = false;
                            }else{
                                System.out.println("2move not possible");
                                playingState = false;
                            }
                        }
                    }
                }else if(!goldTurn){
                    if(!playingState){
                        if(playingBoard[x][y] == 2){
                            playingState = true;
                            storage[0] = x;
                            storage[1] = y;
                            System.out.println("not in playingState");
                        }else{
                            System.out.println("pick a black piece");
                        }
                    }else{
                        System.out.println("in playingState");
                        if(playingBoard[storage[0]][storage[1]] == 2){
                            if(storage[1] == y && storage[0] == x){
                                playingState = false;
                            }
                            if(storage[1] == y && storage[0] + 1 == x){
                                blackMoveForward(storage[0], storage[1]);
                                if(gameEnd()){
                                    System.out.println("game over");
                                }
                                goldTurn = true;
                                playingState = false;
                            }
                            else if(storage[1] + 1 == y && storage[0] + 1 == x){
                                blackMoveRight(storage[0], storage[1]);
                                if(gameEnd()){
                                    System.out.println("game over");
                                }
                                goldTurn = true;
                                playingState = false;
                            }
                            else if(storage[1] - 1 == y && storage[0] + 1 == x){
                                blackMoveLeft(storage[0], storage[1]);
                                if(gameEnd()){
                                    System.out.println("game over");
                                }
                                goldTurn = true;
                                playingState = false;
                            }else{
                                System.out.println("4move not possible");
                                playingState = false;
                            }
                        }
                    }
                }
                matrixUpdate(playingBoard);
            }

            @Override
            public void mousePressed(MouseEvent e) {

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
        };
        frame.addMouseListener(mouseListener);


         }

    @Override
    public void paintComponent(Graphics g) {
        // Draw board
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.setBackground(new Color(255, 255, 255));

        for (int x = 0; x<xtiles; x++){
            g.setColor(new Color(0, 41, 65));
            for (int y = 0; y < ytiles; y++) {
                if((x+y)%2!=0) {
                    g.fillRect(x * px / xtiles, y * py / ytiles, px / xtiles, px / ytiles);
                }
            }
        }

        // Draw pieces
        if(playingBoard!=null) {
            for (int x = 0; x < xtiles; x++) {
                for (int y = 0; y < ytiles; y++) {
                    if (playingBoard[y][x] == 2) {
                        g.setColor(new Color(229, 184, 11));
                        g.fillOval(60 * x + 15, 60 * y + 15, 30, 30);
                    }
                    if (playingBoard[y][x] == 1) {
                        g.setColor(new Color(140, 140, 140, 255));
                        g.fillOval(60 * x + 15, 60 * y + 15, 30, 30);

                    }
                    if (playingBoard[y][x] == 3){
                        g.setColor(new Color(255, 143, 0, 255));
                        g.fillOval(60 * x + 10, 60 * y + 10, 40, 40);

                    }

                }
            }
        }

    }
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
    }

    private void newBoard() {
        int[][] freshBoard = {
                {0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,1,0,1,0,0,0,0},
                {0,0,0,1,0,0,0,1,0,0,0},
                {0,0,1,0,0,2,0,0,1,0,0},
                {0,1,0,0,2,2,2,0,0,1,0},
                {1,0,0,2,2,3,2,2,0,0,1},
                {0,1,0,0,2,2,2,0,0,1,0},
                {0,0,1,0,0,2,0,0,1,0,0},
                {0,0,0,1,0,0,0,1,0,0,0},
                {0,0,0,0,1,0,1,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0},

        };
        matrixUpdate(freshBoard);
    }

    public void matrixUpdate(int[][] newBoard) {
        for (int x = 0; x < playingBoard.length; x++) {
            for (int y = 0; y < playingBoard[x].length; y++) {
                playingBoard[x][y] = newBoard[x][y];
            }
        }
        repaint();
    }


    public boolean gameEnd(){ //CAN MAKE THIS A BOOLEAN

        for (int i = 0; i < playingBoard.length; i++) {
            if(playingBoard[0][i] == 1 ){
                System.out.println("white wins");
                return true;
            }
            if(playingBoard[7][i] == 2){
                System.out.println("black wins");
                return true;
            }
        }
        return false;

    }

    public void whiteMoveRight(int x, int y){
        if(goldTurn){
            if(playingBoard[x][y]  == 1){
                if(y !=8 ){
                    if(playingBoard[x-1][y+1] == 0 || playingBoard[x-1][y+1] == 2){
                        playingBoard[x][y] = 0;
                        playingBoard[x-1][y+1] = 1;
                        goldTurn = false;
                    }else{
                        System.out.println("whiteMoveRight not possible");
                    }
                }else{
                    System.out.println("whiteMoveRight not possible");
                }
            }else{
                System.out.println("whiteMoveRight not possible");
            }
        }
    }

    public void whiteMoveLeft(int x, int y){
        if(goldTurn){
            if(playingBoard[x][y]  == 1){
                if(y != 0){
                    if(playingBoard[x-1][y-1] == 0 || playingBoard[x-1][y-1] == 2){
                        playingBoard[x][y] = 0;
                        playingBoard[x-1][y-1] = 1;
                        goldTurn = false;
                    }else{
                        System.out.println("whiteMoveLeft not possible");
                    }
                }else{
                    System.out.println("whiteMoveLeft not possible");
                }
            }else{
                System.out.println("whiteMoveLeft not possible");
            }
        }
        repaint();
    }

    public void whiteMoveForward(int x, int y){
        if(goldTurn){
            if(playingBoard[x][y] == 1){
                if(playingBoard[x-1][y] == 0){
                    playingBoard[x][y] = 0;
                    playingBoard[x-1][y] = 1;
                    goldTurn = false;
                }else{
                    System.out.println(playingBoard[x-1][y]);
                    System.out.println("1whiteMoveForward not possible");
                }
            }else{
                System.out.println(playingBoard[x][y]);
                System.out.println("2whiteMoveForward not possible");
            }
        }
        System.out.println("bro");
        System.out.println(Arrays.deepToString(playingBoard));
        repaint();
    }
    public void blackMoveRight(int x, int y){
        if(!goldTurn){
            if(playingBoard[x][y]  == 2){
                if(y !=8 ){
                    if(playingBoard[x+1][y+1] == 0 || playingBoard[x+1][y+1] == 1){
                        playingBoard[x][y] = 0;
                        playingBoard[x+1][y+1] = 2;
                        goldTurn = true;
                    }else{
                        System.out.println("move not possible");
                    }
                }else{
                    System.out.println("move not possible");
                }
            }else{
                System.out.println("move not possible");
            }
        }
        repaint();
    }

    public void blackMoveLeft(int x, int y){
        if(!goldTurn){
            if(playingBoard[x][y]  == 2){
                if(y != 0){
                    if(playingBoard[x+1][y-1] == 0 || playingBoard[x+1][y-1] == 1){
                        playingBoard[x][y] = 0;
                        playingBoard[x+1][y-1] = 2;
                        goldTurn = true;
                    }else{
                        System.out.println("move not possible");
                    }
                }else{
                    System.out.println("move not possible");
                }
            }else{
                System.out.println("move not possible");
            }
        }
        repaint();
    }

    public void blackMoveForward(int x, int y){

        if(!goldTurn){
            if(playingBoard[x][y] == 2){
                if(playingBoard[x+1][y] == 0){
                    playingBoard[x][y] = 0;
                    playingBoard[x+1][y] = 2;
                    goldTurn = true;
                }else{
                    System.out.println("move not possible");
                }
            }else{
                System.out.println("move not possible");
            }
        }
        repaint();
    }

}






