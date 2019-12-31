package person.mochi.tool.client.binarybytes.netty.foundation;

import io.netty.buffer.ByteBuf;

public interface BytesHandler {

    public void handle(ByteBuf input);

}
