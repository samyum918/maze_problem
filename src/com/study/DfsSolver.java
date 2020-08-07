package com.study;

public class DfsSolver extends ISolver {
    public Boolean solveMaze(int maze[][]) {
        MAZE_X_SIZE = maze.length;
        MAZE_Y_SIZE = maze[0].length;

        int startX = 0;
        int startY = 0;
        int sol[][] = new int[MAZE_X_SIZE][MAZE_Y_SIZE];

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
        if(!isValidLocation(x, y) || !isRoad(maze, x, y) || isVisited(x, y, sol)) {
            return false;
        }

        if (maze[x][y] == END) {
            sol[x][y] = 1;
            return true;
        }

        sol[x][y] = 1;
        for(int[] dir : DIRECTIONS) {
            if(solveMazeCore(maze, x + dir[0], y + dir[1], sol))
                return true;
        }

        sol[x][y] = 0;
        return false;
    }
}
