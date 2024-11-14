import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// The main menu scene that contains buttons for staring the game
// viewing instruction, and exiting

public class MainMenuScene extends Scene {
	private GLabel label = new GLabel("MainMenuScene", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 2);
	private GButton startButton;
	private GButton helpButton;
	private GButton exitButton;
	private GImage backgroundMainMenu;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public MainMenuScene(MainApplication mainApp)
	{
		super(mainApp);
	}
	
	private void drawStartButton() {
		String filename = IMG_FILENAME_PATH + "startButton" + IMG_EXTENSION;
		GImage startButtonImage = new GImage(filename);
		int startButtonX = (MainApplication.getResolutionWidth() - (int) startButtonImage.getWidth()) / 2;
		int startButtonY = (int)(MainApplication.getResolutionHeight() * 0.6);
		this.startButton = new GButton(startButtonImage,startButtonX,startButtonY);
		addElement(startButton);
		this.startButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				startButton.onClick();
				// trigger action to start the game
				System.out.println("Start Button clicked!");
				mainApp.switchSceneTo(mainApp.LevelSelectScene);
			}
			
			public void mouseEntered (MouseEvent e) {
				startButton.onHover();
			}
		});
	}
	
	private void drawHelpButton() {
		String filename = IMG_FILENAME_PATH + "helpButton" + IMG_EXTENSION;
		GImage helpButtonImage = new GImage(filename);
		int helpButtonX = (int)(MainApplication.getResolutionWidth() * 0.02);
		int helpButtonY = (int)(MainApplication.getResolutionHeight() * 0.02);
		this.helpButton = new GButton(helpButtonImage,helpButtonX,helpButtonY);
		addElement(helpButton);
		this.helpButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				helpButton.onClick();
				// trigger action to start the game
				System.out.println("Help Button clicked!");
				mainApp.switchSceneTo(mainApp.HowToPlayScene);
			}
			public void mouseEntered (MouseEvent e) {
				helpButton.onHover();
			}
		});
	}
	
	private void drawExitButton() {
		String filename = IMG_FILENAME_PATH + "exitButton" + IMG_EXTENSION;
		GImage exitButtonImage = new GImage(filename);
		int exitButtonX = (int)(MainApplication.getResolutionWidth() * 0.90);
		int exitButtonY = (int)(MainApplication.getResolutionHeight() * 0.02);
		this.exitButton = new GButton(exitButtonImage,exitButtonX,exitButtonY);
		addElement(exitButton);
		this.exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				exitButton.onClick();
				// trigger action to start the game
				System.out.println("Help Button clicked!");
				mainApp.exitApplication();
			}
			public void mouseEntered (MouseEvent e) {
				exitButton.onHover();
			}
		});
	}
	
	private void drawBackground() {
		String filename = IMG_FILENAME_PATH + "backgroundMainMenu" + IMG_EXTENSION;
	    this.backgroundMainMenu = new GImage(filename);
	    this.backgroundMainMenu.setLocation(0, 0);
	    mainApp.add(this.backgroundMainMenu);
	}
	
	public void showContents() {
		System.out.println("Show contents from this point...");
		addElement(this.label);
		drawBackground();
		drawStartButton();
		drawHelpButton();
		drawExitButton();
		activeContents.add(this.backgroundMainMenu);

	}
	
	public void hideContents() {
		System.out.println("Hide contents from this point..");
		for (GObject obj : new ArrayList<>(activeContents)) // create a copy to avoid modification
		{
			removeElement(obj);
		}
	}
	
	public static void main(String[] args) {
		
	}
}
