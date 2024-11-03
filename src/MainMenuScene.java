import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// The main menu scene that contains buttons for staring the game
// viewing instruction, and exiting
public class MainMenuScene extends Scene {
	private GLabel label = new GLabel("MainMenuScene", MainApplication.getResolutionWidth(), MainApplication.getResolutionHeight());
	private MainApplication mainApp;
	private GButton startButton;
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	@Override
	public void init() {
		setSize(MainApplication.getResolutionWidth(), MainApplication.getResolutionHeight());
		requestFocus();
		showContents();
	}
	
	@Override
	public void run() {
		addMouseListeners();
	}
	
	
	private void drawButton() {
		String filename = IMG_FILENAME_PATH + "startButton" + IMG_EXTENSION;
		GImage startImage = new GImage(filename);
		this.startButton = new GButton(startImage,225,400);
		add(this.startButton);
		
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				startButton.onClick();
				// trigger action to start the game
				// switchSceneTo(new GameScene());
				// switchSceneTo(MainMenuScene()); // it's supposed to be GameScene() like the line above
			}
			
			public void mouseEntered (MouseEvent e) {
				startButton.onHover();
			}
		});
	}
	
	public void showContents() {
		drawButton();
		System.out.println("Show contents from this point..");
		addElement(label);
	}
	
	public void hideContents() {
		remove(startButton);
	}
	
	public static void main(String[] args) {
		new MainMenuScene().start();
	}
}
