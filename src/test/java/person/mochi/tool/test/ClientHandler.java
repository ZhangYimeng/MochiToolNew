package person.mochi.tool.test;

import io.netty.buffer.ByteBuf;
import person.mochi.tool.data.interconversion.DataInterconversionTool;
import person.mochi.tool.server.binarybytes.netty.foundation.BytesHandler;

public class ClientHandler implements BytesHandler {

    @Override
    public ByteBuf handle(ByteBuf input) {
        byte[] bytes = new byte[9];
        input.readBytes(bytes);
        System.out.println(DataInterconversionTool.bytesToString(bytes));
        return input;
    }

}
