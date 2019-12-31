package person.mochi.tool.test;

import person.mochi.tool.client.binarybytes.socket.foundation.BytesHandler;
import person.mochi.tool.data.interconversion.DataInterconversionTool;

public class SocketClientHandler implements BytesHandler {

    @Override
    public void handle(byte[] request) {
        System.out.println(DataInterconversionTool.bytesToString(request));
    }

}
