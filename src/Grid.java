import java.util.*;
import java.util.Vector;
import java.util.ArrayList;

public class Grid {
	Space[][] gameGrid;
	private int rows = 5;
	private int cols = 9;

	    
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		gameGrid = new Space[rows][cols];
		
		for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                gameGrid[row][col] = new Space(row, col);
            }
        }
	}
	
	 public Space getSpace(int row, int col) {
	        if (row >= 0 && row < rows && col >= 0 && col < cols) {
	            return gameGrid[row][col];
	        } 
	        else {
	            return null;  
	        }
	    }
	 
	 public void printAsciiGrid() {
	        for (int row = 0; row < rows; row++) {
	            // this loop prints the top border of each row
	            for (int col = 0; col < cols; col++) {
	                System.out.print("+---");
	            }
	            System.out.println("+");

	          
	            for (int col = 0; col < cols; col++) {
	                System.out.print("|   ");
	            }
	            System.out.println("|");
	        }

	        // this loop prints the bottom border of the last row
	        for (int col = 0; col < cols; col++) {
	            System.out.print("+---");
	        }
	        System.out.println("+");
	 }
}