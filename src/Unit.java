import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
	
	protected GameScene gameScene;
	
	public Unit(GameScene gameScene)
	{
		this.gameScene = gameScene;
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
		} else {
			System.out.println("No enemy in lane " + lane);
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
		ArrayList<Robot> robots = gameScene.getRobotsInLane(this.lane);
		return !robots.isEmpty();
	}
	
	public void shoot() {
		System.out.println("Shoot!");
	}
	
	public int takeDamage(int damage) {
		health -= damage;
        if (health <= 0) { 
            health = 0;
            System.out.println("Unit destroyed");
        } 
        else {
            System.out.println("Unit took " + damage + " damage. Health: " + health);
        }
        return health;
	}
	// if the player unit's health hits zero
	public boolean isDeath() {
		return health <= 0; 
	}
	
	public static void main(String[] args) {
		
	}
}
