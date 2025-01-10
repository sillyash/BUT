package control;

import entity.Engine;

import java.awt.*;
import java.util.Random;

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

    @Override
    public void right() {

    }

    @Override
    public void down() {

    }

    @Override
    public void up() {

    }

    @Override
    public void left() {

    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public int score() {
        return 0;
    }
}
