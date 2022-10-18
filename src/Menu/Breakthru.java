package Menu;

import GUI.UI;
import GameLogic.PlayingBoard;
import GameLogic.Team;
import GameLogic.TheGame;
import Players.Human;
import Players.Player;

import java.util.Arrays;

/**
 * Launcher class for the game. It instantiates: players, user interface, logical part of the game.
 */
public class Breakthru implements Runnable {
    private final static Object lock = new Object();

    /**
     * Constructor for launching the game. It creates players, user interface, game logic instances. It also starts the game.
     */
    public Breakthru(){
        Thread t1 =new Thread(this);
        t1.start();
    }

    /**
     * Main class that you need to run the game.
     * @param args main class args.
     */
    public static void main(String[] args) {
        Breakthru breakthru = new Breakthru();
    }

    @Override
    public void run() {
        PlayingBoard board = new PlayingBoard();
        UI ui = new UI(lock);
        Player gold = new Human(Team.g,lock,ui);
        Player silver = new Human(Team.s,lock,ui);
        System.out.println(board);
        TheGame game = new TheGame(board, silver, gold, ui);

        ui.getBoard().updateBoard(board);
        System.out.println(board.toString());
        game.play();
    }
}
