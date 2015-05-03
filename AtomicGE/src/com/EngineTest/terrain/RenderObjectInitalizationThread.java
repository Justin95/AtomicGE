package com.EngineTest.terrain;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.model.Model;
import com.AtomicGE.modernRender.render.RenderQueue;
import com.AtomicGE.modernRender.renderObject.RenderObject;

public class RenderObjectInitalizationThread extends Thread {
	
	private Model model;
	private Vector pos;
	private Vector rot;
	private Sector sec;
	private RenderQueue renQueue;
	
	public RenderObjectInitalizationThread(Model model, Vector pos, Vector rot, Sector sec, RenderQueue renQueue){
		this.model = model;
		this.pos = pos;
		this.rot = rot;
		this.sec = sec;
		this.renQueue = renQueue;
	}
	
	
	public void run(){
		renQueue.initializeModel(model);
		RenderObject renObject = new RenderObject(model, pos, rot);
		sec.setRenderObject(renObject);
	}
	
	
}
