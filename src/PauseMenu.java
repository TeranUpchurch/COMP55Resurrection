import acm.graphics.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class PauseMenu extends PopupMenu{
	private GLabel label = new GLabel("pauseMenu", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 4);
	
	private GImage pauseMenu;
	private GButton biggerExitButton;

	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public PauseMenu(String imagePath)
	{
		super(imagePath);  // Use the image as the background for the popup
		
		String filename = IMG_FILENAME_PATH + "pauseMenu" + IMG_EXTENSION;
		this.pauseMenu = new GImage(filename);
		this.biggerExitButton = drawButton("biggerExitButton", pauseMenu);
		
		addMenuElement(biggerExitButton);
		
		addActionListeners();
		
		
	}
	
	private GButton drawButton(String lable, GImage backgroundImage) {
		GImage image = new GImage(IMG_FILENAME_PATH + lable + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - backgroundImage.getWidth()) / 2 + 30;
		double y = MainApplication.getResolutionHeight() * 0.4;
		GButton button = new GButton(image, x, y);
		
		return button;
	}
	
	private void addActionListeners() {
		biggerExitButton.addActionListener(new ActionListener() {
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
