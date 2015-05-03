package com.AtomicGE.modernRender.render;

import com.AtomicGE.IO.TextureFileReader;
import com.AtomicGE.modernRender.lighting.Light;
import com.AtomicGE.modernRender.model.Model;
import com.AtomicGE.modernRender.texture.Texture;


/**
 * 
 * @author Justin  Github: Justin95
 * 
 * This class allows for a thread safe way for threads to complete tasks which openGL
 * mandates are to be done in the rendering thread. For instance model initialization
 * (VAO and VBO creation, upload of data to GPU etc.), and Texture creation.
 * Each thread must use its own RenderQueue object, two threads must not attempt to use
 * the same object to initialize models or create textures.
 *
 */
public class RenderQueue {
	
	private static Object modelLock   = new Object();
	private static Object textureLock = new Object();
	
	private boolean modelReady;
	private boolean textureReady;
	
	private Model toInitialize;
	private String textureName;
	private Texture texture;
	private Light light;
	
	
	public RenderQueue(){
		modelReady = false;
		textureReady = false;
	}
	
	
	/**
	 * Initializes the given Model object.
	 * This method blocks until the Model has been initialized before returning.
	 * Models must be initialized before they can be drawn. Initialization uploads the model
	 * data to the GPU.
	 * @param model the Model object to initialize
	 */
	public void initializeModel(Model model){
		synchronized(modelLock){
			synchronized(this){
				System.out.println("Thread in: " + Thread.currentThread());
				if(model == null) return; //null would cause infinite wait
				this.toInitialize = model;
				this.modelReady = false;
				while(!modelReady){
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				this.toInitialize = null;
			}
			System.out.println("Thread out: " + Thread.currentThread());
		}
	}
	
	/**
	 * Creates a Texture object from the given texture file name.
	 * This method blocks until the Texture has been created in the rendering thread.
	 * @param textureName the fileName of the Image file to make the Texture from
	 * @return a new Texture object
	 */
	public Texture makeTexture(String textureName){
		synchronized(textureLock){
			this.textureName = textureName;
			this.textureReady = false;
			while(!textureReady){
				try {
					System.out.println("waiting on " + textureName);
					textureLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Texture temp = this.texture;
			this.texture = null;
			this.textureName = null;
			return temp;
		}
	}
	
	
	/**
	 * Queues a light to be enabled by the rendering thread. Lights must be enabled in the rendering thread.
	 * @param light the Light to enable
	 */
	public void enableLight(Light light){
		this.light = light; //this particular task likely does not need to be synchronized
		/*while(!lightReady){
			try{
				this.wait();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		this.light = null;*/
	}
	
	
	
	/**
	 * Initializes Model objects which a thread has given to this RenderQueue object.
	 * Models must be initialized before they can be drawn. Initialization uploads the model
	 * data to the GPU.
	 * This method must be called in the thread which has the openGL rendering context.
	 * In other words this method must be called in the rendering thread.
	 */
	public void initalizeQueuedModels(){
		synchronized(this){
			if(this.toInitialize == null) return; //if nothing to initialize then nothing to do
			toInitialize.initialize();
			this.modelReady = true;
			this.notifyAll();
		}
	}
	
	/**
	 * Creates Texture objects from names given to this RenderQueue object.
	 * This method must be called in the render thread, the render thread being a thread
	 * with an openGL Rendering Context.
	 */
	public void createQueuedTextures(){
		synchronized(textureLock){
			if(this.textureName == null) return; //if nothing to initialize then nothing to do
			this.texture = TextureFileReader.loadTexture(this.textureName);
			this.textureReady = true;
			textureLock.notifyAll();
		}
	}
	
	
	/**
	 * Enables Queued Lights given to this RenderQueue object. Lights must be enabled to be used.
	 * This method must be called in the render thread, the render thread being a thread
	 * with an openGL Rendering Context.
	 */
	public void enableQueuedLights(){
		if(this.light == null) return; //if nothing to enable then nothing to do
		this.light.enable();
	}
	
	
}
