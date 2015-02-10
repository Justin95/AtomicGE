package com.AtomicGE.networking;

public class PacketType {
	
	
	private int packetID;
	private PacketDataLayout packetLayout;
	
	
	PacketType(int id, PacketDataLayout packetLayout){
		this.packetID = id;
		this.packetLayout = packetLayout;
	}
	
	
	
	
	/**
	 * Represents this Packet as an array of bytes such that:
	 * 4 bytes:   an integer describing the length of this byte array
	 * 4 bytes:   an integer describing the packetID of this packet
	 * remainder: arbitrary data to be read by the PacketData object associated with this packet.
	 * @return an array of bytes describing this packet as described above
	 */
	public byte[] getAsByteArray(){
		
	}
	
	
	
	
	/**
	 * 
	 * @return the packetID of this packet
	 */
	public int getPacketID(){
		return this.packetID;
	}
	
	
}
