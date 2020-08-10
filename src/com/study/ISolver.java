package com.study;

public abstract class ISolver {
    static int MAZE_X_SIZE;
    static int MAZE_Y_SIZE;
    static final int BLOCK = 0;
    static final int ROAD = 1;
    static final int ENTRY = 2;
    static final int EXIT = 3;
    static final int[][] DIRECTIONS = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

    int maze[][];
    int visit[][];
    int sol[][];
    Coordinate entryPoint;

    public ISolver(int maze[][]) {
        this.maze = maze;
        MAZE_X_SIZE = maze.length;
        MAZE_Y_SIZE = maze[0].length;
        this.visit = new int[MAZE_X_SIZE][MAZE_Y_SIZE];
        this.sol = new int[MAZE_X_SIZE][MAZE_Y_SIZE];
        this.entryPoint = findCoor(ENTRY);
    }

    abstract void solveMaze();

    Coordinate findCoor(int target) {
        Coordinate result;
        for(int x = 0; x < MAZE_X_SIZE; x++) {
            for(int y = 0; y < MAZE_Y_SIZE; y++) {
                if(this.maze[x][y] == target) {
                    result = new Coordinate(x, y);
                    return result;
                }
            }
        }
        return null;
    }

    Boolean isValidLocation(int x, int y) {
        return (x >= 0 && x < MAZE_X_SIZE && y >= 0 && y < MAZE_Y_SIZE);
    }

    Boolean isVisited(int x, int y) {
        return this.visit[x][y] == 1;
    }

    Boolean isRoad(int x, int y) {
        return this.maze[x][y] != BLOCK;
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
