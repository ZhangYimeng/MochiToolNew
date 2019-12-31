package person.mochi.tool.client.binarybytes.netty.foundation;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class MochiClientSender {

    private Channel ch;

    public MochiClientSender(Channel ch) {
        this.ch = ch;
    }

    public void send(ByteBuf bytesBuffer) {
        ch.writeAndFlush(bytesBuffer);
    }

    public void shutdown() throws InterruptedException {
        ch.closeFuture().sync();
    }

}
