import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Robot {
	private GImage image;
	private int health;
	private int damage;
	private int lane;
	private int moveSpeed;
	private int currencyEarned;
	private Unit unitAttacking;
	public boolean isMoving;
	
	public Robot() {
		image = new GImage("robot.png");
		health = 100;
		damage = 20;
		lane = 1;
		moveSpeed = 5;
		currencyEarned = 25;
		unitAttacking = null;
		isMoving = true;
	}
	
	public int getImageX() {
		return 0;
	}
	
	public int getImageY() {
		return 0;
	}
	
	public void damageUnit() {
		
	}
	
	public void attackUnit() {
		
	}
	
	public int takeDamage() {
		return 0;
	}
	
	public boolean isDeath(int health) {
		return true;
	}
	
	public boolean hasReachUnit() {
		return true;
	}
}
