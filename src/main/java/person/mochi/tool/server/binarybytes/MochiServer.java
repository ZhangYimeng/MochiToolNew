package person.mochi.tool.server.binarybytes;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import person.mochi.tool.server.binarybytes.foundation.BytesHandler;
import person.mochi.tool.server.binarybytes.foundation.ServerExecutor;

public class MochiServer {

	private static final boolean SSL = System.getProperty("ssl") != null;
	private SslContext sslCtx;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private ServerBootstrap b;
	private int port;
	private BytesHandler bytesHandler;
	
	public MochiServer(int port, BytesHandler bytesHandler) throws CertificateException, SSLException {
		if (SSL) {
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
		} else {
			sslCtx = null;
		}
		bossGroup = new NioEventLoopGroup(1);
		workerGroup = new NioEventLoopGroup();
		b = new ServerBootstrap();
		this.port = port;
		this.bytesHandler = bytesHandler;
	}
	
	public void start() {
		ServerExecutor executor = new ServerExecutor(sslCtx, bossGroup, workerGroup, b, port, bytesHandler);
		executor.start();
	}
	
}
