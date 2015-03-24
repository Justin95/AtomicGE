package com.AtomicGE.terrain;

import com.AtomicGE.modernRender.model.ModelMaker;
import com.AtomicGE.modernRender.renderObject.RenderObject;

public class Sky {
	
	
	private SkyBox skyBox;
	
	
	public Sky(){
		this.skyBox = ModelMaker.getDefaultSkyBox();//TEMP skybox source
	}
	
	
	public RenderObject getRenderObject(){
		return this.skyBox;
	}
	
	
	
	
}
