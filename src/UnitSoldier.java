import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// this class handles creating the player unit, or the units that the player will use to defeat enemies
public class UnitSoldier extends Unit{
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	protected static GameTimer cooldownTimer;
	protected static int cooldown; // in function calls per 500MS.
	protected static int numTimesCooldown;
	
	// private GImage image = new GImage(IMG_FILENAME_PATH + "soldier" + IMG_EXTENSION);
	private UnitType unitType = UnitType.SOLDIER;
	
	private Game game;

	public UnitSoldier(GameScene gameScene, Game game)
	{
		super(gameScene, game);
		this.image = new GImage(unitType.getImagePath());
        this.health = unitType.getHealth();
        this.cost = unitType.getCost();
        this.frequency = unitType.getFrequency();
        UnitSoldier.cooldown = unitType.getCooldown();
        this.numTimes = 0;
        this.enemyDetected = false;
        this.lane = lane;
	}
	
	public void startTimer()
	{
		routineTimerSoldier = new GameTimer(100, "Soldier");
		routineTimerSoldier.start();
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	numTimes = numTimes + 1;
		    	 
		    	if (numTimes > frequency) {
		    		routine();
		    		numTimes = 0;
		    	}
		    }};
		    
		    routineTimerSoldier.createActionListener(listener);
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
			System.out.println("No enemy in lane " + lane);
			return;
		}
		
	}
	
	@Override
	public void shoot() {
		double projectileStartX = image.getX() + image.getWidth(); // Right edge of the soldier
		double projectileStartY = image.getY() + 0.15 * image.getHeight(); // Slightly below the top
		if (!gameScene.isPaused() && gameScene.game.grid.getUnitAtSpace(lane, getCurrentColumn()) != null
			&& !gameScene.getRobotsInLane(lane).isEmpty()) {
			Projectile projectile = new Projectile(
					new GImage(IMG_FILENAME_PATH + "paintball_Yellow" + IMG_EXTENSION),
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
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (numTimesCooldown >= cooldown)
		    	{
		    		cooldownTimer.stop();
		    		cooldownTimer.removeActionListener(this);
		    		cooldownTimer = null;
		    	}
		    	else
		    	{
		    		numTimesCooldown = numTimesCooldown + 1;
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
	
	public boolean isItUpgradable(boolean upgrade) {
		return this.upgradable && upgrade && unitToUpgradeTo != null;
	}
	
	public boolean isDeath() {
		return health <= 0; 
	}
	
	public static void main(String[] args) {
		
	}
}
