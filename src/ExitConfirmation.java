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
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public ExitConfirmation(String imagePath, MainApplication mainApp, PauseMenu pauseMenu) {
		super(imagePath);
		this.pauseMenu = pauseMenu;
		this.mainApp = mainApp;
		
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
	
	private void handleConfirm() {
		System.out.println("Exiting to main menu scene...");
		hidePopup(mainApp);
		mainApp.switchSceneTo(mainApp.MainMenuScene);
	}
	
	private void handleCancel() {
		System.out.println("Cancel");
		hidePopup(mainApp);
		pauseMenu.showPopup(mainApp);
	}
	
}
