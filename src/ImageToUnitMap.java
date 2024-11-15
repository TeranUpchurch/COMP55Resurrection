import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class ImageToUnitMap {
	
	private HashMap<GImage, Unit> map;
	
	public ImageToUnitMap()
	{
		map = new HashMap<GImage, Unit>();
	}
	
	public void addPair(GImage image, Unit unit)
	{
		map.put(image, unit);
	}
	
	public void deletePair(GImage image)
	{
		map.remove(image);
	}
	
	public Unit get(GImage image)
	{
		return map.get(image);
	}
	
	public static void main(String[] args) {
		
	}

}
