from sudokusolver.sudoku import Sudoku


class SudokuSolver(Sudoku):
    def __init__(self, grid):
        self.grid = grid

    def numbers_from_row(self, row):
        """This function returns a set of valid numbers for a given row"""
        row_values = set(self.grid[row])
        valid_numbers = set(range(1, 10)) - row_values
        return valid_numbers

    def numbers_from_column(self, column):
        """This function returns a set of valid numbers for a given column"""
            column_values = set(row[column] for row in self.grid)
            valid_numbers = set(range(1, 10)) - column_values
            return valid_numbers

    def numbers_from_box(self, row, column):
        """This function returns a set of valid numbers for a given box"""
        box_row = (row // 3) * 3
        box_column = (column // 3) * 3
        box_values = set(
            self.grid[i][j]
            for i in range(box_row, box_row + 3)
            for j in range(box_column, box_column + 3)
        )
        valid_numbers = set(range(1, 10)) - box_values
        return valid_numbers

    def possible_values(self, row, column):
        """This function returns a set of valid numbers for a cell in the grid"""
        row_values = set(self.grid[row])
        column_values = set(row[column] for row in self.grid)
        box_row = (row // 3) * 3
        box_column = (column // 3) * 3
        box_values = set(
            self.grid[i][j]
            for i in range(box_row, box_row + 3)
            for j in range(box_column, box_column + 3)
        )
        valid_numbers = set(range(1, 10)) - (row_values | column_values | box_values)
        return valid_numbers

    def solve(self):
        """This function solves the Sudoku puzzle"""
        for row in range(9):
            for column in range(9):
                if self.grid[row][column] == 0:
                    for value in self.possible_values(row, column):
                        self.grid[row][column] = value
                        if self.solve():
                            return self.grid
                        self.grid[row][column] = 0
                    return None
        return self.grid
