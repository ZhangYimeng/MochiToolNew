package person.mochi.tool.client.binarybytes.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import person.mochi.tool.client.binarybytes.socket.foundation.BytesHandler;
import person.mochi.tool.client.binarybytes.socket.foundation.MochiClientSender;

public class MochiClient {

    private String ip;
    private int port;
    private BytesHandler bytesHandler;
    private Socket socket;
    private InputStream input;
    private OutputStream output;

    public MochiClient(String ip, int port, BytesHandler bytesHandler) {
        this.ip = ip;
        this.port = port;
        this.bytesHandler = bytesHandler;
    }

    public void initial() throws UnknownHostException, IOException {
        socket = new Socket(ip, port);
        input = socket.getInputStream();
        output = socket.getOutputStream();
    }

    public MochiClientSender getSender() {
        return new MochiClientSender(input, output, bytesHandler);
    }

}
