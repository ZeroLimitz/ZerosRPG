
public class Player extends Mob {
	
	private InputHandler input;

	public Player(Level level, int x, int y, InputHandler input) {
		super(level, "OMFJesus", x, y, 1);
	}

	public boolean hasCollided(int xa, int ya) {
		return false;
	}


	public void tick() {
		int xa = 0;
		int ya = 0;
		
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		
		if(xa != 0 || ya != 0){
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = false;
		}
	}


	public void render(Screen screen) {

	}
	
}
