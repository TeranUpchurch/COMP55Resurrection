import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles creating the player unit, or the units that the player will use to defeat enemies
public class Unit {
	private GImage image;
	private int health;
	private int cost;
	private int lane;
	private int placementCooldown;
	private boolean upgradable;
	private int upgradeCost;
	Unit unitToUpgradeTo;
	Timer routineTimer;
	private int frequency;
	private int numTimes;
	public boolean flag;
	
	public Unit(GImage image, int health, int cost, int lane, int frequency) {
        this.image = image;
        this.health = health;
        this.cost = cost;
        this.lane = lane;
        this.frequency = frequency;
        this.numTimes = 0;
        this.flag = false;

        // Initializes the timer to call the routine periodically
        this.routineTimer = new Timer(frequency, this::routine);
        this.routineTimer.start();
    }
	
	public void routine (ActionEvent e) {
		
	}
	
	 public void stopRoutine() {
	        if (routineTimer != null) {
	            routineTimer.stop();
	        }
	    }
	// checks if a player unit is upgradable to a stronger unit
	public boolean isItUpgradable(boolean upgrade) {
		return this.upgradable && upgrade && unitToUpgradeTo != null;
	}
	
	// checks if an enemy is in the same lane as the player unit. If it is, the player unit starts attacking the enemy
	public boolean checkForEnemy(boolean robotLocation) {
		return robotLocation;
	}
	
	public void attackRobot() {
		System.out.println("Unit attacking robot in lane " + lane);
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
}
