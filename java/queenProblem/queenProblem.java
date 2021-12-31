package de.entwicklerheld.queensProblemJava;

import java.util.List;
import java.util.ArrayList;

public class QueensProblem {
    public static boolean isSafeRook(List<Position> positions, Position rook) {
        // implement this in scenario 1
        for (Position enemy : positions) {
            if(
                enemy.rowIndex == rook.rowIndex
                || enemy.columnIndex == rook.columnIndex
            ) {
                return false;
            }
        }

        return true;
    }

    public static boolean isSafeQueen(List<Position> positions, Position queen) {
        for (Position enemy : positions) {
            if(
                enemy.rowIndex == queen.rowIndex
                || enemy.columnIndex == queen.columnIndex
                || enemy.leftDiagonal() == queen.leftDiagonal()
                || enemy.rightDiagonal() == queen.rightDiagonal()
            ) {
                return false;
            }
        }

        return true;
    }

    public static List<Position> getQueensProblemSolution(int boardSize) {
        List<Position> positions = new ArrayList<>();

        if(boardSize < 4) {
            return positions;
        }

        solveNQUtil(positions, 0, boardSize);

        return positions;
    }

    public static boolean solveNQUtil(List<Position> positions, int col, int boardsize)
    {
        /* base case: If all queens are placed
           then return true */
        if (col >= boardsize)
            return true;

        /* Consider this column and try placing
           this queen in all rows one by one */
        for (int i = 0; i < boardsize; i++) {
            /* Check if the queen can be placed on
               board[i][col] */
            Position p = new Position(i, col);
            if (isSafeQueen(positions, p)) {
                positions.add(p);

                /* recur to place rest of the queens */
                if (solveNQUtil(positions, col + 1, boardsize) == true)
                    return true;

                positions.remove(p);
            }
        }

        /* If the queen can not be placed in any row in
           this column col, then return false */
        return false;
    }
}
