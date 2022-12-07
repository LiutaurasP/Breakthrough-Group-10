package Players;
import GameLogic.PlayingBoard;
import GameLogic.Team;

/**
 * Abstract class used to set the expected behavior for the player (Human/AI).
 */
abstract public class Player {
    Team team;

    /**
     * Typical player constructor.
     * @param team Which team does the player play for.
     */
    public Player(Team team) {
        this.team = team;
    }

    /**
     * Typical function to get the next move from the player.
     * @return Returns a move that player wishes to play.
     */
    public abstract Move getHMove();
    public abstract PlayingBoard getAMove(PlayingBoard board);
    /**
     * Team color getter.
     * @return Returns team color.
     */
    public Team getTeam() {
        return this.team;
    }
}