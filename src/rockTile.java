


public class rockTile extends Tile {
	public rockTile(int id){
		super(id);
		
		tile = Sprites.terrain[1][0];
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderSprite(x * tile.w, y * tile.h, tile);
	}
}
