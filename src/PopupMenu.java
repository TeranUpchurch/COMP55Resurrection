import acm.graphics.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class PopupMenu {
	protected List<GObject> menuElements = new ArrayList<>();
	protected GCompound overlay = new GCompound();
	protected boolean isVisible = false;
	
	public PopupMenu(String imagePath) {
		GImage background = new GImage (imagePath);
		this.overlay.add(background);
		this.menuElements.add(background);
	}
	
	public void addMenuElement(GObject element) {
		overlay.add(element);
		this.menuElements.add(element);
	}
	
	public void showPopup (GraphicsApplication mainApp) {
		if (!isVisible) {
			for (GObject obj : menuElements) {
                mainApp.add(obj);
            }
            mainApp.add(overlay);
            isVisible = true;
            pauseGame(mainApp); 
		}
	}
	
	public void hidePopup(GraphicsApplication mainApp) {
        if (isVisible) {
            for (GObject obj : menuElements) {
                mainApp.remove(obj);
            }
            mainApp.remove(overlay);
            isVisible = false;
            resumeGame(mainApp);
        }
    }
	
	protected void pauseGame(GraphicsApplication mainApp) {
        System.out.println("Game paused.");
    }

    protected void resumeGame(GraphicsApplication mainApp) {
        System.out.println("Game resumed.");
    }
}
