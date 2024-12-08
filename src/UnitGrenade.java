import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
// this class handles creating the player unit, or the units that the player will use to defeat enemies
	public class UnitGrenade extends Unit{
	private static final int EXPLOSION_RANGE = 140;
	private static final int EXPLOSION_DAMAGE = 70;
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	private static final int Y_UNIT_BAR = 20;
	private static final int X_UNIT_BAR = 20;
	
	public static GameTimer cooldownTimer;
	protected static GLabel cooldownLabel;
	protected static boolean isOnCooldown = false;
	protected static int cooldown; // in function calls per 500MS.
	protected static int numTimesCooldown;
	
	// private GImage image = new GImage(IMG_FILENAME_PATH + "grenade" + IMG_EXTENSION);
	private UnitType unitType = UnitType.GRENADE;
	private Game game;
	
	boolean[] isEffectVisible = {false};
	
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
		this.lane = lane;
	}
	
	public void startTimer()
	{
		routineTimerGrenade = new GameTimer(100, "Grenade");
		routineTimerGrenade.start();
		cooldownLabel = new GLabel("");
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	numTimes = numTimes + 1;
		    	
	            if (isEffectVisible[0]) {
	                image.setVisible(false);
	            } else {
	                image.setVisible(true);
	            }
		    	
		    	isEffectVisible[0] = !isEffectVisible[0]; // Toggle the flag
		    	
		    	if (numTimes > 50) {
		    		isEffectVisible[0] = false; 
			    	if (checkForEnemyInRange()) {
			    		startCountdownToExplode();  // Initiate countdown if detected
			    		routineTimerGrenade.stop();
			    	}
		    	}
		    	
		    }};
		   
		    routineTimerGrenade.createActionListener(listener);
	}
	
	public void routine () {
		
	}
	
	private boolean checkForEnemyInRange() {
		if (gameScene == null || gameScene.getRobotsInLane(this.lane) == null) {
			return false;
		}
		
		ArrayList<Robot> robotsInLane = gameScene.getRobotsInLane(this.lane);
		double grenadeX = image.getX();
		
		for (Robot robot : robotsInLane) {
			double robotX = robot.getImage().getX();
			if (Math.abs(robotX - grenadeX) <= 140) {  // Check distance
	            return true;  // Robot detected in range
	        }
		}
		return false;
}
	
	private void startCountdownToExplode() {
		GameTimer countdownTimer = new GameTimer(2000, "GrenadeExplosion") ;
		countdownTimer.start();
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				explode();
				countdownTimer.stop();
				
			}
		};
		countdownTimer.createActionListener(listener);
	}
	
	private void explode() {
		ArrayList<Robot> robotsInLane = gameScene.getRobotsInLane(this.lane);
		double grenadeX = image.getX();
		
		String filename = IMG_FILENAME_PATH + "effect_grenade_explode" + IMG_EXTENSION;
		GImage effect = new GImage(filename);
	    int calculatedEffectX = (int) (gameScene.gridStartX + getCurrentColumn() * gameScene.tileWidth + gameScene.tileWidth / 2 - effect.getWidth() / 2);
	    int calculatedEffectY = (int) (gameScene.gridStartY + lane * gameScene.tileHeight + gameScene.tileHeight / 2 - effect.getHeight() / 2);
	    effect.setLocation(calculatedEffectX, calculatedEffectY); 
	    gameScene.addElement(effect);

		
		if (!gameScene.isPaused() && gameScene.game.grid.getUnitAtSpace(lane, getCurrentColumn()) != null
				&& !gameScene.getRobotsInLane(lane).isEmpty()) {
			for (Robot robot : robotsInLane) {
				double robotX = robot.getImage().getX();
				if (Math.abs(robotX - grenadeX) <= EXPLOSION_RANGE) {  // Check if robots are still in range
					gameScene.addElement(effect);
					robot.takeDamage(EXPLOSION_DAMAGE);
					if (robot.isDefeated()) {
						gameScene.handleRobotDeath(robot);
						
					}
				}
			}
		}
	    gameScene.removeUnitFromGrid(this);
	    GameTimer explosionEffectTimer = new GameTimer (1000, "ExplosionEffect");
	    explosionEffectTimer.start();
	    
	    ActionListener listener = new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		gameScene.removeElement(effect);
	    		explosionEffectTimer.stop();
	    	}
	    };
	    explosionEffectTimer.createActionListener(listener);

	    
	    System.out.println("Grenade exploded!");
	}
	
	public void startCooldown() {
		cooldownTimer = new GameTimer(500, "Cooldown");
cooldownTimer.start();
		
		String filename = IMG_FILENAME_PATH + "unitBar_grenade_cooldown" + IMG_EXTENSION;
		GImage unitBarGrenadeImage_cooldown = new GImage(filename);
		double xStart = X_UNIT_BAR;
		double yStart = Y_UNIT_BAR;
		unitBarGrenadeImage_cooldown.setLocation(xStart + unitBarGrenadeImage_cooldown.getWidth() * unitType.getNum(), yStart );
		gameScene.addElement(unitBarGrenadeImage_cooldown);
		
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
		    		gameScene.removeElement(unitBarGrenadeImage_cooldown);
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
