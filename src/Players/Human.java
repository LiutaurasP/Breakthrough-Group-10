package Players;
import GUI.UI;
import GameLogic.Team;
import java.awt.*;

/**
 * A class that is instantiated for a human player.
 */
public class Human extends Player {
    private final Object lock;
    UI ui;

    /**
     * Constructor for a human player.
     * @param team Which team does the human play for.
     * @param lock Thread lock that is used to wait for user input.
     * @param ui User Interface instance.
     */
    public Human(Team team, Object lock, UI ui) {
        super(team);
        this.lock = lock;
        this.ui= ui;
    }

    /**
     * A function that gets a move human player made using UI.
     * @return Returns a move a human made.
     */
    @Override
    public Move getMove() {
        Move move = new Move();

        while (true){
        try {
            synchronized(lock) {
                lock.wait();
            }
        }
        catch (InterruptedException e){}
        move.oldX = ui.getBoard().click[0];
        move.oldY = ui.getBoard().click[1];
        if (ui.getBoard().getTile(move.getOldX(),move.getOldY()).getSquare().getCurrentPiece()!=null && ui.getBoard().getTile(move.getOldX(),move.getOldY()).getSquare().getCurrentPiece().color.equals(this.team))break;
        }
        System.out.println(move.getOldX()+" "+ move.getOldY());
        ui.getBoard().getTile(move.getOldX(),move.getOldY()).setBackground(new Color(73, 0, 0));
       // if(ui.getBoard().getTile(move.oldX,move.oldY).getSquare().getCurrentPiece().color==this.team) return null;
        try {
            synchronized(lock) {
                lock.wait();
            }
        }
        catch (InterruptedException e){}
        move.newX = ui.getBoard().click[0];
        move.newY = ui.getBoard().click[1];
        ui.getBoard().getTile(move.getOldX(),move.getOldY()).setBackground(Color.BLACK);


        return move;
    }
}
