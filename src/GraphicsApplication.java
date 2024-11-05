import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.swing.*;

// This class is responsible for handling the current "scene"
// and switching between them.

public class GraphicsApplication extends GraphicsProgram{
	protected static final int RESOLUTION_WIDTH = 1200; // tried to make the ratio 16:9
	protected static final int RESOLUTION_HEIGHT = 675;
	
	protected Scene currentScene;
	protected Scene previousScene;
	
	private GLabel label = new GLabel("Test", MainApplication.getResolutionWidth() / 3, MainApplication.getResolutionHeight() / 2);
	
	// Stack to keep track of previous scene
	// Handle the returnButton's job
	private Stack<Scene> sceneHistory = new Stack<>(); 
	
	public void init() {
		setSize(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		addMouseListeners();
		add(label);
	}
	
	public void switchSceneTo(Scene scene, boolean isSubScene)
	{
		if (currentScene != null)
		{
			if (!isSubScene) {
				sceneHistory.push(currentScene); // // Only push non-sub-scenes to history
			}
			System.out.println("Switching scene.");
			previousScene = currentScene;
			currentScene.hideContents();
			currentScene = scene;
			currentScene.showContents();
		}
		else
		{
			System.out.println("Starting application (currentScene = null)");
			currentScene = scene;
			currentScene.showContents();
		}
		
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
	
	public Scene getPreviousScene() {
		return previousScene;
	}
	
	public void setCurrentScene(Scene scene)
	{
		currentScene = scene;
	}
	
	public static void main(String[] args) {
		new GraphicsApplication().start();
	}

}