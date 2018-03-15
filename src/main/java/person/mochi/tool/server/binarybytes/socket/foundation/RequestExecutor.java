package person.mochi.tool.server.binarybytes.socket.foundation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import person.mochi.tool.data.bytescombinetool.BytesCombineTool;
import person.mochi.tool.data.interconversion.DataInterconversionTool;

public class RequestExecutor implements Runnable {

	private Socket socket;
	private BytesHandler bytesHandler;
	private InputStream input;
	private OutputStream output;
	private PassiveResponseExcutor responsor;
	
	public RequestExecutor(Socket socket, BytesHandler bytesHandler) throws IOException {
		this.socket = socket;
		this.bytesHandler = bytesHandler;
		input = socket.getInputStream();
		output = socket.getOutputStream();
		responsor = new PassiveResponseExcutor(output);
	}
	
	@Override
	public void run() {
		byte[] frameLengthBytes = new byte[4];
		byte[] bodyBytes = new byte[DataInterconversionTool.bytesToInt(frameLengthBytes) - 4];
		try {
			input.read(bodyBytes);
			byte[] passiveeResponse = bytesHandler.handle(BytesCombineTool.append(frameLengthBytes, bodyBytes));
			responsor.passiveeply(passiveeResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
		input.close();
		output.close();
		socket.close();
	}

}
