package Bots;

import GameLogic.Team;
import Players.Move;
import Players.Player;

/** questions:
 * 1. should the random bot be able to play against human players or just against itself?
 * 2. play against intelligent AI?
 **/
public class RandomBot extends Player {

    /**
     * Typical player constructor.
     *
     * @param team Which team does the player play for.
     */
    public RandomBot(Team team) {
        super(team);
    }

    @Override
    public Move getMove() {

        return null;
    }

    private Move randomMove(int oldX, int oldY) {
        int newX;
        int newY;



        Move rm = new Move();

        return rm;
    }


}
