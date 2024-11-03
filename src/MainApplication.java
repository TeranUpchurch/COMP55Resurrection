import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// This class is solely responsible for holding the application
// as a whole.

public class MainApplication extends GraphicsApplication{
	private HowToPlayScene HowToPlayScene = new HowToPlayScene();
	private MainMenuScene MainMenuScene = new MainMenuScene();
	private Scene LevelSelectScene;
	private Scene GameScene;
	
	private GLabel label = new GLabel("Test", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 2);
	
	public void init() {
		setSize(GraphicsApplication.getResolutionWidth(), GraphicsApplication.getResolutionHeight());
		requestFocus();
	}
	
	public void run() {
		addMouseListeners();
		switchSceneTo(HowToPlayScene, this.gw);
		switchSceneTo(MainMenuScene);
		switchSceneTo(HowToPlayScene);
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
		
		currentScene.addMouseListeners();
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
