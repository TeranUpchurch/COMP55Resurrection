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
	
	private int enemyNum = 0;
	private GameTimer waveTimer;
	private int MS = 100;
	private int numTimes = 0;
	private int arraySize;
	
	public Wave() {
		
	}
	
	public Wave(ArrayList<Robot> robots, ArrayList<Integer> intervals) {
		this.robots = robots;
		this.intervals = intervals;
		arraySize = robots.size();
	}
	
	public Robot returnRobot() {
		return null;
	}
	
	public void stepThrough()
	{
		waveTimer = new GameTimer(MS, "Wave");
		waveTimer.start();
		
		// timer mechanisms for how a wave works
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println("Tick. " + numTimes +  " Current robot: " + robots.get(enemyNum));
		        if (numTimes >= intervals.get(enemyNum))
		        {
		        	numTimes = 0;
		        	enemyNum = enemyNum + 1;
		        }
		        numTimes = numTimes + 1;
		        if (enemyNum == arraySize)
		        {
		        	enemyNum = numTimes - 1;
		        	waveTimer.stop();
		        }
		    }};
		    
		waveTimer.createActionListener(listener);
	}
	
	public static void main(String[] args) {
		System.out.println("Unit tests:");
		ArrayList<Robot> robots1 = new ArrayList<Robot>();
		robots1.add(new Robot());
		robots1.add(new Robot());
		robots1.add(new Robot());
		robots1.add(new Robot());
		ArrayList<Integer> intervals1 = new ArrayList<Integer>();
		intervals1.add(10);
		intervals1.add(10);
		intervals1.add(10);
		intervals1.add(10);
		Wave wave = new Wave(robots1, intervals1);
		wave.stepThrough();
	}
}
