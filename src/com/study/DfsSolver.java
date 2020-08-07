package com.study;

public class DfsSolver extends ISolver {
    public DfsSolver(int maze[][]) {
        super(maze);
    }

    public Boolean solveMaze() {
        if (!solveMazeCore(entryPoint.getX(), entryPoint.getY())) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        sol = visit;
        printSolution(sol);
        return true;
    }

    Boolean solveMazeCore(int x, int y) {
        if(!isValidLocation(x, y) || !isRoad(x, y) || isVisited(x, y)) {
            return false;
        }

        visit[x][y] = 1;
        if (maze[x][y] == EXIT) {
            return true;
        }

        for(int[] dir : DIRECTIONS) {
            if(solveMazeCore(x + dir[0], y + dir[1]))
                return true;
        }

        visit[x][y] = 0;
        return false;
    }
}
