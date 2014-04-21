import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputHandler implements KeyListener{
	
	public boolean up = false, down = false, left = false, right = false;
	
	public InputHandler(Game game){
		game.addKeyListener(this);
	}
	
	public void toggle(KeyEvent ke, boolean pressed){
		int keyCode = ke.getKeyCode();
		
		if(keyCode == KeyEvent.VK_UP) up = pressed;
		if(keyCode == KeyEvent.VK_DOWN) down = pressed;
		if(keyCode == KeyEvent.VK_LEFT) left = pressed;
		if(keyCode == KeyEvent.VK_RIGHT) right = pressed;
	}

	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}

	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
