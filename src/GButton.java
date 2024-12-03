import acm.graphics.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class GButton extends GCompound {
	private GLabel message;
	private GImage image;
	private int pressCooldown;
	private GPoint origin;
	private GPoint clickPos;
	private double defaultSizeX;
	private double defaultSizeY;
	private double effectSizeX;
	private double effectSizeY;
	
	private Timer buttonTimer;
	
	// Constructor for an image-based button
	public GButton(GImage img, double x, double y) {
		this.image = img;
		add(this.image, x, y);
		
		origin = new GPoint(x, y);
		clickPos = new GPoint(origin.getX() - image.getWidth()*0.05, origin.getY() - image.getHeight()*0.05);
		defaultSizeX = image.getWidth();
		defaultSizeY = image.getHeight();
		effectSizeX = image.getWidth()*1.1;
		effectSizeY = image.getHeight()*1.1;
		
		initTimer();
		initMouseListener();
		
	}
	
	private void initTimer() {
		this.pressCooldown = 200; // Cooldown in milliseconds
		this.buttonTimer = new Timer(this.pressCooldown, this::timerElapsed);
		this.buttonTimer.setRepeats(false);
	}
	
	private void initMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClick();
				onMouseExit();
			}
			@Override
            public void mouseEntered(MouseEvent e) {
                onHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onMouseExit();
            }
		});
	}
	
	public void onClick() {
		buttonEffect();
		
	}
	
	public void onHover() {
		buttonEffect();
	}
	
	public void onMouseExit() {
		buttonReset();
	}
	
	public GImage getImage()
	{
		return image;
	}
	
	private void buttonEffect() {
		this.image.setSize(effectSizeX, effectSizeY);
		this.image.setLocation(clickPos);
	}
	
	private void buttonReset() {
		this.image.setSize(defaultSizeX , defaultSizeY);
		this.image.setLocation(origin);
	}
	
	private void timerElapsed(ActionEvent e) {
		
	}
	
	
	
}
