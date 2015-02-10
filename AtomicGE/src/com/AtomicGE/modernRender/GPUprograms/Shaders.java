package com.AtomicGE.modernRender.GPUprograms;


public class Shaders {
	
	public final static int DEFAULT_SHADER_PROGRAM = new ShaderProgram("DefaultVertexShader","DefaultFragmentShader").getProgramID();
	
	public final static int MESH_SHADER_PROGRAM = new ShaderProgram("MeshVertexShader","MeshFragmentShader").getProgramID();
	
	public final static int FLAT_SHADER_PROGRAM = new ShaderProgram("2DVertexShader","2DFragmentShader").getProgramID();
	
	public final static int CAMERA_STATIC_SHADER_PROGRAM = new ShaderProgram("CameraStaticVertexShader","CameraStaticFragmentShader").getProgramID();
	
	
	/**
	 * The Shader programs allowed for use.
	 */
	public final static int[] SHADER_PROGRAMS = new int[]{
		DEFAULT_SHADER_PROGRAM,
		MESH_SHADER_PROGRAM,
		FLAT_SHADER_PROGRAM,
		CAMERA_STATIC_SHADER_PROGRAM
	};
	
			
	
	
}
