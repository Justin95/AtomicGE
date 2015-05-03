package com.AtomicGE.IO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.AtomicGE.modernRender.texture.Texture;

public class TextureFileReader {
	
	private static final String TEXTURE_FOLDER_FILEPATH = "textures//";
	
	/**
	 * Creates a texture object from the texture file at the given fileName
	 * @param fileName the filename of the texture
	 * @return a new texture object
	 */
	public static Texture loadTexture(String fileName){
		File imageFile = new File(TEXTURE_FOLDER_FILEPATH + fileName);
		Image image = loadImage(imageFile);
		Texture texture = new Texture(image);
		return texture;
	}
	
	
	
	/**
	 * Loads and returns an image from the given file.
	 * @param file the image file
	 * @return a file from the given image
	 */
	private static BufferedImage loadImage(File file){
		BufferedImage image;
		try{
			image = ImageIO.read(file);
		}catch(IOException e){
			System.out.println("Loaded Image as null: \n" + file.getName() + "\n" + e);
			image = null;
		}
		return image;
	}
	
	
	
	
}
