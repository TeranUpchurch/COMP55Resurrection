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
	private GImage backgroundHowToPlayScene;
	private GImage howToPlayDescription;

	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public HowToPlayScene(MainApplication mainApp)
	{
		super(mainApp);
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
				System.out.println("Return Button clicked!");
				mainApp.returnToPreviousScene();
			}
		});
	}
	
	private void drawBackground() {
		String filename = IMG_FILENAME_PATH + "background_HowToPlayScene" + IMG_EXTENSION;
	    this.backgroundHowToPlayScene = new GImage(filename);
	    this.backgroundHowToPlayScene.setLocation(0, 0);
	    mainApp.add(this.backgroundHowToPlayScene);
	}
	
	private void drawDescription() {
		String filename = IMG_FILENAME_PATH + "HowToPlayDescription" + IMG_EXTENSION;
	    this.howToPlayDescription = new GImage(filename);
	    this.howToPlayDescription.setLocation(MainApplication.getResolutionWidth() * 0.09, MainApplication.getResolutionHeight() * 0.02);
	    mainApp.add(this.howToPlayDescription);
	}
	
	
	public void showContents()
	{
		System.out.println("Show contents from this point..");
		addElement(label);
		drawBackground();
		drawDescription();
		drawReturnButton();
		activeContents.add(this.backgroundHowToPlayScene);

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
