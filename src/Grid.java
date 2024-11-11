import java.util.*;
import java.util.Vector;
import java.util.ArrayList;

/**
 * This represents the board.  Really what it is going to do is just have a 2d array of the vehicles
 * (which we'll refer to as grid), and it will be in charge of doing the bounds type checking for doing any of the moves.
 * It will also have a method display board which will give back a string representation of the board
 * 
 * @author Osvaldo
 *
 */

public class Grid {
	Grid[][] gameGrid;
	int colSize;
	int rowSize;
	int exitSpace;
	int numOfMoves;
	// add array of cars
	private ArrayList<Vehicle> vehicles;
	//private Vector <Vehicle> cars;
	//TODO Add the other methods that are in the handout, and fill out the rest of this file
	
	/**
	 * Constructor for the board which sets up an empty grid of size rows by columns
	 * Use the first array index as the rows and the second index as the columns
	 * 
	 * @param rows number of rows on the board
	 * @param cols number of columns on the board
	 */
	public Board(int rows, int cols) {
		rowSize = rows;
		colSize = cols;
		grid = new Vehicle[rows][cols];
		vehicles = new ArrayList<Vehicle>();
		//cars = new Vector<Vehicle>();
	}
	
	/**
	 * @return number of columns the board has
	 */
	public int getNumCols() {
		return colSize;
	}

	/**
	 * @return number of rows the board has
	 */
	public int getNumRows() {
		return rowSize;
	}
	
	/**
	 * Grabs the vehicle present on a particular space if any is there
	 * If a Vehicle occupies three spaces, the same Vehicle pointer should be returned for all three spaces
	 * 
	 * @param s the desired space where you want to look to see if a vehicle is there
	 * @return a pointer to the Vehicle object present on that space, if no Vehicle is present, null is returned
	 */
	public Vehicle getVehicle(Space s) {
		//TODO change this method
		if (s == null) {
			return null;
		}
		return grid[s.getRow()] [s.getCol()];
	}
	
	public ArrayList<Vehicle> getVehiclesOnBoard() {
        return vehicles; 
    }
	
	 public boolean isSpaceOccupied(int row, int col) {
	        return grid[row][col] != null;
	    }

	    public boolean canAddVehicle(Vehicle vehicle) {
	        for (Space space : vehicle.spacesOccupied()) {
	            if (isSpaceOccupied(space.getRow(), space.getCol())) {
	                return false; // Space is already occupied
	            }
	        }
	        return true; // All spaces are free
	    }

	/**
	 * adds a vehicle to the board. It would be good to do some checks for a legal placement here.
	 * 
	 * @param type type of the vehicle
	 * @param startRow row for location of vehicle's top
	 * @param startCol column for for location of vehicle leftmost space
	 * @param length number of spaces the vehicle occupies on the board
	 * @param vert true if the vehicle should be vertical
	 */
	public void addVehicle(VehicleType type, int startRow, int startCol, int length, boolean vert) {
		Vehicle car = new Vehicle(type, startRow, startCol, length, vert);
	    if (!canPlaceVehicle(car)) {
	        System.out.println("Cannot place vehicle: out of bounds or collision detected.");
	        return; // or throw an exception
	    }
		Space[] occupiedSpaces = car.spacesOccupied();
		for (int i = 0; i < length; i++) {
			grid[occupiedSpaces[i].getRow()][occupiedSpaces[i].getCol()] = car;
		}
		vehicles.add(car);
	}
	
	 public void addVehicle(Vehicle vehicle) {
	        if (canAddVehicle(vehicle)) {
	            for (Space space : vehicle.spacesOccupied()) {
	                grid[space.getRow()][space.getCol()] = vehicle; // Occupy the spaces
	            }
	        } else {
	            System.out.println("Cannot add vehicle. Space occupied.");
	        }
	    }
	
	private boolean canPlaceVehicle(Vehicle car) {
	    for (Space space : car.spacesOccupied()) {
	        if (space.getRow() < 0 || space.getRow() >= rowSize || 
	            space.getCol() < 0 || space.getCol() >= colSize ||
	            grid[space.getRow()][space.getCol()] != null) {
	            return false; // Out of bounds or collision
	        }
	    }
	    return true;
	}
	
	

	/**
	 * This method moves the vehicle on a certain row/column a specific number of spaces
	 * 
	 * @param start the starting row/column of the vehicle in question
	 * @param numSpaces the number of spaces to be moved by the vehicle (can be positive or negative)
	 * @return whether or not the move actually happened
	 */
	public boolean moveNumSpaces(Space start, int numSpaces) {
		Vehicle car = getVehicle(start);
		if (car == null || !canMoveNumSpaces(start, numSpaces)) {
	            return false; // No vehicle at the start position
		}

	        // Removing the vehicle from its current positions
	        for (Space s : car.spacesOccupied()) {
	            grid[s.getRow()][s.getCol()] = null;
	        }
	        car.move(numSpaces);

	        // Adding the vehicle to its new positions
	        for (Space s : car.spacesOccupied()) {
	            grid[s.getRow()][s.getCol()] = car;
	        }
	        return true;
	    }
		
