package person.mochi.tool.client.binarybytes.netty;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import person.mochi.tool.client.binarybytes.netty.foundation.MochiClientInitializer;
import person.mochi.tool.client.binarybytes.netty.foundation.MochiClientSender;
import person.mochi.tool.server.binarybytes.netty.foundation.BytesHandler;

public class MochiClient {

    private static final boolean SSL = System.getProperty("ssl") != null;
    private SslContext sslCtx;
    private EventLoopGroup group;
    private Bootstrap bootstrap;
    private String ip;
    private int port;
    private BytesHandler bytesHandler;

    public MochiClient(String ip, int port, BytesHandler bytesHandler) throws CertificateException, SSLException {
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        this.ip = ip;
        this.port = port;
        this.bytesHandler = bytesHandler;
    }

    public void initial() {
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new MochiClientInitializer(ip, port, sslCtx, bytesHandler));
    }

    public MochiClientSender getSender() throws InterruptedException {
        Channel ch = bootstrap.connect(ip, port).sync().channel();
        return new MochiClientSender(ch);
    }

    public void clientClose() {
        group.shutdownGracefully();
    }

}
