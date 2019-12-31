package person.mochi.tool.server.binarybytes.netty.foundation;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class MochiServerHandler extends ChannelInboundHandlerAdapter {

    private static BytesHandler bytesHandler;

    public MochiServerHandler(BytesHandler bytesHandler) {
        MochiServerHandler.bytesHandler = bytesHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            ByteBuf reponse = bytesHandler.handle(in);
            ctx.write(reponse);
            ctx.flush();
        } finally {
            ReferenceCountUtil.release(msg);
        }
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