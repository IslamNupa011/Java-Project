package game_air_fighter_main;

import game_air_fighter_display.Display;
import game_air_fighter_gamesetup.Game_Setup;

public class Main {
	
	public static void main(String[] args){
	Game_Setup game = new Game_Setup("Air Fighter",500,600);
	game.start();
	}

}
