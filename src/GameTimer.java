import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameTimer implements ActionListener{
	  private Timer timer;
	  private String name;

	  public GameTimer(int MS, String name){
	     timer = new Timer(MS, this);
	     this.name = name;
	  }

	  public void start(){
	     timer.start();
	     System.out.println(name + " started.");
	  }

	  public void stop(){
	     timer.stop();
	     System.out.println(name + " stopped.");
	  }
	  
	  public void createActionListener(ActionListener l)
	  {
		  timer.addActionListener(l);
	  }
	  
	  public void removeActionListener(ActionListener l)
	  {
		  timer.removeActionListener(l);
	  }

	  public void actionPerformed(ActionEvent e){

	  }

	  public boolean isTimeToStop(){
	     return false;
	  }
}