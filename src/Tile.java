


public class Tile {
	public final int id;
	public Sprite tile;
	
	public static Tile[] tiles = new Tile[25000];
	
	public static Tile grasstile = new grassTile(0);
	public static Tile rocktile = new rockTile(1);
	
	public Tile(int id){
		this.id = id;
		tiles[id] = this;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
}
