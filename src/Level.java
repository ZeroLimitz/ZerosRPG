

public class Level {
	int w;
	int h;
	
	public int[] tiles;
	
	public Level(int w, int h){
		this.w = w;
		this.h = h;
		
		tiles = new int[w * h];
		
		loadMap(0, 0, 0, 0);
	}
	
	public void renderBackground(int xScroll, int yScroll, Screen screen){
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;
		
		int w = (screen.w + 15 >> 4);
		int h = (screen.h + 15 >> 4);
		
		screen.setOffs(xScroll, yScroll);
		for (int y = yo; y <= h + yo; y++) {
			for (int x = xo; x <= w + xo; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		screen.setOffs(0, 0);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= w || y >= h) return Tile.rocktile;
		return Tile.tiles[tiles[x + y * w]];		
	}
	
	public void loadMap(int x0, int y0, int x1, int y1){
		Sprite sprite = Sprites.level[x0][y0];
		
		for(int y = 0; y < sprite.h; y++) {
			for(int x = 0; x < sprite.w; x++) {
				if(sprite.pixels[x + y * sprite.h] == -9276814){
					tiles[x + x1 + (y + y1) * h] = Tile.rocktile.id;
				} else {
					tiles[x + x1 + (y + y1) * h] = Tile.grasstile.id;
				}
			}
		}
	}
}
