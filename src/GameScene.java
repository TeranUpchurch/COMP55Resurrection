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
	private String labelText;
	private GLabel currencyLabel;
	private GImage selectedUnit = null;
	private UnitType chosenUnitName;
	private GImage currencyBackground;
	private GImage backgroundGameScene;
	
	private int resX = MainApplication.getResolutionWidth();
	private int resY = MainApplication.getResolutionHeight();
	
	private int gridStartX = (int)(resX * 0.05);
	private int gridStartY = (int)(resY * 0.252);
	
	private int gridWidth = (int)(resX * 0.9);
	private int gridHeight = (int)(resY * 0.75);
	
	private int tileWidth;
	private int tileHeight;
	
	private Game game;
	private ArrayList<Projectile> projectileCache = new ArrayList<>();
	private ArrayList<Robot> robotCache = new ArrayList<>();
	private Set<Unit> unitContainer = new HashSet<>();
	private ImageToUnitMap imageToUnitMap = new ImageToUnitMap();
	private ImageToRobotMap imageToRobotMap = new ImageToRobotMap();
	private GameTimer gameTimer;
	private int numTimes = 0;
	private int currency;
	
	private GButton pauseButton;
	private UnitBar unitBar;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public GameScene(MainApplication mainApp, String difficulty)
	{
		super(mainApp);
		labelText = difficulty;
		unitBar = new UnitBar();
	}
	
	private void drawPauseButton() {
		String filename = IMG_FILENAME_PATH + "pauseButton" + IMG_EXTENSION;
		GImage pauseButtonImage = new GImage(filename);
		int pauseButtonX = (int)(MainApplication.getResolutionWidth() * 0.90);
		int pauseButtonY = (int)((MainApplication.getResolutionHeight() * 0.02) + 20);
		this.pauseButton = new GButton(pauseButtonImage,pauseButtonX,pauseButtonY);
		addElement(pauseButton);
		pauseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// trigger return to main menu
				System.out.println("Pause Button clicked!");
				PauseMenu pauseMenu = new PauseMenu ("media/pauseMenu.png", mainApp);
				pauseMenu.showPopup(mainApp); // Display the pause menu
			}
		});
	}
	
	private void drawCurrencyBackground() {
		String filename = IMG_FILENAME_PATH + "currencyBackground" + IMG_EXTENSION;
		currencyBackground = new GImage(filename);
		int currencyBackgroundX = (int)(MainApplication.getResolutionWidth() * 0.70);
		int currencyBackgroundY = (int)((MainApplication.getResolutionHeight() * 0.02) + 20);
		this.currencyBackground.setLocation(currencyBackgroundX, currencyBackgroundY);
		addElement(currencyBackground);
	}
	
	private void drawCurrencyCounter() {
		this.currency = CURRENCY_START;
		this.currencyLabel = new GLabel("" + currency);
		this.currencyLabel.setFont("Arial-Bold-50");
		this.currencyLabel.setColor(Color.BLACK);
		this.currencyLabel.setLocation(currencyBackground.getX() + 0.8 * (currencyBackground.getWidth() / 2), currencyBackground.getY() + 1.3 * (currencyBackground.getHeight() / 2));
		addElement(currencyLabel);
	}
	
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
		// should show notification to player when they do not have enough money. DO IT LATER
		return false;
	}
	private void updateCurrencyLabel() {
		this.currencyLabel.setLabel("" + currency);
	}
	
	// when clicking certain buttons, a new screen will pop up. showContents makes the screens pop up, while hideContents makes them disappear
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
	
	public void hideContents()
	{
		System.out.println("Hide contents from this point..");
		for (GObject obj : new ArrayList<>(activeContents)) // create a copy to avoid modification
		{
			removeElement(obj);
			game = null;
		}
	}
	
	public void drawBackground() {
		String fence = IMG_FILENAME_PATH + "fence" + IMG_EXTENSION;
		String background = IMG_FILENAME_PATH + "backgroundGameScene" + IMG_EXTENSION;
		String ground = IMG_FILENAME_PATH + "ground" + IMG_EXTENSION;
		
		this.backgroundGameScene = new GImage(background);
		GImage fenceImage = new GImage(fence);
		GImage groundImage = new GImage(ground);
		
		int x = gridStartX + gridWidth;
		System.out.println("CHECK " + x);
		
		backgroundGameScene.setLocation(0, 0);
		fenceImage.setLocation(0, gridStartY - fenceImage.getHeight());
		groundImage.setLocation(0, gridStartY);
		
		addElement(backgroundGameScene);
		addElement(fenceImage);
		addElement(groundImage);
	}
	
	public void drawGrid(int rows, int cols)
	{
		String darkTileFilename = IMG_FILENAME_PATH + "darkTile" + IMG_EXTENSION;
		String lightTileFilename = IMG_FILENAME_PATH + "lightTile" + IMG_EXTENSION;
		
		System.out.println("Grid startX: " + gridStartX + " starY: " + gridStartY);
		
		tileWidth = gridWidth / cols;
		tileHeight = gridHeight / rows;
		
		int yOffset = 0;
		for (int i = 0; i < rows; i++)
		{
			int xOffset = 0;
			for (int j = 0; j < cols; j++)
			{
				String filename = ((i + j) % 2 == 0) ? lightTileFilename : darkTileFilename;
				GImage tile = new GImage(filename);
				tile.setSize(tileWidth, tileHeight);
				tile.setLocation(gridStartX + xOffset, gridStartY + yOffset);
				addElement(tile);
				xOffset = xOffset + tileWidth;
			}
			yOffset = yOffset + tileHeight;
		}
	}
	
	public void getSpaceFromCursorPosition(int x, int y)
	{
		
	}
	
	public void startGame()
	{
		System.out.println("Starting game.");
		game = new Game(this);	// default game constructor, this will change when level selection is added
		game.startCurrentWave();
		System.out.println("Starting first wave");
		drawGrid(game.grid.getRows(), game.grid.getCols());
		gameTimer = new GameTimer(50, "Game");
		
		ActionListener listener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// Move all projectiles in cache.
		    	for (Projectile item : projectileCache)
		    	{
		    		item.step();
		    	}
		    	// Move all robots in cache.
		    	for (Robot robot : robotCache)
		    	{
		    		robot.step();
		    	}
		    	// Check the enemy counter - if 0, go to next wave.
		    	if (game.getActiveEnemyCount() <= 0)
		    	{
		    		System.out.println("Starting next wave");
		    		game.incrementWaveNum();
		    		game.startCurrentWave();
		    	}
		    }};
		   
	    gameTimer.createActionListener(listener);
	    gameTimer.start();
	    
	    
	}
	
	public void instantiateRobot(Robot robot)
	{
		System.out.println("Spawn robot" + robot + "at this point.");
	}
	
	public void instantiateUnit(UnitType unitName, int x, int y)
	{
		Unit unit = null;
		
		if (isOutOfBound(x, y))
		{
			return;
		}
		
		switch (unitName) {
			case SOLDIER -> unit = new UnitSoldier(this, x, y);
			case MACHINE_GUN -> unit = new UnitMachineGun(this, x, y);
			
			// case GRENADE;
			case ROCK -> unit = new UnitRock(this, x, y);
		}
		if (unit != null)
		{
			GImage unitImage = unit.getImageFromUnit();
			
			int row = (y - gridStartY) / tileHeight;
			int col = (x - gridStartX) / tileWidth;
			
			if (isOccupied(row, col))
			{
				System.out.println("Invalid space");
				return;
			}
			
			System.out.println(row + " " + col);
				
			int calculatedImageX = gridStartX + col * tileWidth;
			int calculatedImageY = gridStartY + row * tileHeight;
			
			unit.setImagePos(calculatedImageX, calculatedImageY);
			game.grid.setSpace(unit, row, col);
			unit.startTimer();
			imageToUnitMap.addPair(unitImage, unit);
			addElement(unitImage);
		}
		System.out.println("Instantiated unit:" + unitName.getName());
	}
	
	public boolean isOccupied (int row, int col) {
		if (game.grid.getUnitAtSpace(row, col) != null)
		{
			return true;
		}
		return false;
	}
	
	private boolean isOutOfBound(int x, int y) {
		return x < gridStartX || y < gridStartY;
	}
	
	public void instantiateProjectile(Projectile projectile, double x, double y)
	{
		GImage projImage = projectile.getImage();
		projectile.setLocation(x, y);
		addElement(projImage);
		projectileCache.add(projectile);
		System.out.println("Added projectile " + projectile + " to cache");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed.");
		// Check if the mouse click is on one of the squares in unit bar
		chosenUnitName = unitBar.handleMousePressed(e.getX(), e.getY());
		if (chosenUnitName == null)
		{
			return;
		}
		selectedUnit = new GImage(chosenUnitName.getImagePath()); 
		selectedUnit.setLocation(e.getX() - selectedUnit.getWidth() / 2, e.getY() - selectedUnit.getHeight() / 2);
		addElement(selectedUnit);
	}
	
	public void clearSelection() {
		unitBar.clearSelectedUnit();
        removeElement(selectedUnit);
        selectedUnit = null;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (selectedUnit == null) {
			clearSelection();
	        return;
		}
		
		UnitType chosenUnitType = unitBar.getSelectedUnit();
	    if (chosenUnitType != null) {
	    	int row = (e.getY() - gridStartY) / tileHeight;
			int col = (e.getX() - gridStartX) / tileWidth;
			
			if (isOutOfBound(e.getX(), e.getY())) {
				System.out.println("OUT OF BOUND");
				clearSelection();
				return;
			}
			
	    	if (isOccupied(row, col)) {
	    		clearSelection();
		        return;
		    }
	    	
	    	if (canAfford(chosenUnitType.getCost())) {
	    		instantiateUnit(chosenUnitType, e.getX(), e.getY());
	   
	    	} else {
	    		System.out.println("NOT ENOUGH MONEY");
	    	}
	    }
	    unitBar.clearSelectedUnit();
	    removeElement(selectedUnit);
	    selectedUnit = null;
	}
	
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Mouse clicked.");
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// Move the selected unit with the mouse
		if (selectedUnit != null) {
			selectedUnit.setLocation(e.getX() - selectedUnit.getWidth() / 2, e.getY() - selectedUnit.getHeight() / 2);
		}
	}

	public static void main(String[] args) {

	}
	

}
