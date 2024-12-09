import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;
import java.util.List;
import java.util.Set;

public class GameScene extends Scene{
	// Constant variables
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	private static final int CURRENCY_START = 200;
	private static final double UNIT_REMOVAL_REFUND_RATIO = 0.5;
	
	private static final double DELETE_BUTTON_Y_RATIO = 0.02;
	private static final int DELETE_BUTTON_Y_OFFSET = 20;
	private static final double DELETE_BUTTON_X_RATIO = 0.43;
	
	private static final double PAUSE_BUTTON_Y_RATIO = 0.02;
	private static final int PAUSE_BUTTON_Y_OFFSET = 20;
	private static final double PAUSE_BUTTON_X_RATIO = 0.90;
	
	private static final double CURRENCY_BACKGROUND_Y_RATIO = 0.02;
	private static final int CURRENCY_BACKGROUND_Y_OFFSET = 20;
	private static final double CURRENCY_BACKGROUND_X_RATIO = 0.70;
	private static final double CURRENCY_LABEL_Y_MULTIPLIER = 1.3;
	private static final double CURRENCY_LABEL_X_MULTIPLIER = 0.8;
	
	private static final double NOTIFICATION_Y_POSITION_RATIO = 0.17;
	private static final double NOTIFICATION_X_POSITION_RATIO = 0.47;
	private static final double NOTIFICATION_WIDTH_SCALE = 0.8;
	private static final int NOTIFICATION_DURATION = 2000;
	
	// Display properties
	private int resX = MainApplication.getResolutionWidth();
	private int resY = MainApplication.getResolutionHeight();
	public int gridStartX = (int)(this.resX * 0.05);
	public int gridStartY = (int)(this.resY * 0.252);
	private int gridWidth = (int)(this.resX * 0.9);
	private int gridHeight = (int)(this.resY * 0.75);
	public int tileWidth;
	public int tileHeight;
	
	// UI elements
	private GLabel currencyLabel;
	private GButton pauseButton;
	private UnitBar unitBar;
	private GImage currencyBackground;
	private GImage backgroundGameScene;
	private GButton deleteButton;
	private GButton deleteButtonInUse;
	private WinMenu winMenu;
	private LossMenu lossMenu;
	
	// Game state
	private String difficulty;
	public Game game;
	private GameTimer gameTimer;
	private int currency;
	private boolean isInDeleteState = false;
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
		this.difficulty = difficulty;
		this.unitBar = new UnitBar();
		
