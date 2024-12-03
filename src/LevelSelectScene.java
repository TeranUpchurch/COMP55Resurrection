import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// The level select scene that contains buttons to select
// difficulty level of game..

public class LevelSelectScene extends Scene{
	private GLabel label = new GLabel("LevelSelectScene", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 4);
	
	private GImage backgroundLevelSelectScene;
	private GButton easyLevelButton;
	private GButton mediumLevelButton;
	private GButton hardLevelButton;
	private GButton returnButton;
	private GButton helpButton;

	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public LevelSelectScene(MainApplication mainApp)
	{
		super(mainApp);
	}
	
	private void drawEasyLevelButton() {
		String filename = IMG_FILENAME_PATH + "button_easy" + IMG_EXTENSION;
		GImage easyButtonImage = new GImage(filename);
		this.easyLevelButton = new GButton(easyButtonImage,MainApplication.getResolutionWidth() / 16,MainApplication.getResolutionHeight() / 2);
		addElement(easyLevelButton);
		easyLevelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// trigger return to main menu
				System.out.println("Easy Level Button clicked!");
				mainApp.switchSceneToGame("Easy"); // for now switch to main menu scene
			}
		});
	}
	
	
	private void drawMediumLevelButton() {
		String filename = IMG_FILENAME_PATH + "button_medium" + IMG_EXTENSION;
		GImage mediumButtonImage = new GImage(filename);
		this.mediumLevelButton = new GButton(mediumButtonImage,6 * MainApplication.getResolutionWidth() / 16,MainApplication.getResolutionHeight() / 2);
		addElement(mediumLevelButton);
		mediumLevelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// trigger return to main menu
				System.out.println("Return Button clicked!");
				mainApp.switchSceneToGame("Medium");
			}
		});
	}
	
	private void drawHardLevelButton() {
		String filename = IMG_FILENAME_PATH + "button_hard" + IMG_EXTENSION;
		// change from robot to "X" button
		GImage hardButtonImage = new GImage(filename);
		this.hardLevelButton = new GButton(hardButtonImage, 11 * MainApplication.getResolutionWidth() / 16,MainApplication.getResolutionHeight() / 2);
		addElement(hardLevelButton);
		hardLevelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// trigger return to main menu
				System.out.println("Return Button clicked!");
				mainApp.switchSceneToGame("Hard");
			}
		});
	}
	
	private void drawReturnButton() {
		String filename = IMG_FILENAME_PATH + "button_return" + IMG_EXTENSION;
		GImage returnButtonImage = new GImage(filename);
		int returnButtonX = (int)(MainApplication.getResolutionWidth() * 0.90);
		int returnButtonY = (int)(MainApplication.getResolutionHeight() * 0.02);
		this.returnButton = new GButton(returnButtonImage,returnButtonX,returnButtonY);
		addElement(returnButton);
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// trigger return to main menu
				System.out.println("Return Button clicked!");
				mainApp.returnToPreviousScene();
			}
		});
	}
	
	private void drawHelpButton() {
		String filename = IMG_FILENAME_PATH + "button_help" + IMG_EXTENSION;
		GImage helpButtonImage = new GImage(filename);
		int helpButtonX = (int)(MainApplication.getResolutionWidth() * 0.015);
		int helpButtonY = (int)(MainApplication.getResolutionHeight() * 0.02);
		this.helpButton = new GButton(helpButtonImage,helpButtonX,helpButtonY);
		addElement(helpButton);
		this.helpButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// trigger action to start the game
				System.out.println("Help Button clicked!");
				mainApp.switchSceneTo(mainApp.HowToPlayScene);
			}
		});
	}
	
	private void drawBackground() {
		String filename = IMG_FILENAME_PATH + "backgroundLevelSelectScene" + IMG_EXTENSION;
	    this.backgroundLevelSelectScene = new GImage(filename);
	    this.backgroundLevelSelectScene.setLocation(0, 0);
	    mainApp.add(this.backgroundLevelSelectScene);
	}
	
	public void showContents()
	{
		System.out.println("Show contents from this point..");
		addElement(label);
		drawBackground();
		drawEasyLevelButton();
		drawMediumLevelButton();
		drawHardLevelButton();
		drawReturnButton();
		drawHelpButton();
		activeContents.add(this.backgroundLevelSelectScene);
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
