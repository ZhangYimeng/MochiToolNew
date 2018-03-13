package person.mochi.tool.test;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import person.mochi.tool.server.binarybytes.MochiServer;

public class MochiServerTest {

	public static void main(String[] args) throws CertificateException, SSLException {
		MochiServer ms = new MochiServer(8080, null);
		ms.start();
	}

}
