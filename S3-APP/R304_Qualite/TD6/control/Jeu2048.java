import control.IControl;
import entity.Engine;

public class Jeu2048 implements IControl
{
    private Engine engine;

    public Jeu2048 (Engine e) {
        this.engine = e;
    }

    @Override
    public void init() {

    }

    @Override
    public int[][] getGrid() {
        return new int[0][];
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
