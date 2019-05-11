package game_air_fighter_image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Load_Images {
	
	public static BufferedImage image;
	public static BufferedImage plane;
	public static BufferedImage player;
	public static BufferedImage enemy;
	
	
	
	public static void init(){
		image = imageLoader("/night.png");
		plane = imageLoader("/airplane.png");
		crop();
		
		
	}
	
	public static BufferedImage imageLoader(String path){
		
		try {
			return ImageIO.read(Load_Images.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null;  // not return nothing 
	}
	
	public static void crop(){
		enemy = plane.getSubimage(0, 0, 115, 95);
		player = plane.getSubimage(115, 0, 115, 95);
		
	}

}
