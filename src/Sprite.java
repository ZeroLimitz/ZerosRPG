
public class Sprite {
	public int w;
	public int h;
	public int[] pixels;
	
	public Sprite(int w, int h){
		this.w = w;
		this.h = h;
		pixels = new int[w * h];
	}
	
	public void clear(int color){
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
}
