import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// This class is responsible for handling the current "scene"
// and switching between them.

public class GraphicsApplication extends GraphicsProgram{
	protected static final int RESOLUTION_WIDTH = 800;
	protected static final int RESOLUTION_HEIGHT = 600;
	
	protected Scene currentScene;
	
	public void init() {
		setSize(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		addMouseListeners();
	}
	
	public static int getResolutionWidth()
	{
		return RESOLUTION_WIDTH;
	}
	
	public static int getResolutionHeight()
	{
		return RESOLUTION_HEIGHT;
	}
	
	public Scene getCurrentScene()
	{
		return currentScene;
	}
	
	public void setCurrentScene(Scene scene)
	{
		currentScene = scene;
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}

}