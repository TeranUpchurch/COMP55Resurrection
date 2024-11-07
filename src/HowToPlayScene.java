import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// The how to play scene that contains instructions on how to play
// and a button to return to main menu scene..

public class HowToPlayScene extends Scene{
	private GLabel label = new GLabel("HowToPlayScene", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 2);
	
	private GButton returnButton;
	private GButton pauseButton;

	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public HowToPlayScene(MainApplication mainApp)
	{
		super(mainApp);
	}
	
	private void drawReturnButton() {
		String filename = IMG_FILENAME_PATH + "returnButton" + IMG_EXTENSION;
		GImage returnButtonImage = new GImage(filename);
		int returnButtonX = (int)(MainApplication.getResolutionWidth() * 0.90);
		int returnButtonY = (int)(MainApplication.getResolutionHeight() * 0.02);
		this.returnButton = new GButton(returnButtonImage,returnButtonX,returnButtonY);
		addElement(returnButton);
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				returnButton.onClick();
				// trigger return to main menu
				System.out.println("Return Button clicked!");
				// mainApp.switchSceneTo(mainApp.getPreviousScene());
				mainApp.returnToPreviousScene();
			}
			public void mouseEntered (MouseEvent e) {
				returnButton.onHover();
			}
		});
	}
	
	private void drawPauseButton() {
		String filename = IMG_FILENAME_PATH + "pauseButton" + IMG_EXTENSION;
		GImage pauseButtonImage = new GImage(filename);
		int pauseButtonX = (int)(0);
		int pauseButtonY = (int)(0);
		this.pauseButton = new GButton(pauseButtonImage,pauseButtonX,pauseButtonY);
		addElement(pauseButton);
		pauseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pauseButton.onClick();
				// trigger return to main menu
				System.out.println("Pause Button clicked!");
				PauseMenu pauseMenu = new PauseMenu ("media/pauseMenu.png");
				pauseMenu.showPopup(mainApp); // Display the pause menu
			}
			public void mouseEntered (MouseEvent e) {
				pauseButton.onHover();
			}
		});
	}
	
	public void showContents()
	{
		System.out.println("Show contents from this point..");
		addElement(label);
		drawReturnButton();
		drawPauseButton();

	}
	
	public void hideContents()
	{
		System.out.println("Hide contents from this point..");
		for (GObject obj : new ArrayList<>(activeContents)) // create a copy to avoid modification
		{
			removeElement(obj);
		}
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
