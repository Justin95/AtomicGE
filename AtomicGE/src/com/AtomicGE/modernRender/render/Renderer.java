package com.AtomicGE.modernRender.render;

import java.util.ArrayList;

import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.PixelFormat;

import static org.lwjgl.opengl.GL11.*;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.renderObject.RenderObject;

public class Renderer {
	
	private int targetFPS;
	ArrayList<RenderObject> renderList;
	private RORenderer roRenderer;
	private TimeTracker fpsTracker;
	private Camera camera;
	
	public Renderer(float fov, float nearClip, float farClip){
		renderList = new ArrayList<RenderObject>();
		targetFPS = 60;
		roRenderer = new RORenderer();
		this.fpsTracker = new TimeTracker();
		initOpenGL(fov,nearClip,farClip); //also initializes camera
	}
	
	
	/** 
	 * Renders a frame
	 */
	public void render(){
		
		//glClearColor(.2f,.2f,.2f,1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		camera.updateCamera(Display.getDisplayMode());
		camera.useCameraView();
		
		for(RenderObject renderObject : renderList){
			roRenderer.render(renderObject);
		}
		
		System.out.println(fpsTracker.getDeltaTime()); //handle in separate method later
		Display.sync(targetFPS);
		Display.update();
	}
	
	
	
	
	
	/**
	 * Updates this Renderer's Camera Object with the given Position and Rotation Vectors.
	 * Effects will take affect next frame.
	 * @param pos the new position Vector for the Camera
	 * @param rot the new rotation Vector for the Camera
	 */
	public void updateCamera(Vector pos, Vector rot){
		this.camera.updateCamera(pos, rot);
	}
	
	
	/**
	 * Initializes the openGL context for this Thread. Also initializes the Camera Object.
	 * @param fov the field of vision
	 * @param nearClip the near clipping distance
	 * @param farClip the far clipping distance
	 */
	private void initOpenGL(float fov, float nearClip, float farClip){
		ContextAttribs conAtt = new ContextAttribs(3,3).withProfileCore(true);
		PixelFormat pixelForm = new PixelFormat();
		Display.setTitle("OpenGL Test");
		DisplayMode dm = Display.getDesktopDisplayMode();
		Display.setVSyncEnabled(true);
		Display.setResizable(true);
		this.camera = new Camera(
				new Vector(0,0,0),
				new Vector(0,0,0),
				fov,
				((float)dm.getWidth())/dm.getHeight(),//life pro tip: aspect ratio is width/height not height/width
				nearClip,
				farClip
		);
		try{
			Display.setDisplayMode(dm);
			Display.create(pixelForm,conAtt);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Operating System: " 		+ System.getProperty("os.name"));
		System.out.println("Operating System Version: " + System.getProperty("os.version"));
		System.out.println("OpenGL Version: " 			+ glGetString(GL_VERSION));
		System.out.println("Renderer: "       			+ glGetString(GL_RENDERER));
		System.out.println("Shading Language Version: " + glGetString(GL20.GL_SHADING_LANGUAGE_VERSION));
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
	}
	
	
	/**
	 * Whether or not the Renderer is requesting the close of the program.
	 * @return true if the program should continue to run
	 */
	public boolean shouldKeepRunning(){
		return !Display.isCloseRequested();
	}
	
	
	/**
	 * Adds the given RenderObject to the List of RenderObjects to be rendered each frame.
	 * @param renObj the object to be added
	 */
	public void addToRenderList(RenderObject renObj){
		renderList.add(renObj);
	}
	
	
	/**
	 * Adds the given RenderObjects to the List of RenderObjects to be rendered each frame.
	 * @param renObjs an array of RenderObjects to be added
	 */
	public void addToRenderList(RenderObject[] renObjs){
		for(RenderObject renobj : renObjs){
			addToRenderList(renobj);
		}
	}
	
	
	/**
	 * Removes the given RenderObject from the List of RenderObjects to be rendered each frame.
	 * @param renObj the RenderObject to removed
	 */
	public void removeFromRenderList(RenderObject renObj){
		renderList.remove(renObj);
	}
	
	
	/**
	 * Removes the given RenderObjects from the List of RenderObjects to be rendered each frame.
	 * @param renObjs the RenderObjects to remove
	 */
	public void removeFromRenderList(RenderObject[] renObjs){
		for(RenderObject renobj : renObjs){
			removeFromRenderList(renobj);
		}
	}
	
	
	public void cleanUp(){
		Display.destroy();
	}
	
}
