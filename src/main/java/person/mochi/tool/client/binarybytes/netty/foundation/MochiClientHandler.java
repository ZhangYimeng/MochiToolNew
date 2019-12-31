package person.mochi.tool.client.binarybytes.netty.foundation;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import person.mochi.tool.server.binarybytes.netty.foundation.BytesHandler;

public class MochiClientHandler extends ChannelInboundHandlerAdapter {

    private BytesHandler bytesHandler;

    public MochiClientHandler(BytesHandler bytesHandler) {
        this.bytesHandler = bytesHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        bytesHandler.handle(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
