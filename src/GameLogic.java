public class GameLogic {

    public static boolean goldTurn = Launcher.goldTurn;

    public static void goldCapture(int initX, int initY, int x, int y) {

        if ((initX - x == 1 && (initY - y == 1 || initY - y == -1)) || (initX - x == -1 && (initY - y == 1 || initY - y == -1))) {
            if (goldTurn) {
                if (Launcher.playingBoard[initX][initY] == 2) {
                    if (Launcher.playingBoard[x][y] == 1) {     //TODO if Andra has time make method for this
                        Launcher.playingBoard[x][y] = 2;
                        Launcher.playingBoard[initX][initY] = 0;
                    }else{
                        System.out.println("Capturing is not possible");
                        return;
                    }
                }else{
                    System.out.println("Clicked piece is not gold");
                }

            }else{
                System.out.println("It is not gold's turn");
            }
        }
    }
}
