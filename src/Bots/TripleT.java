package Bots;

import java.util.ArrayList;
import java.util.Arrays;

import GameLogic.PlayingBoard;
import GameLogic.Square;
import GameLogic.Team;
import Pieces.Flag;
import Pieces.RegularPiece;
import Players.Move;
import Players.Player;




public class TripleT extends Player{

    final int DIMENSION = 3; // NxN tic tac toe setup //TODO change for breakthru
    static int alphaCount = 0;
    static int minicount = 0;
    int iterator = 0;

    int state = 3; // 0 for draw, 1 for x win, 2 for o win, 3 for going
    // 0 is empty, 1 is x and 2 is 0
    //game loop
    public int[][] board;
    public boolean goldTurn;

    public TripleT(Team team) {
        super(team);
        if(team == Team.g){
            this.goldTurn = true;
        }else{
            this.goldTurn = false;
        }
    }


    public void run() throws InterruptedException {
        //UI ui = new UI(30, 30, 30); //insta gui
        int[][] board = new int[DIMENSION][DIMENSION];
        boolean xTurn = true;

        while(true) {
            // check draw condition
            if (draw(board)) {System.out.println("DRAW"); break;} // check draw

            if(!xTurn){
                board = findAlphaBeta(board, xTurn);
            }
            else{

                //MctsTicTacToePlayer player = new MctsTicTacToePlayer(5000);
                //Board board1 = new Board(board, xTurn);
                //board = player.getMove(board1);
                // board = findAlphaBeta(board, xTurn);
            }

            //toggle xTurn
            xTurn = toggleBoolean(xTurn);

            // wait x ms
            //sleep(1000); 

            // repaint gui frame
            //ui.setState(board); 

            //check if game over by win
            if(checkWin(board)){break;}
        }
    }

