package person.mochi.tool.server.binarybytes.socket.foundation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import person.mochi.tool.data.interconversion.DataInterconversionTool;

public class RequestExecutor extends Thread {

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
		try {
			input.read(frameLengthBytes);
			byte[] bodyBytes = new byte[DataInterconversionTool.bytesToInt(frameLengthBytes)];
			input.read(bodyBytes);
			byte[] passiveeResponse = bytesHandler.handle(bodyBytes);
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
