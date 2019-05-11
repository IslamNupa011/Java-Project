package game_air_fighter_gamer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game_air_fighter_bullet.Bullet;
import game_air_fighter_display.Display;
import game_air_fighter_gamemanager.Game_Manager;
import game_air_fighter_image.Load_Images;

public class Gamer implements KeyListener {
	private int x;
	private int y;
	private boolean left;
	private boolean right;
	private boolean fire;
	
	private long current;
	private long delay;
	private int health;

	public Gamer(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void init(){
		Display.frame.addKeyListener(this);
		current =  System.nanoTime();
		delay = 100;
		health = 3;
		
	}
	
	public void tick(){
		if(!(health<=0)){
		if(left){
			if(x>=50){
			x-=4;
			}
		}
		if(right){
			if(x<=450-60){
			x+=4;
			}
		}
		if(fire){
			long breaking = (System.nanoTime() - current)/1000000;
			if(breaking>delay){
			Game_Manager.bullet.add(new Bullet(x+30,y));
			}
			current = System.nanoTime();
		}
	}
		
		
	}
	
	public void render(Graphics g){
		if(!(health<=0)){
		
		g.drawImage(Load_Images.player, x, y, 60,60,null);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int source = e.getKeyCode();
		if(source==KeyEvent.VK_LEFT){
			left = true;
		}
		if(source == KeyEvent.VK_RIGHT){
			right = true;
		}
		if(source == KeyEvent.VK_B){
			fire = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int source = e.getKeyCode();
		if(source==KeyEvent.VK_LEFT){
			left = false;
		}
		if(source == KeyEvent.VK_RIGHT){
			right = false;
		}
		if(source == KeyEvent.VK_B){
			fire = false;
		}
		
		
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getHealth(){
		return health;
	}
	public void setHealth(int health){
		this.health=health;
	}
}
