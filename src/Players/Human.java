package Players;

import GUI.UI;
import GameLogic.Team;

import java.awt.*;
import java.util.ArrayList;

public class Human extends Player {
    private final Object lock;
    UI ui;
    public Human(Team team, Object lock, UI ui) {
        super(team);
        this.lock = lock;
        this.ui= ui;
    }

    @Override
    public Move getMove() {

        Move move = new Move();
        try {
            synchronized(lock) {
                lock.wait();
            }
        }
        catch (InterruptedException e){}
        move.oldX = ui.getBoard().click[0];
        move.oldY = ui.getBoard().click[1];
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
