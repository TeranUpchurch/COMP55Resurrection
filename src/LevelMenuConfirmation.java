import acm.graphics.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// this class controls what happens on the level select screen, such as what level of difficulty the player chooses to play the game on
public class LevelMenuConfirmation extends PopupMenu{
	private static final double Y_RATIO = 0.60;
	private static final int BUTTON_DISTANCE = 305;
	
	private GImage levelMenuBackground;
	private GButton confirmButton;
	private GButton cancelButton;
	private MainApplication mainApp;
	private PauseMenu pauseMenu;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public LevelMenuConfirmation(String imagePath, MainApplication mainApp, PauseMenu pauseMenu) {
		super(imagePath);
		this.pauseMenu = pauseMenu;
		this.mainApp = mainApp;
		
		String filename = IMG_FILENAME_PATH + "levelMenuBackground" + IMG_EXTENSION;
		this.levelMenuBackground = new GImage(filename);
		this.confirmButton = drawConfirmButton("confirmButton", levelMenuBackground); 
		this.cancelButton = drawCancelButton("cancelButton", levelMenuBackground);
		
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
	// if the player wants to go back to the main menu
	private void handleConfirm() {
		System.out.println("Exiting to level select scene...");
		hidePopup(mainApp);
		mainApp.switchSceneTo(mainApp.LevelSelectScene);
	}
	
	private void handleCancel() {
		System.out.println("Cancel");
		hidePopup(mainApp);
		pauseMenu.showPopup(mainApp);
	}
	
}
