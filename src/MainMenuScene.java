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
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public MainMenuScene(MainApplication mainApp)
	{
		super(mainApp);
	}
	
	private void drawStartButton() {
		String filename = IMG_FILENAME_PATH + "startButton" + IMG_EXTENSION;
		GImage startImage = new GImage(filename);
		this.startButton = new GButton(startImage,225,200);
		addElement(startButton);
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				startButton.onClick();
				// trigger action to start the game
				// switchSceneTo(new GameScene());
				// switchSceneTo(MainMenuScene()); // it's supposed to be GameScene() like the line above
				System.out.println("Start Button clicked!");
			}
			
			public void mouseEntered (MouseEvent e) {
				startButton.onHover();
			}
		});
	}
	
	private void drawHelpButton() {
		String filename = IMG_FILENAME_PATH + "startButton" + IMG_EXTENSION;
		GImage startImage = new GImage(filename);
		this.startButton = new GButton(startImage,0,400);
		addElement(startButton);
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				startButton.onClick();
				// trigger action to start the game
				// switchSceneTo(new GameScene());
				// switchSceneTo(MainMenuScene()); // it's supposed to be GameScene() like the line above
				System.out.println("Help Button clicked!");
				mainApp.switchSceneTo(mainApp.HowToPlayScene);
			}
			public void mouseEntered (MouseEvent e) {
				startButton.onHover();
			}
		});
	}
	
	public void showContents() {
		drawStartButton();
		drawHelpButton();
		System.out.println("Show contents from this point...");
		addElement(label);
	}
	
	public void hideContents() {
		for (GObject obj : activeContents)
		{
			removeElement(obj);
		}
	}
	
	public static void main(String[] args) {
		
	}
}
