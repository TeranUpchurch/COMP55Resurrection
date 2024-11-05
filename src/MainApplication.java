import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// This class is solely responsible for holding the application
// as a whole.

public class MainApplication extends GraphicsApplication{
	public HowToPlayScene HowToPlayScene;
	public MainMenuScene MainMenuScene;
	public LevelSelectScene LevelSelectScene;
	public Scene GameScene;
	
	public void init() {
		setSize(GraphicsApplication.getResolutionWidth(), GraphicsApplication.getResolutionHeight());
		requestFocus();
		HowToPlayScene = new HowToPlayScene(this);
		MainMenuScene = new MainMenuScene(this);
		LevelSelectScene = new LevelSelectScene(this);
	}
	
	public void run() {
		addMouseListeners();
		switchSceneTo(MainMenuScene); // DEBUG - 
	}
	
	public void switchSceneTo(Scene scene)
	{
		if (currentScene != null)
		{
			System.out.println("Switching scene.");
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
	
	// TODO - Actually make the Game class before this. 
	public void switchSceneToGame(String difficulty)
	{
		System.out.println("Switching scene.");
		currentScene.hideContents();
		currentScene = GameScene;
		currentScene.showContents();
	}
	
	public void switchSceneToString(String sceneName)
	{
		if (sceneName == "HowToPlayScene")
		{
			currentScene = HowToPlayScene;
		}
		else if (sceneName == "MainMenuScene")
		{
			currentScene = MainMenuScene;
		}
		
		System.out.println("Switching scene to" + sceneName);
		currentScene.hideContents();
		currentScene.showContents();
	}
	
	public void exitApplication() {
		System.exit(0); // Terminates the application
	}
	
	// Mouse event handlers
	public void mousePressed(MouseEvent e) {
		if (currentScene != null) {
			currentScene.mousePressed(e);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (currentScene != null) {
			currentScene.mouseReleased(e);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (currentScene != null) {
			currentScene.mouseClicked(e);
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if (currentScene != null) {
			currentScene.mouseDragged(e);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if (currentScene != null) {
			currentScene.mouseMoved(e);
		}
	}
	
	

	public static void main(String[] args) {
		new MainApplication().start();
	}

}
