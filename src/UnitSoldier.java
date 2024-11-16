import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles creating the player unit, or the units that the player will use to defeat enemies
public class UnitSoldier extends Unit{
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
	private int frequency = 2; // 2 seconds
	private int numTimes;
	public boolean enemyDetected;
	private int lane;
	
	public UnitSoldier(GameScene gameScene)
	{
		super(gameScene);
	}
	
	public void startTimer()
	{
		routineTimer = new GameTimer(100, "Soldier");
		routineTimer.start();
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	numTimes = numTimes + 1;
		    	 
		    	if (numTimes > 20) {
		    		routine();
		    		numTimes = 0;
		    	}
		    }};
		    
		    routineTimer.createActionListener(listener);
	}
	
	public void routine () {
		Projectile projectile = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball" + IMG_EXTENSION),
				10,
				10,
				10,
				10,
				image.getX(),
				image.getY(),
				gameScene
				);
		gameScene.instantiateProjectile(projectile, image.getX(), image.getY());
		System.out.println("IMAGE SOLDIER x: " + image.getX() + " y: " + image.getY());
		System.out.println("Instantiated projectile from " + this);
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
