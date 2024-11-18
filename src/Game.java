import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Game {
	private ArrayList<Unit> unitOptions;
	private Level level;
	public Grid grid;
	private int currency;
	private ImageToUnitMap imageToUnitMap;
	private ImageToRobotMap imageToRobotMap;
	
	
	public Game()
	{
		grid = new Grid(5, 9);
		imageToUnitMap = new ImageToUnitMap();
		imageToRobotMap = new ImageToRobotMap();
	}
	
	public Game(Level chosenLevel, ArrayList<Unit> chosenUnits)
	{
		unitOptions = chosenUnits;
		level = chosenLevel;
		grid = new Grid(5, 9);
		imageToUnitMap = new ImageToUnitMap();
		imageToRobotMap = new ImageToRobotMap();
	}
	
	public Unit getUnitFromImage(GImage image)
	{
		return imageToUnitMap.get(image);
	}
	
	public Robot getRobotFromImage(GImage image)
	{
		return imageToRobotMap.get(image);
	}
	
	public static void main(String[] args) {
		
	}

}
