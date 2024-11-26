import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;
import java.util.List;
import java.util.Set;

// The how to play scene that contains instructions on how to play
// and a button to return to main menu scene..

public class GameScene extends Scene{
	private static final int CURRENCY_START = 200;
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	// Display properties
	private int resX = MainApplication.getResolutionWidth();
	private int resY = MainApplication.getResolutionHeight();
	private int gridStartX = (int)(this.resX * 0.05);
	private int gridStartY = (int)(this.resY * 0.252);
	private int gridWidth = (int)(this.resX * 0.9);
	private int gridHeight = (int)(this.resY * 0.75);
	private int tileWidth;
	private int tileHeight;
	
	// UI elements
	private GLabel currencyLabel;
	private GButton pauseButton;
	private UnitBar unitBar;
	private GImage currencyBackground;
	private GImage backgroundGameScene;
	
	// Game state
	private String labelText;
	private Game game;
	private GameTimer gameTimer;
	private int numTimes = 0;
	private int currency;
	private boolean isPaused;
	
	// Unit management
	private UnitType chosenUnitName;
	private GImage selectedUnit = null;
	private Set<Unit> unitContainer = new HashSet<>();
	private ImageToUnitMap imageToUnitMap = new ImageToUnitMap();
	private ImageToRobotMap imageToRobotMap = new ImageToRobotMap();
	private ArrayList<Projectile> projectileCache = new ArrayList<>();
	private ArrayList<Robot> robotCache = new ArrayList<>();
	private Set<Projectile> projectilesToDestroy = new HashSet<>();
	private Set<Robot> robotsToDestroy = new HashSet<>();
	protected List<Notification> notifications = new ArrayList<>();
	
	public GameScene(MainApplication mainApp, String difficulty)
	{
		super(mainApp);
		labelText = difficulty;
		unitBar = new UnitBar();
	}
	
	@Override
	public void showContents()
	{
		System.out.println("Show contents from this point..");
		addElement(new GLabel(labelText, MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 2));
		drawBackground();
		drawPauseButton();
		unitBar.drawUnitBar(this);
		drawCurrencyBackground();
		drawCurrencyCounter();
		startGame();
	}
	
	@Override
	public void hideContents()
	{
		System.out.println("Hide contents from this point..");
		gameTimer.stop();
		for (GObject obj : new ArrayList<>(activeContents)) // create a copy to avoid modification
		{
			removeElement(obj);
			game = null;
		}
	}
	
	// UI drawing methods
	public void drawBackground() {
		String fence = IMG_FILENAME_PATH + "fence" + IMG_EXTENSION;
		String background = IMG_FILENAME_PATH + "backgroundGameScene" + IMG_EXTENSION;
		String ground = IMG_FILENAME_PATH + "ground" + IMG_EXTENSION;
		
		this.backgroundGameScene = new GImage(background);
		GImage fenceImage = new GImage(fence);
		GImage groundImage = new GImage(ground);
		
		this.backgroundGameScene.setLocation(0, 0);
		fenceImage.setLocation(0, gridStartY - fenceImage.getHeight());
		groundImage.setLocation(0, gridStartY);
		
		addElement(this.backgroundGameScene);
		addElement(fenceImage);
		addElement(groundImage);
	}
	
	public void drawGrid(int rows, int cols)
	{
		String darkTileFilename = IMG_FILENAME_PATH + "darkTile" + IMG_EXTENSION;
		String lightTileFilename = IMG_FILENAME_PATH + "lightTile" + IMG_EXTENSION;
		
		this.tileWidth = this.gridWidth / cols;
		this.tileHeight = this.gridHeight / rows;
		
		int yOffset = 0;
		for (int i = 0; i < rows; i++)
		{
			int xOffset = 0;
			for (int j = 0; j < cols; j++)
			{
				String filename = ((i + j) % 2 == 0) ? lightTileFilename : darkTileFilename;
				GImage tile = new GImage(filename);
				tile.setSize(this.tileWidth, this.tileHeight);
				tile.setLocation(this.gridStartX + xOffset, this.gridStartY + yOffset);
				addElement(tile);
				xOffset = xOffset + this.tileWidth;
			}
			yOffset = yOffset + this.tileHeight;
		}
	}
	
