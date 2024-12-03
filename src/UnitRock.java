import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles creating the player unit, or the units that the player will use to defeat enemies
public class UnitRock extends Unit{
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	private static final int Y_UNIT_BAR = 20;
	private static final int X_UNIT_BAR = 20;
	
	public static GameTimer cooldownTimer;
	protected static GLabel cooldownLabel;
	protected static boolean isOnCooldown = false;
	protected static int cooldown; // in function calls per 500MS.
	protected static int numTimesCooldown;
	
	// private GImage image = new GImage(IMG_FILENAME_PATH + "soldier" + IMG_EXTENSION);
	private UnitType unitType = UnitType.ROCK;
	
	private Game game;

	public UnitRock(GameScene gameScene, Game game)
	{
		super(gameScene, game);
		this.image = new GImage(unitType.getImagePath());
		this.health = unitType.getHealth();
        this.cost = unitType.getCost();
        this.frequency = unitType.getFrequency();
        UnitRock.cooldown = unitType.getCooldown();
        
        this.numTimes = 0;
        this.enemyDetected = false;
	}
	 
	public void startTimer()
	{
		// Don't even make a timer, the rock does nothing.
		cooldownLabel = new GLabel("");	
	}
	
	public void routine () {
	}
	
	public void startCooldown()
	{
		cooldownTimer = new GameTimer(500, "Cooldown");
		cooldownTimer.start();
		
		String filename = IMG_FILENAME_PATH + "unitBar_Rock_cooldown" + IMG_EXTENSION;
		GImage unitBarRockImage_cooldown = new GImage(filename);
		double xStart = X_UNIT_BAR;
		double yStart = Y_UNIT_BAR;
		unitBarRockImage_cooldown.setLocation(xStart + unitBarRockImage_cooldown.getWidth() * unitType.getNum(), yStart );
		gameScene.addElement(unitBarRockImage_cooldown);
		
		numTimesCooldown = 0;
		int seconds = cooldown / 2;
		cooldownLabel.setLabel(Integer.toString(seconds));
		reconfigureLabel(cooldownLabel, unitType);
		gameScene.addElement(cooldownLabel);
		
		isOnCooldown = true;
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (numTimesCooldown >= cooldown - 1)
		    	{
		    		gameScene.removeElement(cooldownLabel);
		    		cooldownTimer.stop();
		    		cooldownTimer.removeActionListener(this);
		    		cooldownTimer = null;
		    		isOnCooldown = false;
		    		gameScene.removeElement(unitBarRockImage_cooldown);
		    	}
		    	else
		    	{
		    		numTimesCooldown = numTimesCooldown + 1;
		    		if (numTimesCooldown % 2 == 0)
		    		{
		    			cooldownLabel.setLabel(Integer.toString(seconds - numTimesCooldown / 2));
		    		}
		    	}
		    }};
		    
		cooldownTimer.createActionListener(listener);
	}
	
	
	public boolean isCooldownActive()
	{
		if (isOnCooldown == true)
		{
			return true;
		}
		else
		{
			return false;
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
	
	// if the player unit's health hits zero
	public boolean isDeath() {
		return health <= 0; 
	}
	
	public static void main(String[] args) {
		
	}
}
