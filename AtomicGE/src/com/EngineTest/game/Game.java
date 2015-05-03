package com.EngineTest.game;

import com.AtomicGE.modernRender.lighting.Light;
import com.AtomicGE.modernRender.render.RenderQueue;
import com.AtomicGE.modernRender.render.RenderSettings;
import com.AtomicGE.modernRender.texture.TextureLibrary;
import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;
import com.AtomicGE.sound.SoundPlayer;

public class Game {
	
	private RenderThread renderThread;
	private boolean running;
	private Player player;
	private World world;
	private SoundPlayer soundPlayer;
	private RenderQueue renderQueue;
	private TextureLibrary textureLib;
	
	
	public Game(float fov, float nearClip, float farClip, long seed){
		this.renderQueue = new RenderQueue();
		this.renderThread = new RenderThread(new RenderSettings(fov, nearClip, farClip), renderQueue);
		renderThread.start();
		player = new Player(new Vector(0,0,0),new Vector(0,0,0));
		this.textureLib = new TextureLibrary(renderQueue, "Grass", "Grass1", "Grass256", "NO_TEXTURE", "Sand", "Stone", "Water");
		this.world = new World(seed, 17, renderQueue, textureLib);
		this.soundPlayer = new SoundPlayer();
	}
	
	
	/**
	 * Start the game loop
	 */
	public void start(){
		this.running = true;
		//this.soundPlayer.playSound(Sounds.TEST_SONG);
		loop();
		stop();
	}
	
	
	/**
	 * The main game loop
	 */
	private void loop(){
		double t = 0;
		while(running){
			Light light = new Light(VectorMath.getUnitVector(new Vector(Math.sin(t), -.4, Math.cos(t))));
			renderQueue.enableLight(light);
			if(!renderThread.shouldKeepRunning()) running = false;
			
			world.updateWorld(player.getPosition());
			//System.out.println("game tick");
			renderThread.setToRenderer(world.getRenderObjects());
			player.update();
			
			renderThread.updateCamera(player.getPosition(), player.getRotation());
			
			t = t + .01;
			try{
				Thread.sleep(8); //arbitrary use proper system later
			}catch(Exception e){
				
			}
		}
	}
	
	
	private void stop(){
		renderThread.cleanUp();
		try {
			renderThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		soundPlayer.shutDown();
	}
	
}
