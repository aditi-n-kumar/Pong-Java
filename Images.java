package Pong;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Images {
	
	private BufferedImage spongebobRainbow; {
		try {
			File file = new File("src/images/spongebobRainbow.jpg");
			FileInputStream fis = new FileInputStream(file);
			setSpongebobRainbow(ImageIO.read(fis));
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	public BufferedImage getSpongebobRainbow() {
		return spongebobRainbow;
	}
	public void setSpongebobRainbow(BufferedImage spongebobRainbow) {
		this.spongebobRainbow = spongebobRainbow;
	}
}