	private void drawPauseButton() {
		String filename = IMG_FILENAME_PATH + "pauseButton" + IMG_EXTENSION;
		GImage pauseButtonImage = new GImage(filename);
		int pauseButtonX = (int)(MainApplication.getResolutionWidth() * 0.90);
		int pauseButtonY = (int)((MainApplication.getResolutionHeight() * 0.02) + 20);
		this.pauseButton = new GButton(pauseButtonImage,pauseButtonX,pauseButtonY);
		addElement(pauseButton);
		PauseMenu pauseMenu = new PauseMenu ("media/pauseMenu.png", mainApp, gameTimer, this);
		pauseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Pause Button clicked!");
				pauseMenu.showPopup(mainApp); // Display the pause menu
			}
		});
	}
	
	private void drawCurrencyBackground() {
		String filename = IMG_FILENAME_PATH + "currencyBackground" + IMG_EXTENSION;
		this.currencyBackground = new GImage(filename);
		int currencyBackgroundX = (int)(MainApplication.getResolutionWidth() * 0.70);
		int currencyBackgroundY = (int)((MainApplication.getResolutionHeight() * 0.02) + 20);
		this.currencyBackground.setLocation(currencyBackgroundX, currencyBackgroundY);
		addElement(this.currencyBackground);
	}
	
	private void drawCurrencyCounter() {
		this.currency = CURRENCY_START;
		this.currencyLabel = new GLabel("" + currency);
		this.currencyLabel.setFont("Arial-Bold-50");
		this.currencyLabel.setColor(Color.BLACK);
		this.currencyLabel.setLocation(this.currencyBackground.getX() + 0.8 * (this.currencyBackground.getWidth() / 2), this.currencyBackground.getY() + 1.3 * (this.currencyBackground.getHeight() / 2));
		addElement(currencyLabel);
	}
	
	private void drawNotification(GImage image, double x, double y, int duration) {
		Notification noti = new Notification (image, x, y, duration);
		notifications.add(noti);
		addElement(noti.getImage());
		System.out.println("Showed notification");
	}
	
	private void updateNotification() {
		notifications.removeIf(notification -> {
			if (notification.isExpired()) {
				mainApp.remove(notification.getImage());
				return true;
			}
			return false;
		});
	}
	
	// Currency management methods
	public void addCurrency(int amount) {
		this.currency += amount;
		updateCurrencyLabel();
	}
	
	public boolean canAfford(int amount) {
		if (this.currency >= amount) {
			currency -= amount;
			updateCurrencyLabel();
			return true;
		}
		return false;
	}
	
	private void updateCurrencyLabel() {
		this.currencyLabel.setLabel("" + currency);
	}
	
	public void getSpaceFromCursorPosition(int x, int y)
	{
		
	}
	
	// Game Logic
	public void startGame()
	{
		System.out.println("Starting game.");
		game = new Game(this);	// default game constructor, this will change when level selection is added
		game.startCurrentWave();
		drawGrid(game.grid.getRows(), game.grid.getCols());
		gameTimer = new GameTimer(25, "Game");
		setPaused(false);
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	for (Projectile proj : projectileCache)
		    	{
		    		proj.step();
		    		
		    		if (proj.getImage().getX() > resX)
		    		{
		    			projectilesToDestroy.add(proj);
		    			continue;
		    		}
		    		
		    		GPoint projLocation = proj.getLocation();
		    		GObject element = getElementAtCoordinate(projLocation.getX() + proj.getImage().getWidth() + proj.getSpeed(), projLocation.getY());
		    		
		    		if (element instanceof GImage)
		    		{
		    			GImage image = (GImage) element;
		    			Robot robot = imageToRobotMap.get(image);

		    			if (robot == null)
			    		{
			    			continue;
			    		}
			    		else
			    		{
			    			// Inflict damage once robot is found.
			    			robot.takeDamage(proj.getDamage());
			    			
			    			// Immediately check if the robot has been defeated after this update.
			    			if (robot.isDefeated())
			    			{
			    				removeElement(image);
			    				imageToRobotMap.deletePair(image);
			    				robotsToDestroy.add(robot);
			    			}
			    			
			    			projectilesToDestroy.add(proj);
			    		}
		    		}
		    		else
		    		{
		    			continue;
		    		}
		    	}
		    	// Move all robots in cache.
		    	for (Robot robot : robotCache)
		    	{
		    		robot.step();
		    	}
		    	
		    	// Remove robots and projectiles from their respective caches according to the robotsToDestroy and projectilesToDestroy sets.
		    	for (Projectile proj : projectilesToDestroy)
		    	{
		    		removeElement(proj.getImage());
		    		projectileCache.remove(proj);
		    	}
		    	for (Robot robot : robotsToDestroy)
		    	{
		    		removeElement(robot.getImage());
		    		robotCache.remove(robot);
		    	}
		    	
		    	// Check the enemy counter - if 0, go to next wave.
		    	if (game.getActiveEnemyCount() <= 0)
		    	{
		    		System.out.println("Starting next wave");
		    		game.incrementWaveNum();
		    		game.startCurrentWave();
		    	}
		    	
		    	updateNotification();
		    }};
		   
	    gameTimer.createActionListener(listener);
	    gameTimer.start();
	}
	
	public void restartLevel() {
		gameTimer.stop();
		
		for (Unit unit : unitContainer) {
			unit.stopRoutine();
		}
		
		clearProjectiles();
		clearUnits();
		clearRobots();
		initializeGameScene();
		game = new Game (this);
		game.resetGame();
		startGame();
		System.out.println("Level restarted.");
	}
	
	public void initializeGameScene() {
		drawBackground();
		drawPauseButton();
		unitBar.drawUnitBar(this);
		drawCurrencyBackground();
		drawCurrencyCounter();
	}

	
	// Unit and Robot management methods
	public void instantiateRobot(Robot robot)
	{
		GImage robotImage = robot.getImage();
		robotImage.setSize(tileWidth, tileHeight);
		robotImage.setLocation(this.resX, gridStartY + robot.getLane() * tileHeight);
		addElement(robotImage);
		imageToRobotMap.addPair(robot.getImage(), robot);
		robotCache.add(robot);
		System.out.println("Added robot " + robot + " to screen.");
	}
	
	public void instantiateUnit(UnitType unitName, int x, int y)
	{
		Unit unit = null;
		
		if (isOutOfBound(x, y))
		{
			return;
		}
		
		switch (unitName) {
			case SOLDIER -> unit = new UnitSoldier(this);
			case MACHINE_GUN -> unit = new UnitMachineGun(this);
			case GRENADE -> unit = new UnitGrenade(this);
			case ROCK -> unit = new UnitRock(this);
		}
		if (unit != null)
		{
			placeUnit(unit, x, y);
			unitContainer.add(unit);
		}
		System.out.println("Instantiated unit:" + unitName.getName());
	}
	
	private void placeUnit(Unit unit, int x, int y) {
		GImage unitImage = unit.getImageFromUnit();
		
		int row = (y - this.gridStartY) / this.tileHeight;
		int col = (x - this.gridStartX) / this.tileWidth;
		
		if (isOccupied(row, col))
		{
			return;
		}
		
		System.out.println(row + " " + col);
			
		int calculatedImageX = this.gridStartX + col * this.tileWidth;
		int calculatedImageY = this.gridStartY + row * this.tileHeight;
		
		unit.setImagePos(calculatedImageX, calculatedImageY);
		game.grid.setSpace(unit, row, col);
		unit.startTimer();
		imageToUnitMap.addPair(unitImage, unit);
		addElement(unitImage);
	}
	
	public void instantiateProjectile(Projectile projectile, double x, double y)
	{
		GImage projImage = projectile.getImage();
		projectile.setLocation(x, y);
		addElement(projImage);
		projectileCache.add(projectile);
		System.out.println("Added projectile " + projectile + " to cache");
		System.out.println("Current number of projectiles: " + projectileCache.size());
	}
	
	public boolean isPaused() {
		return this.isPaused;
	}
	
	public void setPaused (boolean paused) {
		this.isPaused = paused;
	}
	
	// Helper methods
	private boolean isOccupied (int row, int col) {
		if (game.grid.getUnitAtSpace(row, col) != null)
		{
			return true;
		}
		return false;
	}
	
	private boolean isOutOfBound(int x, int y) {
		return x < this.gridStartX || y < this.gridStartY;
	}
	
	private void clearSelection() {
		unitBar.clearSelectedUnit();
        removeElement(this.selectedUnit);
        this.selectedUnit = null;
	}
	
	private void clearProjectiles() {
		for (Projectile projectile : projectileCache) {
			System.out.println("Cleared projectile: " + projectile);
	        removeElement(projectile.getImage()); // Remove the graphical element
	    }
		projectileCache.clear();
		System.out.println("All projectiles cleared");
	}
	
	private void clearUnits() {
		for (Unit unit : unitContainer) {
			System.out.println("Cleared unit: " + unit);
	        removeElement(unit.getImageFromUnit()); // Remove the graphical element
	    }
	    
	    unitContainer.clear();
	    
		System.out.println("All units are cleared.");
	}
	
	private void clearRobots() {
		for (Robot robot : robotCache) {
			System.out.println("Cleared robot: " + robot);
	        removeElement(robot.getImage()); // Remove the graphical element
	    }
		robotCache.clear();
		System.out.println("All robots are cleared.");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed.");
		// Check if the mouse click is on one of the squares in unit bar
		this.chosenUnitName = unitBar.handleMousePressed(e.getX(), e.getY());
		if (this.chosenUnitName == null)
		{
			return;
		}
		this.selectedUnit = new GImage(this.chosenUnitName.getImagePath()); 
		this.selectedUnit.setLocation(e.getX() - this.selectedUnit.getWidth() / 2, e.getY() - this.selectedUnit.getHeight() / 2);
		addElement(this.selectedUnit);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.selectedUnit == null) {
			clearSelection();
	        return;
		}
		
		UnitType chosenUnitType = unitBar.getSelectedUnit();
	    if (chosenUnitType != null) {
	    	int row = (e.getY() - this.gridStartY) / tileHeight;
			int col = (e.getX() - this.gridStartX) / tileWidth;
	    	
	    	if (isOutOfBound(e.getX(), e.getY()) || isOccupied(row, col)) {
	    		clearSelection();
				return;
	    	}
	    	
	    	if (canAfford(chosenUnitType.getCost())) {
	    		instantiateUnit(chosenUnitType, e.getX(), e.getY());
	   
	    	} else {
	    		String filename = IMG_FILENAME_PATH + "notification_notEnoughMoney" + IMG_EXTENSION;
				GImage notEnoughMoney = new GImage(filename);
				notEnoughMoney.setSize(notEnoughMoney.getWidth() * 0.8, notEnoughMoney.getHeight() * 0.8);
				double imageX = (resX - notEnoughMoney.getWidth()) / 2;
				double imageY = (resY - notEnoughMoney.getHeight()) / 2;
				drawNotification(notEnoughMoney, imageX, imageY, 2000);
	    	}
	    }
	    
	    clearSelection();
	}
	
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Mouse clicked.");
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// Move the selected unit with the mouse
		if (this.selectedUnit != null) {
			this.selectedUnit.setLocation(e.getX() - this.selectedUnit.getWidth() / 2, e.getY() - this.selectedUnit.getHeight() / 2);
		}
	}

	public static void main(String[] args) {

	}
	

}
