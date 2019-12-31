package person.mochi.tool.server.binarybytes.socket.foundation;

import java.io.IOException;
import java.io.OutputStream;

import person.mochi.tool.data.arraycombinetool.BytesCombineTool;
import person.mochi.tool.data.interconversion.DataInterconversionTool;

public class PassiveResponseExcutor {

    private OutputStream output;

    public PassiveResponseExcutor(OutputStream output) throws IOException {
        this.output = output;
    }

    public void passiveeply(byte[] response) throws IOException {
        output.write(BytesCombineTool.append(DataInterconversionTool.intToBytes(response.length), response));
        output.flush();
    }

}
