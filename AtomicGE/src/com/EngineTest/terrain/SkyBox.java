package com.EngineTest.terrain;

import org.lwjgl.opengl.GL11;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.model.Model;
import com.AtomicGE.modernRender.renderObject.RenderObject;

public class SkyBox extends RenderObject{
	
	
	
	
	public SkyBox(Model model, Vector position, Vector rotation) {
		super(model, position, rotation);
	}
	
	
	/**
	 * Called before rendering this SkyBox
	 */
	@Override
	public void preRenderCall(){
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	
	
	/**
	 * Called after rendering this SkyBox
	 */
	@Override
	public void postRenderCall(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	
	
	
	
}
