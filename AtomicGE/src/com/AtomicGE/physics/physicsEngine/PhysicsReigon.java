package com.AtomicGE.physics.physicsEngine;

import java.util.ArrayList;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.physics.collisionDetection.CollisionVolume;
import com.AtomicGE.physics.collisionDetection.SphereCollisionDetector;

public class PhysicsReigon {
	
	private CollisionVolume boundingVolume;
	private ArrayList<PhysicsObject> containedObjects;
	
	
	public PhysicsReigon(CollisionVolume boundingVolume){
		this.boundingVolume = boundingVolume;
		this.containedObjects = new ArrayList<>();
	}
	
	
	
	public void tick(){
		for(PhysicsObject object : containedObjects){
			object.tick();
		}
	}
	
	
	public void add(PhysicsObject object){
		this.containedObjects.add(object);
	}
	
	
	/**
	 * 
	 * @return a new PhysicsReigon covering all space
	 */
	public static PhysicsReigon getDefaultPhysicsReigonCopy(){
		SphereCollisionDetector infiniteSphere = new SphereCollisionDetector(
				new Vector(0,0,0),
				new Vector(0,0,0),
				Double.POSITIVE_INFINITY
			);
		CollisionVolume infiniteVolume = new CollisionVolume(infiniteSphere);
		return new PhysicsReigon(infiniteVolume);
	}
	
}
