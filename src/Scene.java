import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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

public class Scene extends GraphicsProgram implements Interfaceable{
	public static final int RESOLUTION_X = 800;
	public static final int RESOLUTION_Y = 600;
	public static final int SIZE = 25;
	
	public void init() {
		setSize(RESOLUTION_X, RESOLUTION_Y);
		requestFocus();
	}
	
	public void run() {
		addMouseListeners();
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
		new Scene().start();
	}

}