	/**
	 * This method just checks to see if the vehicle on a certain row/column can move a specific number of spaces, though
	 * it will not move the vehicle.  You should use this when you wish to move or want to see if you can
	 * move a vehicle numSpaces without going out of bounds or hitting another vehicle
	 * 
	 * @param start the starting row/column of the vehicle in question
	 * @param numSpaces number of spaces to be moved by the vehicle (positive or negative)
	 * @return whether or not the move is possible
	 */
	public boolean canMoveNumSpaces(Space start, int numSpaces) {
		Vehicle car = getVehicle(start);
	    if (car == null) {
	        return false;
	    }

	    int startRow = car.getStartRow();
	    int startCol = car.getStartCol();
	    int length = car.getLength();

	    int newStartRow = startRow + (car.isItVertical() ? numSpaces : 0);
	    int newStartCol = startCol + (car.isItVertical() ? 0 : numSpaces);

	    if (car.isItVertical()) {
	        if (newStartRow < 0 || newStartRow + length > rowSize) {
	            return false; // Out of bounds
	        }
	    } else {
	        if (newStartCol < 0 || newStartCol + length > colSize) {
	            return false; // Out of bounds
	        }
	    }

	    // Check all spaces the vehicle would occupy in the new position
	    for (int i = 0; i < length; i++) {
	        int row = car.isItVertical() ? newStartRow + i : newStartRow;
	        int col = car.isItVertical() ? newStartCol : newStartCol + i;
	        if (row >= 0 && row < rowSize && col >= 0 && col < colSize) {
		        if (grid[row][col] != null && grid[row][col] != car) {
		            return false; // Collision detected
		        }
	        }
	        else {
	        	return false;
	        }
	    }

	    // Check all spaces between the current position and the new position
	    for (int i = 0; i < Math.abs(numSpaces); i++) {
	        int row = car.isItVertical() ? startRow + (numSpaces > 0 ? i : -i) : startRow;
	        int col = car.isItVertical() ? startCol : startCol + (numSpaces > 0 ? i : -i);
	        if (row >= 0 && row < rowSize && col >= 0 && col < colSize) {
		        if (grid[row][col] != null && grid[row][col] != car) {
		            return false; // Collision detected
		        }
	        }
	        else {
	        	return false;
	        }
	    }

	    return true;
	}
		
	public int getNumberOfVehicles() {
	    return vehicles.size();
	}

	public List<Vehicle> getAllVehicles() {
	    return new ArrayList<Vehicle>(vehicles); // Return a copy to prevent modification
	}
	
	// This method helps create a string version of the board
	public String toString() {
		return BoardConverter.createString(this);
	}

	/* Testing methods down here for testing the board 
	 * make sure you run the board and it works before you write the rest of the program! */
	
	public static void main(String[] args) {
		Board b = new Board(5, 5);
		b.addVehicle(VehicleType.MYCAR, 1, 0, 2, false);
		b.addVehicle(VehicleType.TRUCK, 0, 2, 3, true);
		b.addVehicle(VehicleType.AUTO, 3, 3, 2, true);
		b.addVehicle(VehicleType.AUTO, 0, 3, 2, true); 
		System.out.println(b);
		testCanMove(b);
		testMoving(b);
		System.out.println(b);
		
		// test code
		Board newboard = new Board(10, 10);
		newboard.addVehicle(VehicleType.MYCAR, 9, 2, 2, false);
		newboard.addVehicle(VehicleType.MYCAR, 8, 5, 2, true);
		System.out.println(newboard);
		System.out.println("Jumping over the car, should be false " + newboard.canMoveNumSpaces(new Space(9, 3), 5));
		newboard.moveNumSpaces(new Space(9, 3), 5); 
		/*b.exitSpace();
		int counter = 0;
		while(notFinished) {
			get user input;
			b.moveNumSpaces(space/ moves) 
		} */
	}
	
	
	public static void testMoving(Board b) {
		System.out.println("just moving some stuff around");
		b.moveNumSpaces(new Space(1, 2), 1);
		b.moveNumSpaces(new Space(1, 2), 1);
		b.moveNumSpaces(new Space(1, 1), 1);
	}
	
	public static void testCanMove(Board b) {
		System.out.println("Ok, now testing some moves...");
		System.out.println("These should all be true");
		System.out.println("Moving truck down " + b.canMoveNumSpaces(new Space(0, 2), 2));
		System.out.println("Moving truck down " + b.canMoveNumSpaces(new Space(1, 2), 2));
		System.out.println("Moving truck down " + b.canMoveNumSpaces(new Space(2, 2), 2));
		System.out.println("Moving lower auto up " + b.canMoveNumSpaces(new Space(3, 3), -1));
		System.out.println("Moving lower auto up " + b.canMoveNumSpaces(new Space(4, 3), -1));
		
		System.out.println("And these should all be false");
		System.out.println("Moving truck down " + b.canMoveNumSpaces(new Space(3, 2), 2));
		System.out.println("Moving the car into truck " + b.canMoveNumSpaces(new Space(1, 0), 1));
		System.out.println("Moving the car into truck " + b.canMoveNumSpaces(new Space(1, 0), 2));
		System.out.println("Moving nothing at all " + b.canMoveNumSpaces(new Space(4, 4), -1));
		System.out.println("Moving lower auto up " + b.canMoveNumSpaces(new Space(3, 3), -2));
		System.out.println("Moving lower auto up " + b.canMoveNumSpaces(new Space(4, 3), -2));
		System.out.println("Moving upper auto up " + b.canMoveNumSpaces(new Space(0, 3), -1));
		System.out.println("Moving upper auto up " + b.canMoveNumSpaces(new Space(1, 3), -1));
	}
}

