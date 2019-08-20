package person.mochi.tool.base64;

import java.util.Base64;

public class MochiBase64 {

    public byte[] encode(byte[] source) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encode(source);
    }

    public byte[] decode(byte[] source) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(source);
    }

}
