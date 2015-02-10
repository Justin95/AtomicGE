package com.AtomicGE.modernRender.render;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;

public class RenderMatrixHelper {
	
	
	/**
	 * Calculates the View Matrix from the Camera's given position and rotation vector.
	 * @param pos the camera's position Vector
	 * @param rot the camera's rotation Vector
	 * @return a Matrix Object representing the View Matrix
	 */
	public static Matrix4f getViewMatrix(Vector pos, Vector rot){
		pos = VectorMath.getInverse(pos);
		rot = VectorMath.getInverse(rot);
		Matrix4f view = getModelMatrix(pos,rot);
		return view;
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
	public static Matrix4f getProjectionMatrix(float fov, float aspect, float nearClip, float farClip){
		float yScale = (float) (1.0/Math.tan(Math.toRadians(fov/2f)));
		float xScale = yScale / aspect;
		Matrix4f proj = Matrix4f.setZero(new Matrix4f());
		
		//http://wiki.lwjgl.org/index.php?title=The_Quad_with_Projection,_View_and_Model_matrices
		proj.m00 = xScale;
		proj.m11 = yScale;
		proj.m22 = -((farClip + nearClip) / (farClip - nearClip));
		proj.m23 = -1;
		proj.m32 = -((2 * nearClip * farClip) / (farClip - nearClip));
		
		return proj;
	}
	
	
	
	
	/**
	 * Gets a new Matrix representing a Model Matrix for this position and rotation.
	 * @param pos the Model's position Vector
	 * @param rot the Model's rotation Vector, in degrees
	 */
	public static Matrix4f getModelMatrix(Vector pos, Vector rot){
		//convert given vectors to LWJGL supported objects of Vector3f
		Vector3f position = new Vector3f((float)pos.getIHat(),(float)pos.getJHat(),(float)pos.getKHat());
		float xRot = -(float)Math.toRadians(rot.getIHat());
		float yRot = -(float)Math.toRadians(rot.getJHat());
		float zRot = -(float)Math.toRadians(rot.getKHat());
		Matrix4f modelMatrix = Matrix4f.setIdentity(new Matrix4f());
		
		//scale rotate then translate
		
		Matrix4f.rotate(xRot, new Vector3f(1f,0,0), modelMatrix, modelMatrix);
		Matrix4f.rotate(yRot, new Vector3f(0,1f,0), modelMatrix, modelMatrix);
		Matrix4f.rotate(zRot, new Vector3f(0,0,1f), modelMatrix, modelMatrix);
		
		Matrix4f.translate(position, modelMatrix, modelMatrix);
		
		return modelMatrix;
	}
	
	
}
