package com.AtomicGE.modernRender.render;

import static org.lwjgl.opengl.GL20.glUseProgram;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import com.AtomicGE.modernRender.model.Model;
import com.AtomicGE.modernRender.renderObject.RenderObject;
import com.AtomicGE.modernRender.texture.Texture;

/**
 * 
 * @author Justin Bonner
 * This class exists in order to render RenderObjects
 *
 */
public class RORenderer {
	
	
	RORenderer(){
		
	}
	
	
	/**
	 * Renders the given RenderObject.
	 * @param renderObject the RenderObject to render
	 */
	public void render(RenderObject renderObject){
		
		Model model = renderObject.getModel();
		int numOfIndices = renderObject.getNumIndices();
		int shaderProgram = model.getShaderProgramID();
		Matrix4f modelMatrix = renderObject.getModelMatrix();
		
		glUseProgram(shaderProgram); //binds the program
		
		int uniformLoc = GL20.glGetUniformLocation(shaderProgram,"model");
		FloatBuffer modelBuffer = BufferUtils.createFloatBuffer(16);
		modelMatrix.store(modelBuffer);
		modelBuffer.flip();
		GL20.glUniformMatrix4(uniformLoc, false, modelBuffer); //that boolean should be false, don't know why
		
		bindTextures(shaderProgram, model.getTextures());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		
		GL30.glBindVertexArray(model.getVAO());
		GL11.glDrawElements(GL11.GL_TRIANGLES,numOfIndices, GL11.GL_UNSIGNED_SHORT, 0);
	}
	
	
	private void bindTextures(int shaderProgram, Texture[] textures){
		for(int i = 0; i < textures.length; i++){
			int uniformLoc = GL20.glGetUniformLocation(shaderProgram, "texture" + (i+1));//variables named texture1..
			GL13.glActiveTexture(GL13.GL_TEXTURE0 + i);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,textures[i].getTextureID());
			GL20.glUniform1i(uniformLoc, i);
		}
	}
	
}
