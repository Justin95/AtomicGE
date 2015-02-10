package com.AtomicGE.modernRender.renderObject;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.model.Model;

/**
 * 
 * @author Justin Bonner
 *This class is the only way to create a new RenderObject.
 *This class is also the only way to modify an existing RenderObject.
 *This is done to encourage object reuse in a performance sensitive environment,
 *in which recreating large numbers new objects frequently is unacceptable.
 *This class allows thread safe modification of its RenderObject.
 */
public class RenderObjectModifier {
	
	private RenderObject renderObject;
	
	public RenderObjectModifier(Model model, Vector position, Vector rotation){
		this.renderObject = new RenderObject(model,position,rotation);
	}
	
	
	public void setPosition(Vector pos){
		this.renderObject.setPosition(pos);
	}
	
	
	public void setRotation(Vector rot){
		this.renderObject.setRotation(rot);
	}
	
	
	public void setModel(Model model){
		this.renderObject.setModel(model);
	}
	
	
	
	/**
	 * 
	 * @return the RenderObject associated with this RenderObjectModifier
	 */
	public RenderObject getRenderObject(){
		return this.renderObject;
	}
	
}
