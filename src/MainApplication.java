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
	private Scene MainMenuScene;
	private Scene LevelSelectScene;
	private Scene GameScene;
	
	public void init() {
		setSize(GraphicsApplication.getResolutionWidth(), GraphicsApplication.getResolutionHeight());
		requestFocus();
	}
	
	public void run() {
		addMouseListeners();
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

	public static void main(String[] args) {
		new MainApplication().start();
	}

}
