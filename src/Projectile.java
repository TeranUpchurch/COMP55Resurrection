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
	private int speedX;
	private int speedY;
	private int steps = 0;
	private int lifetime = -1;
	private boolean hasLifetime;
	
	private GameScene scene;
	
	// Horizontally-moving projectiles
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
		this.speedX = speed;
		this.speedY = 0;
		this.hasLifetime = false; 
		
		this.scene = scene;
	}
	
	// Omnidirectional-moving projectiles
	public Projectile(GImage image, 
			int width, 
			int height, 
			int damage, 
			int speedX, 
			int speedY,
			double xPos, 
			double yPos, 
			int lifetime,
			GameScene scene) {
		this.image = image;
		this.width = width;
		this.height = height;
		image.setSize(width, height);
		this.damage = damage;
		this.speedX = speedX;
		this.speedY = speedY;
		this.hasLifetime = true;
		this.lifetime = lifetime;
		
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
	
	public int getSteps()
	{
		return steps;
	}
	
	public int getLifetime()
	{
		return lifetime;
	}
	
	public boolean hasLifetime()
	{
		return hasLifetime;
	}
	
	public void step() {
		image.move(speedX, speedY);
		steps+=1;
	}
}
