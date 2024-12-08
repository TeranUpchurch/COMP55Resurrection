import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

// this class handles creating the player unit, or the units that the player will use to defeat enemies
public class Unit {
	protected static final String IMG_FILENAME_PATH = "media/";
	protected static final String IMG_EXTENSION = ".png";
	
	protected GImage image;
	protected int health;
	protected int cost;
	protected int placementCooldown;
	protected boolean upgradable;
	protected int upgradeCost;
	protected String unitToUpgradeTo;
	
	protected GameTimer routineTimerSoldier;
	protected GameTimer routineTimerMachineGun;
	protected GameTimer routineTimerGrenade;
	protected GameTimer routineTimerRock;
	protected int frequency;
	protected int numTimes;
	protected boolean enemyDetected;
	protected int lane;
	protected Space location;
	
	protected GameScene gameScene;
	protected Game game;
	
	public Unit(GameScene gameScene, Game game)
	{
		this.gameScene = gameScene;
		this.game = game;
	}
	
	public Unit(GImage image, int health, int cost, int lane, int frequency) {
        this.image = image;
        this.health = health;
        this.cost = cost;
        this.lane = lane;
        this.frequency = frequency;
        this.numTimes = 0;
        this.enemyDetected = false;
    }
	
	public void startTimer()
	{
		
	}
	
	public void routine () {
		if (checkForEnemy()) {
			shoot();
		} 
		else {
			System.out.println("No enemies in lane " + lane);
		}
	}
	
	public void stopRoutine() {
	   if (routineTimerSoldier != null) {
		   routineTimerSoldier.stop();
	   }
	   if (routineTimerMachineGun != null) {
		   routineTimerMachineGun.stop();
	   }
	   if (routineTimerGrenade != null) {
		   routineTimerGrenade.stop();
	   }
	   if (routineTimerRock != null) {
		   routineTimerRock.stop();
	   }
	}
	
	public void clearTimers() {
		   if (routineTimerSoldier != null) {
			   routineTimerSoldier.stop();
			   routineTimerSoldier = null;
		   }
		   if (routineTimerMachineGun != null) {
			   routineTimerMachineGun.stop();
			   routineTimerMachineGun = null;
		   }
		   if (routineTimerGrenade != null) {
			   routineTimerGrenade.stop();
			   routineTimerGrenade = null;
		   }
		   if (routineTimerRock != null) {
			   routineTimerRock.stop();
			   routineTimerRock = null;
		   }
		}
	
	public void startCooldown() {
		
	}
	
	public void reconfigureLabel(GLabel label, UnitType unitType)
	{
		label.setLocation(60, 18);
		List<UnitType> typeList = new ArrayList<>();
		typeList = gameScene.returnUnitBar().getTypeList();
		
		for (int i = 0; i < typeList.size(); i++)
		{
			System.out.print(typeList.get(i));
			System.out.println(unitType);
			if (typeList.get(i).getName() == unitType.getName())
			{
				break;
			}
			else
			{
				label.move(120, 0);
			}
		}
		label.setFont("Arial-Bold-20");
	}
	
	public boolean isCooldownActive()
	{
		return false;
	}
	
	public void setLane(int lane) {
		this.lane = lane;
	}
	
	public int getLane() {
		return this.lane;
	}
	
	public GImage getImageFromUnit()
	{
		return image;
	}
	
	public void setImagePos(int x, int y)
	{
		image.setLocation(x, y);
	}
	
	public boolean isItUpgradable(boolean upgrade) {
		return this.upgradable && upgrade && unitToUpgradeTo != null;
	}
	
	public boolean checkForEnemy() {
		int unitCol = getCurrentColumn();
		ArrayList<Robot> robots = gameScene.getRobotsInLane(this.lane);
		for (Robot robot : robots) {
			int robotCol = getRobotColumn(robot);
			if (robotCol >= unitCol) {
				return true;
			}
		}
		return false;
	}
	
	protected int getCurrentColumn() {
		int gridStartX = gameScene.gridStartX;
		int tileWidth = gameScene.tileWidth;
		return (int)((image.getX() - gridStartX) / tileWidth);
	}
	
	protected int getRobotColumn(Robot robot) {
		int gridStartX = gameScene.gridStartX;
		int tileWidth = gameScene.tileWidth;
		return (int)((robot.getImage().getX() - gridStartX) / tileWidth);
	}
	
	public void shoot() {
		System.out.println("Shoot!");
	}
	
	// Return false if the unit dies. Return true if it just takes damage only.
	public boolean takeDamage(int damage) {
		health -= damage;
        if (health <= 0) { 
            health = 0;
            System.out.println("Unit destroyed!!!");
            return false;
        } 
        else {
            System.out.println("Unit took " + damage + " damage. Health: " + health);
        }
        return true;
	}
	// if the player unit's health hits zero
	public boolean isDeath() {
		return health <= 0; 
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public Space getLocation()
	{
		return location;
	}
	
	public void setLocation(int row, int col)
	{
		location = new Space(row, col);
	}
	
	public static void main(String[] args) {
		
	}
}
