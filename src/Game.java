import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Game {
	private ArrayList<Unit> unitOptions;
	private Level level;
	private Grid grid;
	private int currency;
	private ImageToUnitMap imageToUnitMap;
	private ImageToRobotMap imageToRobotMap;
	
	
	public Game()
	{
		
	}
	
	public Game(Level chosenLevel, ArrayList<Unit> chosenUnits)
	{
		unitOptions = chosenUnits;
		level = chosenLevel;
		imageToUnitMap = new ImageToUnitMap();
		imageToRobotMap = new ImageToRobotMap();
	}
	
	public Unit getUnitFromImage(GImage image)
	{
		return imageToUnitMap.get(image);
	}
	
	public static void main(String[] args) {
		
	}

}
