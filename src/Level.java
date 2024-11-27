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
		
		ArrayList<Robot> robots2 = new ArrayList<Robot>();
		robots2.add(new RobotStrong(1));
		robots2.add(new RobotStrong(2));
		robots2.add(new RobotStrong(3));
		robots2.add(new RobotStrong(2));
		ArrayList<Integer> intervals2 = new ArrayList<Integer>();
		intervals2.add(10);
		intervals2.add(10);
		intervals2.add(10);
		intervals2.add(10);
		
		waves = new ArrayList<Wave>();
		waves.add(new Wave(gameScene, robots1, intervals1));
		waves.add(new Wave(gameScene, robots2, intervals2));
		difficulty = "Easy";
	}
	
	public Wave getWave(int n)
	{
		return waves.get(n);
	}
	
	public static void main(String[] args) {
		
	}

}
