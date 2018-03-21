package person.mochi.tool.server.binarybytes.socket;

import java.io.IOException;
import java.net.ServerSocket;

import person.mochi.tool.server.binarybytes.socket.foundation.BytesHandler;
import person.mochi.tool.server.binarybytes.socket.foundation.ServerExecutor;

public class MochiServer {

	private ServerSocket server;
	private int port;
	private BytesHandler bytesHandler;
	private ServerExecutor executor;
	
	public MochiServer(int port, BytesHandler bytesHandler) {
		this.port = port;
		this.bytesHandler = bytesHandler;
	}
	
	public void start() throws IOException {
		server = new ServerSocket(port);
		executor = new ServerExecutor(server, bytesHandler);
		executor.start();
	}
	
	public void close() throws IOException {
		server.close();
	}
	
}
