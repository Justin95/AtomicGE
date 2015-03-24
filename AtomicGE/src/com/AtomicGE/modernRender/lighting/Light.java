package com.AtomicGE.modernRender.lighting;

import org.lwjgl.opengl.GL20;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.GPUprograms.Shaders;

public class Light {
	
	private Vector direction;
	
	/**
	 * Creates a Light shining from a specific direction with no origin location.
	 * @param direction
	 */
	public Light(Vector direction){
		this.direction = direction;
	}
	
	
	
	
	/**
	 * Makes this Light the active light.
	 */
	public void enable(){
		float x = (float) -this.direction.getIHat(); //must invert the vector
		float y = (float) -this.direction.getJHat();
		float z = (float) -this.direction.getKHat();
		for(int program : Shaders.SHADER_PROGRAMS){
			GL20.glUseProgram(program);
			int uniformLocation = GL20.glGetUniformLocation(program, "lightDirection");
			GL20.glUniform3f(uniformLocation, x, y, z);
		}
	}
	
	
	public static void setAmbientLight(float light){
		//TODO
	}
	
}
