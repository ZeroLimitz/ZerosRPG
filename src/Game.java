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
	public static final int HEIGHT = 380;
	public static final int WIDTH = 380;
	public static final int SCALE = 2;
	public static Dimension GAME_DIM = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public int fps = 0, updates = 0, tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	public Level level;
	public InputHandler input = new InputHandler(this);
	
	public volatile boolean running = false;
	Random random = new Random();
	public int xScroll = 0, yScroll = 0;
	
	public synchronized void start(){
		running = true;
		new Thread(this).start();
	}
	
	public Game(){
	}
	
	public void init(){
		
		screen = new Screen(WIDTH, HEIGHT);
		
		level = new Level(16, 16);
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000 / 60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while(delta >= 1){
				ticks++;
				delta -= 1;
				tick();
				shouldRender = true;
			}
			
			if(shouldRender){
				frames++;
				render();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				System.out.println("FPS " + frames + "," + " " + "Ticks: " + ticks + "," + " " + "Tick Count: " + tickCount);
				frames = 0;
				ticks = 0;
			}
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				 e.printStackTrace();
			}
		}
	}
	
	public synchronized void stop(){
		running = false;
	}
	
	public void tick(){
		tickCount++;
		if(input.right) xScroll++;
		if(input.left) xScroll--;
		if(input.up) yScroll--;
		if(input.down) yScroll++;
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null){
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		level.renderBackground(xScroll, yScroll, screen);
		
		
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
