import acm.graphics.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RestartConfirmation extends PopupMenu{
	private GImage restartBackground;
	private GButton confirmButton;
	private GButton cancelButton;
	
	public static final String IMG_FILENAME_PATH = "media/";
	public static final String IMG_EXTENSION = ".png";
	
	public RestartConfirmation(String imagePath, MainApplication mainApp) {
		super(imagePath);
		String filename = IMG_FILENAME_PATH + "restartBackground" + IMG_EXTENSION;
		this.restartBackground = new GImage(filename);
		this.confirmButton = drawConfirmButton("confirmButton", restartBackground); // JUST FOR NOW
		this.cancelButton = drawCancelButton("cancelButton", restartBackground); // JUST FOR NOW
		
		addMenuElement(confirmButton);
		addMenuElement(cancelButton);
		addMouseListeners();
	}
	
	private GButton drawCancelButton(String label, GImage background) {
		GImage image = new GImage(IMG_FILENAME_PATH + label + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - image.getWidth()) / 3;
		double y = MainApplication.getResolutionHeight() * 0.60;
		GButton button = new GButton(image, x, y);
		return button;
	}
	
	private GButton drawConfirmButton(String label, GImage background) {
		GImage image = new GImage(IMG_FILENAME_PATH + label + IMG_EXTENSION);
		
		double x = (MainApplication.getResolutionWidth() - (0.9)*background.getWidth());
		double y = MainApplication.getResolutionHeight() * 0.60;
		GButton button = new GButton(image, x, y);
		return button;
	}
	
	private void addMouseListeners() {
		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleConfirm();
			}
		});
		
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleCancel();
			}
		});
		
	}
	
	private void handleConfirm() {
		System.out.println("Restarting game...");
	}
	
	private void handleCancel() {
		System.out.println("Cancel");
	}
	
}
