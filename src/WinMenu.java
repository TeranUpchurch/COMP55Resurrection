import acm.graphics.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// this class handles the win menu and when the player wins the game

public class WinMenu extends PopupMenu{
	private static final double Y_RATIO = 0.4;
	private static final double Y_RATIO_RESUME_BUTTON = 0.65;

	private GameTimer gameTimer;
	private GameScene gameScene;

	private GLabel label = new GLabel("winMenu", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 4);
	
	private GImage winMenu;
	private GButton biggerRestartButton;
	private GButton biggerLevelMenuButton;
	private GButton biggerExitButton;
	private MainApplication mainApp;
	private RestartConfirmation restartConfirmation;
	private LevelMenuConfirmation levelMenuConfirmation;
	private ExitConfirmation exitConfirmation;


	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public WinMenu(String imagePath, MainApplication mainApp, GameTimer gameTimer, GameScene gameScene)
	{
		super(imagePath);  // Use the image as the background for the popup
		this.mainApp = mainApp;
		this.gameTimer = gameTimer;
		this.gameScene = gameScene;
		
		String filename1 = IMG_FILENAME_PATH + "menu_winMenu" + IMG_EXTENSION;
		this.winMenu = new GImage(filename1);
		this.biggerRestartButton = drawRestartButton("button_restart_bigger", winMenu);
		this.biggerLevelMenuButton = drawLevelMenuButton("button_levelMenu_bigger", winMenu);
		this.biggerExitButton = drawExitButton("button_exit_bigger", winMenu);		
		
		addMenuElement(biggerRestartButton);
		addMenuElement(biggerLevelMenuButton);
		addMenuElement(biggerExitButton);
		
		addMouseListeners();
		
	}
	
	private GButton drawRestartButton(String lable, GImage backgroundImage) {
		GImage image = new GImage(IMG_FILENAME_PATH + lable + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - backgroundImage.getWidth()) / 2 + 30;
		double y = MainApplication.getResolutionHeight() * Y_RATIO;
		GButton button = new GButton(image, x, y);
		
		return button;
	}
	
	private GButton drawLevelMenuButton(String lable, GImage backgroundImage) {
		GImage image = new GImage(IMG_FILENAME_PATH + lable + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - backgroundImage.getWidth()) / 2 + 240;
		double y = MainApplication.getResolutionHeight() * Y_RATIO;
		GButton button = new GButton(image, x, y);

		return button;
	}
	
	private GButton drawExitButton(String lable, GImage backgroundImage) {
		GImage image = new GImage(IMG_FILENAME_PATH + lable + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - backgroundImage.getWidth()) / 2 + 450;
		double y = MainApplication.getResolutionHeight() * Y_RATIO;
		GButton button = new GButton(image, x, y);

		return button;
	}
	
	
	//TODO: ADD FOR ANOTHER BUTTONS
	private void addMouseListeners() {
		biggerRestartButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleRestart();
			}
		});
		
		biggerLevelMenuButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleLevelSelect();
			}
		});
		

		biggerExitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleExit();
			}
		});
		
	}
	
	private void handleRestart() {
		System.out.println("Restart button clicked: Showing confirmation dialog.");
		hidePopup(this.mainApp, false);
		
		String filename = IMG_FILENAME_PATH + "background_restartConfirm" + IMG_EXTENSION;
		this.restartConfirmation = new RestartConfirmation(filename, mainApp, this);
		this.restartConfirmation.showPopup(mainApp); // Display the confirmation menu
	}
	
	private void handleExit() {
		System.out.println("Exit button clicked: Showing confirmation dialog.");
		hidePopup(this.mainApp, false);
		
		String filename = IMG_FILENAME_PATH + "background_exitConfirm" + IMG_EXTENSION;
		this.exitConfirmation = new ExitConfirmation(filename, mainApp, this, gameTimer);
		this.exitConfirmation.showPopup(mainApp); // Display the confirmation menu
	}

	private void handleLevelSelect() {
		System.out.println("LevelSelect button clicked: Showing confirmation dialog.");
		hidePopup(this.mainApp, false);
		
		String filename = IMG_FILENAME_PATH + "background_levelMenuConfirm" + IMG_EXTENSION;
		this.levelMenuConfirmation = new LevelMenuConfirmation(filename, mainApp, this);
		this.levelMenuConfirmation.showPopup(mainApp); // Display the confirmation menu
	}
	
	
	/*@Override
	protected void pauseGame(GraphicsApplication mainApp) {
		super.pauseGame(mainApp);
		gameScene.setPaused(true);
		if (gameTimer != null) {
			gameTimer.stop();
		}
	}*/
	
}
