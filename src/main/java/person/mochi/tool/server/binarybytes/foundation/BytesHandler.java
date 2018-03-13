package person.mochi.tool.server.binarybytes.foundation;

import io.netty.buffer.ByteBuf;

public interface BytesHandler {

	public ByteBuf handle(ByteBuf input);

}
