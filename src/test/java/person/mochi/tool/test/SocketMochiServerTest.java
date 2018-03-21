package person.mochi.tool.test;

import java.io.IOException;

import person.mochi.tool.server.binarybytes.socket.MochiServer;

public class SocketMochiServerTest {

	public static void main(String[] args) throws IOException {
		MochiServer server = new MochiServer(8080, new SocketServerHandler());
		server.start();
	}

}
