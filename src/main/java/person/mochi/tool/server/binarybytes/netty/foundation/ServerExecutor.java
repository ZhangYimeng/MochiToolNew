package person.mochi.tool.server.binarybytes.netty.foundation;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;

public class ServerExecutor extends Thread {

    private SslContext sslCtx;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap bootstrap;
    private int port;
    private BytesHandler bytesHandler;

    public ServerExecutor(SslContext sslCtx, EventLoopGroup bossGroup, EventLoopGroup workerGroup,
                          ServerBootstrap bootstrap, int port, BytesHandler bytesHandler) {
        this.sslCtx = sslCtx;
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
        this.bootstrap = bootstrap;
        this.port = port;
        this.bytesHandler = bytesHandler;
    }

    @Override
    public void run() {
        try {
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MochiServerInitializer(sslCtx, bytesHandler));
            bootstrap.bind(port).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
