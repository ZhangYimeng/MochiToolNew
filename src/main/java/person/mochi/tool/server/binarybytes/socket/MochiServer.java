package person.mochi.tool.server.binarybytes.socket;

import java.net.ServerSocket;

import person.mochi.tool.server.binarybytes.socket.foundation.BytesHandler;

public class MochiServer {

	private ServerSocket server;
	private int port;
	private BytesHandler bytesHandler;
	
	public MochiServer(int port, BytesHandler bytesHandler) {
		this.port = port;
		this.bytesHandler = bytesHandler;
	}
	
	public void start() {
		
	}
	
}
