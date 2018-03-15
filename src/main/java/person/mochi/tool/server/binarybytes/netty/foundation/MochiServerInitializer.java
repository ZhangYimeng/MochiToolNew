package person.mochi.tool.server.binarybytes.netty.foundation;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

public class MochiServerInitializer extends ChannelInitializer<SocketChannel> {

	private static MochiServerHandler SERVER_HANDLER;

	private final SslContext sslCtx;

	public MochiServerInitializer(SslContext sslCtx, BytesHandler bytesHandler) {
		this.sslCtx = sslCtx;
		SERVER_HANDLER = new MochiServerHandler(bytesHandler);
	}

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (sslCtx != null) {
			pipeline.addLast(sslCtx.newHandler(ch.alloc()));
		}
		// business logic.
		pipeline.addLast(SERVER_HANDLER);
	}
}
