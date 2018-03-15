package person.mochi.tool.server.binarybytes.socket.foundation;

import java.io.IOException;
import java.io.OutputStream;

public class PassiveResponseExcutor {

	private OutputStream output;
	
	public PassiveResponseExcutor(OutputStream output) throws IOException {
		this.output = output;
	}
	
	public void passiveeply(byte[] response) throws IOException {
		output.write(response);
		output.flush();
	}
	
}
