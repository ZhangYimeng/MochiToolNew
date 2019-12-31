package person.mochi.tool.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import person.mochi.tool.data.interconversion.DataInterconversionTool;
import person.mochi.tool.server.binarybytes.netty.foundation.BytesHandler;

public class ServerHandler implements BytesHandler {

    @Override
    public ByteBuf handle(ByteBuf input) {
        byte[] frameLengthBytes = new byte[4];
        input.readBytes(frameLengthBytes);
        int frameLength = DataInterconversionTool.bytesToInt(frameLengthBytes);
        System.out.println(frameLength);
        byte[] bodyBytes = new byte[frameLength - 4];
        input.readBytes(bodyBytes);
        System.out.println(DataInterconversionTool.bytesToString(bodyBytes));
//		byte[] responseBytes = DataInterconversionTool.stringToBytes("我收到的你的消息！");
        ByteBuf response = Unpooled.directBuffer();
        response.writeBytes(bodyBytes);
        return response;
    }

}
