import acm.graphics.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PauseMenu extends PopupMenu{
	private GLabel label = new GLabel("pauseMenu", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 4);
	
	private GImage pauseMenu;
	private GButton biggerRestartButton;
	private GButton resumeButton;
	private MainApplication mainApp;

	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public PauseMenu(String imagePath, MainApplication mainApp)
	{
		super(imagePath);  // Use the image as the background for the popup
		this.mainApp = mainApp;
		
		String filename1 = IMG_FILENAME_PATH + "pauseMenu" + IMG_EXTENSION;
		this.pauseMenu = new GImage(filename1);
		this.biggerRestartButton = drawRestartButton("biggerRestartButton", pauseMenu);
		this.resumeButton = drawResumeButton("resumeButton", pauseMenu);
		
		addMenuElement(biggerRestartButton);
		addMenuElement(resumeButton);
		
		addMouseListeners();
		
	}
	
	private GButton drawRestartButton(String lable, GImage backgroundImage) {
		GImage image = new GImage(IMG_FILENAME_PATH + lable + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - backgroundImage.getWidth()) / 2 + 30;
		double y = MainApplication.getResolutionHeight() * 0.4;
		GButton button = new GButton(image, x, y);
		
		return button;
	}
	
	private GButton drawResumeButton(String lable, GImage backgroundImage) {
		GImage image = new GImage(IMG_FILENAME_PATH + lable + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - image.getWidth()) / 2;
		double y = MainApplication.getResolutionHeight() * 0.65;
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
		
		resumeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleResume();
			}
		});
	}
	
	private void handleRestart() {
		System.out.println("Restarting game...");
		// Logic to restart the game (e.g., restarting level)
	}
	private void handleResume() {
		System.out.println("Resuming game...");
		hidePopup(this.mainApp);
	}
	
	private void handleExit() {
        System.out.println("Exiting to main menu...");
        // Logic to exit to main menu (e.g., loading MainMenuScene)
    }
	
	
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed.");
	}
	
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse released.");
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked.");
	}
	
	public void mouseDragged(MouseEvent e) {
		System.out.println("Mouse dragged.");
	}

	public static void main(String[] args) {

	}

}
