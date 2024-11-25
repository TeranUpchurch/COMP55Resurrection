import acm.graphics.*;
import java.util.ArrayList;
import java.util.List;

public class Notification {
	private GImage image;
	private double x, y;
	private long startTime;
	private int duration;
	protected List<GObject> notiElements = new ArrayList<>();
	
	public Notification (GImage image, double x, double y, int duration) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.duration = duration;
		this.startTime = System.currentTimeMillis(); // captures the current system time in milliseconds.
		image.setLocation(x, y);
	}
	
	public boolean isExpired() {
		return System.currentTimeMillis() - startTime > duration;
	}
	
	public GImage getImage() {
		return image;
	}

}
