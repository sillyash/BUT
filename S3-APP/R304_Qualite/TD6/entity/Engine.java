package entity;

import java.awt.Dimension;
import java.util.Arrays;

public class Engine {
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

}
