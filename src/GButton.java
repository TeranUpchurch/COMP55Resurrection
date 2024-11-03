import acm.graphics.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class GButton extends GCompound {
	private GObject shape;
	private GLabel message;
	private GImage image;
	private int pressCooldown;
	private Timer buttonTimer;
	
	// Constructor for an image-based button
	public GButton(GImage img, double x, double y) {
		this.image = img;
		add(this.image, x, y);
		
		this.shape = image;
		
		initTimer();
		
	}
	
	private void initTimer() {
		this.pressCooldown = 200; // Cooldown in milliseconds
		this.buttonTimer = new Timer(this.pressCooldown, this::timerElapsed);
		this.buttonTimer.setRepeats(false);
	}
	
	public void onClick() {
		//change color or appearance of click
		if (this.shape instanceof GRect) {
			((GRect) this.shape).setFillColor(Color.GRAY);
		}
	}
	
	public void onHover() {
		//change appearance on hover
		if (this.shape instanceof GRect) {
			((GRect) this.shape).setFillColor(Color.LIGHT_GRAY);
		}
	}
	
	public void setShape(GObject newShape) {
		if (this.shape != null) {
			remove(this.shape);
		}
		this.shape = newShape;
		add(this.shape);
	}
	
	private void timerElapsed(ActionEvent e) {
		// reset appearance after cooldown 
		if (this.shape instanceof GRect) {
			((GRect) this.shape).setFillColor(Color.WHITE);
		}
	}
	
	private void sizeLabelFont(GLabel label, double w, double h) {
		// size font to fit within button dimension
		label.setFont(new Font("SansSerif", Font.PLAIN, (int)(h * 0.4)));
	}
	
	public void setFillColor(Color c) {
		if (this.shape instanceof GRect) {
			((GRect) this.shape).setFillColor(c);
		}
	}
	
	public void setColor(Color c) {
		if (this.shape instanceof GRect) {
			((GRect) this.shape).setColor(c);
		} else if (image != null) {
			image.setColor(c);
		}
	}
	
}
