import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class ImageToRobotMap {
	
	private HashMap<GImage, Robot> map;
	
	public ImageToRobotMap()
	{
		map = new HashMap<GImage, Robot>();
	}
	
	public void addPair(GImage image, Robot robot)
	{
		map.put(image, robot);
	}
	
	public void deletePair(GImage image)
	{
		map.remove(image);
	}
	
	public Robot get(GImage image)
	{
		return map.get(image);
	}
	
	public static void main(String[] args) {
		
	}

}
