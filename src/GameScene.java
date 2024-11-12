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
	private GImage selectedUnit = null;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public GameScene(MainApplication mainApp, String difficulty)
	{
		super(mainApp);
		labelText = difficulty;
	}
	
	public void drawUnitBar() {
		String[] unitsImages = {"soldierUnitBar"};
		double xStart = 20;
		double yStart = 20;
		
		for (int i = 0; i < unitsImages.length; i++) {
			GImage unit = new GImage(IMG_FILENAME_PATH + unitsImages[i] + IMG_EXTENSION);
			unit.setLocation(xStart, yStart);
			unitBar.add(unit); // Add to unitBar list for tracking
			addElement(unit); // Add to display so it appears on screen
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
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed.");
		// Check if the mouse click is on one of the squares in unit bar
		for (int i = 0; i < unitBar.size(); i++) {
			GImage itemUnitBar = unitBar.get(i);
			if (itemUnitBar.contains(e.getX(), e.getY())) {
				String[] unit = {"soldier"};
				selectedUnit = new GImage(IMG_FILENAME_PATH + unit[i] + IMG_EXTENSION);
				selectedUnit.setLocation(e.getX() - selectedUnit.getWidth() / 2, e.getY() - selectedUnit.getHeight() / 2);
				addElement(selectedUnit);
				break;
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse released.");
		// When mouse is released, print out the coordinates and placed unit
		if (selectedUnit != null) {
			System.out.println("Item placed at x: " + selectedUnit.getX() + "; y: " + selectedUnit.getY());
			selectedUnit = null;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked.");
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Mouse dragged.");
		// Move the selected unit with the mouse
		if (selectedUnit != null) {
			selectedUnit.setLocation(e.getX() - selectedUnit.getWidth() / 2, e.getY() - selectedUnit.getHeight() / 2);
		}
	}

	public static void main(String[] args) {

	}
	

}
