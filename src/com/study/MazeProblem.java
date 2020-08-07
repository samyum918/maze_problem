package com.study;

public class MazeProblem {
    static int N;
    static final int BLOCK = 0;
    static final int ROAD = 1;
    static final int START = 2;
    static final int END = 3;

    int[][] getMaze() {
        // 0 = block
        // 1 = road
        // 2 = starting point
        // 3 = destination

        int maze[][] = { { 1, 0, 0, 1, 0 },
                         { 2, 1, 0, 1, 3 },
                         { 0, 1, 0, 1, 0 },
                         { 1, 1, 0, 1, 0 },
                         { 1, 1, 1, 1, 0 } };

//        int maze[][] = { { 1, 1, 1, 1, 0 },
//                         { 2, 1, 1, 1, 3 },
//                         { 1, 1, 1, 1, 1 },
//                         { 1, 1, 1, 1, 1 },
//                         { 1, 1, 1, 1, 1 } };
        return maze;
    }

    public static void main(String[] args) {
        MazeProblem mp = new MazeProblem();
        int maze[][] = mp.getMaze();
        N = maze.length;
        mp.solveMaze(maze);
    }

    Boolean solveMaze(int maze[][]) {
        int startX = 0;
        int startY = 0;
        int sol[][] = new int[N][N];

        int[] startingPoint = findStartingPoint(maze);
        if(startingPoint == null) {
            return false;
        }
        startX = startingPoint[0];
        startY = startingPoint[1];

        if (!solveMazeCore(maze, startX, startY, sol)) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        printSolution(sol);
        return true;
    }

    Boolean solveMazeCore(int maze[][], int x, int y, int sol[][]) {
        if(!withinMazeBoundary(x, y)) {
            return false;
        }

        if(isVisited(x, y, sol)) {
            return false;
        }

        if (maze[x][y] == END) {
            sol[x][y] = 1;
            return true;
        }

        if (isRoad(maze, x, y)) {
            sol[x][y] = 1;
            if (solveMazeCore(maze, x + 1, y, sol))
                return true;

            if (solveMazeCore(maze, x, y + 1, sol))
                return true;

            if (solveMazeCore(maze, x - 1, y, sol))
                return true;

            if (solveMazeCore(maze, x, y - 1, sol))
                return true;

            sol[x][y] = 0;
            return false;
        }
        return false;
    }

    int[] findStartingPoint(int maze[][]) {
        int result[] = new int[2];
        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                if(maze[x][y] == START) {
                    result[0] = x;
                    result[1] = y;
                    return result;
                }
            }
        }
        return null;
    }

    Boolean withinMazeBoundary(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    Boolean isVisited(int x, int y, int sol[][]) {
        return sol[x][y] == 1;
    }

    Boolean isRoad(int maze[][], int x, int y) {
        return maze[x][y] != BLOCK;
    }

    void printSolution(int sol[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + sol[i][j] + " ");
            }
            System.out.println();
        }
    }
}