    public int evaluation(int[][] board) {
        int numGoldPieces = 0;
        int numSilverPieces = 0;
        int flagX = 0;
        int flagY = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 1){
                    numSilverPieces++;
                }else if(board[i][j] == 2){
                    numGoldPieces++;
                }else if(board[i][j] == 3){
                    numGoldPieces++;
                    flagX = j;
                    flagY = i;
                }
            }
        }

        if(flagX == 0 && flagY == 0){
            return -999999;
        }

        int pieceDiff = numGoldPieces - numSilverPieces;
        int flagSafety = 0;

        if(board[flagY-1][flagX] == 2){
            flagSafety += 1;
        }
        if(board[flagY-1][flagX-1] == 2){
            flagSafety += 1;
        }
        if(board[flagY][flagX-1] == 2){
            flagSafety += 1;
        }
        if(board[flagY+1][flagX-1] == 2){
            flagSafety += 1;
        }
        if(board[flagY+1][flagX] == 2){
            flagSafety += 1;
        }
        if(board[flagY+1][flagX+1] == 2){
            flagSafety += 1;
        }
        if(board[flagY][flagX+1] == 2){
            flagSafety += 1;
        }
        if(board[flagY-1][flagX+1] == 2){
            flagSafety += 1;
        }



        if(board[flagY-1][flagX] == 1){
            flagSafety -= 1;
        }
        if(board[flagY-1][flagX-1] == 1){
            flagSafety -= 10;
        }
        if(board[flagY][flagX-1] == 1){
            flagSafety -= 1;
        }
        if(board[flagY+1][flagX-1] == 1){
            flagSafety -= 10;
        }
        if(board[flagY+1][flagX] == 1){
            flagSafety -= 1;
        }
        if(board[flagY+1][flagX+1] == 1){
            flagSafety -= 10;
        }
        if(board[flagY][flagX+1] == 1){
            flagSafety -= 1;
        }
        if(board[flagY-1][flagX+1] == 1){
            flagSafety -= 10;
        }

        return pieceDiff + flagSafety;

    }
    public int alphaBeta(int[][] board, boolean xTurn, int alpha, int beta, int depth, int maxDepth){
        alphaCount++;
        if(gameOver(board)){return gameOverValue(board);}
        if (depth >= maxDepth) { return evaluation(board);}
        int bestVal;
        if(xTurn){
            bestVal = -1000;
            Team team = Team.g;
            if(!goldTurn){
                team = Team.s;
            }
            Square[][] arr = intToSquare(board);
            ArrayList<int[][]> aList = getAllPossibleBoards(arr, team);
            int value;
            for (int[][] move : aList) {
                value = alphaBeta(move, false, alpha, beta, depth + 1, maxDepth);
                bestVal = Math.max(value, bestVal);
                alpha = Math.max(alpha, bestVal);
                if (beta <= alpha) {break;}
            }
        }
        else{
            bestVal = 1000;
            Team team = Team.g;
            if(!goldTurn){
                team = Team.s;
            }
            Square[][] arr = intToSquare(board);
            ArrayList<int[][]> aList = getAllPossibleBoards(arr, team);
            int value;
            for (int[][] move : aList) {
                value = alphaBeta(move, true, alpha, beta, depth + 1, maxDepth);
                bestVal = Math.min(value, bestVal);
                beta = Math.min(beta, value);
                if (beta <= alpha) {break;}
            }
        }
        return bestVal;
    }

    public boolean gameOver(int[][] board){
        if(silverWin(board) || goldWin(board)) {return true;}
        return false;
    }

    public int[][] findAlphaBeta(int[][] board, boolean xTurn){
        int[][] bestBoard = new int[DIMENSION][DIMENSION];
        int bestBoardEval;
        if (xTurn){
            bestBoardEval = -1000;
        }
        else{
            bestBoardEval = 1000;
        }
        Square[][] arr = intToSquare(board);
        Team team = Team.g;
        if(!goldTurn){
            team = Team.s;
        }
        ArrayList<int[][]> moveList = getAllPossibleBoards(arr, team);
        int boardEval;
        for (int[][] move : moveList) {
            boardEval = alphaBeta(move, !xTurn,-1000, 1000, 0, 1);
            if (betterThan(boardEval, bestBoardEval, xTurn)) {
                bestBoardEval = boardEval;
                bestBoard = copy2DArray(move);
            }
        }
        return bestBoard;
    }


    public boolean betterThan(int boardEval, int bestBoardEval, boolean xTurn){
        if(xTurn){
            return boardEval > bestBoardEval;
        }
        else{
            return boardEval < bestBoardEval;
        }
    }








































    public void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        return;
    }

    public boolean checkWin(int[][] board){
        if (silverWin(board)){System.out.println("silver WINS"); return true;}
        if (goldWin(board)){System.out.println("gold WINS"); return true;}
        return false;
    }

    public boolean goldWin(int[][] board){
        boolean win = false;
        for (int i = 0; i < board.length; i++) {
            if(board[i][0] == 3){
                win = true;
            }else if(board[0][i] == 3){
                win = true;
            }else if(board[board.length-1][i] == 3){
                win = true;
            }else if(board[i][board.length-1] == 3){
                win = true;
            }
        }
        return win;
    }

    public boolean silverWin(int[][] board){
        boolean win = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == 3){
                    win = false;
                }
            }
        }
        return win;
    }

    public int[][] randomBot(int[][] board, boolean xTurn) {
        long milis = System.currentTimeMillis();

        iterator++;
        //System.out.println(iterator);
        //printArray(board);
        Team team = Team.s;
        if(xTurn){
            team = Team.g;
        }

        ArrayList<int[][]> moveList = getAllPossibleBoards(intToSquare(board), team);
        //System.out.println(moveList.size());
        long current = System.currentTimeMillis();
        System.out.println(current-milis);
        return moveList.get((int) (Math.random() * moveList.size()));
    }


    public int[][] copy2DArray(int[][] array){
        int[][] newArray = new int[array.length][array.length];
        for (int y = 0; y < newArray.length; y++) {
            for (int x = 0; x < newArray.length; x++) {
                newArray[y][x] = array[y][x];
            }
        }
        return newArray;

    }

    /*
     * Method for toggling a boolean variable
    */
    public Boolean toggleBoolean(Boolean b) {
        if (b == false){
            return true;
        }
        return false;
    }

    public int gameOverValue(int[][] board){
        int depth = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    depth++;
                }
            }
        }
        if (goldWin(board)){return 10 - depth;}
        if (silverWin(board)){return -10 + depth;}
        if (draw(board)){return 0;}
        System.out.println("GAME OVERsss ERROR");
        sleep(10000000);
        return 5;
    }

    public boolean draw(int[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == 0){return false;}
            }
        }
        return true;
    }

    public  void printArray(int array[][]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
               System.out.print(array[i][j] + " ");
            }
            System.out.println("\n");
        }
        System.out.println("---------------------------");
    }

    public static Square[][] intToSquare(int[][] board){
        Square[][] squares = new Square[board.length][board[0].length];

        for (int y = 0; y < squares.length; y++) {
            for (int x = 0; x < squares[y].length; x++) {

                if(board[y][x] == 1){
                    Square sq = new Square(x, y);
                    sq.setCurrentPiece(new RegularPiece(Team.s, x, y));
                    squares[y][x] = sq;

                }else if(board[y][x] == 2){
                    Square sq = new Square(x, y);
                    sq.setCurrentPiece(new RegularPiece(Team.g, x, y));
                    squares[y][x] = sq;

                }else if(board[y][x] == 3){
                    Square sq = new Square(x, y);
                    sq.setCurrentPiece(new Flag(Team.g, x, y));
                    squares[y][x] = sq;

                }else{
                    Square sq = new Square(x, y);
                    sq.setCurrentPiece(null);
                    squares[y][x] = sq;
                }
            }
        }
        return squares;
    }


    public static int[][] squareToArray(Square[][] array){

        int[][] board = new int[array.length][array[0].length];

        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                if(array[y][x].toString() == "f") {
                    board[y][x] = 3;
                }
                else if(array[y][x].toString() == "g") {
                    board[y][x] = 2;
                }
                else if(array[y][x].toString() == "s") {
                    board[y][x] = 1;
                }
                else if(array[y][x].toString() == "-"){
                    board[y][x] = 0;
                }
            }
        }

        return board;
    }

    /*
     * Method for getting all possible capture moves for a given piece
    */

    public ArrayList<int[][]> getCaptures(int x, int y, int[][] board, int player){
        ArrayList<int[][]> moveList = new ArrayList<int[][]>();
        int[][] copy = copy2DArray(board);
        boolean goldPlayer = true;
        ArrayList<Integer> capturables = new ArrayList<Integer>();

        if(player == 1){
            goldPlayer = false;
        }

        if(goldPlayer){
            capturables.add(1);
        }else{
            capturables.add(2);
            capturables.add(3);
        }

        if(x + 1 < board.length && y + 1 < board.length && capturables.contains(board[y+1][x+1])){
            copy[y+1][x+1] = player;
            copy[y][x] = 0;
            moveList.add(copy);
            copy = copy2DArray(board);
        }

        if(x + 1 < board.length && y - 1 >= 0 && capturables.contains(board[y-1][x+1])){
            copy[y-1][x+1] = player;
            copy[y][x] = 0;
            moveList.add(copy);
            copy = copy2DArray(board);
        }
        if(x - 1 >= 0 && y - 1 >= 0 && capturables.contains(board[y-1][x-1])){
            copy[y-1][x-1] = player;
            copy[y][x] = 0;
            moveList.add(copy);
            copy = copy2DArray(board);
        }
        if(x - 1 >= 0 && y + 1 < board.length && capturables.contains(board[y+1][x-1])){
            copy[y+1][x-1] = player;
            copy[y][x] = 0;
            moveList.add(copy);
            copy = copy2DArray(board);
        }
        return moveList;
    }

    /*
     * Method for getting all possible moves for a given piece
    */
    public ArrayList<int[][]> getMoves(int x, int y, int[][] board, int player){
        ArrayList<int[][]> moveList = new ArrayList<int[][]>();
        int[][] copy = copy2DArray(board);
        int originalY = y;
        int originalX = x;

            // going right

            while(x + 1 < board.length && board[y][x+1] == 0){
                copy[y][x+1] = player;
                copy[originalY][originalX] = 0;
                moveList.add(copy);
                copy = copy2DArray(board);
                x++;
            }
            y = originalY;
            x = originalX;
            // going left
            while(x - 1 >= 0 && board[y][x-1] == 0){
                copy[y][x-1] = player;
                copy[originalY][originalX] = 0;
                moveList.add(copy);
                copy = copy2DArray(board);
                x--;
            }
            y = originalY;
            x = originalX;
            // going down
            while(y + 1 < board.length && board[y+1][x] == 0){
                copy[y+1][x] = player;
                copy[originalY][originalX] = 0;
                moveList.add(copy);
                copy = copy2DArray(board);
                y++;
            }
            x = originalX;
            y = originalY;
            // goin' up
            while(y - 1 >= 0 && board[y-1][x] == 0){
                copy[y-1][x] = player;
                copy[originalY][originalX] = 0;
                moveList.add(copy);
                copy = copy2DArray(board);
                y--;
            }



         return moveList;

    }

    ArrayList<int[][]> getAllPossibleBoards(Square[][] arr, Team team){

        ArrayList<int[][]> silverCaptureBoards = new ArrayList<int[][]>();
        ArrayList<int[][]> goldCaptureBoards = new ArrayList<int[][]>();

        ArrayList <int[][]> flagBoards = new ArrayList<>();


        ArrayList<int[][]> silverFinalBoards = new ArrayList<int[][]>();
        ArrayList<int[][]> goldFinalBoards = new ArrayList<int[][]>();



        int[][] gameBoard = squareToArray(arr);
       // int[][] copyBoard = copy2DArray(gameBoard);

        int player = 1;

        if(team == Team.g){
            player = 2;
        }


        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[y].length; x++) {
              //  System.out.println(x +" "+ y);
                if(gameBoard[y][x] == 3 && player == 2){
                    ArrayList<int[][]> captures = getCaptures(x, y, gameBoard, 3);
                    ArrayList<int[][]> moves = getMoves(x, y, gameBoard, 3);
                    flagBoards.addAll(captures);
                    flagBoards.addAll(moves);
                }

                else if(gameBoard[y][x] == 2 && player == 2){
                    ArrayList<int[][]> captures = getCaptures(x, y, gameBoard, player);
                    ArrayList <int[][]> moves = getMoves(x, y, gameBoard, 2);
                    goldCaptureBoards.addAll(captures);
                    for(int[][] move : moves){
                        int[][] secondCopy= copy2DArray(move);
                        for (int yd = 0; yd < move.length; yd++) {
                            for (int xd = 0; xd < move.length; xd++) {
                                if(secondCopy[yd][xd] == 2){
                                    ArrayList<int[][]> seconds = getMoves(xd, yd, secondCopy, 2);
                                    goldFinalBoards.addAll(seconds);
                                }
                            }
                        }
                    }
                }

                else if(gameBoard[y][x] == 1 && player == 1){
                    ArrayList<int[][]> captures = getCaptures(x, y, gameBoard, player);
                    ArrayList<int[][]> moves = getMoves(x, y, gameBoard, 1);
                    silverCaptureBoards.addAll(captures);
                    for(int[][] move : moves){
                       int[][] secondCopy= copy2DArray(move);
                        for (int yd = 0; yd < move.length; yd++) {
                            for (int xd = 0; xd < move.length; xd++) {
                                if(secondCopy[yd][xd] == 1){
                                    ArrayList<int[][]> seconds = getMoves(xd, yd, secondCopy, 1);
                                    silverFinalBoards.addAll(seconds);
                                }
                            }
                        }
                    }
                }

            }
        }


        ArrayList<int[][]> onGodFinals = new ArrayList<int[][]>();



        //move ordering
        onGodFinals.addAll(silverCaptureBoards);
        onGodFinals.addAll(flagBoards);
        onGodFinals.addAll(goldCaptureBoards);
        onGodFinals.addAll(goldFinalBoards);
        onGodFinals.addAll(silverFinalBoards);
