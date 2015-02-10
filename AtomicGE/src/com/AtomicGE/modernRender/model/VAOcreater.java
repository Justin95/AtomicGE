package com.AtomicGE.modernRender.model;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL11;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.GPUprograms.ShaderProgram;
import com.AtomicGE.modernRender.texture.Texture;

public class VAOcreater { //https://open.gl/content/code/c6_base.txt
	
	
	/**
	 * Creates a VAO from the List of ModelTriangles
	 * @param triangles a List of ModelTriangles to create a VAO from
	 * @param shaderProgramID the integer ID of the shader who's inputs to bind the VBOs too
	 * @return an array who's first index is the created VAO's address,
	 * and who's second index is the length of the indices
	 */
	public static VAO createVAO(ArrayList<ModelTriangle> triangles,int shaderProgramID){
		//create VBOs
		ArrayList<Vertex> rawVertices = getAsVertexList(triangles);
		int[] orderOfVertices = getOrderOfVertices(rawVertices);
		ArrayList<Vertex> vertices = getOrderedVertices(rawVertices);
		int vertexVBO = getVertexVBO(vertices);
		int indiceVBO = getIndiceVBO(orderOfVertices);
		int texCoordsVBO = getTexCoordsVBO(vertices);
		int colorVBO = getColorVBO(vertices); 
		int normalVBO = getNormalVBO(vertices);
		
		Texture[] uniqueTextureIDs = getUniqueTexIDs(vertices);
		int textureIdVBO = getTextureIdVBO(uniqueTextureIDs,vertices);
		
		//create VAO
		int address = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(address);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexVBO);
		bindVBOtoShaderInput(ShaderProgram.POSITION_INDEX, GL11.GL_FLOAT, 3);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indiceVBO);//shader input binding unnecessary
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texCoordsVBO);
		bindVBOtoShaderInput(ShaderProgram.TEXTURE_COORDS_INDEX, GL11.GL_FLOAT, 2);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorVBO);
		bindVBOtoShaderInput(ShaderProgram.COLOR_INDEX, GL11.GL_FLOAT, 4);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, textureIdVBO);
		bindVBOtoShaderInput(ShaderProgram.TEXTURE_NUMBER, GL11.GL_FLOAT, 1);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, normalVBO);
		bindVBOtoShaderInput(ShaderProgram.NORMAL_INDEX, GL11.GL_FLOAT, 3);
		
		GL30.glBindVertexArray(0); //unbind vertex array
		return new VAO(address, orderOfVertices.length, uniqueTextureIDs);
	}
	
	
	/**
	 * Creates an ArrayList of Vertices from an ArrayList of ModelTriangles.
	 * @param triangles ModelTriangles to make the Vertex ArrayList from
	 * @return an ArrayList of Vertices
	 */
	private static ArrayList<Vertex> getAsVertexList(ArrayList<ModelTriangle> triangles){
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		for(ModelTriangle element : triangles){
			vertices.add(element.getA());
			vertices.add(element.getB());
			vertices.add(element.getC());
		}
		return vertices;
	}
	
	
	/**
	 * Calculates the order of the Vertices such that
	 * Vertices: a  b  c  b  c  d  a  b  d
	 * returns
	 * int[]:    1  2  3  2  3  4  1  2  4
	 * @param rawVertices an ArrayList of Vertices
	 * @return an int array describing the order of vertices as described above
	 */
	private static int[] getOrderOfVertices(ArrayList<Vertex> rawVertices){ // check this for bugs later
		ArrayList<Integer> order = new ArrayList<Integer>();
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		int counter = 0;
		for(int i = 0; i < rawVertices.size(); i++){
			Vertex a = rawVertices.get(i);
			int index = vertices.indexOf(a);
			if(index == -1){
				order.add(counter);
				vertices.add(a);
				counter++;
			}else{
				order.add(index);
			}
		}
		int[] vertexOrder = new int[order.size()];
		for(int i = 0; i < vertexOrder.length; i++){
			vertexOrder[i] = order.get(i);
		}
		return vertexOrder;
	}
	
	/**
	 * Calculates the order of vertices so that none are repeated such that
	 * rawVertices: a  b  c  b  c  d  c  d  a
	 * Vertices:    a  b  c  d
	 * @param rawVertices the list of unordered vertices
	 * @return an ArrayList of unique Vertices in the order they appear in rawVertices
	 */
	private static ArrayList<Vertex> getOrderedVertices(ArrayList<Vertex> rawVertices){
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		for(Vertex vertex : rawVertices){
			if(!vertices.contains(vertex)) vertices.add(vertex);
		}
		return vertices;
	}
	
	
	/**
	 * Creates an integer array of the unique textureIDs from the given ModelTriangles, in the order they appear.
	 * @param triangles the ArrayList of ModelTriangles to get textureIDs from
	 * @return an array of unique textureIDs from the given ModelTriangles
	 */
	public static Texture[] getUniqueTexIDs(ArrayList<Vertex> vertices){
		ArrayList<Texture> texIDs = new ArrayList<Texture>();
		for(Vertex vertex : vertices){
			Texture texture = vertex.getTexture();
			if(!texIDs.contains(texture)) texIDs.add(texture);
		}
		Texture[] textureIDs = new Texture[texIDs.size()];
		for(int i = 0; i < texIDs.size(); i++){
			textureIDs[i] = texIDs.get(i);
		}
		return textureIDs;
	}
	
	/**
	 * Creates a VBO describing which texture each Vertex should use.
	 * @param textureIDs
	 * @param triangles The ArrayList of ModelTriangles
	 * @return the integer ID of the created VBO
	 */
	public static int getTextureIdVBO(Texture[] textures, ArrayList<Vertex> vertices){
		float[] vertexTextures = new float[vertices.size()];
		for(int i = 0; i < vertices.size(); i++){
			Texture texture = vertices.get(i).getTexture();
			int textureNumber = 0;
			for(int j = 0; j < textures.length; j++){
				if(textures[j].equals(texture))	textureNumber = j+1; //find index of texture
			}
			vertexTextures[i] = textureNumber;
		}
		//stored as floats when it should be integers because openGL does not handle uploaded ints well, would arrive in shader as wrong number
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertexTextures.length);
		buffer.put(vertexTextures);
		buffer.flip();
		int vbo = createVBO(buffer, GL15.GL_ARRAY_BUFFER, GL15.GL_STATIC_DRAW);
		return vbo;
	}
	
	
	/**
	 * Creates a VBO of vertices given List of Vertices
	 * @param vertices
	 * @return the integer ID of the VBO
	 */
	private static int getVertexVBO(ArrayList<Vertex> vertices){
		float[] vertexPositions = new float[vertices.size() * 3];
		for(int i = 0; i < vertices.size(); i++){
			Vector pos = vertices.get(i).getPosition();
			vertexPositions[3 * i + 0] = (float) pos.getIHat();
			vertexPositions[3 * i + 1] = (float) pos.getJHat();
			vertexPositions[3 * i + 2] = (float) pos.getKHat();
		}
		//System.out.println(Arrays.toString(vertexPositions));
		FloatBuffer verticesBuffer = getAsBuffer(vertexPositions);
		return createVBO(verticesBuffer,GL15.GL_ARRAY_BUFFER,GL15.GL_STATIC_DRAW);
	}
	
	
	/**
	 * Creates a VBO of indices and returns its openGL recognized ID.
	 * @param orderOfVertices an int[] describing the order of vertices
	 * @return an openGL recognized VBO ID
	 */
	private static int getIndiceVBO(int[] orderOfVertices){
		short[] indices = new short[orderOfVertices.length];
		for(int i = 0; i < indices.length; i++) indices[i] = (short) orderOfVertices[i];
		ShortBuffer buffer = getAsBuffer(indices);
		int address = createVBO(buffer,GL15.GL_ELEMENT_ARRAY_BUFFER,GL15.GL_STATIC_DRAW);
		return address;
	}
	
	
	/**
	 * Creates a VBO of Texture Coordinates and returns its openGL recognized ID.
	 * @param vertices an ArrayList of Vertex objects describing the vertexes to get texture coordinates from
	 * @return the integer ID of the created VBO
	 */
	private static int getTexCoordsVBO(ArrayList<Vertex> vertices){
		float[] texCoords = new float[vertices.size() * 2]; // * 2 for 2 floats per textureCoord point
		for(int i = 0; i < vertices.size(); i++){
			Vector point = vertices.get(i).getTextureCoord();
			texCoords[2 * i + 0] = (float) point.getIHat();
			texCoords[2 * i + 1] = (float) point.getJHat();
		}
		FloatBuffer buffer = getAsBuffer(texCoords);
		int address = createVBO(buffer,GL15.GL_ARRAY_BUFFER,GL15.GL_STATIC_DRAW);
		return address;
	}
	
	
	/**
	 * Creates a VBO of Colors and returns its openGL recognized ID.
	 * @param vertices an ArrayList of Vertex objects describing the vertexes to get Colors from
	 * @return the integer ID of the created VBO
	 */
	private static int getColorVBO(ArrayList<Vertex> vertices){
		float[] colors = new float[vertices.size() * 4]; // * 4 for 4 floats per color
		for(int i = 0; i < vertices.size(); i++){
			Color color = vertices.get(i).getColor();
			colors[4 * i + 0] = color.getRed()   / 255f;
			colors[4 * i + 1] = color.getGreen() / 255f;
			colors[4 * i + 2] = color.getBlue()  / 255f;
			colors[4 * i + 3] = color.getAlpha() / 255f;
		}
		FloatBuffer buffer = getAsBuffer(colors);
		int address = createVBO(buffer, GL15.GL_ARRAY_BUFFER, GL15.GL_STATIC_DRAW);
		return address;
	}
	
	
	/**
	 * Creates a VBO of Normals and returns its openGL recognized ID.
	 * @param vertices an ArrayList of Vertex objects describing the vertexes to get the Normals from
	 * @return the integer ID of the created VBO
	 */
	private static int getNormalVBO(ArrayList<Vertex> vertices){
		float[] normals = new float[vertices.size() * 3]; //3 floats per vector
		for(int i = 0; i < vertices.size(); i++){
			Vector normal = vertices.get(i).getNormal();
			normals[3 * i + 0] = (float) normal.getIHat();
			normals[3 * i + 1] = (float) normal.getJHat();
			normals[3 * i + 2] = (float) normal.getKHat();
		}
		FloatBuffer buffer = getAsBuffer(normals);
		int address = createVBO(buffer, GL15.GL_ARRAY_BUFFER, GL15.GL_STATIC_DRAW);
		return address;
	}
	
	
	/**
	 * Creates an openGL VBO and return's its ID
	 * @param buffer a java.nio.FloatBuffer describing the contents of the VBO to be created
	 * @param target openGL target ex: GL_ARRAY_BUFFER
	 * @param usage how openGL will store the memory ex: GL_STATIC_DRAW
	 * @return the openGl recognized ID of the VBO
	 */
	private static int createVBO(FloatBuffer buffer, int target, int usage){
		int address = GL15.glGenBuffers();
		GL15.glBindBuffer(target, address);
		GL15.glBufferData(target, buffer, usage);
		return address;
	}
	
	
	/**
	 * Creates an openGL VBO and return's its ID
	 * @param buffer a java.nio.ShortBuffer describing the contents of the VBO to be created
	 * @param target openGL target ex: GL_ARRAY_BUFFER
	 * @param usage how openGL will store the memory ex: GL_STATIC_DRAW
	 * @return the openGl recognized ID of the VBO
	 */
	private static int createVBO(ShortBuffer buffer, int target, int usage){
		int address = GL15.glGenBuffers();
		GL15.glBindBuffer(target, address);
		GL15.glBufferData(target, buffer, usage);
		return address;
	}
	
	
	/**
	 * Creates an openGL VBO and return's its ID
	 * @param buffer a java.nio.IntBuffer describing the contents of the VBO to be created
	 * @param target openGL target ex: GL_ARRAY_BUFFER
	 * @param usage how openGL will store the memory ex: GL_STATIC_DRAW
	 * @return the openGl recognized ID of the VBO
	 */
	@SuppressWarnings("unused")
	private static int createVBO(IntBuffer buffer, int target, int usage){
		int address = GL15.glGenBuffers();//glsl does not play nice with integers, just a warning
		GL15.glBindBuffer(target, address);
		GL15.glBufferData(target, buffer, usage);
		return address;
	}
	
	
	/**
	 * Creates a java.nio.FloatBuffer and fills it with the data in the inputed float[].
	 * @param input an array of floats to be put into the new FloatBuffer
	 * @return a FloatBuffer filled from input
	 */
	private static FloatBuffer getAsBuffer(float[] input){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(input.length);
		buffer.put(input);
		buffer.flip();
		return buffer;
	}
	
	
	/**
	 * Creates a java.nio.ShortBuffer and fills it with the data in the inputed short[].
	 * @param input an array of shorts to be put into the new ShortBuffer
	 * @return a ShortBuffer filled from input
	 */
	private static ShortBuffer getAsBuffer(short[] input){
		ShortBuffer buffer = BufferUtils.createShortBuffer(input.length);
		buffer.put(input);
		buffer.flip();
		return buffer;
	}
	
	/**
	 * Specifies the layout of the vertex data in the currently bound VBO.
	 * A VBO MUST BE BOUND before a call to this method is made.
	 * Specifies the index of this VBO. Use the constants in ShaderProgram.java.
	 * @param index the arbitrarily decided index for a type of data, use the constants in ShaderProgram.java
	 * @param type the type of data in this VBO. ex: GL_FLOAT, GL_SHORT etc
	 * @param indicesPerElement the number of indices in this VBO to describe one Vertex
	 */
	private static void bindVBOtoShaderInput(int index,int type, int indicesPerElement){
		GL20.glEnableVertexAttribArray(index);
		GL20.glVertexAttribPointer(index,indicesPerElement,type,false,0,0);
	}
	
	
	
	
	
}
