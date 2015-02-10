package com.AtomicGE.game;

import com.AtomicGE.modernRender.lighting.Light;
import com.AtomicGE.modernRender.model.ModelMaker;
import com.AtomicGE.modernRender.render.Renderer;
import com.AtomicGE.modernRender.renderObject.RenderObjectModifier;
import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;
import com.AtomicGE.sound.SoundPlayer;
import com.AtomicGE.sound.Sounds;

public class Game {
	
	private Renderer render;
	private boolean running;
	private Player player;
	private World world;
	private SoundPlayer soundPlayer;
	
	
	public Game(Renderer render,long seed){
		this.render = render;
		running = true;
		player = new Player(new Vector(0,0,0),new Vector(0,0,0));
		this.world = new World(seed,17);
		this.soundPlayer = new SoundPlayer();
	}
	
	
	/**
	 * Start the game loop
	 */
	public void start(){
		//this.soundPlayer.playSound(Sounds.TEST_SONG);
		loop();
		stop();
	}
	
	
	/**
	 * The main game loop
	 */
	private void loop(){
		Light light = new Light(VectorMath.getUnitVector(new Vector(10,-10, 2)));
		light.enable();
		double t = 0;
		//render.addToRenderList(new RenderObjectModifier(ModelMaker.getTestModel(),new Vector(0,30,0),new Vector(0,0,0)).getRenderObject());
		while(running){
			light = new Light(VectorMath.getUnitVector(new Vector(Math.sin(t), -.4, Math.cos(t))));
			light.enable();
			if(!render.shouldKeepRunning()) running = false;
			
			world.updateWorld(player.getPosition());
			
			render.addToRenderList(world.getRenderObjects());
			player.update();
			
			render.updateCamera(player.getPosition(), player.getRotation());
			render.render();
			
			render.removeFromRenderList(world.getRenderObjects());
			t = t + .01;
		}
	}
	
	
	private void stop(){
		render.cleanUp();
		soundPlayer.shutDown();
	}
	
}
