


public class grassTile extends Tile {
	
	public grassTile(int id){
		super(id);
		
		tile = Sprites.terrain[0][0];
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderSprite(x * tile.w, y * tile.h, tile);
	}
}
