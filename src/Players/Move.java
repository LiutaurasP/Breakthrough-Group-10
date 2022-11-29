package Players;

/**
 * A class that is used to store a possible move.
 */
public class Move {
    int oldX;
    int oldY;
    int newX;
    int newY;

    /**
     * Constructor for the move class.
     * @param oldX From X.
     * @param oldY From Y.
     * @param newX To X.
     * @param newY To Y.
     */
    public Move(int oldX, int oldY, int newX, int newY){
        this.oldX=oldX;
        this.oldY=oldY;
        this.newX=newX;
        this.newY=newY;
    }

    /**
     * Constructor for when an empty move class might be needed.
     */
    public Move(){
    }

    /**
     * From X getter.
     * @return Returns from X.
     */
    public int getOldX() {
        return oldX;
    }

    /**
     * From Y getter.
     * @return Returns from Y.
     */
    public int getOldY() {
        return oldY;
    }

    /**
     * To X getter.
     * @return Returns to X.
     */
    public int getNewX() {
        return newX;
    }

    /**
     * To Y getter.
     * @return Returns to Y.
     */
    public int getNewY() {
        return newY;
    }


}
