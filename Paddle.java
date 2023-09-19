package Pong;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class Paddle extends Rectangle {
	
	public int paddle_speed = 8;
	
	public Paddle(int xLoc, int yLoc) {
		super(xLoc, yLoc, 10, 130);
	}

	public void moveUp() {
		this.y -= paddle_speed;
	}
	
	public void moveDown() {
		this.y += paddle_speed;
	}
	
	
}

