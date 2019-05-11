package game_air_fighter_gamesetup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game_air_fighter_display.Display;
import game_air_fighter_gamemanager.Game_Manager;
import game_air_fighter_image.Load_Images;

public class Game_Setup implements Runnable {
	private String title;
	private int width;
	private int height;
	private Thread thread;
	private boolean running;
	private BufferStrategy buffer;
	private Graphics g;
	private int y;
	private Game_Manager manager;
	private Display display;
	public static final  int gameWidth  = 400;
	public static final  int gameHeight = 400;
	
	public Game_Setup(String title,int width,int height){
		
		this.title  = title;
		this.width  = width;
		this.height = height;
	}
	
	public void  init(){
		display = new Display(title,width,height);
		Load_Images.init();
		manager = new Game_Manager();
		
		manager.init();
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		
		if(thread==null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop(){
		if(!(running))
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void tick(){
		manager.tick();
		
		
	}
	
	public void render(){
		buffer = display.getCanvas().getBufferStrategy();
		if(buffer == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		//draw
		
		
		g.drawImage(Load_Images.image,50,50,gameWidth,gameHeight,null);
		manager.render(g);
		
		
		
		// end draw work
		buffer.show();
		g.dispose();
		
	}

	@Override
	public void run() {
		
		init();
		
		int fps = 50;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		
		long current = System.nanoTime();
		
		
		while(running){
			
			delta = delta + (System.nanoTime()- current)/timePerTick;
			current = System.nanoTime();
			
			if(delta>=1){
			
	  	  tick();
		  render();
		  delta--;
			
			}
		}
		
	}

}
