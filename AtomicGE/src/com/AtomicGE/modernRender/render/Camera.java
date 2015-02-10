package com.AtomicGE.modernRender.render;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.GPUprograms.Shaders;

public class Camera {
	
	private Vector position;
	private Vector rotation;
	private Matrix4f projection;
	private float fov;
	private float nearClip;
	private float farClip;
	
	/**
	 * Creates a new Camera Object
	 * @param pos the camera position
	 * @param rot the camera rotation
	 * @param fov the field of vision of this Camera
	 * @param aspect the aspect ratio of this Camera
	 * @param nearClip the near clipping distance of this Camera
	 * @param farClip  the far clipping distance of this Camera
	 */
	Camera(Vector pos, Vector rot,float fov, float aspect, float nearClip, float farClip){
		this.position = pos;
		this.rotation = rot;
		this.fov = fov;
		this.nearClip = nearClip;
		this.farClip = farClip;
		this.projection = getProjectionMatrix(fov,aspect,nearClip,farClip);
	}
	
	
	
	/**
	 * Calculates the View Matrix from a given position and rotation vector.
	 * @param pos the position Vector
	 * @param rot the rotation Vector
	 * @return a Matrix Object representing the View Matrix
	 */
	private Matrix4f getViewMatrix(Vector pos, Vector rot){
		return RenderMatrixHelper.getViewMatrix(pos, rot);
	}
	
	
	
	/**
	 * Calculates the Projection Matrix from:
	 * field of vision, aspect ratio, near clipping distance, and far clipping distance
	 * @param fov the field of vision
	 * @param aspect the aspect ratio (width / height)
	 * @param nearClip the near clipping distance
	 * @param farClip the far clipping distance
	 * @return a Matrix object representing the Projection Matrix
	 */
	private Matrix4f getProjectionMatrix(float fov, float aspect, float nearClip, float farClip){
		return RenderMatrixHelper.getProjectionMatrix(fov,aspect,nearClip,farClip);
	}
	
	
	
	/**
	 * Sets the view and projection matrices for all the legal shader programs.
	 */
	public void useCameraView(){
		
		Matrix4f view = getViewMatrix(position,rotation);
		int[] programs = Shaders.SHADER_PROGRAMS;
		for(int program : programs){
			GL20.glUseProgram(program);//bind program to allow uniform locations
			
			int uniformLoc = GL20.glGetUniformLocation(program,"view");
			FloatBuffer viewBuffer = BufferUtils.createFloatBuffer(16); //16 for a 4 by 4 matrix
			view.store(viewBuffer);
			viewBuffer.flip();
			GL20.glUniformMatrix4(uniformLoc, false, viewBuffer);
			
			uniformLoc = GL20.glGetUniformLocation(program,"proj");
			FloatBuffer projBuffer = BufferUtils.createFloatBuffer(16);
			this.projection.store(projBuffer);
			projBuffer.flip();
			GL20.glUniformMatrix4(uniformLoc, false, projBuffer);
			
		}
	}
	
	
	/**
	 * Updates this Camera object with the given Vectors.
	 * @param pos the new position vector
	 * @param rot the new rotation vector
	 */
	public void updateCamera(Vector pos, Vector rot){
		this.position = pos;
		this.rotation = rot;
	}
	
	
	/**
	 * Updates the Projection Matrix to account for any changes in the Window's DisplayMode.
	 * @param dm the new DisplayMode to account for
	 */
	public void updateCamera(DisplayMode dm){
		float aspectRatio = ((float)dm.getWidth())/dm.getHeight();
		this.projection = getProjectionMatrix(this.fov,aspectRatio,nearClip,farClip);
	}
	
	
	
}
