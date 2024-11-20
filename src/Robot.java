import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles the enemy units in the game and their attributes, such as how fast they move and how much health they have
public class Robot {
	private GImage image;
	private int health;
	private int damage;
	private int lane;
	private int moveSpeed;
	private int currencyEarned;
	private Unit unitAttacking;
	public boolean isMoving;
	public boolean hasTakenDamage;
	
	
	
	public Robot() {
		image = new GImage("robot.png");
		health = 100;
		damage = 20;
		lane = 1;
		moveSpeed = 2;
		currencyEarned = 25;
		unitAttacking = null;
		isMoving = true;
		hasTakenDamage = false;
	}
	
	public Robot(GImage image, int health, int damage, int lane, int moveSpeed, int currencyEarned) {
		this.image = image;
		this.health = health;
		this.damage = damage;
		this.lane = lane;
		this.moveSpeed = moveSpeed;
		this.currencyEarned = currencyEarned;
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
	
	// how much damage the enemy deals to the player unit it is attacking
	public void damageUnit(int d) {
		damage = d;
	}
	
	public void attackUnit() { // GameScene related
		
	}
	
	// how much damage the enemy unit takes from a player unit
	public int takeDamage() {
		health -= damage;
        if (health <= 0) { 
            health = 0;
            System.out.println("Robot destroyed");
            System.out.println(currencyEarned + "currency awarded");
        } 
        else {
            System.out.println("Robot took " + damage + " damage. Health: " + health);
        }
        return health;
	}
	
	public void step() // kind of similar to projectile, 
	{
		image.move(-1 * moveSpeed, 0);
	}
	
	// if an enemy's health reaches zero, it is defeated and disappears from the grid
	public boolean isDeath(int health, int currencyEarned) {
		return health <= 0;
	}
	
	// determines if an enemy unit is close enough to a player unit to start attacking and dealing damage to it
	public boolean hasReachedUnit(boolean isMoving) {
		if (isMoving == false) {
			return false;
		}
		return true;
	}
}
