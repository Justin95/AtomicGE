package com.AtomicGE.modernRender.GPUprograms;

import java.io.File;
import java.util.Scanner;

public class ShaderFileReader {
	
	public static final String SHADER_SOURCE_FILEPATH = "shaders//";
	public static final String SHADER_SOURCE_EXTENTION = ".glsl";
	
	
	/**
	 * Loads a String, from a .glsl file, which is the source code for a shader with the given filename
	 * @param fileName the name of the shader source file to read from, do not include '.glsl'
	 * @return a String representing the source of the shader
	 */
	public static String getShaderSource(String fileName){
		File sourceFile = new File(SHADER_SOURCE_FILEPATH + fileName + SHADER_SOURCE_EXTENTION);
		StringBuilder shaderSource = new StringBuilder();
		Scanner fileScanner;
		try{
			fileScanner = new Scanner(sourceFile);
			while(fileScanner.hasNextLine()){
				shaderSource.append(fileScanner.nextLine());
				shaderSource.append("\n");
			}
			fileScanner.close();
		}catch(Exception e){
			System.out.println("Error in shaderFileReader.getShaderSource(): ");
			e.printStackTrace();
		}
		String shaderSourceString = shaderSource.toString();
		return shaderSourceString;
	}
	
	/**
	 * Loads Strings from a .glsl file, which are the source codes for shaders with the given file names.
	 * @param fileNames the file names to get the shader sources from
	 * @return an array of Strings which are each shader sources
	 */
	public static String[] getShadersSources(String[] fileNames){
		String[] shaders = new String[fileNames.length];
		for(int i = 0; i < fileNames.length; i++){
			shaders[i] = getShaderSource(fileNames[i]);
		}
		return shaders;
	}
	
}