//        for (int[][] arra:
//             goldFinalBoards) {
//            int count = 0;
//            for (int i = 0; i < arra.length; i++) {
//                for (int j = 0; j < arra.length; j++) {
//                    if(arra[i][j]!=0)count++;
//                }
//            }
//            if(count>33) printArray(arra);
//        }
        return onGodFinals;
    }

    private int[][] squaresToInts(Square[][] arr) {
        int[][] array = new int[arr.length][arr[0].length];
        for (int y = 0; y < arr.length ; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                if(arr[y][x].getCurrentPiece()!=null){
                    String piece = arr[y][x].getCurrentPiece().toString();
                    if(piece.equals("f")){
                        array[y][x]=3;
                    } else if (piece.equals("g")) {
                        array[y][x]=2;
                    } else if (piece.equals("s")) {
                        array[y][x]=1;
                    }
                }
            }
        }
        return array;
    }

    public static void main(String[] args) throws InterruptedException {
        TripleT tripleT = new TripleT(Team.g);
        for (int index = 0; index < 100; index++) {
            tripleT.run();
        }



    }
    @Override
    public Move getHMove() {
        return null;
    }
    @Override
    public PlayingBoard getAMove(PlayingBoard board) {

        Square[][] arr = board.getBoard();
        int[][] gameBoard = squareToArray(arr);

        int[][] bestBoard = findAlphaBeta(gameBoard, goldTurn);
        Square[][] bestSquareBoard = intToSquare(bestBoard);

        PlayingBoard bestPlayingBoard = new PlayingBoard(bestSquareBoard);

        return bestPlayingBoard;
    }

}
    
    

