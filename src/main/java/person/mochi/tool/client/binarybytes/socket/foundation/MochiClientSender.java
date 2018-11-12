package person.mochi.tool.client.binarybytes.socket.foundation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import person.mochi.tool.data.arraycombinetool.BytesCombineTool;
import person.mochi.tool.data.interconversion.DataInterconversionTool;

public class MochiClientSender {

	private OutputStream output;
	private InputStream input;
	private BytesHandler bytesHandler;
	
	public MochiClientSender(InputStream input, OutputStream output, BytesHandler bytesHandler) {
		this.input = input;
		this.output = output;
		this.bytesHandler = bytesHandler;
	}
	
	public void send(byte[] msg) throws IOException {
		output.write(BytesCombineTool.append(DataInterconversionTool.intToBytes(msg.length), msg));
		output.flush();
		byte[] frameLengthBytes = new byte[4];
		input.read(frameLengthBytes);
		byte[] bodyBytes = new byte[DataInterconversionTool.bytesToInt(frameLengthBytes)];
		input.read(bodyBytes);
		System.out.println("收到服务器反馈信息");
		bytesHandler.handle(bodyBytes);
	}
	
}
