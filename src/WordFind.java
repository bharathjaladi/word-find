
public class WordFind {

    private char[][] board = new char[4][4];
    private boolean[][] default_or_no = new boolean[4][4];

    public WordFind(char[][] input) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = input[i][j];
                default_or_no[i][j] = true;
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public char getValue(int x, int y) {
        return board[x][y];
    }

    public boolean makeNotDefault(int x, int y) {
        if (default_or_no[x][y]) {
            default_or_no[x][y] = false;
            return true;
        }
        else {
            return false;
        }
    }

    public void makeDefault(int x, int y) {
        default_or_no[x][y] = true;
    }
}
