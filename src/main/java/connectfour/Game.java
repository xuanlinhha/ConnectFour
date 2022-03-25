package connectfour;

public class Game {
    private static final Integer HEIGHT = 6;
    private static final Integer WIDTH = 7;
    private char[][] matrix;
    public static final Integer MAX_TURN = 42;
    private int lastInsertedRow = -1;
    private int lastInsertedCol = -1;
    char nextPlayer = 'R';

    public Game(char first) {
        this.nextPlayer = first;
        matrix = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j=0; j< WIDTH; ++j) {
                matrix[i][j] = ' ';
            }
        }
    }

    public boolean canInsert(Integer col) {
        return (matrix[0][col-1] == ' ');
    }

    public void insert(Integer col, char color) {
        for (int i = HEIGHT - 1; i >= 0 ; i--) {
            if (matrix[i][col-1] == ' ') {
                matrix[i][col-1] = color;
                lastInsertedRow = i;
                lastInsertedCol = col-1;
                break;
            }
        }
        switchTurn();
    }

    public String horizontal() {
        return new String(matrix[lastInsertedRow]);
    }

    public String vertical() {
        StringBuilder sb = new StringBuilder(HEIGHT);
        for (int row = 0; row < HEIGHT; row++) {
            sb.append(matrix[row][lastInsertedCol]);
        }
        return sb.toString();
    }

    public String diagonal1() {
        StringBuilder sb = new StringBuilder(HEIGHT);
        for (int h = 0; h < HEIGHT; h++) {
            int w = lastInsertedCol + lastInsertedRow - h;
            if (0 <= w && w < WIDTH) {
                sb.append(matrix[h][w]);
//                System.out.println("(" + h + "," + w + ")");
            }
        }
        return sb.toString();
    }

    public String diagonal2() {
        StringBuilder sb = new StringBuilder(HEIGHT);
        for (int h = 0; h < HEIGHT; h++) {
            int w = lastInsertedCol - lastInsertedRow + h;
            if (0 <= w && w < WIDTH) {
                sb.append(matrix[h][w]);
//                System.out.println("(" + h + "," + w + ")");
            }
        }
        return sb.toString();
    }

    public boolean isWin() {
        char color = matrix[lastInsertedRow][lastInsertedCol];
        String pattern = String.format("%c%c%c%c", color, color, color, color);
        return horizontal().contains(pattern) ||
                vertical().contains(pattern) ||
                diagonal1().contains(pattern) ||
                diagonal2().contains(pattern);
    }

    public void display() {
        for (int i = 0; i < HEIGHT; ++i) {
            System.out.print("|");
            for (int j = 0; j < WIDTH; ++j) {
                System.out.print(matrix[i][j] + "|");
            }
            System.out.println("");
        }
        System.out.println(" 1 2 3 4 5 6 7");
    }

    public void printNextTurn() {
        if (nextPlayer == 'R') {
            System.out.print("Player 1 [RED] - choose column (1-7): ");
        } else {
            System.out.print("Player 2 [GREEN] - choose column (1-7): ");
        }
    }

    public void switchTurn() {
        if(nextPlayer == 'R') {
            nextPlayer = 'G';
        } else if(nextPlayer == 'G') {
            nextPlayer = 'R';
        }
    }

    public void displayWin() {
        display();
        if(nextPlayer == 'G') {
            System.out.println("Player 1 [RED] wins!");
        } else {
            System.out.println("Player 2 [GREEN] wins!");
        }
    }

    public char getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(char nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public int getLastInsertedRow() {
        return lastInsertedRow;
    }

    public void setLastInsertedRow(int lastInsertedRow) {
        this.lastInsertedRow = lastInsertedRow;
    }

    public int getLastInsertedCol() {
        return lastInsertedCol;
    }

    public void setLastInsertedCol(int lastInsertedCol) {
        this.lastInsertedCol = lastInsertedCol;
    }
}
