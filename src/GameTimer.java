import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameTimer implements ActionListener{
	  private Timer timer;
	  private String name;
	  
	  //Pause tracking variables
	  private long pauseStartTime = 0;
	  private long totalPausedTime = 0;
	  private boolean isPaused = false;
	  private long lastActionTime = 0;
	  

	  public GameTimer(int MS, String name){
	     timer = new Timer(MS, this);
	     this.name = name;
	  }

	  public void start(){
	     timer.start();
	     lastActionTime = System.currentTimeMillis();
	     System.out.println(name + " started.");
	  }

	  public void stop(){
	     timer.stop();
	     System.out.println(name + " stopped.");
	  }
	  
	  public void pause() {
		  if (!isPaused && timer.isRunning()) {
			  pauseStartTime = System.currentTimeMillis();
			  System.out.println(pauseStartTime);
			  timer.stop();
			  isPaused = true;
			  System.out.println(name + " paused!");
		  }
	  }
	  
	  public void resume() {
		  if (isPaused) {
			  long currentTime = System.currentTimeMillis();
			  totalPausedTime += (currentTime - pauseStartTime);
			  timer.start();
			  isPaused = false;
	          System.out.println(name + " resumed!");
	        }
	    }
	  
	  public void createActionListener(ActionListener l)
	  {
		  timer.addActionListener(l);
	  }
	  
	  public void removeActionListener(ActionListener l)
	  {
		  timer.removeActionListener(l);
	  }

	  @Override
	  public void actionPerformed(ActionEvent e) {
		  long currentTime = System.currentTimeMillis();
		  long elapsedTime = currentTime - lastActionTime - totalPausedTime;
		  lastActionTime = currentTime;
	  }
	  
	  public void resetPauseTime() {
	      totalPausedTime = 0;
	      pauseStartTime = 0;
	  }

	  public boolean isTimeToStop(){
	     return false;
	  }
}