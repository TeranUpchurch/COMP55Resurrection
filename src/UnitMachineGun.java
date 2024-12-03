import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles creating the player unit, or the units that the player will use to defeat enemies
public class UnitMachineGun extends Unit{
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public static GameTimer cooldownTimer;
	protected static GLabel cooldownLabel;
	protected static boolean isOnCooldown = false;
	protected static int cooldown; // in function calls per 500MS.
	protected static int numTimesCooldown;
	
	private UnitType unitType = UnitType.MACHINE_GUN;
	
	private Game game;

	public UnitMachineGun(GameScene gameScene, Game game)
	{
		super(gameScene, game);
		this.image = new GImage(unitType.getImagePath());
		this.health = unitType.getHealth();
        this.cost = unitType.getCost();
        this.frequency = unitType.getFrequency();
        UnitMachineGun.cooldown = unitType.getCooldown();
        
        this.numTimes = 0;
        this.enemyDetected = false;
	}
	
	public void startTimer()
	{
		routineTimerMachineGun = new GameTimer(20, "MachineGun");
		routineTimerMachineGun.start();
		cooldownLabel = new GLabel("");
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	numTimes = numTimes + 1;
		    	 
		    	if (numTimes > 20) {
		    		routine();
		    		numTimes = 0;
		    	}
		    }};
		    
		    routineTimerMachineGun.createActionListener(listener);
	}
	
	@Override
	public void routine () {
		if (gameScene.isPaused()) {
			return;
		}
		if (checkForEnemy()) {
			shoot();
		}
		else {
			System.out.println("No enemy in lane ");
			return;
		}
	}
	
	@Override
	public void shoot() {
		double projectileStartX = image.getX() + image.getWidth(); // Right edge of the machine gun
        double projectileStartY = image.getY() + 0.15 * image.getHeight(); // Slightly below the top
        if (!gameScene.isPaused() && gameScene.game.grid.getUnitAtSpace(lane, getCurrentColumn()) != null
    			&& !gameScene.getRobotsInLane(lane).isEmpty()) {
        	Projectile projectile = new Projectile(
    				new GImage(IMG_FILENAME_PATH + "paintball_Red" + IMG_EXTENSION),
    				10,
    				10,
    				10,
    				10,
    				projectileStartX,
    				projectileStartY,
    				gameScene
    				);
    		gameScene.instantiateProjectile(projectile, projectileStartX, projectileStartY);
    		System.out.println("Instantiated projectile from " + this);
        }
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
