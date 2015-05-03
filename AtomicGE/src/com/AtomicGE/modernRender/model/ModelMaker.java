package com.AtomicGE.modernRender.model;

import java.awt.Color;
import java.util.ArrayList;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.GPUprograms.Shaders;
import com.AtomicGE.modernRender.renderObject.RenderObject;
import com.AtomicGE.modernRender.texture.Texture;
import com.AtomicGE.modernRender.texture.TextureLibrary;
import com.EngineTest.game.Material;
import com.EngineTest.game.MaterialLibrary;
import com.EngineTest.terrain.Sector;
import com.EngineTest.terrain.SkyBox;
import com.EngineTest.terrain.TerrainMap;

public class ModelMaker {
	
	public static double MAX_GRASS_HEIGHT = 100;
	public static double CLIFF_SLOPE = 1;
	
	/**
	 * Creates a model from the given heightMap
	 * @param heightMap an array of arrays of doubles, which describe heights
	 * @return a com.OpenGLPractace.modernRender.model.Model object which describes how to render the given heightMap
	 */
	public static Model makeModel(TerrainMap terrain,double distanceBetweenPoints, MaterialLibrary matLib){
		int shaderProgramID = Shaders.MESH_SHADER_PROGRAM;
		ArrayList<ModelTriangle> triangles = new ArrayList<ModelTriangle>();
		for(int i = 0; i < terrain.getWidth()-1; i++){
			for(int j = 0; j < terrain.getWidth()-1; j++){
				ModelTriangle[] twoTriangles = createHeightMapSquare(i, j, terrain, distanceBetweenPoints);
				triangles.add(twoTriangles[0]);
				triangles.add(twoTriangles[1]);
			}
		}
		addOcean(triangles, distanceBetweenPoints * (terrain.getWidth() - 1), terrain.getOceanMaterial());
		Model model = new Model(triangles,shaderProgramID);
		return model;
	}
	
	
	
	/**
	 * Creates the 2 ModelTriangles to the top right of the given point of the given heightMap.
	 * @param x the x coordinate
	 * @param z the z coordinate
	 * @param heightMap an array of arrays of doubles representing heights
	 * @param distance the distance between points in the heightMap
	 * @return an array of ModelTriangle objects of length 2
	 */
	private static ModelTriangle[] createHeightMapSquare(int x, int z, TerrainMap terrain, double distance){
		Color triangleColor = Color.WHITE;
		Texture texture = terrain.materialAt(x, z).getTexture();
		
		Vector pos  = new Vector(x * distance,terrain.heightAt(x, z),z*distance);
		Vector norm =  terrain.normalAt(x, z);
		Vector texCoord = new Vector(x,z,0);
		Vertex v1   = new Vertex(pos,norm,texCoord,triangleColor, texture);
		
		pos         = new Vector((x+1)*distance,terrain.heightAt(x+1, z),z  *distance);
		norm		=  terrain.normalAt(x+1, z);
		texCoord    = new Vector(x+1, z  ,0); 
		Vertex v2   = new Vertex(pos,norm,texCoord,triangleColor, texture);
		
		pos         = new Vector(x * distance,terrain.heightAt(x, z+1),(z+1)*distance);
		norm 		=  terrain.normalAt(x, z+1);
		texCoord    = new Vector(x  ,z+1, 0);
		Vertex v3   = new Vertex(pos,norm,texCoord,triangleColor, texture);
		
		pos         = new Vector((x+1)*distance,terrain.heightAt(x+1, z+1),(z+1)*distance);
		norm 		=  terrain.normalAt(x+1, z+1);
		texCoord    = new Vector( x+1, z+1, 0);
		Vertex v4   = new Vertex(pos,norm,texCoord,triangleColor, texture);
		
		
		ModelTriangle modelTri1 = new ModelTriangle(v1,v4,v2);
		ModelTriangle modelTri2 = new ModelTriangle(v1,v3,v4);
		return new ModelTriangle[]{modelTri1,modelTri2};
	}
	
	
	
	/**
	 * Creates a triangle for Debugging purposes.
	 * @return
	 */
	public static Model getTestModel(){
		ArrayList<ModelTriangle> triangles = new ArrayList<ModelTriangle>();
		Vertex a = new Vertex(new Vector(0,0,0),new Vector(0,1,0),new Vector(0,0,0), Color.BLUE, Textures.GRASS);
		Vertex b = new Vertex(new Vector(1,0,0),new Vector(0,1,0),new Vector(1,0,0), Color.RED , Textures.GRASS);
		Vertex c = new Vertex(new Vector(1,1,1),new Vector(0,1,0),new Vector(0,1,0), Color.GREEN,Textures.GRASS);
		triangles.add(new ModelTriangle(a,b,c));
		return new Model(triangles,Shaders.MESH_SHADER_PROGRAM);
	}
	
	//dirty temp method
	private static void addOcean(ArrayList<ModelTriangle> triangles, double length, Material oceanMat){
		double sea = Sector.SEA_LEVEL;
		Texture water = oceanMat.getTexture();
		Vector normal = new Vector(0,1,0);
		Vertex aa = new Vertex(new Vector(0,sea,0), normal, new Vector(0,0,0), Color.WHITE, water);
		Vertex ba = new Vertex(new Vector(length,sea,0), normal, new Vector(1,0,0), Color.WHITE, water);
		Vertex ab = new Vertex(new Vector(0,sea,length), normal,new Vector(0,1,0), Color.WHITE, water);
		Vertex bb = new Vertex(new Vector(length,sea,length), normal, new Vector(1,1,0), Color.WHITE, water);
		ModelTriangle first      = new ModelTriangle(aa,bb,ba);
		ModelTriangle second     = new ModelTriangle(aa,ab,bb);
		ModelTriangle firstBack  = new ModelTriangle(aa,ba,bb);
		ModelTriangle secondBack = new ModelTriangle(aa,bb,ab);
		triangles.add(first);
		triangles.add(second);
		triangles.add(firstBack);
		triangles.add(secondBack);
	}
	
	
	public static SkyBox getDefaultSkyBox(){
		Color skyColor = new Color(135,206,235);
		ArrayList<ModelTriangle> triangles = new ArrayList<>();
		Vector zero = new Vector(0,0,0);
		Vertex a = new Vertex(new Vector(-1,-1,-1),zero,zero,skyColor,Textures.NO_TEXTURE);
		Vertex b = new Vertex(new Vector( 1,-1,-1),zero,zero,skyColor,Textures.NO_TEXTURE);
		Vertex c = new Vertex(new Vector(-1, 1,-1),zero,zero,skyColor,Textures.NO_TEXTURE);
		Vertex d = new Vertex(new Vector( 1, 1,-1),zero,zero,skyColor,Textures.NO_TEXTURE);
		Vertex e = new Vertex(new Vector(-1,-1, 1),zero,zero,skyColor,Textures.NO_TEXTURE);
		Vertex f = new Vertex(new Vector( 1,-1, 1),zero,zero,skyColor,Textures.NO_TEXTURE);
		Vertex g = new Vertex(new Vector(-1, 1, 1),zero,zero,skyColor,Textures.NO_TEXTURE);
		Vertex h = new Vertex(new Vector( 1, 1, 1),zero,zero,skyColor,Textures.NO_TEXTURE);
		triangles.add(new ModelTriangle(a,b,c));
		triangles.add(new ModelTriangle(b,d,c));
		Model model = new Model(triangles, Shaders.FLAT_SHADER_PROGRAM);
		return new SkyBox(model, zero, zero);
	}
	
	
}
