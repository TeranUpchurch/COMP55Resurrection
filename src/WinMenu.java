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
	private GButton nextLevelButton;
	private MainApplication mainApp;
	private RestartConfirmation restartConfirmation;
	private LevelMenuConfirmation levelMenuConfirmation;
	private ExitConfirmation exitConfirmation;
	private String difficulty;


	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public WinMenu(String imagePath, MainApplication mainApp, GameTimer gameTimer, GameScene gameScene, String difficulty)
	{
		super(imagePath);  // Use the image as the background for the popup
		this.mainApp = mainApp;
		this.gameTimer = gameTimer;
		this.gameScene = gameScene;
		this.difficulty = difficulty;
		
		String filename1 = IMG_FILENAME_PATH + "menu_winMenu" + IMG_EXTENSION;
		this.winMenu = new GImage(filename1);
		this.biggerRestartButton = drawRestartButton("button_restart_bigger", winMenu);
		this.biggerLevelMenuButton = drawLevelMenuButton("button_levelMenu_bigger", winMenu);
		this.biggerExitButton = drawExitButton("button_exit_bigger", winMenu);		
		this.nextLevelButton = drawNextLevelButton("button_nextLevel", winMenu);
		
		addMenuElement(biggerRestartButton);
		addMenuElement(biggerLevelMenuButton);
		addMenuElement(biggerExitButton);
		
		if (this.difficulty != "Hard") { // if dicculty != hard
			addMenuElement(nextLevelButton);
		}

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
	
	private GButton drawNextLevelButton(String lable, GImage backgroundImage) {
		GImage image = new GImage(IMG_FILENAME_PATH + lable + IMG_EXTENSION);
		double x = (MainApplication.getResolutionWidth() - image.getWidth()) / 2;
		double y = MainApplication.getResolutionHeight() * Y_RATIO_RESUME_BUTTON;
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
		
		nextLevelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleNextLevel();
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
	
	private void handleNextLevel() {
		System.out.println("Next Level button clicked: Showing confirmation dialog.");
		hidePopup(this.mainApp, false);
		// TODO make next level function and class for confirmation screen maybe
		if (difficulty == "Easy") {
			mainApp.switchSceneToGame("Medium");
			System.out.println("Level Switched from Easy to Medium");

		}
		else {
			mainApp.switchSceneToGame("Hard");
			System.out.println("Level Switched from Medium to Hard");
		}	

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
