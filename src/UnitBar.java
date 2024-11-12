import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;

// at the top of the screen is a unit bar that has all of the player units in it.
// the player can click and drag on a unit in the unit bar and place it down anywhere on the grid (if they have enough currency to afford it)
public class UnitBar {
	private static final int Y_UNIT_BAR = 20;
	private static final int X_UNIT_BAR = 20;
	
	private List<GImage> unitBar = new ArrayList<>();
	private GImage selectedUnit = null;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public void drawUnitBar(GameScene scene) {
		String[] unitsImages = {"soldierUnitBar", "machineGunUnitBar"};
		double xStart = X_UNIT_BAR;
		double yStart = Y_UNIT_BAR;
		
		for (int i = 0; i < unitsImages.length; i++) {
			GImage unit = new GImage(IMG_FILENAME_PATH + unitsImages[i] + IMG_EXTENSION);
			unit.setLocation(xStart + unit.getWidth()*(i), yStart );
			unitBar.add(unit); // Add to unitBar list for tracking
			scene.addElement(unit); // Add to display so it appears on screen
		}
	}
	// handles how many units are in the unit bar
	public GImage handleMousePressed(double x, double y) {
		String[] unit = {"soldier", "machineGun"};
		for (int i = 0; i < unitBar.size(); i++) {
			GImage itemUnitBar = unitBar.get(i);
			if (itemUnitBar.contains(x, y)) {
				selectedUnit = new GImage(IMG_FILENAME_PATH + unit[i] + IMG_EXTENSION);
				selectedUnit.setLocation(x - selectedUnit.getWidth() / 2, y - selectedUnit.getHeight() / 2);
				return selectedUnit;
			}
		}
		return null;
	}
	
	public GImage getSelectedUnit() {
		return selectedUnit;
	}
	
	public void clearSelectedUnit() {
		selectedUnit = null;
	}

}
