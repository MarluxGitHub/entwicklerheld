<?php

declare(strict_types=1);

namespace entwicklerheld;

class QueensProblem
{
    static function isSafeRook(array $position, $rook): bool
    {
        // check pos
        foreach ($position as $enemy) {
            if (
                $enemy->rowIndex === $rook->rowIndex
                || $enemy->columnIndex === $rook->columnIndex
            ) {
                return false;
            }
        }

        return true;
    }

    static function isSafeQueen(array $positions, $queen): bool
    {
        // check pos
        foreach ($positions as $enemy) {
            if (
                $enemy->rowIndex === $queen->rowIndex
                || $enemy->columnIndex === $queen->columnIndex
                || $queen->leftDiagonal() === $enemy->leftDiagonal()
                || $queen->rightDiagonal() === $enemy->rightDiagonal()
            ) {
                return false;
            }
        }

        return true;
    }

    static function getQueensProblemSolution(int $boardSize): array
    {
        if ($boardSize < 4) {
            return [];
        }

        $positions = [];

        $p = new Position(0, 0);
        $positions[] = $p;

        $row = 0;

        while (count($positions) < $boardSize) {
            $row++;

            for ($column = 0; $column < $boardSize; $column++) {
                $p = new Position($row, $column);

                if (self::isSafeQueen($positions, $p)) {
                    $positions[] = $p;
                    break;
                }
            }
        }

        return $positions;
    }
}
