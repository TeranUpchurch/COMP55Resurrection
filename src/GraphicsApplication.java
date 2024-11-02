import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// This class is responsible for handling the current "scene"
// and switching between them.

public class GraphicsApplication extends GraphicsProgram{
	public static final int RESOLUTION_X = 800;
	public static final int RESOLUTION_Y = 600;
	public static final int SIZE = 25;
	
	// aaaaaaaaaahhhhhhhhhhhhs
	public void init() {
		setSize(RESOLUTION_X, RESOLUTION_Y);
		requestFocus();
	}
	
	public void run() {
		addMouseListeners();
	}
	
	public void mousePressed(MouseEvent e) {
		GOval ball = makeBall(SIZE/2, e.getY());
		add(ball);
	}
	
	public GOval makeBall(double x, double y) {
		GOval temp = new GOval(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
		temp.setColor(Color.RED);
		temp.setFilled(true);
		return temp;
	}
	
	public static void main(String[] args) {
		new GraphicsApplication().start();
	}

}
