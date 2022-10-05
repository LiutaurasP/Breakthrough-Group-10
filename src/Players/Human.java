package Players;

import GUI.UI;
import GameLogic.Team;

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
        try {
            synchronized(lock) {
                lock.wait();
            }
        }
        catch (InterruptedException e){}
        move.newX = ui.getBoard().click[0];
        move.newY = ui.getBoard().click[1];


     return move;
    }
}
