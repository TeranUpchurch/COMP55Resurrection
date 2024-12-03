import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Level {
	private ArrayList<Wave> waves;
	private String difficulty;
	private int numWaves;
	private GameScene gameScene;
	
	public Level(GameScene gameScene)
	{
		ArrayList<Robot> robots1 = new ArrayList<Robot>();
		robots1.add(new RobotGreen(1, gameScene));
		robots1.add(new RobotGreen(2, gameScene));
		robots1.add(new RobotGreen(3, gameScene));
		robots1.add(new RobotYellow(2, gameScene));
		robots1.add(new RobotGrey(4, gameScene));
		ArrayList<Integer> intervals1 = new ArrayList<Integer>();
		intervals1.add(10);
		intervals1.add(10);
		intervals1.add(10);
		intervals1.add(10);
		intervals1.add(10);
		intervals1.add(10);
		
		ArrayList<Robot> robots2 = new ArrayList<Robot>();
		robots2.add(new RobotGrey(0, gameScene));
		robots2.add(new RobotYellow(1, gameScene));
		robots2.add(new RobotYellow(2, gameScene));
		robots2.add(new RobotYellow(3, gameScene));
		robots2.add(new RobotYellow(2, gameScene));
		ArrayList<Integer> intervals2 = new ArrayList<Integer>();
		intervals2.add(10);
		intervals2.add(10);
		intervals2.add(10);
		intervals2.add(10);
		intervals2.add(10);
		intervals2.add(10);
		
		waves = new ArrayList<Wave>();
		waves.add(new Wave(gameScene, robots1, intervals1));
		waves.add(new Wave(gameScene, robots2, intervals2));
		
		difficulty = "Easy";
		numWaves = waves.size();
	}
	
	public Level(GameScene gameScene, String difficulty)
	{
		waves = new ArrayList<Wave>();
		if (difficulty == "Easy")
		{
			ArrayList<Robot> robots1 = new ArrayList<Robot>();
			robots1.add(new RobotGreen(1, gameScene));
			robots1.add(new RobotGreen(2, gameScene));
			robots1.add(new RobotGreen(3, gameScene));
			robots1.add(new RobotGreen(2, gameScene));
			robots1.add(new RobotGreen(4, gameScene));
			ArrayList<Integer> intervals1 = new ArrayList<Integer>();
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(50);
			waves.add(new Wave(gameScene, robots1, intervals1));
			
			ArrayList<Robot> robots2 = new ArrayList<Robot>();
			robots2.add(new RobotGrey(0, gameScene));
			robots2.add(new RobotGrey(1, gameScene));
			robots2.add(new RobotGrey(2, gameScene));
			robots2.add(new RobotYellow(3, gameScene));
			robots2.add(new RobotGrey(2, gameScene));
			ArrayList<Integer> intervals2 = new ArrayList<Integer>();
			intervals2.add(50);
			intervals2.add(50);
			intervals2.add(50);
			intervals2.add(50);
			intervals2.add(50);
			intervals2.add(50);
			waves.add(new Wave(gameScene, robots2, intervals2));
			
			ArrayList<Robot> robots3 = new ArrayList<Robot>();
			robots2.add(new RobotGreen(0, gameScene));
			robots2.add(new RobotYellow(1, gameScene));
			robots2.add(new RobotGreen(2, gameScene));
			robots2.add(new RobotYellow(3, gameScene));
			robots2.add(new RobotGreen(2, gameScene));
			ArrayList<Integer> intervals3 = new ArrayList<Integer>();
			intervals2.add(1);
			intervals2.add(50);
			intervals2.add(10);
			intervals2.add(50);
			intervals2.add(10);
			intervals2.add(10);
			waves.add(new Wave(gameScene, robots3, intervals3));
		}
		else if (difficulty == "Medium")
		{
			ArrayList<Robot> robots1 = new ArrayList<Robot>();
			robots1.add(new RobotGreen(1, gameScene));
			robots1.add(new RobotGreen(2, gameScene));
			robots1.add(new RobotGreen(3, gameScene));
			robots1.add(new RobotYellow(2, gameScene));
			robots1.add(new RobotGrey(4, gameScene));
			ArrayList<Integer> intervals1 = new ArrayList<Integer>();
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(1);
			waves.add(new Wave(gameScene, robots1, intervals1));
			
			ArrayList<Robot> robots2 = new ArrayList<Robot>();
			robots2.add(new RobotGrey(0, gameScene));
			robots2.add(new RobotYellow(1, gameScene));
			robots2.add(new RobotYellow(2, gameScene));
			robots2.add(new RobotYellow(3, gameScene));
			robots2.add(new RobotYellow(2, gameScene));
			ArrayList<Integer> intervals2 = new ArrayList<Integer>();
			intervals2.add(10);
			intervals2.add(10);
			intervals2.add(10);
			intervals2.add(10);
			intervals2.add(10);
			intervals2.add(10);
			waves.add(new Wave(gameScene, robots2, intervals2));
		}
		else if (difficulty == "Hard")
		{
			ArrayList<Robot> robots1 = new ArrayList<Robot>();
			robots1.add(new RobotGreen(1, gameScene));
			robots1.add(new RobotGreen(2, gameScene));
			robots1.add(new RobotGreen(3, gameScene));
			robots1.add(new RobotYellow(2, gameScene));
			robots1.add(new RobotGrey(4, gameScene));
			ArrayList<Integer> intervals1 = new ArrayList<Integer>();
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(50);
			intervals1.add(1);
			waves.add(new Wave(gameScene, robots1, intervals1));
			
			ArrayList<Robot> robots2 = new ArrayList<Robot>();
			robots2.add(new RobotGrey(0, gameScene));
			robots2.add(new RobotYellow(1, gameScene));
			robots2.add(new RobotYellow(2, gameScene));
			robots2.add(new RobotYellow(3, gameScene));
			robots2.add(new RobotYellow(2, gameScene));
			ArrayList<Integer> intervals2 = new ArrayList<Integer>();
			intervals2.add(10);
			intervals2.add(10);
			intervals2.add(10);
			intervals2.add(10);
			intervals2.add(10);
			intervals2.add(10);
			waves.add(new Wave(gameScene, robots2, intervals2));
		}
		
		numWaves = waves.size();
	}
	
	public Wave getWave(int n)
	{
		return waves.get(n);
	}
	
	public int getNumWaves()
	{
		return numWaves;
	}
	
	public static void main(String[] args) {
		
	}

}
