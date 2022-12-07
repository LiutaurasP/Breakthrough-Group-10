package Players;

import GameLogic.PlayingBoard;
import GameLogic.Team;

/**
 * A class that is instantiated for an AI player.
 */
public class AI extends Player{
    /**
     * Constructor for AI player.
     * @param team Which team does the AI play for.
     */
    public AI(Team team) {
        super(team);
    }

    /**
     * A function that gets a move AI player made.
     * @return
     */
    @Override
    public Move getHMove() {
        return null;
    }
    public PlayingBoard getAMove(PlayingBoard board) {
        return null;
    }
}
