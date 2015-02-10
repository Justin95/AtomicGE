package com.AtomicGE.IO;


public class ModelFileReader {
	
	//private static final String MODEL_FOLDER_FILEPATH = "models//";
	
	/**
	 * Returns a Model object created from the information in the file associated with the given fileName.
	 * @param fileName The String fileName of the file to read from
	 * @return a Model object described by the given file
	 */
	/*public static RawModelData readModelFromFile(String fileName){
		File file = new File(MODEL_FOLDER_FILEPATH + fileName);
		Texture texture = null;
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		ArrayList<Vector> textureCoords = new ArrayList<Vector>();
		try{
			Scanner scanny = new Scanner(file);
			if(scanny.hasNextLine()){
				String textureFileName = scanny.nextLine().trim();
				texture = null;//TextureFileReader.loadTexture(textureFileName);
			}
			while(scanny.hasNextLine()){
				String line = scanny.nextLine().trim();
				String[] descriptors = getDescriptors(line);
				Triangle triangle = stringToTriangle(descriptors[0]);
				triangles.add(triangle);
				Vector[] triangleTextureCoords = stringToTextureCoords(descriptors[1]);
				textureCoords.add(triangleTextureCoords[0]);
				textureCoords.add(triangleTextureCoords[1]);
				textureCoords.add(triangleTextureCoords[2]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		RawModelData modelData = new RawModelData(triangles,textureCoords,texture,null);//the null is colors
		if(texture == null || triangles == null || textureCoords == null) System.out.println("null in ModelFileReader: " + fileName);
		return modelData;
	}*/
	
	
	/**
	 * Creates a vector array describing texture coordinates from the given String
	 * @param line the string to read the texture coordinate information from
	 * @return an array of vectors whos ihats and jhats describe texture coordinates
	 */
	/*private static Vector[] stringToTextureCoords(String line){
		String[] vectorStrings = seperateString(line,':');
		Vector[] textureCoords = new Vector[3]; //one vector for each point on a triangle
		for(int i = 0; i < vectorStrings.length; i++){
			textureCoords[i] = stringToVector(vectorStrings[i] + " 0"); //adds " 0" to add a khat of 0 to the vector
		}
		return textureCoords;
	}*/
	
	
	/**
	 * Creates a string array the first element of which is a string describing a triangle, the second element describes texture coordinates.
	 * @param line The line to create the descriptors from, will be read from a file
	 * @return
	 */
	/*private static String[] getDescriptors(String line){
		String[] descriptors = seperateString(line,'|');
		return descriptors;
	}*/
	
	
	
	/**
	 * Creates a Vector object from a given String which contains exactly 3 values which can be interpreted as doubles.
	 * @param vectorString The String containing the vector components
	 * @return a new Vector constructed from the given string
	 */
	/*private static Vector stringToVector(String vectorString){
		String[] valueStrings = seperateString(vectorString,' ');
		assert valueStrings.length == 3;
		double x = Double.parseDouble(valueStrings[0]);
		double y = Double.parseDouble(valueStrings[1]);
		double z = Double.parseDouble(valueStrings[2]);
		Vector v = new Vector(x,y,z);
		return v;
	}*/
	
	
	/**
	 * Creates a Triangle object from a given String which is a line of text describing the points of the triangle, each point separated by a ':'.
	 * @param line
	 * @return
	 */
	/*private static Triangle stringToTriangle(String line){
		String[] stringVectors = seperateString(line,':');
		assert stringVectors.length == 3;
		Vector[] vectors = new Vector[3];
		for(int i = 0; i < vectors.length; i++){
			vectors[i] = stringToVector(stringVectors[i]);
		}
		Triangle triangle = new Triangle(vectors[0],vectors[1],vectors[2]);
		return triangle;
	}*/
	
	/**
	 * Creates an array of strings by separating the given string at indexes containing the given character and removing any whitespace around them.
	 * @param string The String to separate
	 * @param seperator The character to separate at
	 * @return an Array of Strings which were separated the the given character.
	 */
	/*private static String[] seperateString(String string, char seperator){
		ArrayList<String> seperateStrings = new ArrayList<String>();
		int index = string.indexOf(seperator);
		while(index != -1){
			String seperated = string.substring(0,index).trim();
			string = string.substring(index+1).trim();
			seperateStrings.add(seperated);
			index = string.indexOf(seperator);
		}
		seperateStrings.add(string);
		String[] strings = seperateStrings.toArray(new String[seperateStrings.size()]);
		return strings;
	}*/
	
	
}
