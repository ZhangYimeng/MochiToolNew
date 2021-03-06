package person.mochi.tool.server.binarybytes.socket.foundation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExecutor extends Thread {

    private BytesHandler bytesHandler;
    private ServerSocket server;

    public ServerExecutor(ServerSocket server, BytesHandler bytesHandler) {
        this.server = server;
        this.bytesHandler = bytesHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = server.accept();
                RequestExecutor reqExecutor = new RequestExecutor(socket, bytesHandler);
                reqExecutor.start();
            } catch (IOException e) {
                System.err.println("连接创建失败!");
                e.printStackTrace();
            }
        }
    }

}
