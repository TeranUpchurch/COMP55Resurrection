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
		
		shape = image;
		
		initTimer();
		
	}
	
	private void initTimer() {
		pressCooldown = 200; // Cooldown in milliseconds
		buttonTimer = new Timer(pressCooldown, this::timerElapsed);
		buttonTimer.setRepeats(false);
	}
	
	private void onClick() {
		//change color or appearance of click
		if (shape instanceof GRect) {
			((GRect) shape).setFillColor(Color.GRAY);
		}
	}
	
	public void onHover() {
		//change appearance on hover
		if (shape instanceof GRect) {
			((GRect) shape).setFillColor(Color.LIGHT_GRAY);
		}
	}
	
	public void setShape(GObject newShape) {
		if (shape != null) {
			remove(shape);
		}
		shape = newShape;
		add(shape);
	}
	
	private void timerElapsed(ActionEvent e) {
		// reset appearance after cooldown 
		if (shape instanceof GRect) {
			((GRect) shape).setFillColor(Color.WHITE);
		}
	}
	
	private void sizeLabelFont(GLabel label, double w, double h) {
		// size font to fit within button dimension
		label.setFont(new Font("SansSerif", Font.PLAIN, (int)(h * 0.4)));
	}
	
	public void setFillColor(Color c) {
		if (shape instanceof GRect) {
			((GRect) shape).setFillColor(c);
		}
	}
	
	public void setColor(Color c) {
		if (shape instanceof GRect) {
			((GRect) shape).setColor(c);
		} else if (image != null) {
			image.setColor(c);
		}
	}
	
}
