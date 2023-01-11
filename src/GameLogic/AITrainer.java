package GameLogic;


import Players.Player;


import java.util.ArrayList;

public class AITrainer {
    public ArrayList<Player> players = new ArrayList<>();
    PlayingBoard board;
    public AITrainer(PlayingBoard board, Player gold, Player silver) {
        this.board=board;
        players.add(gold);
        players.add(silver);
        play();
    }

    /**
     * The main function of the game that contains the game loop.
     */
    public void play() {
        Boolean silverWon = false;
        gameloop:
        while (true) {
            for (Player player : players) {
                    PlayingBoard aiMove = player.getAMove(board);
                    board = new PlayingBoard(aiMove.getBoard());
                    if(isFlagAtBorder(board.getBoard())) break gameloop;
                    if(checkForSilverWin(board.getBoard())){
                        silverWon=true;
                        break gameloop;
                    }
            }
        }
        if (silverWon) {
            System.out.println("Silver Won!");
        }
        else {
            System.out.println("Gold Won!");
        }
        System.out.println("Final board: ");
        System.out.println(board.toString());
    }

    private boolean checkForSilverWin(Square[][] arr) {
        for (int i = 0; i < board.SIZE_OF_BOARD; i++) {
            for (int j = 0; j < board.SIZE_OF_BOARD; j++) {
                if(arr[i][j].getCurrentPiece()!= null && arr[i][j].getCurrentPiece().toString().equals("f")) return false;
            }
        }
        return true;
    }

    public boolean isFlagAtBorder(Square[][] arr) {
        int sizeBoard = board.SIZE_OF_BOARD;
        for (int i = 0; i < sizeBoard; i++) {
            // flag piece touches:
            // bottom outer border
            if(arr[sizeBoard-1][i].getCurrentPiece()!=null && arr[sizeBoard-1][i].getCurrentPiece().toString().equals("f"))
                return true;

            // right outer border
            if(arr[i][sizeBoard-1].getCurrentPiece()!=null && arr[i][sizeBoard-1].getCurrentPiece().toString().equals("f"))
                return true;

            // top outer border
            if(arr[0][i].getCurrentPiece()!=null && arr[0][i].getCurrentPiece().toString().equals("f"))
                return true;

            // left outer border
            if(arr[i][0].getCurrentPiece()!=null && arr[i][0].getCurrentPiece().toString().equals("f"))
                return true;
        }
        return false;
    }
}
