
import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

	// this class handles the enemy units in the game and their attributes, such as how fast they move and how much health they have
public class RobotGrey extends Robot {
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	private GameScene scene;
	
	public RobotGrey(int laneNum, GameScene scene) {
		super(laneNum, scene);
		this.image = new GImage("robot_Grey.png");
		this.health = 70;
		this.damage = 25;
		this.moveSpeed = 2;
		this.currencyEarned = 50;
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
	
	// determines if an enemy unit is close enough to a player unit to start attacking and dealing damage to it
	public boolean hasReachedUnit(boolean isMoving) {
		if (isMoving == false) {
			return false;
		}
		return true;
	}
}