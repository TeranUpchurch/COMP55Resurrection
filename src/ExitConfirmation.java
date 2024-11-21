import acm.graphics.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitConfirmation extends PopupMenu{
	private static final double Y_RATIO = 0.60;
	private static final int BUTTON_DISTANCE = 305;
	
	private GImage exitBackground;
	private GButton confirmButton;
	private GButton cancelButton;
	private MainApplication mainApp;
	private PauseMenu pauseMenu;
	private GameTimer gameTimer;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	// handles exiting the game
	public ExitConfirmation(String imagePath, MainApplication mainApp, PauseMenu pauseMenu, GameTimer gameTimer) {
		super(imagePath);
		this.pauseMenu = pauseMenu;
		this.mainApp = mainApp;
		this.gameTimer = gameTimer;
		
		String filename = IMG_FILENAME_PATH + "exitBackground" + IMG_EXTENSION;
		this.exitBackground = new GImage(filename);
		this.confirmButton = drawConfirmButton("confirmButton", exitBackground); 
		this.cancelButton = drawCancelButton("cancelButton", exitBackground);
		
		addMenuElement(confirmButton);
		addMenuElement(cancelButton);
		addMouseListeners();
	}
	
	private GButton drawCancelButton(String label, GImage background) {
		GImage image = new GImage(IMG_FILENAME_PATH + label + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - image.getWidth()) / 3;
		double y = MainApplication.getResolutionHeight() * Y_RATIO;
		GButton button = new GButton(image, x, y);
		return button;
	}
	
	private GButton drawConfirmButton(String label, GImage background) {
		GImage image = new GImage(IMG_FILENAME_PATH + label + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - image.getWidth()) / 3 + BUTTON_DISTANCE;
		double y = MainApplication.getResolutionHeight() * Y_RATIO;
		GButton button = new GButton(image, x, y);
		return button;
	}
	
	private void addMouseListeners() {
		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleConfirm();
			}
		});
		
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleCancel();
			}
		});
		
	}
	
	// exits the game
	private void handleConfirm() {
		System.out.println("Exiting to main menu scene...");
		hidePopup(mainApp, false);
		mainApp.switchSceneTo(mainApp.MainMenuScene);
	}
	
	// if the player hits a button then realizes they don't want to use the function they clicked on, this handles them canceling that function
	private void handleCancel() {
		System.out.println("Cancel");
		hidePopup(mainApp, false);
		pauseMenu.showPopup(mainApp);
	}
	
	@Override
	protected void pauseGame (GraphicsApplication mainApp) {
		super.pauseGame(mainApp);
	}
	
	@Override
	protected void resumeGame (GraphicsApplication mainApp) {
		super.resumeGame(mainApp);
	}
	
}
