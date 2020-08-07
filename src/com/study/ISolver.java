package com.study;

public abstract class ISolver {
    static int MAZE_X_SIZE;
    static int MAZE_Y_SIZE;
    static final int BLOCK = 0;
    static final int ROAD = 1;
    static final int START = 2;
    static final int END = 3;
    static final int[][] DIRECTIONS = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

    abstract Boolean solveMaze(int maze[][]);

    int[] findStartingPoint(int maze[][]) {
        int result[] = new int[2];
        for(int x = 0; x < MAZE_X_SIZE; x++) {
            for(int y = 0; y < MAZE_Y_SIZE; y++) {
                if(maze[x][y] == START) {
                    result[0] = x;
                    result[1] = y;
                    return result;
                }
            }
        }
        return null;
    }

    Boolean isValidLocation(int x, int y) {
        return (x >= 0 && x < MAZE_X_SIZE && y >= 0 && y < MAZE_Y_SIZE);
    }

    Boolean isVisited(int x, int y, int sol[][]) {
        return sol[x][y] == 1;
    }

    Boolean isRoad(int maze[][], int x, int y) {
        return maze[x][y] != BLOCK;
    }

    void printSolution(int sol[][]) {
        for (int i = 0; i < MAZE_X_SIZE; i++) {
            for (int j = 0; j < MAZE_Y_SIZE; j++) {
                System.out.print(" " + sol[i][j] + " ");
            }
            System.out.println();
        }
    }
}
