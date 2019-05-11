package game_air_fighter_bullet;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private int x;
	private int y;
	private int bullet_speed;
	
	public Bullet(int x,int y){
		this.y=y;
		this.x=x;
		bullet_speed=10;
		
	}
	
	public void tick(){
		y -=bullet_speed;
		
	}
	public int getY(){
		return y;
	}
	
	public int getX(){
		return x;
	}
	
	
	
	public void render(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(x, y, 6, 10);
		
		
	}

}
