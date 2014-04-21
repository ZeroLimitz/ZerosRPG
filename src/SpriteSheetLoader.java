import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;



public class SpriteSheetLoader {
	public int[] sheetPixels;
	public int[] pixels;
	int x, y, sheetWidth;
	
	public SpriteSheetLoader(){
	
	}
	
	public static Sprite[][] cutTiles(String filename, int w, int h){
		return cutTiles(filename, w, h, 0, 0);
	}
	
	public static Sprite[][] cutTiles(String filename, int w, int h, int xOffs, int yOffs){
		
		try{
			BufferedImage image = ImageIO.read(Game.class.getResource(filename));
			
			int xTiles = (image.getWidth() - xOffs) / w;
			int yTiles = (image.getHeight() - yOffs) / h;
			
			Sprite[][] result = new Sprite[xTiles][yTiles];
			
			
			for (int x = 0; x < xTiles; x++) {
				for (int y = 0; y < yTiles; y++) {
					result[x][y] = new Sprite(w, h);
					image.getRGB(xOffs + x * w, yOffs + y * h, w, h, result[x][y].pixels, 0, w);
				}
			}
			
			return result;
			
		} catch(Exception e){
			System.err.println(e.getStackTrace());
		}
		return null;
	}
}
