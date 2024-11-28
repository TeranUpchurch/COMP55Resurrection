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
	private ImageToUnitMap imageToUnitMap;
	private ImageToRobotMap imageToRobotMap;
	
	public GameScene gameScene;
	
	
	public Game(GameScene gameScene)
	{
		grid = new Grid(5, 9);
		level = new Level(gameScene);
		waveNum = 0;
		currentWave = level.getWave(waveNum);
		this.gameScene = gameScene;
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
		currentWave.resetWave();
		currentWave = level.getWave(waveNum);
		System.out.print("Starting the wave " + this.currentWave + ". ");
		System.out.println("Wave Number " + waveNum);
		activeEnemyCount = currentWave.getTotalEnemyCount();
		currentWave.stepThrough(gameScene);
	}
	
	public void resetGame() {
		waveNum = 0;
		// Reset the level's waves
	    if (level != null) {
	        currentWave = level.getWave(waveNum);
	        if (currentWave != null) {
	            currentWave.resetWave(); // Reset the wave
	        }
	    }
	    System.out.println("Game has been reset!");
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
	
	public void removeUnitFromGrid(Unit unit)
	{
		grid.removeUnit(unit);
	}
	
	public boolean isBossWaveNext()
	{
		return level.getNumWaves() - 2 == waveNum;
	}
	
	public boolean isWaveNumMax()
	{
		return level.getNumWaves() - 1 == waveNum;
	}
	
	public static void main(String[] args) {
		
	}

}
