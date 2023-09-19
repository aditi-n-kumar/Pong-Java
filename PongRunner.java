package Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import utilities.GDV5;
import java.util.Random;
import java.lang.Math;


public class PongRunner extends GDV5 {

	Balls ball = new Balls(15);
	Paddle paddle_left = new Paddle(25, 300);
	Paddle paddle_right = new Paddle(1150, 300);
	int pointsP1 = 0;
	int pointsP2 = 0;
	//String gameState = "START";
	String gameState = "START";
	String winner;
	Images image = new Images();
	ImagesSecond image2 = new ImagesSecond();
	ImagesThird image3 = new ImagesThird();
	int pointsToWin = 2;
	Color[] colors = {Color.MAGENTA, Color.PINK, Color.BLUE, Color.RED, Color.YELLOW, Color.WHITE, Color.GREEN, Color.CYAN, Color.ORANGE};
	int colorsIndex = 0;

	public static void main(String[] args) {
		PongRunner p1 = new PongRunner();
		p1.start(); 
		
		
		}

	public void paddleEvents() {
		if (KeysPressed[KeyEvent.VK_UP] && paddle_right.y > 0) {
			paddle_right.moveUp(); } 
		else if (KeysPressed[KeyEvent.VK_DOWN] && (paddle_right.y + 130 < getMaxWindowY())) {
			paddle_right.moveDown(); }

		if (KeysPressed[KeyEvent.VK_W] && paddle_left.y > 0) {
			paddle_left.moveUp(); } 
		else if (KeysPressed[KeyEvent.VK_S] && (paddle_left.y + 130 < getMaxWindowY())) {
			paddle_left.moveDown(); }
	}

	
	public void collisionBall() {
		
		// check for PADDLE collision with ball
		if ((ball.getX() < paddle_left.getX() + paddle_left.getWidth()) && (ball.getY() > paddle_left.getY())
		&& (ball.getY() < paddle_left.getY() + paddle_left.getHeight()) && ball.speedX < 0) { // left paddle
			ball.speedX *= -1; }
				
		if ((ball.getX() + ball.getWidth()> paddle_right.getX()) && (ball.getY() > paddle_right.getY())
		&& (ball.getY() < paddle_right.getY() + paddle_right.getHeight()) && ball.speedX > 0 ) { // right paddle
			ball.speedX *= -1; }

	}
	

	public void increasePoints() {
		if (ball.getX() + ball.getWidth() >= GDV5.getMaxWindowX()) {
			// increase point of respective player
			pointsP1 += 1;
			ball.setLocation(600, 400); }

		else if (ball.getX() + ball.getWidth() < 0) {
			pointsP2 += 1;
			ball.setLocation(600, 400); }

		if (ball.getY() + ball.getWidth() >= GDV5.getMaxWindowY() || ball.getY() < 0) {
			ball.speedY *= -1; }
	}

	public void keysPushed() {
		if (KeysPressed[KeyEvent.VK_SHIFT]) {
			colorsIndex = (int) (Math.random() * 9); }
		
		if (KeysPressed[KeyEvent.VK_SPACE]) {
			gameState = "RUN"; }
		
		if (KeysPressed[KeyEvent.VK_ENTER]) {
			gameState = "START"; 
			pointsP1 = 0;
			pointsP2 = 0; }
		
		if (KeysPressed[KeyEvent.VK_ESCAPE]) {
			gameState = "ENDED"; }
	}
	
	@Override
	public void update() { // refreshes at 60 frames per second

		keysPushed(); 
		
		if (gameState == "RUN") {
			collisionBall();
			ball.move();

			increasePoints();
			if (pointsP1 == pointsToWin || pointsP2 == pointsToWin) {
				gameState = "OVER"; }

			paddleEvents();

		}

	}

	@Override
	public void draw(Graphics2D win) { // refresh at the processor speed - approx 3000 frames per second
		// drawing ball
		win.setColor(Color.BLACK);
		win.drawOval((int) ball.getX(), (int) ball.getY(), (int) ball.getWidth(), (int) ball.getHeight()); // win.draw(ball);
		win.setColor(colors[colorsIndex]);
		win.fillOval((int) ball.getX(), (int) ball.getY(), (int) ball.getWidth(), (int) ball.getHeight()); // win.fill(ball);
		// drawing paddles
		win.setColor(Color.white);
		win.draw(paddle_right);
		win.fillRect((int) paddle_right.getX(), (int) paddle_right.getY(), (int) paddle_right.getWidth(), (int) paddle_right.getHeight());
		win.draw(paddle_left);
		win.fillRect((int) paddle_left.getX(), (int) paddle_left.getY(), (int) paddle_left.getWidth(), (int) paddle_left.getHeight());
		win.setFont(new Font("Arial", Font.PLAIN, 30));
		win.setColor(Color.CYAN);
		win.drawString("Player 1: ", 55, 100);
		win.drawString("" + pointsP1, 180, 100);
		win.drawString("Player 2: ", 990, 100);
		win.drawString("" + pointsP2, 1115, 100);
		
		if (gameState == "START") {
			win.setColor(Color.BLUE);
			win.setFont(new Font("Arial", Font.ITALIC, 35));
			win.drawString("Welcome to Pong!", 455, 200);
			win.drawString("Click the Spacebar to Start.", 385, 235);
			win.drawImage(image.getSpongebobRainbow(), 350, 250, 500, 300, this); 
			win.drawString("If you want to end the game, press ESC", 295, 580); 
			win.drawString("Click W for UP and S for DOWN to move the left paddle.", 180, 620);
			win.drawString("Click UP and DOWN to move the right paddle.", 250, 660); 
			win.drawString("Click SHIFT to change the color of the ball!", 265, 700); }
		
		else if (gameState == "OVER") {
			winner = pointsP1 > pointsP2 ? "Player 1" : "Player 2";
			win.setColor(Color.YELLOW);
			win.setFont(new Font("Arial", Font.ITALIC, 35));
			win.drawString(""+winner+" is the winner!", 435, 200);
			win.drawString("Press ENTER to RESTART", 385, 235);
			win.drawImage(image2.getSpongebobSquarepantsCongratulations(), 345, 250, 500, 300, this); }

		else if (gameState == "ENDED") {
			win.setColor(Color.RED);
			win.setFont(new Font("Arial", Font.ITALIC, 35));
			win.drawString("Awwww...you ended the game?", 360, 200);
			win.drawString("Press ENTER to RESTART", 380, 235);
			win.drawImage(image3.getSpongebobConfused(), 345, 250, 500, 350, this); }
		
		
		
	}
}















































//if (GDV5.collisionDirection(paddle_left.getBounds(), ball.getBounds(), 0, 0) == 0 && ball.getX() <= paddle_left.getX() + paddle_left.getWidth() && ball.speedX < 0) {
//	ball.speedX *= -1; 
//}
//
//if (GDV5.collisionDirection(paddle_right.getBounds(), ball.getBounds(), (int) ball.getWidth(), 0) == 2 && ball.getX() + ball.getWidth() >= paddle_right.getX()  && ball.speedX > 0) {
//	ball.speedX *= -1; 
//}
