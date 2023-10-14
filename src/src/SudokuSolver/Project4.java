package SudokuSolver;

public class Project4 {

    private static final int BOARD_SIZE = 9;
    private static final int EMPTY_CELL = 0;

    public boolean solveSudoku(int[][] board) {
        int row, col;
        int[] emptyCell = findEmptyCell(board);

        // If there is no empty cell, the Sudoku puzzle is solved
        if (emptyCell == null) {
            return true;
        }

        row = emptyCell[0];
        col = emptyCell[1];

        // Try placing numbers 1 to 9 in the current empty cell
        for (int num = 1; num <= BOARD_SIZE; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;

                // Recur to solve the rest of the Sudoku
                if (solveSudoku(board)) {
                    return true;
                }

                // If placing 'num' at (row, col) doesn't lead to a solution, backtrack
                board[row][col] = EMPTY_CELL;
            }
        }

        // No solution found, backtrack to previous empty cell
        return false;
    }

    private int[] findEmptyCell(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    return new int[]{row, col};
                }
            }
        }
        return null;  // No empty cell found
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        // Check if 'num' is not in the same row and column
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check if 'num' is not in the same 3x3 subgrid
        int subgridStartRow = row - row % 3;
        int subgridStartCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[subgridStartRow + i][subgridStartCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
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

        Project4 sudokuSolver = new Project4();

        System.out.println("Sudoku Puzzle:");
        sudokuSolver.printBoard(board);
        System.out.println();

        if (sudokuSolver.solveSudoku(board)) {
            System.out.println("Sudoku Solution:");
            sudokuSolver.printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
