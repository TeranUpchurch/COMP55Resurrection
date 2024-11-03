import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

// This class is responsible for handling the current "scene"
// and switching between them.

interface Displayable {
	public void showContents();
	public void hideContents();
}

interface Interfaceable extends Displayable{
	public void mousePressed(MouseEvent e);
	public void mouseReleased(MouseEvent e);
	public void mouseClicked(MouseEvent e);
	public void mouseDragged(MouseEvent e);
}

public class Scene extends GraphicsApplication implements Interfaceable{
	protected static Set<GObject> activeContents = new HashSet<>();
	protected MainApplication mainApp;
	
	public Scene(MainApplication mainApp)
	{
		this.mainApp = mainApp;
	}
	
	public void addElement(GObject element)
	{
		activeContents.add(element);
		mainApp.add(element);
	}
	
	public void removeElement(GObject element)
	{
		if (activeContents.contains(element))
		{
			activeContents.remove(element);
			mainApp.remove(element);
		}
		else
		{
			return;
		}
	}
	
	public void showContents()
	{
		System.out.println("Show contents from this point..");
	}
	
	public void hideContents()
	{
		System.out.println("Hide contents from this point..");
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
