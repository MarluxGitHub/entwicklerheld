package java.walkingRobot;

import java.util.List;

public abstract class Grid {
    public int width;
    public int height;
    public int[][] grid;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<List<Integer>> getGrid() {
        return grid;
    }

    public int[] getRow(int row) {
        return grid[row];
    }

    public int getValue(int row, int column) {
        return grid[row][column];
    }

    public void setValue(int row, int column, int value) {
        grid[row][column] = value;
    }

    public abstract void setObstacle(int row, int column);
}
