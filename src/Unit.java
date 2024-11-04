import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


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
	
	public Unit() {
		
	}
	
	public void routine (ActionEvent e) {
		
	}
	
	public boolean isItUpgradable(boolean upgrade) {
		return upgrade;
	}
	
	public boolean checkForEnemy(boolean robotLocation) {
		return robotLocation;
	}
	
	public void attackRobot() {
		
	}
	
	public int takeDamage() {
		return 0;
	}
	
	public boolean isDeath(int unitHealth) {
		return true;
	}
}
