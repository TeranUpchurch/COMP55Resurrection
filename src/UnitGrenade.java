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
	
	public static GameTimer cooldownTimer;
	protected static GLabel cooldownLabel;
	protected static boolean isOnCooldown = false;
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
	}
	
	public void startTimer()
	{
		routineTimerGrenade = new GameTimer(100, "Grenade");
		routineTimerGrenade.start();
		cooldownLabel = new GLabel("");
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	numTimes = numTimes + 1;
		    	
		    	if (numTimes > 15)
		    	{
		    		image.setColor(new Color(255,0,0));
		    	}
		    	 
		    	if (numTimes > 20) {
		    		routine();
		    		routineTimerGrenade.stop();
		    	}
		    }};
		    
		    routineTimerGrenade.createActionListener(listener);
	}
	
	public void routine () {
		if (gameScene.isPaused()) {
			return;
		}
		explode();
		deleteSelf();
	}
	
	public void deleteSelf()
	{
		gameScene.removeUnitFromGrid(this);
	}
	
	public void explode() {
		double projectileStartX = image.getX() + image.getWidth() / 2; // Right edge of the soldier
		double projectileStartY = image.getY() + image.getHeight() / 2; // Slightly below the top
		int lifetime = 20;
		Projectile projectile1 = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball_Yellow" + IMG_EXTENSION),
				10,
				10,
				5,
				10,
				0,
				projectileStartX,
				projectileStartY,
				lifetime,
				gameScene
				);
		gameScene.instantiateProjectile(projectile1, projectileStartX, projectileStartY);
		Projectile projectile2 = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball_Yellow" + IMG_EXTENSION),
				10,
				10,
				5,
				7,
				7,
				projectileStartX,
				projectileStartY,
				lifetime,
				gameScene
				);
		gameScene.instantiateProjectile(projectile2, projectileStartX, projectileStartY);
		Projectile projectile3 = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball_Yellow" + IMG_EXTENSION),
				10,
				10,
				5,
				0,
				10,
				projectileStartX,
				projectileStartY,
				lifetime,
				gameScene
				);
		gameScene.instantiateProjectile(projectile3, projectileStartX, projectileStartY);
		Projectile projectile4 = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball_Yellow" + IMG_EXTENSION),
				10,
				10,
				5,
				-7,
				7,
				projectileStartX,
				projectileStartY,
				lifetime,
				gameScene
				);
		gameScene.instantiateProjectile(projectile4, projectileStartX, projectileStartY);
		Projectile projectile5 = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball_Yellow" + IMG_EXTENSION),
				10,
				10,
				5,
				-10,
				0,
				projectileStartX,
				projectileStartY,
				lifetime,
				gameScene
				);
		gameScene.instantiateProjectile(projectile5, projectileStartX, projectileStartY);
		Projectile projectile6 = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball_Yellow" + IMG_EXTENSION),
				10,
				10,
				5,
				-7,
				-7,
				projectileStartX,
				projectileStartY,
				lifetime,
				gameScene
				);
		gameScene.instantiateProjectile(projectile6, projectileStartX, projectileStartY);
		Projectile projectile7 = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball_Yellow" + IMG_EXTENSION),
				10,
				10,
				5,
				0,
				-10,
				projectileStartX,
				projectileStartY,
				lifetime,
				gameScene
				);
		gameScene.instantiateProjectile(projectile7, projectileStartX, projectileStartY);
		Projectile projectile8 = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball_Yellow" + IMG_EXTENSION),
				10,
				10,
				5,
				7,
				-7,
				projectileStartX,
				projectileStartY,
				lifetime,
				gameScene
				);
		gameScene.instantiateProjectile(projectile8, projectileStartX, projectileStartY);

		
	}
	
	public void startCooldown()
	{
		cooldownTimer = new GameTimer(500, "Cooldown");
		cooldownTimer.start();
		
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
