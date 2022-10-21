package Menu;

import GUI.UI;
import GameLogic.PlayingBoard;
import GameLogic.Team;
import GameLogic.TheGame;
import Players.Human;
import Players.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Launcher class for the game. It instantiates: players, user interface, logical part of the game.
 */
public class Breakthru implements Runnable {
    private final static Object lock = new Object();
    Team player1;
    Team player2;

    /**
     * Constructor for launching the game. It creates players, user interface, game logic instances. It also starts the game.
     */
    public Breakthru(Team player1, Team player2){
        this.player1 = player1;
        this.player2 = player2;
        Thread t1 =new Thread(this);
        t1.start();
    }

    /**
     * Main class that you need to run the game.
     * @param args main class args.
     */
    public static void main(String[] args) {
        Breakthru breakthru = new Breakthru(Team.g,Team.s);
    }

    @Override
    public void run() {
        PlayingBoard board = new PlayingBoard();
        UI ui = new UI(lock);
        Player p1 = new Human(player1,lock,ui);
        Player p2 = new Human(player2,lock,ui);
        System.out.println(board);
        TheGame game = new TheGame(board, p1, p2, ui);

        ui.getBoard().updateBoard(board);
        System.out.println(board.toString());
        game.play();
    }
}
