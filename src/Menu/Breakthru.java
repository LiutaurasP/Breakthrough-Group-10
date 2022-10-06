package Menu;

import GUI.UI;
import GameLogic.PlayingBoard;
import GameLogic.Team;
import GameLogic.TheGame;
import Players.Human;
import Players.Player;

import java.util.Arrays;

public class Breakthru {
    private final static Object lock = new Object();
    public Breakthru(){
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
    public static void main(String[] args) {
        Breakthru breakthru = new Breakthru();
    }

}
