import GUI.UI;
import GameLogic.PlayingBoard;
import GameLogic.Team;
import GameLogic.TheGame;
import Players.Human;
import Players.Player;

import java.util.Arrays;

public class Breakthru {
    public static void main(String[] args) {
        PlayingBoard board = new PlayingBoard();
        Player silver = new Human(Team.s);
        Player gold = new Human(Team.s);
        UI ui = new UI();
        System.out.println(board);
        TheGame game = new TheGame(board, silver, gold, ui);
        ui.getBoard().updateBoard(board);
        System.out.println(game.getAllPossibleMoves(board.getPiece(5,1)));
    }

}
