import java.util.Scanner;

public class ConnectFour {
    public static int numRows = 6;
    public static int numCols = 7;
    public static int xWins = 0;
    public static int oWins = 0;
    public static String[][] board = new String[numRows][numCols];
    public static int[] counts = new int[numCols];

    public static void main (String[] args) {
        xWins = 0;
        oWins = 0;
        startGame();
    }

    public static void startGame() {
        counts = new int[numCols];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "_";
            }
        }

        String turn;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("*** Who goes first? Enter 'X' or 'O' ***");
            turn = sc.nextLine();
            if (turn.equals("X") || turn.equals("O")) {
                break;
            }
        }

        boolean gameOver = false;
        while (!gameOver) {
            printBoard();
            String col = "";
            while (true) {
                System.out.println("*** It is " + turn + "'s turn! Select a column ***");
                Scanner sc = new Scanner(System.in);
                col = sc.nextLine();
                if (verifyColInput(col)) {
                    break;
                }
            }

            int colNum = Integer.parseInt(col) - 1;
            if (counts[colNum] < numRows) {
                counts[colNum]++;
                board[numRows - counts[colNum]][colNum] = turn;
            }

            gameOver = checkGameOver();
            if (gameOver) {
                break;
            }

            if (turn.equals("X")) {
                turn = "O";
            } else {
                turn = "X";
            }

            System.out.println();
        }
        printBoard();
        System.out.println("*** Congratulations! " + turn + " is the winner! ***");
        if (turn == "X") {
            xWins++;
        } else {
            oWins++;
        }
        System.out.println("*** SCOREBOARD: X: " + xWins + " ***");
        System.out.println("*** SCOREBOARD: O: " + oWins + " ***");

        while (true) {
            System.out.println("*** Would you like to play again? Enter 'Y' or 'N' ***");
            Scanner sc = new Scanner(System.in);
            String again = sc.nextLine();
            if (again.equals("Y")) {
                startGame();
                break;
            } else if (again.equals("N")) {
                System.out.println("*** Thanks for playing ***");
                System.exit(0);
            }
        }
    }

    public static boolean checkGameOver() {
        for (int i = 0; i < board.length - 3; i++) { // negative diagonal
            for (int j = 0; j < board[i].length - 3; j++) {
                if (!board[i][j].equals("_")) {
                    if (board[i][j].equals(board[i + 1][j + 1]) && board[i][j].equals(board[i + 2][j + 2]) &&
                            board[i][j].equals(board[i + 3][j + 3])) { // row increase, column increase
                        return true;
                    }
                }
            }
        }

        for (int i = 3; i < board.length; i++) { // positive diagonal
            for (int j = 0; j < board[i].length - 3; j++) {
                if (!board[i][j].equals("_")) {
                    if (board[i][j].equals(board[i - 1][j + 1]) && board[i][j].equals(board[i - 2][j + 2]) &&
                            board[i][j].equals(board[i - 3][j + 3])) { // row decrease, column increase
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) { // vertical
            for (int j = 0; j < board[i].length - 3; j++) {
                if (!board[i][j].equals("_")) {
                    if (board[i][j].equals(board[i][j + 1]) && board[i][j].equals(board[i][j + 2]) &&
                            board[i][j].equals(board[i][j + 3])) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < board.length - 3; i++) { // horizontal
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].equals("_")) {
                    if (board[i][j].equals(board[i + 1][j]) && board[i][j].equals(board[i + 2][j]) &&
                            board[i][j].equals(board[i + 3][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean verifyColInput(String col) {
        return (col.equals("1") || col.equals("2") || col.equals("3") || col.equals("4") || col.equals("5") ||
                col.equals("6") || col.equals("7"));
    }

    public static void printBoard() {
        System.out.println("_________________");
        System.out.println("| 1 2 3 4 5 6 7 |");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("-----------------");
    }
}
