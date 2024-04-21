package RPC;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

public class RpcClient {
    private volatile Channel channel;
    private static final AtomicLong INVOKE_ID = new AtomicLong(0);
    private Bootstrap b;
    public RpcClient() throws InterruptedException {
        EventLoopGroup group=new NioEventLoopGroup();
        NettyClientHander nettyClientHander = new NettyClientHander();
        b = new Bootstrap();
         b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true)
                 .handler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     protected void initChannel(SocketChannel ch) throws Exception {
                         ChannelPipeline pipeline = ch.pipeline();
                      //   ByteBuf  = Unpooled.copiedBuffer("|".getBytes());
                         ByteBuf delimit = Unpooled.directBuffer();
                         delimit.writeBytes("|".getBytes());
                         pipeline.addLast(new DelimiterBasedFrameDecoder(1000,delimit));
                         pipeline.addLast(new StringDecoder());
                         pipeline.addLast(new StringEncoder());
                         pipeline.addLast(nettyClientHander);


                     }
                 });
        ChannelFuture f = b.connect("localhost", 12800).sync();
        if(f.isDone() && f.isSuccess()){
            this.channel= f.channel();
        }

    }
    private  void sendMsg(String msg){
        channel.writeAndFlush(msg);
    }
    public  void close(){
        if(null!=b){
            b.group().shutdownGracefully();
        }
        if(null!=channel){
            channel.close();
        }
    }
    private String generatoreFrame(String msg,String reqId){
        return msg+":"+reqId+"|";
    }
    public CompletableFuture rpcAsynCall(String msg){
        CompletableFuture<String> future = new CompletableFuture<>();
        String reqId=INVOKE_ID.getAndIncrement()+"";
        msg=generatoreFrame(msg,reqId);
        this.sendMsg(msg);
        FutureMapUtil.put(reqId,future);
        return future;

    }
    public String rpcSyncCall(String msg) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future=new CompletableFuture<>();
        String reqId=INVOKE_ID.getAndIncrement()+"";
        msg=generatoreFrame(msg,reqId);
        this.sendMsg(msg);
        FutureMapUtil.put(reqId,future);
        String result=future.get();
        return result;
    }


}
