package game_air_fighter_enemies;

import java.awt.Color;
import java.awt.Graphics;

import game_air_fighter_image.Load_Images;

public class Enemy {
	private int x;
	private int y;
	
	public Enemy(int x,int y){
		this.x=x;
		this.y=y;
		
	}
	
	public void tick(){
		y+=1;
		
	}
	
	public void render(Graphics g){
		
		
		g.drawImage(Load_Images.enemy, x, y, 25,25,null);
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
