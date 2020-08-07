package com.study;

public class MazeProblem {
    static int[][] getMaze() {
        // 0 = block
        // 1 = road
        // 2 = starting point
        // 3 = destination

        int maze[][] = { { 1, 0, 0, 1, 0 },
                         { 2, 1, 0, 1, 0 },
                         { 0, 1, 0, 1, 3 },
                         { 1, 1, 0, 1, 0 },
                         { 1, 1, 1, 1, 1 } };

//        int maze[][] = { { 1, 1, 1, 1, 0 },
//                         { 2, 1, 1, 1, 1 },
//                         { 1, 1, 1, 1, 1 },
//                         { 1, 1, 1, 1, 1 },
//                         { 1, 1, 1, 1, 1 },
//                         { 1, 1, 3, 1, 1 } };
        return maze;
    }

    public static void main(String[] args) {
        ISolver is = new DfsSolver();
        int maze[][] = getMaze();
        is.solveMaze(maze);
    }
}
