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
	private List<UnitType> typeList = new ArrayList<>();
	private UnitType selectedUnit = null;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public void drawUnitBar(GameScene scene, List<UnitType> chosenTypes) {
		String[] unitsImages = {"unitBar_soldier", "unitBar_machineGun", "unitBar_grenade", "unitBar_rock"};
		
		typeList = chosenTypes;
		
		double xStart = X_UNIT_BAR;
		double yStart = Y_UNIT_BAR;
		
		for (int i = 0; i < chosenTypes.size(); i++) {
				GImage unitBarImage = new GImage(chosenTypes.get(i).getUnitBarImagePath());
				unitBarImage.setLocation(xStart + unitBarImage.getWidth()*(i), yStart );
				unitBarImages.add(unitBarImage); // Add to unitBar list for tracking
				scene.addElement(unitBarImage); // Add unit image to display so it appears on screen
		}
	}
	
	public UnitType handleMousePressed(double x, double y) {
		for (int i = 0; i < unitBarImages.size(); i++) {
			GImage itemUnitBar = unitBarImages.get(i);
			if (itemUnitBar.contains(x, y)) {
				selectedUnit = UnitType.values()[i];
				return selectedUnit;
			}
		}
		return null;
	}
	
	public List<UnitType> getTypeList()
	{
		return typeList;
	}
	
	public UnitType getSelectedUnit() {
		return selectedUnit;
	}
	
	public void clearSelectedUnit() {
		selectedUnit = null;
	}

}
