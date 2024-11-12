import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;

// The how to play scene that contains instructions on how to play
// and a button to return to main menu scene..

public class GameScene extends Scene{
	private String labelText;
	private GLabel label;
	
	private List<GImage> unitBar = new ArrayList<>();
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public GameScene(MainApplication mainApp, String difficulty)
	{
		super(mainApp);
		labelText = difficulty;
	}
	
	public void drawUnitBar() {
		String[] itemsImages = {"soldierUnitBar"};
		double xStart = 20;
		double yStart = 20;
		
		for (int i = 0; i < itemsImages.length; i++) {
			GImage item = new GImage(IMG_FILENAME_PATH + itemsImages[i] + IMG_EXTENSION);
			item.setLocation(xStart, yStart);
			unitBar.add(item); // Add to unitBar list for tracking
			addElement(item); // Add to display so it appears on screen
		}
	}
	
	public void showContents()
	{
		System.out.println("Show contents from this point..");
		addElement(new GLabel(labelText, MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 2));
		drawUnitBar();
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
