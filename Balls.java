package Pong;
//PONG
import java.awt.Rectangle;

public class Balls extends Rectangle {
	public double speedX = 2;
	public double speedY = 2;
	
	public Balls(int length) {
		super(300, 400, length, length);
	}

	public void move() {
		this.x += speedX;
		this.y += speedY;
	}
	

}
