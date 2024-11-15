import acm.graphics.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class GButton extends GCompound {
	private GLabel message;
	private GImage image;
	private int pressCooldown;
	private Timer buttonTimer;
	
	// Constructor for an image-based button
	public GButton(GImage img, double x, double y) {
		this.image = img;
		add(this.image, x, y);
		
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
	
	private void buttonEffect() {
		this.image.setSize(image.getWidth()*1.1 , image.getHeight()*1.1);
		this.image.setLocation(image.getX() - image.getWidth()*0.05, image.getY() - image.getHeight()*0.05);
	}
	
	private void buttonReset() {
		this.image.setSize(image.getWidth()/1.1 , image.getHeight()/1.1);
		this.image.setLocation(image.getX() + image.getWidth()*0.05, image.getY() + image.getHeight()*0.05);
	}
	
	private void timerElapsed(ActionEvent e) {
		
	}
	
	
}
