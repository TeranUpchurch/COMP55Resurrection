import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles the wave system the game uses to spawn enemies and progress the level the user is playing
public class Wave {
	private ArrayList<Robot> robots;
	private ArrayList<Integer> intervals;
	private GameScene gameScene;
	
	private int enemyNum = 0;
	private GameTimer waveTimer;
	private int MS = 100;
	private int numTimes = 0;
	private int arraySize;
	
	public Wave() {
		
	}
	
	// Constructor for unit testing.
	public Wave(ArrayList<Robot> robots, ArrayList<Integer> intervals) {
		this.robots = robots;
		this.intervals = intervals;
		arraySize = robots.size();
	}
	
	// Constructor for in-game implementation.
	public Wave(GameScene gameScene, ArrayList<Robot> robots, ArrayList<Integer> intervals) {
		this.gameScene = gameScene;
		this.robots = robots;
		this.intervals = intervals;
		arraySize = robots.size();
	}
	
	public void resetWave() {
		if (waveTimer != null) {
			waveTimer.stop(); // Stop any running timers
		}
		
		enemyNum = 0; // Reset enemy counter
	    numTimes = 0; // Reset the timer tick counter
	}
	
	public Robot returnRobot() {
		return null;
	}
	
	public int getTotalEnemyCount()
	{
		return robots.size();
	}
	
	public void stepThrough()
	{
		waveTimer = new GameTimer(MS, "Wave");
		waveTimer.start();
		
		// timer mechanisms for how a wave works
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	numTimes = numTimes + 1;
		    	 
		    	System.out.println("Tick. " + numTimes +  " Current robot: " + robots.get(enemyNum));
		        if (numTimes >= intervals.get(enemyNum))
		        {
		        	numTimes = 0;
		        	enemyNum = enemyNum + 1;
		        }
		        
		        if (enemyNum == arraySize)
		        {
		        	enemyNum = numTimes - 1;
		        	waveTimer.stop();
		        }
		    }};
		    
		waveTimer.createActionListener(listener);
	}
	
	public void stepThrough(GameScene gameScene)
	{
		if (gameScene == null) {return;}
		
		waveTimer = new GameTimer(MS, "Wave");
		waveTimer.start();
		System.out.println("Starting the timer for wave " + this);
		
		// timer mechanisms for how a wave works
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        if (enemyNum >= arraySize)
		        {
		        	numTimes = 0;
		        	enemyNum = 0;
		        	waveTimer.stop();
		        	waveTimer.removeActionListener(this);
		        	return;
		        }
		    	//System.out.println("Tick. " + numTimes +  " Current robot: " + robots.get(enemyNum));
		        if (numTimes >= intervals.get(enemyNum) && !gameScene.isPaused())
		        {
		        	numTimes = 0;
		        	gameScene.instantiateRobot(robots.get(enemyNum));
		        	enemyNum = enemyNum + 1;
		        }
		        
		        numTimes = numTimes + 1;
		    }};
		    
		waveTimer.createActionListener(listener);
	}
	
	public static void main(String[] args) {
		System.out.println("Unit tests:");
		ArrayList<Robot> robots1 = new ArrayList<Robot>();
		robots1.add(new RobotWeak(1));
		robots1.add(new RobotWeak(2));
		robots1.add(new RobotWeak(3));
		robots1.add(new RobotStrong(2));
		ArrayList<Integer> intervals1 = new ArrayList<Integer>();
		intervals1.add(10);
		intervals1.add(10);
		intervals1.add(10);
		intervals1.add(10);
		Wave wave = new Wave(robots1, intervals1);
		wave.stepThrough();
	}
}
