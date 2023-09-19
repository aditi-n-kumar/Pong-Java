package Pong;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ImagesThird {
	
	private BufferedImage spongebobConfused; {
		try {
			
			File file3 = new File("src/images/spongebobConfused.JPG");
			FileInputStream fis3 = new FileInputStream(file3);
			setSpongebobConfused(ImageIO.read(fis3));
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	public BufferedImage getSpongebobConfused() {
		return spongebobConfused;
	}
	public void setSpongebobConfused(BufferedImage spongebobConfused) {
		this.spongebobConfused = spongebobConfused;
	}
}