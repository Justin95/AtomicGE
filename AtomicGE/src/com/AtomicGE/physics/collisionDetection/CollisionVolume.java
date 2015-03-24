package com.AtomicGE.physics.collisionDetection;

public class CollisionVolume {
	
	private CollisionDetector[] boundingVolumes;
	
	
	/**
	 * Constructs a CollisionVolume from given CollisionDetectors. 
	 * This CollisionVolume acts as a container of CollisionDetectors.
	 * @param volumes
	 */
	public CollisionVolume(CollisionDetector... volumes){
		this.boundingVolumes = volumes;
	}
	
	
	public CollisionReport getCollisionReport(CollisionDetector colDet){
		for(CollisionDetector collisionDetector : boundingVolumes){
			CollisionReport report = collisionDetector.g
		}
	}
	
	
}
