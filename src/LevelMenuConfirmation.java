import acm.graphics.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LevelMenuConfirmation extends PopupMenu{
	private static final double Y_RATIO = 0.60;
	private static final int BUTTON_DISTANCE = 305;
	
	private GImage levelMenuBackground;
	private GButton confirmButton;
	private GButton cancelButton;
	private MainApplication mainApp;
	private PauseMenu pauseMenu;
	private WinMenu winMenu;
	private LossMenu lossMenu;


	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public LevelMenuConfirmation(String imagePath, MainApplication mainApp, PauseMenu pauseMenu) {
		super(imagePath);
		this.pauseMenu = pauseMenu;
		this.mainApp = mainApp;
	
		this.confirmButton = drawConfirmButton("button_confirm", levelMenuBackground); 
		this.cancelButton = drawCancelButton("button_cancel", levelMenuBackground);
		
		addMenuElement(confirmButton);
		addMenuElement(cancelButton);
		addMouseListeners();
	}
	
	public LevelMenuConfirmation(String imagePath, MainApplication mainApp, WinMenu winMenu) {
		super(imagePath);
		this.winMenu = winMenu;
		this.mainApp = mainApp;
	
		this.confirmButton = drawConfirmButton("button_confirm", levelMenuBackground); 
		this.cancelButton = drawCancelButton("button_cancel", levelMenuBackground);
		
		addMenuElement(confirmButton);
		addMenuElement(cancelButton);
		addMouseListeners();
	}
	
	public LevelMenuConfirmation(String imagePath, MainApplication mainApp, LossMenu lossMenu) {
		super(imagePath);
		this.lossMenu = lossMenu;
		this.mainApp = mainApp;
	
		this.confirmButton = drawConfirmButton("button_confirm", levelMenuBackground); 
		this.cancelButton = drawCancelButton("button_cancel", levelMenuBackground);
		
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
		System.out.println("Exiting to level select scene...");
		hidePopup(mainApp, false);
		mainApp.switchSceneTo(mainApp.LevelSelectScene);
	}
	
	private void handleCancel() {
		System.out.println("Cancel");
		hidePopup(mainApp, false);
		if (pauseMenu != null) {
			pauseMenu.showPopup(mainApp);
	    } 
		else if (winMenu != null) {
	        winMenu.showPopup(mainApp);
	    } 
		else if (lossMenu != null) {
	        lossMenu.showPopup(mainApp);
	    }	
	}
	
}
