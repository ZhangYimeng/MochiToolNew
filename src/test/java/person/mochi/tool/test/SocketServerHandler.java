package person.mochi.tool.test;

import person.mochi.tool.data.interconversion.DataInterconversionTool;
import person.mochi.tool.server.binarybytes.socket.foundation.BytesHandler;

public class SocketServerHandler implements BytesHandler {

	@Override
	public byte[] handle(byte[] request) {
		System.out.println(DataInterconversionTool.bytesToString(request));
		return request;
	}

}
