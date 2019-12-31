package person.mochi.tool.test;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import person.mochi.tool.server.binarybytes.netty.MochiServer;
import person.mochi.tool.server.binarybytes.netty.foundation.BytesHandler;

public class MochiServerTest {

    public static void main(String[] args) throws CertificateException, SSLException {
        BytesHandler handler = new ServerHandler();
        MochiServer ms = new MochiServer(8080, handler);
        ms.start();
    }

}