		String winMenuImagePath = IMG_FILENAME_PATH + "menu_winMenu" + IMG_EXTENSION;
		String defeatMenuImagePath = IMG_FILENAME_PATH + "menu_defeatMenu" + IMG_EXTENSION;
		this.lossMenu = new LossMenu(defeatMenuImagePath, mainApp, gameTimer, this);
		this.winMenu = new WinMenu(winMenuImagePath, mainApp, gameTimer, this, difficulty);
	}
	
	@Override
	public void showContents()
	{
		System.out.println("Show contents from this point..");
		addElement(new GLabel(difficulty, MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 2));
		initializeGameScene();
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
		String fence = IMG_FILENAME_PATH + "backgroundGS_fence" + IMG_EXTENSION;
		String background = IMG_FILENAME_PATH + "backgroundGameScene" + IMG_EXTENSION;
		String ground = IMG_FILENAME_PATH + "backgroundGS_ground" + IMG_EXTENSION;
		
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
		String darkTileFilename = IMG_FILENAME_PATH + "tile_dark" + IMG_EXTENSION;
		String lightTileFilename = IMG_FILENAME_PATH + "tile_light" + IMG_EXTENSION;
		
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
		String filename = IMG_FILENAME_PATH + "button_pause" + IMG_EXTENSION;
		GImage pauseButtonImage = new GImage(filename);
		int pauseButtonX = (int)(MainApplication.getResolutionWidth() * PAUSE_BUTTON_X_RATIO);
		int pauseButtonY = (int)((MainApplication.getResolutionHeight() * PAUSE_BUTTON_Y_RATIO) + PAUSE_BUTTON_Y_OFFSET);
		this.pauseButton = new GButton(pauseButtonImage,pauseButtonX,pauseButtonY);
		addElement(pauseButton);
		PauseMenu pauseMenu = new PauseMenu ("media/menu_pauseMenu.png", mainApp, gameTimer, this);
		pauseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Pause Button clicked!");
				pauseMenu.showPopup(mainApp); 
			}
		});
	}
	
	private void drawCurrencyBackground() {
		String filename = IMG_FILENAME_PATH + "background_currency" + IMG_EXTENSION;
		this.currencyBackground = new GImage(filename);
		int currencyBackgroundX = (int)(MainApplication.getResolutionWidth() * CURRENCY_BACKGROUND_X_RATIO);
		int currencyBackgroundY = (int)((MainApplication.getResolutionHeight() * CURRENCY_BACKGROUND_Y_RATIO) + CURRENCY_BACKGROUND_Y_OFFSET);
		this.currencyBackground.setLocation(currencyBackgroundX, currencyBackgroundY);
		addElement(this.currencyBackground);
	}
	
	private void drawCurrencyCounter() {
		this.currency = CURRENCY_START;
		this.currencyLabel = new GLabel("" + currency);
		this.currencyLabel.setFont("Arial-Bold-50");
		this.currencyLabel.setColor(Color.BLACK);
		double currencyLabel_X = this.currencyBackground.getX() + CURRENCY_LABEL_X_MULTIPLIER * (this.currencyBackground.getWidth() / 2);
		double currencyLabel_Y = this.currencyBackground.getY() + CURRENCY_LABEL_Y_MULTIPLIER * (this.currencyBackground.getHeight() / 2);
		this.currencyLabel.setLocation(currencyLabel_X, currencyLabel_Y);
		addElement(currencyLabel);
	}
	
	private void drawNotification(GImage image, double x, double y, int duration) {
		Notification noti = new Notification (image, x, y, duration);
		notifications.add(noti);
		addElement(noti.getImage());
	}
	
	private void drawDeleteButton() {
		String filename = IMG_FILENAME_PATH + "button_delete" + IMG_EXTENSION;
		GImage deleteButtonImage = new GImage(filename);
		int imageX = (int)(MainApplication.getResolutionWidth() * DELETE_BUTTON_X_RATIO);
		int imageY = (int)((MainApplication.getResolutionHeight() * DELETE_BUTTON_Y_RATIO) + DELETE_BUTTON_Y_OFFSET);
		this.deleteButton = new GButton(deleteButtonImage,imageX,imageY);
		addElement(deleteButton);
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isPaused()) return;
				GameTimer delay = new GameTimer(50, "DeleteButtonDelay");
				delay.start();
				
				ActionListener listener = new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	isInDeleteState = true;
				    	String filename = IMG_FILENAME_PATH + "button_delete_InUse" + IMG_EXTENSION;
				    	System.out.println("Delete state is now: " + isInDeleteState);
				    	GImage deleteButtonImage = new GImage(filename);
						int imageX = (int)(MainApplication.getResolutionWidth() * DELETE_BUTTON_X_RATIO);
						int imageY = (int)((MainApplication.getResolutionHeight() * DELETE_BUTTON_Y_RATIO) + DELETE_BUTTON_Y_OFFSET);
						deleteButtonInUse = new GButton(deleteButtonImage,imageX,imageY);
						addElement(deleteButtonInUse);
						
				    	delay.stop();
				    	delay.removeActionListener(this);
				    }};
				delay.createActionListener(listener);
			}
		});
	}
	
	// Currency management methods
	public void addCurrency(int amount) {
		this.currency += amount;
		updateCurrencyLabel();
	}
	
	public boolean canAfford(int amount) {
		if (this.currency >= amount) {
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
		game = new Game(this, difficulty);	// default game constructor, this will change when level selection is added
		game.startCurrentWave();
		drawGrid(game.grid.getRows(), game.grid.getCols());
		gameTimer = new GameTimer(25, "Game");
		setPaused(false);
		
		// Ensure that projectile cache is empty
		projectileCache.clear();
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (game.gameScene.isPaused()) return;
		    	
		    	for (Projectile proj : projectileCache)
		    	{
		    		proj.step();
		    		
		    		if (!imageIsInBounds(proj.getImage())) 
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
			    				// If the robot's HP is 0 or less after do the following:
			    				// delete Robot from screen, 
			    				// remove from backend 
			    				// add currency
			    				// decrement Game activeEnemyCount
			    				handleRobotDeath(robot);
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
		    		if (!robot.isMoving) continue;
		    		robot.step();
		    		if (robot.getImageX() < 0)
		    		{
		    			System.out.println("Game over.");
		    			setPaused(true);
		    			lossMenu.showPopup(mainApp);
		    		}
		    	}
		    	
		    	// Remove robots and projectiles from their respective caches according to the robotsToDestroy and projectilesToDestroy sets.
		    	for (Projectile proj : projectilesToDestroy)
		    	{
		    		System.out.println("Removing " + proj + " from cache");
		    		removeElement(proj.getImage());
		    		projectileCache.remove(proj);
		    	}
		    	for (Robot robot : robotsToDestroy)
		    	{
		    		removeElement(robot.getImage());
		    		robotCache.remove(robot);
		    	}
		    	
		    	projectilesToDestroy.clear();
		    	robotsToDestroy.clear();
		    	
		    	// Check the enemy counter - if 0, check if boss wave is next, all waves are complete, or neither. Otherwise go to next wave and start it
		    	if (game.getActiveEnemyCount() <= 0)
		    	{
		    		if (game.isBossWaveNext())
		    		{
		    			System.out.println("Starting BOSS wave");
		    			game.incrementWaveNum();		    		
			    		game.startCurrentWave();
		    		}
		    		else if (game.isWaveNumMax())
		    		{
		    			System.out.println("You win");
		    			setPaused(true);
		    			winMenu.showPopup(mainApp);
		    		}
		    		else
		    		{
		    			System.out.println("Starting next wave");
		    			game.incrementWaveNum();		    		
			    		game.startCurrentWave();
		    		}		    		
		    	}
		    	
		    	updateNotification();
		    }};
		   
	    gameTimer.createActionListener(listener);
	    gameTimer.start();
	}
	
	public void restartLevel() {
		gameTimer.stop();
		
		for (Unit unit : unitContainer) {
			unit.clearTimers();
		}
		
		clearProjectiles();
		clearUnits();
		clearRobots();
		initializeGameScene();
		game.resetGame();
		game = null;
		game = new Game(this, difficulty);
		resetAllCooldowns();
		startGame();
		System.out.println("Level restarted.");
	}
	
	public void initializeGameScene() {
		drawBackground();
		drawPauseButton();
		drawDeleteButton();
		List<UnitType> chosenUnits = useDefaultUnits();
		unitBar.drawUnitBar(this, chosenUnits);
		drawCurrencyBackground();
		drawCurrencyCounter();
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
	
	public void handleRobotDeath(Robot robot) {
		removeElement(robot.getImage());
		imageToRobotMap.deletePair(robot.getImage());
		robotsToDestroy.add(robot);
		addCurrency(robot.getCurrencyEarned());
		game.decrementEnemyCount();
	}
	
	// Returns the default set of 4 units to be chosen in the UnitBar.
	public List<UnitType> useDefaultUnits()
	{
		List<UnitType> allTypes = new ArrayList<>();
		allTypes.add(UnitType.SOLDIER);
		allTypes.add(UnitType.MACHINE_GUN);
		allTypes.add(UnitType.GRENADE);
		allTypes.add(UnitType.ROCK);
		return allTypes;
	}

	// INCOMPLETE - loads default units for now into UnitBar.
	public List<UnitType> groupChosenUnits()
	{
		List<UnitType> allTypes = new ArrayList<>();
		allTypes.add(UnitType.SOLDIER);
		allTypes.add(UnitType.MACHINE_GUN);
		allTypes.add(UnitType.GRENADE);
		allTypes.add(UnitType.ROCK);
		return allTypes;
	}
	
	// Unit and Robot management methods
	public void instantiateRobot(Robot robot)
	{
		GImage robotImage = robot.getImage();
		robotImage.setLocation(this.resX, gridStartY + robot.getLane() * tileHeight - robotImage.getHeight() / 6 );
		addElement(robotImage);
		imageToRobotMap.addPair(robot.getImage(), robot);
		robotCache.add(robot);
		System.out.println("Added robot " + robot + " to screen.");
	}
	
	public ArrayList<Robot> getRobotsInLane(int lane) {
		ArrayList<Robot> robotsInLane = new ArrayList<>();
		for (Robot robot : robotCache) {
			if (robot.getLane() == lane) {
				robotsInLane.add(robot);
			}
		}
		return robotsInLane;
	}
	
	public ArrayList<Unit> getUnitsInLane(int lane) {
		ArrayList<Unit> unitsInLane = new ArrayList<>();
		for (Unit unit : unitContainer) {
			if (unit.getLane() == lane) {
				unitsInLane.add(unit);
			}
		}
		return unitsInLane;
	}
	
	public void instantiateUnit(UnitType unitName, int x, int y)
	{
		Unit unit = null;
		
		if (isOutOfBound(x, y))
		{
			return;
		}
		
		switch (unitName) {
			case SOLDIER -> unit = new UnitSoldier(this, game);
			case MACHINE_GUN -> unit = new UnitMachineGun(this, game);
			case GRENADE -> unit = new UnitGrenade(this, game);
			case ROCK -> unit = new UnitRock(this, game);
		}
		
		// Check if the chosen unit's static timer variable is not null.
		// If it is still active and running return (this is a cooldown).
		if (unit.isCooldownActive() == true)
		{
			return;
		}
		
		this.currency -= unit.getCost();
		updateCurrencyLabel();
		
		if (unit != null)
		{
			placeUnit(unit, x, y);
			unit.startCooldown();
			unitContainer.add(unit);
		}
		System.out.println("Instantiated unit:" + unitName.getName());
	}
	
	private void placeUnit(Unit unit, int x, int y) {
		GImage unitImage = unit.getImageFromUnit();
		
		int row = (y - this.gridStartY) / this.tileHeight;
		int col = (x - this.gridStartX) / this.tileWidth;
		
		int calculatedImageX = this.gridStartX + col * this.tileWidth;
		int calculatedImageY = this.gridStartY + row * this.tileHeight;
		
		if (unit instanceof UnitGrenade) {
			calculatedImageX = (int)(this.gridStartX + col * this.tileWidth + this.tileWidth / 2 - unitImage.getWidth() / 2);
			calculatedImageY = (int)(this.gridStartY + row * this.tileHeight + this.tileHeight / 2 - unitImage.getHeight() / 2);
		}
		
		if (isOccupied(row, col))
		{
			return;
		}
					
		int lane = calculateLane(y);
		unit.setLane(lane);
		
		unit.setImagePos(calculatedImageX, calculatedImageY);
		unit.setLocation(row, col);
		game.grid.setSpace(unit, row, col);
		unit.startTimer();
		imageToUnitMap.addPair(unitImage, unit);
		addElement(unitImage);
	}
	
	public void removeUnitFromGrid(Unit unit)
	{
		unitContainer.remove(unit);
		unit.takeDamage(unit.getHealth());
		unit.clearTimers();
		game.removeUnitFromGrid(unit);
		removeElement(unit.getImageFromUnit());
	}
	
	public int calculateLane(int y) {
		int lane = (y - this.gridStartY) / this.tileHeight;
		return lane;
	}
	
	public void instantiateProjectile(Projectile projectile, double x, double y)
	{
		GImage projImage = projectile.getImage();
		projectile.setLocation(x, y);
		addElement(projImage);
		projectileCache.add(projectile);
		System.out.println("Added projectile " + projectile + " to cache");
	}
	
	public boolean isPaused() {
		return this.isPaused;
	}
	
	public void setPaused (boolean paused) {
		this.isPaused = paused;
		
		if (paused) gameTimer.pause();
		else gameTimer.resume();
		
		toggleAllRobotTimers(paused);
		toggleCooldownTimers(paused);
	}
	
	public UnitBar returnUnitBar()
	{
		return unitBar;
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
	
	public GObject getElementAtCoordinate(double x, double y)
	{
		return this.mainApp.getElementAt(x, y);
	}
	
	private void clearSelection() {
		unitBar.clearSelectedUnit();
        removeElement(this.selectedUnit);
        this.selectedUnit = null;
	}
	
	private void toggleAllRobotTimers(boolean paused)
	{
		if (paused == true)
		{
			for (Robot robot : robotCache)
			{
				if (robot.hasReachedUnit())
				{
					robot.stopAttackTimer();
				}
			}
		}
		else
		{
			for (Robot robot : robotCache)
			{
				if (robot.hasReachedUnit())
				{
					robot.resumeAttackTimer();
				}
			}
		}
	}
	
	private void toggleCooldownTimers(boolean paused)
	{
		if (paused == true)
		{
			if (UnitSoldier.cooldownTimer != null) {UnitSoldier.cooldownTimer.stop();}
			if (UnitMachineGun.cooldownTimer != null) {UnitMachineGun.cooldownTimer.stop();}
			if (UnitGrenade.cooldownTimer != null) {UnitGrenade.cooldownTimer.stop();}
			if (UnitRock.cooldownTimer != null) {UnitRock.cooldownTimer.stop();}
		}
		else
		{
			if (UnitSoldier.cooldownTimer != null) {UnitSoldier.cooldownTimer.start();}
			if (UnitMachineGun.cooldownTimer != null) {UnitMachineGun.cooldownTimer.start();}
			if (UnitGrenade.cooldownTimer != null) {UnitGrenade.cooldownTimer.start();}
			if (UnitRock.cooldownTimer != null) {UnitRock.cooldownTimer.start();}
		}
	}
	
	private void resetAllCooldowns()
	{
		UnitSoldier.resetCooldown();
		UnitMachineGun.resetCooldown();
		UnitGrenade.resetCooldown();
		UnitRock.resetCooldown();
	}
	
	private void clearProjectiles() {
		for (Projectile projectile : projectileCache) {
	        removeElement(projectile.getImage());
	    }
		projectileCache.clear();
		System.out.println("All projectiles cleared");
	}
	
	private void clearUnits() {
		for (Unit unit : unitContainer) {
	        removeElement(unit.getImageFromUnit());
	    }
		unitContainer.clear();
		System.out.println("All units are cleared.");
	}
	
	private void clearRobots() {
		for (Robot robot : robotCache) {
	        removeElement(robot.getImage()); 
	    }
		robotCache.clear();
		System.out.println("All robots are cleared.");
	}
	
	private boolean imageIsInBounds(GImage image)
	{
		double x = image.getX();
		double y = image.getY();
		if (x > this.resX || x < 0)
		{
			return false;
		}
		
		if (y > this.resY || y < 0)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (isPaused()) {
			return;
		}
		
		this.chosenUnitName = unitBar.handleMousePressed(e.getX(), e.getY());
		if (this.chosenUnitName == null)
		{
			return;
		}
		
		Unit tempUnit = null;
		switch(this.chosenUnitName) {
			case SOLDIER -> tempUnit = new UnitSoldier(this, game);
			case MACHINE_GUN -> tempUnit = new UnitMachineGun(this, game);
			case GRENADE -> tempUnit = new UnitGrenade(this, game);
			case ROCK -> tempUnit = new UnitRock(this, game);
		}
		if (tempUnit != null && tempUnit.isCooldownActive()) {
			return;
		}
		this.selectedUnit = new GImage(this.chosenUnitName.getImagePath()); 
		this.selectedUnit.setLocation(e.getX() - this.selectedUnit.getWidth() / 2, e.getY() - this.selectedUnit.getHeight() / 2);
		addElement(this.selectedUnit);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.selectedUnit != null) {
			this.selectedUnit.setLocation(e.getX() - this.selectedUnit.getWidth() / 2, e.getY() - this.selectedUnit.getHeight() / 2);
		}
	}

	public static void main(String[] args) {

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
	    		String filename = IMG_FILENAME_PATH + "notification_canNotPlaceUnit" + IMG_EXTENSION;
	    		GImage canNotPlaceUnit = new GImage(filename);
	    		canNotPlaceUnit.setSize(canNotPlaceUnit.getWidth() * NOTIFICATION_WIDTH_SCALE, canNotPlaceUnit.getHeight() * NOTIFICATION_WIDTH_SCALE);
	    		double imageX = resX * NOTIFICATION_X_POSITION_RATIO;
				double imageY = resY * NOTIFICATION_Y_POSITION_RATIO;
				drawNotification(canNotPlaceUnit, imageX, imageY, NOTIFICATION_DURATION);
				return;
	    	}
	    	
	    	if (canAfford(chosenUnitType.getCost())) {
	    		instantiateUnit(chosenUnitType, e.getX(), e.getY());
	   
	    	} else {
	    		String filename = IMG_FILENAME_PATH + "notification_notEnoughMoney" + IMG_EXTENSION;
				GImage notEnoughMoney = new GImage(filename);
				notEnoughMoney.setSize(notEnoughMoney.getWidth() * NOTIFICATION_WIDTH_SCALE, notEnoughMoney.getHeight() * NOTIFICATION_WIDTH_SCALE);
				double imageX = resX * NOTIFICATION_X_POSITION_RATIO;
				double imageY = resY * NOTIFICATION_Y_POSITION_RATIO;
				drawNotification(notEnoughMoney, imageX, imageY, NOTIFICATION_DURATION);
	    	}
	    }
	    
	    clearSelection();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (isPaused()) {
			return;
		}
		
		if (this.isInDeleteState == true)
		{
			int mouseX = e.getX();
			int mouseY = e.getY();
			GObject element = getElementAtCoordinate(mouseX, mouseY);
			if (element instanceof GImage)
			{
				GImage image = (GImage) element;
				Unit unit = imageToUnitMap.get(image);
				if (unit != null)
				{
					// Remove unit from the grid and give some money back (half of unit cost).
					unit.takeDamage(unit.getHealth());
					removeUnitFromGrid(unit);
					addCurrency((int) (unit.getCost() * UNIT_REMOVAL_REFUND_RATIO));
					System.out.println("Successfully removed unit " + unit + " from grid and returned half money.");
					this.isInDeleteState = false;
					System.out.println("Delete state is now: " + isInDeleteState);
					removeElement(deleteButtonInUse);
				}
			}
			else
			{
				System.out.println("Could not find unit to remove.");
				this.isInDeleteState = false;
				System.out.println("Delete state is now: " + isInDeleteState);
				removeElement(deleteButtonInUse);
				return;
			}
		}
	}
}
