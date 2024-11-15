import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles creating the player unit, or the units that the player will use to defeat enemies
public class Unit {
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	private GImage image = new GImage(IMG_FILENAME_PATH + "soldier" + IMG_EXTENSION);
	private int health = 100;
	private int cost = 50;
	private int placementCooldown = 5;
	private boolean upgradable = true;
	private int upgradeCost = 50;
	private String unitToUpgradeTo = "unitSoldier";
	
	private GameTimer routineTimer;
	private int frequency;
	private int numTimes;
	public boolean enemyDetected;
	private int lane;
	
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
		routineTimer = new GameTimer(100, "Soldier");
		routineTimer.start();
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	numTimes = numTimes + 1;
		    	 
		    	if (numTimes > 20) {
		    		
		    	}
		    }};
		    
		    routineTimer.createActionListener(listener);
	}
	
	public void routine () {
		
	}
	
	public void stopRoutine() {
	   if (routineTimer != null) {
	            routineTimer.stop();
	   }
	}
	
	public GImage getImageFromUnit()
	{
		return image;
	}
	
	public void setImagePos(int x, int y)
	{
		image.setLocation(x, y);
	}
	
	// checks if a player unit is upgradable to a stronger unit
	public boolean isItUpgradable(boolean upgrade) {
		return this.upgradable && upgrade && unitToUpgradeTo != null;
	}
	
	// checks if an enemy is in the same lane as the player unit. If it is, the player unit starts attacking the enemy
	public boolean checkForEnemy(boolean robotLocation) {
		return robotLocation;
	}
	
	// handles enemies dealing damage to the player unit
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
