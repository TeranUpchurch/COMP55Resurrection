import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles creating the player unit, or the units that the player will use to defeat enemies
public class UnitGrenade extends Unit{
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	protected static GameTimer cooldownTimer;
	protected static GLabel cooldownLabel;
	protected static int cooldown; // in function calls per 500MS.
	protected static int numTimesCooldown;
	
	// private GImage image = new GImage(IMG_FILENAME_PATH + "grenade" + IMG_EXTENSION);
	private UnitType unitType = UnitType.GRENADE;
	
	private Game game;

	public UnitGrenade(GameScene gameScene, Game game)
	{	// TO DO CHANGE ATTRIBUTES - NOT FINAL
		super(gameScene, game);
		this.image = new GImage(unitType.getImagePath());
		this.health = unitType.getHealth();
        this.cost = unitType.getCost();
        this.frequency = unitType.getFrequency();
        UnitGrenade.cooldown = unitType.getCooldown();
        
        this.numTimes = 0;
        this.enemyDetected = false;
        cooldownLabel = new GLabel("");
	}
	
	public void startTimer()
	{
		routineTimerGrenade = new GameTimer(100, "Grenade");
		routineTimerGrenade.start();
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	numTimes = numTimes + 1;
		    	 
		    	if (numTimes > 20) {
		    		routine();
		    		numTimes = 0;
		    	}
		    }};
		    
		    routineTimerGrenade.createActionListener(listener);
	}
	
	public void routine () {
		
	}
	
	public void startCooldown()
	{
		cooldownTimer = new GameTimer(500, "Cooldown");
		cooldownTimer.start();
		
		numTimesCooldown = 0;
		cooldownLabel.setLabel(Integer.toString(cooldown));
		cooldownLabel.setLocation(20, 20);
		gameScene.addElement(cooldownLabel);
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (numTimesCooldown >= cooldown)
		    	{
		    		gameScene.removeElement(cooldownLabel);
		    		cooldownTimer.stop();
		    		cooldownTimer.removeActionListener(this);
		    		cooldownTimer = null;
		    	}
		    	else
		    	{
		    		numTimesCooldown = numTimesCooldown + 1;
		    		if (numTimesCooldown % 2 == 0)
		    		{
		    			cooldownLabel.setLabel(Integer.toString(cooldown - numTimesCooldown / 2));
		    		}
		    	}
		    }};
		    
		cooldownTimer.createActionListener(listener);
	}
	
	public boolean isCooldownActive()
	{
		if (cooldownTimer != null)
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
