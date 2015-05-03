package com.EngineTest.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.render.RenderQueue;
import com.AtomicGE.modernRender.render.RenderSettings;
import com.AtomicGE.modernRender.render.Renderer;
import com.AtomicGE.modernRender.renderObject.RenderObject;

public class RenderThread extends Thread{
	
	
	
	private Renderer render;
	private boolean keepRunning;
	private List<RenderObject> toRender;
	private RenderSettings renderSettings;
	private RenderQueue renderQueue;
	private boolean initalized;
	
	public RenderThread(RenderSettings renSettings, RenderQueue renderQueue){
		this.toRender = new ArrayList<RenderObject>();
		this.renderSettings = renSettings;
		this.renderQueue = renderQueue;
	}
	
	
	
	
	/**
	 * Main entry point of this thread.
	 */
	public void run(){
		this.keepRunning = true;
		this.render = new Renderer(this.renderSettings);
		this.render.initalize();
		this.initalized = true;
		renderLoop();
	}
	
	
	
	private void renderLoop(){
		while(keepRunning){
			renderQueue.enableQueuedLights();
			renderQueue.createQueuedTextures();
			renderQueue.initalizeQueuedModels();
			render.render(toRender);
		}
	}
	
	
	
	public boolean shouldKeepRunning(){
		if(!this.initalized) return true;
		return render.shouldKeepRunning();
	}
	
	
	public void updateCamera(Vector pos, Vector rot){
		render.updateCamera(pos, rot);
	}
	
	
	/**
	 * Sets the List of RenderObjects which are drawn each frame.
	 * @param renObj
	 */
	public void setToRenderer(RenderObject... renObj){
		List<RenderObject> copy = Arrays.asList(renObj);
		this.toRender = copy;
	}
	
	
	
	
	public void cleanUp(){
		this.keepRunning = false;
	}
	
	
	
}
