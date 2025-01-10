package control;

import entity.Engine;

import java.util.Random;

public class Jeu2048 implements IControl
{
    public static final int GRID_SIZE = 4;
    private Engine engine;

    public Jeu2048 () {
        init();
    }

    @Override
    public void init() {
        /*
        int[][] tab = new int[GRID_SIZE][GRID_SIZE];
        Random rand = new Random();

        for (int i=0; i<GRID_SIZE; i++) {
            for (int j=0; j<GRID_SIZE; j++) {
                rand.ints();
                tab[i][j] =
            }
        }
        */

        int[][] vals = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        this.engine = new Engine(vals);
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
