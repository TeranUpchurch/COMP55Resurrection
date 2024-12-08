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
	public GameScene GameScene;
	
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
			if (!isSubScene(scene) && currentScene != GameScene) {
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
	
	/*
	 * The HowToPlayScene is treated as a subscene
	 * because it serves as auxiliary information rather than a main gameplay or navigation area.
	 * Unlike primary scenes such as MainMenuScene or LevelSelectScene,
	 * which represent key stages of user interaction,
	 * the HowToPlayScene is a temporary display
	 * that provides instructions and does not change the game's overall flow.
	 */
	/*
	 * By marking it as a subscene,
	 * we ensure that it does not disrupt the user's main navigation path.
	 * When returning from HowToPlayScene, the app goes back to the previous main scene
	 */
	public boolean isSubScene (Scene scene) {
		if (currentScene == HowToPlayScene) {
			return true;
		}
		return false;
	}
	
	public void returnToPreviousScene() {
		if (!sceneHistory.empty()) {
			this.previousScene = sceneHistory.pop(); // remove the previous screen from the stack
		}
		switchSceneTo(this.previousScene); 
	}
	
	public void switchSceneToGame(String difficulty)
	{
		System.out.println("Switching to game with difficulty " + difficulty);
		GameScene = null;
		GameScene = new GameScene(this, difficulty);
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
		else if (sceneName == "LevelSelectScene")
		{
			currentScene = LevelSelectScene;
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
