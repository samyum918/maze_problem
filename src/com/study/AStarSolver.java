package com.study;

import java.util.Arrays;
import java.util.PriorityQueue;

// Implemented based on the pseudocode in wiki
// https://en.wikipedia.org/wiki/A*_search_algorithm
public class AStarSolver extends ISolver {
    Coordinate exitPoint;
    static final int WEIGHT = 1;

    public AStarSolver(int maze[][]) {
        super(maze);
        this.exitPoint = findCoor(EXIT);
    }

    public void solveMaze() {
        if (!solveMazeCore()) {
            System.out.print("Solution doesn't exist");
            return;
        }

        printSolution(sol);
    }

    public Boolean solveMazeCore() {
        int[][] gScore = new int[MAZE_X_SIZE][MAZE_Y_SIZE];
        for(int[] gScoreRow : gScore) {
            Arrays.fill(gScoreRow, 999);
        }

        int[][] fScore = new int[MAZE_X_SIZE][MAZE_Y_SIZE];
        for(int[] fScoreRow : fScore) {
            Arrays.fill(fScoreRow, 999);
        }

        gScore[entryPoint.getX()][entryPoint.getY()] = 0;
        fScore[entryPoint.getX()][entryPoint.getY()] = h(entryPoint);

        PriorityQueue<Coordinate> openList = new PriorityQueue<>((coor1, coor2) -> fScore[coor1.getX()][coor2.getY()] - fScore[coor2.getX()][coor2.getY()]);
        openList.add(entryPoint);

        while(!openList.isEmpty()) {
            Coordinate current = openList.poll();
            int x = current.getX();
            int y = current.getY();
            if(current.equals(exitPoint)) {
                backtrackPath(current);
                return true;
            }

            for(int[] dir : DIRECTIONS) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];
                if(!isValidLocation(nextX, nextY) || !isRoad(nextX, nextY)) {
                    continue;
                }

                Coordinate nextCoor = new Coordinate(nextX, nextY, current);
                int tentative_gScore = gScore[x][y] + d(current, nextCoor);
                if(tentative_gScore < gScore[nextX][nextY]) {
                    gScore[nextX][nextY] = tentative_gScore;
                    fScore[nextX][nextY] = gScore[nextX][nextY] + h(nextCoor);
                    if(!openList.contains(nextCoor)) {
                        openList.offer(nextCoor);
                    }
                }
            }
        }

        return false;
    }

    private int d(Coordinate curr, Coordinate nextCoor) {
        // In weighted graph, weight should be retrieved from edge
        return WEIGHT;
    }

    private int h(Coordinate coor) {
        //Manhattan distance
        return Math.abs(exitPoint.getX() - coor.getX()) + Math.abs(exitPoint.getY() - coor.getY());
    }

    private void backtrackPath(Coordinate coor) {
        while (coor != null) {
            sol[coor.getX()][coor.getY()] = 1;
            coor = coor.getParent();
        }
    }
}
