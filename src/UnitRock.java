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
	
	// private GImage image = new GImage(IMG_FILENAME_PATH + "soldier" + IMG_EXTENSION);
	private UnitType unitType = UnitType.ROCK;

	public UnitRock(GameScene gameScene)
	{
		super(gameScene);
		this.image = new GImage(unitType.getImagePath());
		this.health = unitType.getHealth();
        this.cost = unitType.getCost();
        this.frequency = unitType.getFrequency();
        this.numTimes = 0;
        this.enemyDetected = false;
	}
	 
	public void startTimer()
	{
		routineTimerRock = new GameTimer(0, "Rock");
		routineTimerRock.start();
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	numTimes = numTimes + 1;
		    	 
		    	if (numTimes > 20) {
		    		routine();
		    		numTimes = 0;
		    	}
		    }};
		    
		    routineTimerRock.createActionListener(listener);
	}
	
	public void routine () {
	}
		/*double projectileStartX = image.getX() + image.getWidth(); // Right edge of the soldier
        double projectileStartY = image.getY() + 0.15 * image.getHeight(); // Slightly below the top
		Projectile projectile = new Projectile(
				new GImage(IMG_FILENAME_PATH + "paintball" + IMG_EXTENSION),
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
	} */
	
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
