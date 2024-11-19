import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Game {
	private ArrayList<Unit> unitOptions;
	private Level level;
	
	private Wave currentWave;
	private int waveNum;
	
	public Grid grid;
	private int activeEnemyCount;
	private int currency;
	private ImageToUnitMap imageToUnitMap;
	private ImageToRobotMap imageToRobotMap;
	
	private GameScene gameScene;
	
	
	public Game(GameScene gameScene)
	{
		grid = new Grid(5, 9);
		level = new Level(gameScene);
		waveNum = 0;
		currentWave = level.getWave(waveNum);
		activeEnemyCount = currentWave.getTotalEnemyCount();
		imageToUnitMap = new ImageToUnitMap();
		imageToRobotMap = new ImageToRobotMap();
	}
	
	public Game(GameScene gameScene, Level chosenLevel, ArrayList<Unit> chosenUnits)
	{
		unitOptions = chosenUnits;
		level = chosenLevel;
		grid = new Grid(5, 9);
		imageToUnitMap = new ImageToUnitMap();
		imageToRobotMap = new ImageToRobotMap();
	}
	
	public void startCurrentWave()
	{
		currentWave.stepThrough(gameScene);
	}
	
	public Wave getCurrentWave()
	{
		return currentWave;
	}
	
	public void incrementWaveNum()
	{
		waveNum = waveNum + 1;
	}
	
	public int getActiveEnemyCount()
	{
		return activeEnemyCount;
	}
	
	public void setActiveEnemyCount(int n)
	{
		activeEnemyCount = n;
	}
	
	public void decrementEnemyCount()
	{
		activeEnemyCount = activeEnemyCount - 1;
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
