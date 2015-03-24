package com.AtomicGE.main;

import com.AtomicGE.modernRender.render.Renderer;
import com.AtomicGE.game.Game;

public class Main {
	
	
	public static void main(String[] args){
		
		Renderer render = new Renderer(70,.3f,4000f);
		Game game = new Game(render,2376582);
		
		game.start();
		
		
		
	}
	
	
	
}
