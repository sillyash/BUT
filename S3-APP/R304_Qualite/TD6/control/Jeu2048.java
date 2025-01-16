package control;

import entity.Engine;

public class Jeu2048 implements IControl
{
    public static final int GRID_SIZE = 4;
    public static final int INIT_CELLS = 2;
    private Engine engine;

    public Jeu2048 () {
        init();
    }

    @Override
    public void init() {
        this.engine = new Engine(GRID_SIZE, GRID_SIZE);

        for (int i=0; i<INIT_CELLS; i++) {
            this.engine.addNewCell();
        }
    }

    @Override
    public int[][] getGrid() {
        int[][] vals = new int[GRID_SIZE][GRID_SIZE];

        for (int i=0; i<GRID_SIZE; i++) {
            for (int j=0; j<GRID_SIZE; j++) {
                vals[i][j] = engine.getCell(i,j);
            }
        }

        return vals;
    }

    private void rotate() {
        int[][] mat = getGrid();
        int l = engine.getHeight();
        int c = engine.getWidth();

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                engine.setCell(j, l - 1 - i, mat[i][j]);
            }
        }
    }


    private void moveRight() {
        int[][] mat = getGrid();
        int l = engine.getHeight();
        int c = engine.getWidth();

        for (int i = 0; i < l; i++) {
            int index = c - 1; // Start from the rightmost column
            for (int j = c - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    engine.setCell(i, index, mat[i][j]); // Move the cell to the current index
                    if (j != index) {
                        engine.setCell(i, j, 0); // Clear the original cell
                    }
                    index--;
                }
            }
        }
    }

    private void fuseRight() {
        int[][] mat = getGrid();
        int l = engine.getHeight();
        int c = engine.getWidth();

        for (int i = 0; i < l; i++) {
            for (int j = c - 1; j > 0; j--) {
                if (mat[i][j] != 0 && mat[i][j] == mat[i][j - 1]) {
                    engine.setCell(i, j, mat[i][j] * 2); // Merge cells
                    engine.setCell(i, j - 1, 0); // Clear the merged cell
                }
            }
        }
    }

    @Override
    public void right() {
        moveRight();
        fuseRight();
        moveRight(); // Move again to ensure no gaps after fusing
        engine.addNewCell();
    }

    @Override
    public void down() {
        rotate(); // Rotate grid 270 degrees clockwise
        rotate();
        rotate();
        right();
        rotate(); // Rotate back to original orientation
    }

    @Override
    public void up() {
        rotate(); // Rotate grid 90 degrees clockwise
        right();
        rotate();
        rotate();
        rotate(); // Rotate back to original orientation
    }

    @Override
    public void left() {
        rotate(); // Rotate grid 180 degrees
        rotate();
        right();
        rotate();
        rotate(); // Rotate back to original orientation
    }


    @Override
    public boolean isOver() {
        if (!engine.getEmptyCells().isEmpty()) return false;

        int[][] grid = getGrid();
        int l = engine.getHeight();
        int c = engine.getWidth();

        for (int i=1; i<l-1; i++) {
            for (int j=1; j<c-1; j++)
            {
                int center = grid[i][j];
                int left = grid[i][j-1];
                int right = grid[i][j+1];

                int up = grid[i-1][j];
                int down = grid[i+1][j];

                int upLeft = grid[i-1][j-1];
                int upRight = grid[i-1][j+1];

                int downLeft = grid[i+1][j-1];
                int downRight = grid[i+1][j+1];

                if (center == left || center == right) return false;
                if (center == up || center == down) return false;

                if (upLeft == up || upLeft == left) return false;
                if (upRight == up || upRight == right) return false;

                if (downLeft == down || downLeft == left) return false;
                if (downRight == down || downRight == right) return false;
            }
        }

        return true;
    }

    @Override
    public int score() {
        int[][] grid = getGrid();
        int l = engine.getHeight();
        int c = engine.getWidth();
        int max = 0;

        for (int i=0; i<l; i++) {
            for (int j=0; j<c; j++) {
                if (grid[i][j] > max) max = grid[i][j];
            }
        }

        return max;
    }
}
