package com.study;

public class DfsSolver extends ISolver {
    public DfsSolver(int maze[][]) {
        super(maze);
    }

    public void solveMaze() {
        if (!solveMazeCore(entryPoint.getX(), entryPoint.getY())) {
            System.out.print("Solution doesn't exist");
            return;
        }

        sol = visit;
        printSolution(sol);
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
