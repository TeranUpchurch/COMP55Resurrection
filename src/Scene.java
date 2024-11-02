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
		
	}
	
	public void hideContents()
	{
		
	}
	
	public void mousePressed(MouseEvent e) {
		GOval ball = makeBall(SIZE/2, e.getY());
		add(ball);
	}
	
	public void mouseReleased(MouseEvent e) {

	}
	
	public void mouseClicked(MouseEvent e) {

	}
	
	public void mouseDragged(MouseEvent e) {

	}
	
	public GOval makeBall(double x, double y) {
		GOval temp = new GOval(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
		temp.setColor(Color.RED);
		temp.setFilled(true);
		return temp;
	}
	
	public static void main(String[] args) {
		new Scene().start();
	}

}
