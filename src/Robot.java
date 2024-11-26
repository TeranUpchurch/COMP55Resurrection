import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles the enemy units in the game and their attributes, such as how fast they move and how much health they have
public class Robot {
	protected GImage image;
	protected int health;
	protected int damage;
	protected int lane;
	protected int moveSpeed;
	protected int currencyEarned;
	protected Unit unitAttacking;
	protected boolean isMoving;
	protected GameScene scene;

	public Robot(int laneNum) { // Parent class constructor. Initializes lane spawn number and status variables.
		lane = laneNum;
		unitAttacking = null;
		isMoving = true;
	}
	
	public GImage getImage()
	{
		return image;
	}
	
	public int getImageX() {
		return 0;
	}
	
	public int getImageY() {
		return 0;
	}
	
	public int getLane()
	{
		return lane;
	}
	
	// how much damage the enemy deals to the player unit it is attacking
	public void damageUnit(int d) {
		damage = d;
	}
	
	public void attackUnit() { // GameScene related
		
	}
	
	// how much damage the enemy unit takes from a player unit
	public int takeDamage(int damage) {
		health -= damage;
        if (health <= 0) { 
            health = 0;
        } 
        else {
            System.out.println("Robot took " + damage + " damage. Health remaining: " + health);
        }
        return damage;
	}
	
	public void step() // kind of similar to projectile, 
	{
		image.move(-1 * moveSpeed, 0);
	}
	
	// if an enemy's health reaches zero, it is defeated and disappears from the grid
	public boolean isDefeated() {
		return health <= 0;
	}
	
	// determines if an enemy unit is close enough to a player unit to start attacking and dealing damage to it
	public boolean hasReachedUnit(boolean isMoving) {
		if (isMoving == true) {
			return false;
		}
		return true;
	}
}
