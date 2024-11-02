import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

// This class is solely responsible for holding the application
// as a whole.

public class MainApplication extends GraphicsProgram{
	public static final int RESOLUTION_X = 800;
	public static final int RESOLUTION_Y = 600;
	public static final int SIZE = 25;
	
	public Scene HowToPlayScene;
	public Scene MainMenuScene;
	public Scene LevelSelectScene;
	public Scene GameScene;
	
	// aaaaaaaaaahhhhhhhhhhhhs
	public void init() {
		setSize(RESOLUTION_X, RESOLUTION_Y);
		requestFocus();
	}
	
	public void run() {
		addMouseListeners();
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}

}
