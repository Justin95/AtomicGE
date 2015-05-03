package com.AtomicGE.modernRender.render;

public class RenderSettings {
	
	
	private float fov;
	private float nearClip;
	private float farClip;
	
	
	public RenderSettings(float fov, float nearClip, float farClip){
		this.fov = fov;
		this.nearClip = nearClip;
		this.farClip = farClip;
	}
	
	
	
	public float getFov(){
		return this.fov;
	}
	
	public float getNearClippingDistance(){
		return this.nearClip;
	}
	
	public float getFarClippingDistance(){
		return this.farClip;
	}
	
}
