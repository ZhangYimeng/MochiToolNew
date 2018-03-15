package person.mochi.tool.test;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import person.mochi.tool.client.binarybytes.netty.MochiClient;
import person.mochi.tool.client.binarybytes.netty.foundation.MochiClientSender;
import person.mochi.tool.data.bytescombinetool.BytesCombineTool;
import person.mochi.tool.data.interconversion.DataInterconversionTool;
import person.mochi.tool.server.binarybytes.netty.foundation.BytesHandler;

public class MochiClientTest {

	public static void main(String[] args) throws CertificateException, SSLException, InterruptedException {
		BytesHandler handler = new ClientHandler();
		MochiClient mc = new MochiClient("127.0.0.1", 8080, handler);
		mc.initial();
		MochiClientSender mcs = mc.getSender();
		byte[] frameLengthBytes = new byte[4];
		byte[] bodyBytes = DataInterconversionTool.stringToBytes("你好！");
		frameLengthBytes = DataInterconversionTool.intToBytes(frameLengthBytes.length + bodyBytes.length);
		ByteBuf msg = Unpooled.directBuffer();
		msg.writeBytes(BytesCombineTool.append(frameLengthBytes, bodyBytes));
		mcs.send(msg);
		Thread.sleep(2000);
		msg = Unpooled.directBuffer();
		msg.writeBytes(BytesCombineTool.append(frameLengthBytes, bodyBytes));
		mcs.send(msg);
		while(true) {
			Thread.sleep(100);
			msg = Unpooled.directBuffer();
			msg.writeBytes(BytesCombineTool.append(frameLengthBytes, bodyBytes));
			mcs.send(msg);
		}
//		System.out.println("sended");
//		mcs.shutdown();
//		mc.clientClose();
//		System.out.println("done");
	}

}
