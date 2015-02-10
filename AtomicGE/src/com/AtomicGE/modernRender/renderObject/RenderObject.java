package com.AtomicGE.modernRender.renderObject;

import org.lwjgl.util.vector.Matrix4f;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.model.Model;
import com.AtomicGE.modernRender.render.RenderMatrixHelper;


/**
 * 
 * @author Justin Bonner
 *
 *This is the class which is rendered by the RenderObjectRenderer (com.AtomicGE.modernRenderer.RORenderer).
 *This class contains all the necessary information to draw itself.
 *This class only gives information to the RORenderer and does not do any rendering itself.
 *This class cannot be instantiated and must be created by creating a RenderObjectModifier and
 *retrieving the RenderObject from there. A RenderObject can only be modified by its RenderObjectModifier.
 *RenderObjectModifiers are used so new RenderObjects do not need to be removed from a list, recreated, and re-added,
 *in order to modify them.
 */
public class RenderObject { 
	
	private Model model;
	private Vector position;
	private Vector rotation;
	private Matrix4f modelMatrix;
	
	
	protected RenderObject(Model model, Vector position, Vector rotation){ //might want make shader programs per RO
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		updateModelMatrix(position,rotation);
	}
	
	
	
	
	
	/**
	 * Updates this Object's ModelMatrix to account for a change in position and or rotation.
	 * @param pos the new position
	 * @param rot the new rotation
	 */
	private void updateModelMatrix(Vector pos, Vector rot){
		this.modelMatrix = RenderMatrixHelper.getModelMatrix(pos,rot);
	}
	
	
	
	/**
	 * Sets the model for this RenderObject. This should only be called in RenderObjectModifier
	 * @param model the new model
	 */
	protected void setModel(Model model) {
		this.model = model;
	}
	
	
	/**
	 * Updates this RenderObject's position and rotation Vectors simultaneously.
	 * @param position the new Position Vector
	 * @param rotation the new Rotation Vector
	 */
	protected void setPositionRotation(Vector position, Vector rotation){
		this.position = position;
		this.rotation = rotation;
		updateModelMatrix(position,rotation);
	}
	
	
	/**
	 * Sets the position for this RenderObject. This should only be called in RenderObjectModifier
	 * @param position the new position Vector
	 */
	protected void setPosition(Vector position) {
		this.position = position;
		updateModelMatrix(position,this.rotation);
	}
	
	
	
	/**
	 * Sets the rotation for this RenderObject. This should only be called in RenderObjectModifier
	 * @param rotation the new rotation Vector
	 */
	protected void setRotation(Vector rotation) {
		this.rotation = rotation;
		updateModelMatrix(this.position,rotation);
	}
	
	
	
	/**
	 * 
	 * @return this RenderObject's Model Matrix
	 */
	public Matrix4f getModelMatrix(){
		return this.modelMatrix;
	}
	
	
	/**
	 * 
	 * @return the Model of this RenderObject
	 */
	public Model getModel() {
		return model;
	}
	
	/**
	 * 
	 * @return the position Vector of this RenderObject
	 */
	public Vector getPosition() {
		return position;
	}
	
	/**
	 * 
	 * @return the rotation Vector of this RenderObject
	 */
	public Vector getRotation() {
		return rotation;
	}
	
	/**
	 * 
	 * @return the number of Indices in this RenderObejct's Model
	 */
	public int getNumIndices(){
		return this.model.getNumIndices();
	}
	
}
