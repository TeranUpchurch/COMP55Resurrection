import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Wave implements ActionListener {
	private ArrayList<Robot> robots;
	private ArrayList<Integer> intervals;
	
	private int enemyNum = 0;
	private Timer waveTimer;
	private int MS = 100;
	private int numTimes;
	
	public Wave() {
		
	}
	
	public Wave(ArrayList<Robot> robots, ArrayList<Integer> intervals) {
		this.robots = robots;
		this.intervals = intervals;
	}
	
	public Robot returnRobot() {
		return null;
	}
	
	public void stepThrough()
	{
		waveTimer = new Timer(MS, this);
		waveTimer.start();
	}
	
	public void onAction (ActionEvent e) {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (robots == null || intervals == null)
		{
			return;
		}
		
		if (enemyNum >= robots.size())
		{
			return;
		}
		
		if (numTimes >= intervals.get(enemyNum))
		{
			System.out.println(robots.get(enemyNum));
			enemyNum = enemyNum + 1;
			numTimes = 0;
		}
		System.out.println("Tick");
		numTimes = numTimes + 1;
	}
	
	public static void main(String[] args) {
		System.out.println("Unit tests:");
		ArrayList<Robot> robots1 = new ArrayList<Robot>();
		robots1.add(new Robot());
		robots1.add(new Robot());
		robots1.add(new Robot());
		robots1.add(new Robot());
		ArrayList<Integer> intervals1 = new ArrayList<Integer>();
		intervals1.add(2);
		intervals1.add(4);
		intervals1.add(6);
		intervals1.add(8);
		Wave wave = new Wave(robots1, intervals1);
		wave.stepThrough();
	}
}
