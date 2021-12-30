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

        int r = 0;
        Position queen = new Position(r, 0);
        positions.add(queen);

        while(
            positions.size() < boardSize
        ) {
            for (int rowIndex = 0; rowIndex < boardSize; rowIndex++) {
                for (int columnIndex = 0; columnIndex < boardSize; columnIndex++) {

                    queen = new Position(rowIndex, columnIndex);
                    if(isSafeQueen(positions, queen)) {
                        positions.add(queen);
                        break;
                    }
                }
            }

            if(positions.size() == boardSize) {
                break;
            }
            else {
                positions.clear();
                r++;
                if(r > boardSize) {
                    break;
                }
                queen = new Position(r, 0);
                positions.add(queen);

            }
        }

        return positions;
    }
}
