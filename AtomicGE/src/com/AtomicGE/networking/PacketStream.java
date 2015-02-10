package com.AtomicGE.networking;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class PacketStream {
	
	
	private ArrayList<Packet> packets;
	
	public PacketStream(){
		this.packets = new ArrayList<Packet>();
	}
	
	
	/**
	 * Adds a packet to this PacketStream.
	 * @param packet the packet to add
	 */
	public void addPacket(Packet packet){
		this.packets.add(packet);
	}
	
	
	/**
	 * Sends all the packets in this PacketStream through the given Socket, in the order the packets were added.
	 * @param socket the Socket to send the PacketStream through
	 */
	public void sendPackets(Socket socket) throws IOException{
		OutputStream socketStream = socket.getOutputStream();
		for(Packet packet : this.packets){
			byte[] packetAsBytes = packet.getAsByteArray();
			socketStream.write(packetAsBytes);
		}
		socketStream.close();
	}
	
	
	
}
