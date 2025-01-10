package entity;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Engine
{
	public static final double PROBABILITY_TWO = 0.8;
	public static final double PROBABILITY_FOUR = 0.2;
	private Dimension dimension ;
	private Cell grid[][];
	
	public Engine(int l, int c) {
		this.dimension = new Dimension(l,c);
		this.grid = new Cell[l][c];
		
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				this.grid[i][j] = new Cell(0);
			}
		}
	}

	public Engine(int[][] tab) {
		int l = tab.length;
		int c = tab[0].length;

		this.dimension = new Dimension(l, c);
		this.grid = new Cell[l][c];

		for (int i=0; i<l; i++) {
			for (int j=0; j<c; j++) {
				System.out.println(tab[i][j]);
				this.grid[i][j] = new Cell(tab[i][j]);
			}
		}
	}
	
	public int getWidth() {
		return (int) this.dimension.getWidth();
	}
	
	public int getHeight() {
		return (int) this.dimension.getHeight();
	}
	
	public int getCell(int i, int j) {
		return this.grid[i][j].getContent();
	}
	
	public void setCell(int i, int j, int v) {
		this.grid[i][j].setContent(v);
	}

	public ArrayList<Cell> getEmptyCells() {
		ArrayList<Cell> arr = new ArrayList<>();

		for (int i=0; i<getHeight(); i++) {
			for (int j=0; j<getWidth(); j++) {
				Cell c = grid[i][j];
				if (c.getContent() == 0) arr.add(c);
			}
		}

		return arr;
	}

	public void  addNewCell() {
		ArrayList<Cell> emptyCells = this.getEmptyCells();
		if (emptyCells.isEmpty()) return;

		double rand = Math.random();
		int cell = new Random().nextInt(emptyCells.size());

		System.out.println(emptyCells.size());

		if (rand < PROBABILITY_FOUR) {
			emptyCells.get(cell).setContent(4);
		} else {
			emptyCells.get(cell).setContent(2);
		}
	}

}
