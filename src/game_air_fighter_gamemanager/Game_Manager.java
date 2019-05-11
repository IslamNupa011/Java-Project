package game_air_fighter_gamemanager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import game_air_fighter_bullet.Bullet;
import game_air_fighter_display.Display;
import game_air_fighter_enemies.Enemy;
import game_air_fighter_gamer.Gamer;
import game_air_fighter_gamesetup.Game_Setup;

public class Game_Manager implements KeyListener {
	private Gamer gamer;
	public static ArrayList<Bullet> bullet;
	private ArrayList<Enemy> enemies;
	private long current;
	private long delay;
	private int health;
	private int score;
	private boolean start;

	public Game_Manager(){
		
	}
	
	public void init(){
		Display.frame.addKeyListener(this);
		
		gamer = new Gamer((Game_Setup.gameWidth/2)+50,(Game_Setup.gameHeight-60)+50);
		gamer.init();
		bullet = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		
		current = System.nanoTime();
		delay = 2000;
		health = gamer.getHealth();
		score = 0;
		start = true;
	}
	
	public void tick(){
		if(start){
		gamer.tick();
		for(int i=0 ; i<bullet.size(); i++){
			bullet.get(i).tick();
			
			
		}
		
		long breaks  = (System.nanoTime() - current)/1000000;
		if(breaks>delay){
		for(int i=0;i<2;i++){
			Random rand  = new Random();
			int randX = rand.nextInt(450);
			int randY = rand.nextInt(450);
			if(health>0){
			enemies.add(new Enemy(randX,-randY));
			}
		}
		current = System.nanoTime();
	}
		
		for(int i=0;i<enemies.size();i++){
			enemies.get(i).tick();
		}
	}
		
	}
	
	public void render(Graphics g){
		if(start){
		gamer.render(g);
		for(int i=0;i<bullet.size();i++){
			bullet.get(i).render(g);
		}
		for(int i=0;i<bullet.size();i++){
			if(bullet.get(i).getY()<=50){
				bullet.remove(i);
				i--;
			}
		}
		
		for(int i=0;i<enemies.size();i++){
			if(!(enemies.get(i).getX()<=50 || enemies.get(i).getX()>=450-50 || enemies.get(i).getY()>=450-50)) {
				if(enemies.get(i).getY()>=50){
			enemies.get(i).render(g);
				}
			}
		}
		for(int i =0;i<enemies.size();i++){
			int ex = enemies.get(i).getX();
			int ey = enemies.get(i).getY();
			
			int px = gamer.getX();
			int py = gamer.getY();
			
			if(px < ex+50 && px+60>ex && py<ey+50 && py+60>ey){
				enemies.remove(i);
				i--;
				health --;
				System.out.println(health);
				if(health<=0){
					enemies.removeAll(enemies);
					gamer.setHealth(0);
					start = false;
				}
			}
			
			
			for(int j=0;j<bullet.size();j++){
				int bx = bullet.get(j).getX();
				int by = bullet.get(j).getY();
				
				if(ex < bx + 6 && ex + 50 > bx && ey<by + 6 && ey+50 > by){
					enemies.remove(i);
					i--;
					bullet.remove(j);
					j--;
					score = score+5;
				}
				
				
			}
			g.setColor(Color.blue);
			g.setFont(new Font("arial",Font.BOLD,40));
			g.drawString("Score: "+score, 70, 500);
			g.setFont(new Font("arial",Font.BOLD,15));
			g.drawString("Developed By Nupa Islam", 70, 550);
			
		}
	}
else{
	
			g.setFont(new Font("arial",Font.PLAIN,30));
			g.setColor(Color.GREEN);
			g.drawString("Press Enter To Restart",70,(Game_Setup.gameHeight/2)+50);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int source = e.getKeyCode();
		if(source == KeyEvent.VK_ENTER){
			start = true;
			init();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
