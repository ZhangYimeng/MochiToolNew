package person.mochi.tool.client.binarybytes.netty.foundation;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import person.mochi.tool.server.binarybytes.netty.foundation.BytesHandler;

public class MochiClientInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslCtx;
    private String ip;
    private int port;
    private BytesHandler bytesHandler;

    public MochiClientInitializer(String ip, int port, SslContext sslCtx, BytesHandler bytesHandler) {
        this.sslCtx = sslCtx;
        this.ip = ip;
        this.port = port;
        this.bytesHandler = bytesHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc(), ip, port));
        }

        // and then business logic.
        pipeline.addLast(new MochiClientHandler(bytesHandler));
    }

}
