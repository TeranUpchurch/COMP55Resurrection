import acm.graphics.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class PauseMenu extends PopupMenu{
	private GLabel label = new GLabel("pauseMenu", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 4);
	
	private GImage pauseMenu;
	private GButton exitButton;

	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public PauseMenu(String imagePath)
	{
		super(imagePath);  // Use the image as the background for the popup
		
		this.exitButton = drawButton("exitButton", 1000, 200); // JUST FOR NOW
		
		addMenuElement(exitButton);
		
		addActionListeners();
		
		
	}
	
	private GButton drawButton(String lable, double x, double y) {
		GImage image = new GImage(IMG_FILENAME_PATH + "exitButton" + IMG_EXTENSION);
		GButton button = new GButton(image, x, y);
		
		return button;
	}
	
	private void addActionListeners() {
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleExit();
			}
		});
	}
	
	private void handleExit() {
        System.out.println("Exiting to main menu...");
        // Logic to exit to main menu (e.g., loading MainMenuScene)
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
