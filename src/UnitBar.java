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
	
	private List<GImage> unitBarImages = new ArrayList<>();
	private UnitType selectedUnit = null;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public void drawUnitBar(GameScene scene) {
		String[] unitsImages = {"soldierUnitBar", "machineGunUnitBar", "grenadeUnitBar", "rockUnitBar"};
		double xStart = X_UNIT_BAR;
		double yStart = Y_UNIT_BAR;
		
		for (int i = 0; i < unitsImages.length; i++) {
			GImage unit = new GImage(IMG_FILENAME_PATH + unitsImages[i] + IMG_EXTENSION);
			unit.setLocation(xStart + unit.getWidth()*(i), yStart );
			unitBarImages.add(unit); // Add to unitBar list for tracking
			scene.addElement(unit); // Add to display so it appears on screen
		}
	}
	
	// handles how many units are in the unit bar
	public UnitType handleMousePressed(double x, double y) {
		for (int i = 0; i < unitBarImages.size(); i++) {
			GImage itemUnitBar = unitBarImages.get(i);
			if (itemUnitBar.contains(x, y)) {
				selectedUnit = UnitType.values()[i];
				// selectedUnit.setLocation(x - selectedUnit.getWidth() / 2, y - selectedUnit.getHeight() / 2);
				return selectedUnit;
			}
		}
		return null;
	}
	
	public UnitType getSelectedUnit() {
		return selectedUnit;
	}
	
	public void clearSelectedUnit() {
		selectedUnit = null;
	}

}
