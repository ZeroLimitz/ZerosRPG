import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "Darkness Vale";
	public static final int HEIGHT = 240;
	public static final int WIDTH = 240;
	public static final int SCALE = 2;
	public static Dimension GAME_DIM = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	
	public boolean running = false;
	Random random = new Random();
	
	public void start(){
		running = true;
		new Thread(this).start();
	}
	
	public Game(){
	}
	
	public void init(){
		
		screen = new Screen(WIDTH, HEIGHT);
		
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				Sprite sprite = Sprites.terrain[0][0];
				screen.renderSprite(x * sprite.w, y * sprite.h, sprite);
			}
		}
	}
	
	public void run() {
		init();
		while(running){
			tick();
			render();
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				 e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		running = false;
	}
	
	public void tick(){
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null){
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		
		for (int y = 0; y < screen.h; y++) {
			for (int x = 0; x < screen.w; x++) {
				pixels[x + y * WIDTH] = screen.pixels[x + y * screen.w];
			}
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(GAME_DIM));
		game.setMaximumSize(new Dimension(GAME_DIM));
		game.setMinimumSize(new Dimension(GAME_DIM));
		
		JFrame frame = new JFrame(TITLE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		
		game.start();
	}
}
