package de.craftnote.challenge.challenge.stage0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

class Example {

    static boolean[][] createExample() {
        int width = 10;
        int height = 10;

        boolean[][] plan = new boolean[width][height];
        boolean[][] visited = new boolean[width][height];

        // Initialize the plan with a random starting point
        Random random = new Random();
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        plan[startX][startY] = true;
        visited[startX][startY] = true;

        int count = 0;

        // Generate a path through the plan using a depth-first search
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startX, startY});
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];
            List<int[]> neighbors = getNeighbors(x, y, width, height);
            Collections.shuffle(neighbors, random);
            for (int[] neighbor : neighbors) {
                int nx = neighbor[0];
                int ny = neighbor[1];
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    stack.push(new int[]{nx, ny});
                    plan[nx][ny] = true;
                }
            }
            count++;
            if(count > 9) break;
        }

        return plan;
    }

    static List<int[]> getNeighbors(int x, int y, int width, int height) {
        List<int[]> neighbors = new ArrayList<>();
        if (x > 0) neighbors.add(new int[]{x - 1, y});
        if (x < width - 1) neighbors.add(new int[]{x + 1, y});
        if (y > 0) neighbors.add(new int[]{x, y - 1});
        if (y < height - 1) neighbors.add(new int[]{x, y + 1});
        return neighbors;
    }
}
