import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// The level select scene that contains buttons to select
// difficulty level of game..

public class LevelSelectScene extends Scene{
	private GLabel label = new GLabel("LevelSelectScene", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 2);
	
	private GButton easyLevelButton;
	private GButton mediumLevelButton;
	private GButton hardLevelButton;

	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public LevelSelectScene(MainApplication mainApp)
	{
		super(mainApp);
	}
	
	private void drawEasyLevelButton() {
		String filename = IMG_FILENAME_PATH + "robot" + IMG_EXTENSION;
		// change from robot to "X" button
		GImage returnImage = new GImage(filename);
		this.easyLevelButton = new GButton(returnImage,0,0);
		addElement(easyLevelButton);
		easyLevelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				easyLevelButton.onClick();
				// trigger return to main menu
				System.out.println("Return Button clicked!");
				mainApp.switchSceneTo(mainApp.MainMenuScene);
			}
			public void mouseEntered (MouseEvent e) {
				easyLevelButton.onHover();
			}
		});
	}
	
	private void drawMediumLevelButton() {
		String filename = IMG_FILENAME_PATH + "robot" + IMG_EXTENSION;
		// change from robot to "X" button
		GImage returnImage = new GImage(filename);
		this.mediumLevelButton = new GButton(returnImage,0,0);
		addElement(mediumLevelButton);
		mediumLevelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mediumLevelButton.onClick();
				// trigger return to main menu
				System.out.println("Return Button clicked!");
				mainApp.switchSceneTo(mainApp.MainMenuScene);
			}
			public void mouseEntered (MouseEvent e) {
				mediumLevelButton.onHover();
			}
		});
	}
	
	private void drawHardLevelButton() {
		String filename = IMG_FILENAME_PATH + "robot" + IMG_EXTENSION;
		// change from robot to "X" button
		GImage returnImage = new GImage(filename);
		this.hardLevelButton = new GButton(returnImage,0,0);
		addElement(hardLevelButton);
		hardLevelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				hardLevelButton.onClick();
				// trigger return to main menu
				System.out.println("Return Button clicked!");
				mainApp.switchSceneTo(mainApp.MainMenuScene);
			}
			public void mouseEntered (MouseEvent e) {
				hardLevelButton.onHover();
			}
		});
	}
	
	
	public void showContents()
	{
		System.out.println("Show contents from this point..");
		mainApp.add(label);
		drawEasyLevelButton();
		drawMediumLevelButton();
		drawHardLevelButton();

	}
	
	public void hideContents()
	{
		System.out.println("Hide contents from this point..");
		for (GObject obj : activeContents)
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
