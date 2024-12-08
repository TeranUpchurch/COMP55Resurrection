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
	protected boolean isMoving;
	protected GameScene scene;
	
	protected Unit unitAttacking;
	protected GameTimer attackUnitTimer;
	protected int numTimes;
	

	public Robot(int laneNum, GameScene scene) { // Parent class constructor. Initializes lane spawn number and status variables.
		lane = laneNum;
		unitAttacking = null;
		isMoving = true;
		this.scene = scene;
	}

	public GImage getImage()
	{
		return image;
	}
	
	public double getImageX() {
		return image.getX();
	}
	
	public double getImageY() {
		return image.getY();
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
            if (unitAttacking != null)
            {
            	attackUnitTimer.stop();
            	System.out.println("Robot took " + damage );
            }
        } 
        else {
            System.out.println("Robot took " + damage + " damage. Health remaining: " + health);
        }
        return damage;
	}
	
	public void step()
	{
		ArrayList<Unit> unitsInLane = scene.getUnitsInLane(this.lane);
		if (unitAttacking != null)
		{
			return;
		}
		
	    if (scene != null) {
	        int currentCol = getCurrentColumn();
	        int newCol = currentCol - 1; 
	    }
	    
	    if (unitsInLane.isEmpty()) {
	    	image.move(-1 * moveSpeed, 0);
	    	return;
	    }
	    
	    for (Unit unit : unitsInLane) {
	    	int robotCol = getCurrentColumn();
	        int unitCol = getCurrentUnitColumn(unit);
	        
	        if (robotCol == unitCol) {
	        	isMoving = false;
	        	unitAttacking = unit;
	        	startAttackingUnit(unit);
	        	return;
	        }
	    }
	    
	    image.move(-1 * moveSpeed, 0);
	}
	
	public boolean damageUnit(Unit unit)
	{
		return unit.takeDamage(damage);
	}
	
	public void startAttackingUnit(Unit unit)
	{
		attackUnitTimer = new GameTimer(500, "Attacking Unit: " + unit);
		attackUnitTimer.start();
		image.sendToFront();
		
		// timer mechanisms for how a wave works
		ActionListener listener = new ActionListener() {
			private int attackPhase = 0;
		    public void actionPerformed(ActionEvent e) {
		    	boolean outcome = true;
		        if (numTimes >= 2)
		        {
		        	numTimes = 0;
		        	outcome = damageUnit(unit);
		        	// Attack phase logic
		        	// Attack effect
	                switch(attackPhase) {
	                    case 0:
	                        image.move(10, 0);
	                        attackPhase = 1;
	                        break;
	                    case 1:
	                        image.move(-10, 0);
	                        attackPhase = 0;
	                        break;
	                }
		        }
		        // if the unit dies OR robot dies OR unit has been deleted somehow
		        if (outcome == false || health <= 0)
		        {
		        	isMoving = true;
		        	scene.removeUnitFromGrid(unit);
		        	unitAttacking = null;
		        	attackUnitTimer.stop();
		        	attackUnitTimer.removeActionListener(this);
		        }
		        
		        numTimes = numTimes + 1;

		    }};
		    
		    attackUnitTimer.createActionListener(listener);
	}
	
	private int getCurrentColumn() {
	    int gridStartX = scene.gridStartX;
	    int tileWidth = scene.tileWidth;
	    
	    return (int)((image.getX() - gridStartX) / tileWidth);
	}
	
	private int getCurrentUnitColumn(Unit unit) {
	    int gridStartX = scene.gridStartX;
	    int tileWidth = scene.tileWidth;
	    
	    return (int)((unit.getImageFromUnit().getX() - gridStartX) / tileWidth);
	}

	// if an enemy's health reaches zero, it is defeated and disappears from the grid
	public boolean isDefeated() {
		return health <= 0;
	}
	
	public int getCurrencyEarned()
	{
		return currencyEarned;
	}
	
	public void resumeAttackTimer()
	{
		if (attackUnitTimer != null)
		{
			attackUnitTimer.start();
		}
	}
	
	public void stopAttackTimer()
	{
		if (attackUnitTimer != null)
		{
			attackUnitTimer.stop();
		}
	}
	
	// determines if an enemy unit is close enough to a player unit to start attacking and dealing damage to it
	public boolean hasReachedUnit() {
		if (isMoving == true) {
			return false;
		}
		return true;
	}
}
