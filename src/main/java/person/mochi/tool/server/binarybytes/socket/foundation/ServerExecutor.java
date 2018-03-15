package person.mochi.tool.server.binarybytes.socket.foundation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExecutor extends Thread {
	
	private BytesHandler bytesHandler;
	private ServerSocket server;

	public ServerExecutor(ServerSocket server) {
		this.server = server;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Socket socket = server.accept();
				RequestExecutor reqExecutor = new RequestExecutor(socket, bytesHandler);
				new Thread(reqExecutor).start();
			} catch (IOException e) {
				System.err.println("连接创建失败!");
				e.printStackTrace();
			}
		}
	}

}
