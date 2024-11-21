import acm.graphics.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// this class handles the player restarting the level after hitting the pause button
public class RestartConfirmation extends PopupMenu{
	private static final double Y_RATIO = 0.60;
	private static final int BUTTON_DISTANCE = 305;
	
	private GImage restartBackground;
	private GButton confirmButton;
	private GButton cancelButton;
	private MainApplication mainApp;
	private PauseMenu pauseMenu;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public RestartConfirmation(String imagePath, MainApplication mainApp, PauseMenu pauseMenu) {
		super(imagePath);
		this.pauseMenu = pauseMenu;
		this.mainApp = mainApp;
		
		String filename = IMG_FILENAME_PATH + "restartBackground" + IMG_EXTENSION;
		this.restartBackground = new GImage(filename);
		this.confirmButton = drawConfirmButton("confirmButton", restartBackground); 
		this.cancelButton = drawCancelButton("cancelButton", restartBackground);
		
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
		System.out.println("Restarting game...");
		// TODO restart the level
	}
	
	private void handleCancel() {
		System.out.println("Cancel");
		hidePopup(mainApp, false);
		pauseMenu.showPopup(mainApp);
	}
	
}
