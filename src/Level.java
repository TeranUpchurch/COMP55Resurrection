import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Level {
	private ArrayList<Wave> waves;
	private String difficulty;
	private GameScene gameScene;
	
	public Level(GameScene gameScene)
	{
		ArrayList<Robot> robots = new ArrayList<Robot>();
		robots.add(new RobotWeak(1));
		robots.add(new RobotWeak(2));
		robots.add(new RobotWeak(3));
		robots.add(new RobotStrong(2));
		ArrayList<Integer> intervals = new ArrayList<Integer>();
		intervals.add(10);
		intervals.add(10);
		intervals.add(10);
		intervals.add(10);
		
		waves = new ArrayList<Wave>();
		waves.add(new Wave(gameScene, robots, intervals));
		waves.add(new Wave(gameScene, robots, intervals));
		difficulty = "Easy";
	}
	
	public Wave getWave(int n)
	{
		return waves.get(n);
	}
	
	public static void main(String[] args) {
		
	}

}
