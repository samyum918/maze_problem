package com.study;

import java.util.LinkedList;
import java.util.List;

public class BfsSolver extends ISolver {
    public BfsSolver(int maze[][]) {
        super(maze);
    }

    public Boolean solveMaze() {
        if (!solveMazeCore()) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        printSolution(sol);
        return true;
    }

    public Boolean solveMazeCore() {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        nextToVisit.add(entryPoint);

        while(!nextToVisit.isEmpty()) {
            Coordinate currCoor = nextToVisit.remove();

            int x = currCoor.getX();
            int y = currCoor.getY();
            if(!isValidLocation(x, y) || !isRoad(x, y) || isVisited(x, y)) {
                continue;
            }

            visit[x][y] = 1;
            if (maze[x][y] == EXIT) {
                backtrackPath(currCoor);
                return true;
            }

            for(int[] dir : DIRECTIONS) {
                Coordinate nextCoor = new Coordinate(x + dir[0], y + dir[1], currCoor);
                nextToVisit.add(nextCoor);
            }
        }

        return false;
    }

    private void backtrackPath(Coordinate coor) {
        while (coor != null) {
            sol[coor.getX()][coor.getY()] = 1;
            coor = coor.getParent();
        }
    }
}
