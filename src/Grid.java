import java.util.*;
import java.util.Vector;
import java.util.ArrayList;

public class Grid {
	Unit[][] gameGrid;
	private int rows = 5;
	private int cols = 9;
	    
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;

		for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                
            }
        }
	}
	
	 public Space getSpace(int row, int col) {
	        if (row >= 0 && row < rows && col >= 0 && col < cols) {
	            
	        } 
	        else {
	            return null;  
	        }
	        return new Space(1, 2);
	    }
	 
	 public void setSpace(Unit unit, int row, int col)
	 {
		 gameGrid[row][col] = unit;
	 }
	 
	 public int getRows()
	 {
		 return rows;
	 }
	 
	 public int getCols()
	 {
		 return cols;
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