package RPC;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
@ChannelHandler.Sharable
public class NettyServerHander extends ChannelInboundHandlerAdapter {
    public String generatorFrame(String msg,String reqId){
        return msg+":"+reqId+"|";
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        System.out.println(msg);
        String str = (String) msg;
        String reqId=str.split(":")[1];
        String resp =generatorFrame("im jiaduo",reqId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    //  ctx.channel().writeAndFlush(Unpooled.copiedBuffer(resp.getBytes()));
        //零拷贝
        ByteBuf buf=Unpooled.directBuffer();
        buf.writeBytes(resp.getBytes());
        ctx.channel().writeAndFlush(buf);
    }
}
