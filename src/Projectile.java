import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Projectile {
	private GImage image;
	private int width;
	private int height;
	private int damage;
	private int speed;
	
	private GameScene scene;
	
	public Projectile(GImage image, 
			int width, 
			int height, 
			int damage, 
			int speed, 
			double xPos, 
			double yPos, 
			GameScene scene) {
		this.image = image;
		this.width = width;
		this.height = height;
		image.setSize(width, height);
		this.damage = damage;
		this.speed = speed;
		
		this.scene = scene;
	}
	
	public GImage getImage()
	{
		return image;
	}
	
	public void setLocation(double x, double y)
	{
		image.setLocation(x, y);
	}
	
	public GPoint getLocation()
	{
		return image.getLocation();
	}
	
	public int getDamage()
	{
		return this.damage;
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public void step() {
		image.setLocation(image.getX() + speed, image.getY());
	}
}
