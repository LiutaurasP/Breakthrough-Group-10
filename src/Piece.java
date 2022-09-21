public class Piece {
    private int x;
    private int y;
    private int id; // 1 - silver | 2 - gold | 3 - flag

    public Piece(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public void setPositionX(int x) {
        this.x = x;
    }

    public void setPositionY(int y) {
        this.y = y;
    }

    public void setId(int id) {
        this.id = id;
    }
}