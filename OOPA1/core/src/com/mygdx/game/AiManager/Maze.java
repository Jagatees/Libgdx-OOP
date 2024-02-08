package com.mygdx.game;

public class Maze {
    private final int[][] layout;

    public Maze() {
        layout = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
    }

    public boolean isWall(int x, int y) {
        return layout[y][x] == 1;
    }

    public int getWidth() {
        return layout[0].length;
    }

    public int getHeight() {
        return layout.length;
    }
}
