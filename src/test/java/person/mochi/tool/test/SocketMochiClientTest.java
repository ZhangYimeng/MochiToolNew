package person.mochi.tool.test;

import java.io.IOException;
import java.net.UnknownHostException;

import person.mochi.tool.client.binarybytes.socket.MochiClient;
import person.mochi.tool.data.interconversion.DataInterconversionTool;

public class SocketMochiClientTest {

    public static void main(String[] args) throws UnknownHostException, IOException {
        MochiClient client = new MochiClient("127.0.0.1", 8080, new SocketClientHandler());
        client.initial();
        client.getSender().send(DataInterconversionTool.stringToBytes("你好"));
    }

}
