package com.EngineTest.game;

import java.util.HashMap;

import com.AtomicGE.modernRender.texture.TextureLibrary;

public class MaterialLibrary {
	
	
	private HashMap<String, Material> materials;
	
	
	public MaterialLibrary(){
		materials = new HashMap<String, Material>();
	}
	
	
	/**
	 * Adds a new Material to this Material Library and associates it with the given name.
	 * @param name The name of the material
	 * @param material the material to store
	 */
	public void addMaterial(String name, Material material){
		this.materials.put(name, material);
	}
	
	
	/**
	 * Gets the Material associated with the given name.
	 * @param name The name of the material to get
	 * @return the material with the associated name
	 */
	public Material get(String name){
		return this.materials.get(name);
	}
	
	
	/**
	 * Creates a MaterialLibrary from a TextureLibrary and an array of String names.
	 * The names must match the names of the textures for each material.
	 * Each name will also be the associated name of the Material.
	 * @param texLib The TextureLibrary to use to generate this MaterialLibrary
	 * @param names an array of String names to use as the names of the materials
	 * @return the newly created MaterialLibrary
	 */
	public static MaterialLibrary createMaterialLibrary(TextureLibrary texLib, String... names){
		MaterialLibrary matLib = new MaterialLibrary();
		for(String name : names){
			Material mat = new Material(texLib.get(name));
			matLib.addMaterial(name, mat);
		}
		return matLib;
	}
	
	
}
