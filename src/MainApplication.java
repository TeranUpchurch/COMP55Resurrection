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
	
	private GLabel label = new GLabel("Test", MainApplication.getResolutionWidth() / 2, MainApplication.getResolutionHeight() / 2);
	
	public void init() {
		setSize(GraphicsApplication.getResolutionWidth(), GraphicsApplication.getResolutionHeight());
		requestFocus();
	}
	
	public void run() {
		addMouseListeners();
		switchSceneTo(HowToPlayScene, this.gw);
	}

	public static void main(String[] args) {
		new MainApplication().start();
	}

}
