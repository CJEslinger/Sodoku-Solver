//Christopher Eslinger
public class SudokuSolver {
    public static void main(String[] args) {
        int[][] board1 = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}

        };

        if(solveSudoku(board1)){
            printBoard(board1);
        }
    }

    public static boolean solveSudoku(int[][] board) {
        int[] emptyCell = findEmptyCell(board);
        int row = emptyCell[0];
        int col = emptyCell[1];

        if (row == -1 && col == -1) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    private static int[] findEmptyCell(int[][] board) {
        int[] emptyCell = new int[2];
        boolean found = false;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    emptyCell[0] = i;
                    emptyCell[1] = j;
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        if (!found) {
            emptyCell[0] = -1;
            emptyCell[1] = -1;
        }

        return emptyCell;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
            if (board[i][col] == num) {
                return false;
            }
            if (board[(row/3)*3 + i/3][(col/3)*3 + i%3] == num) {
                return false;
            }
        }
        return true;
    }


    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}