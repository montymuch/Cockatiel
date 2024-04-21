package org.example.servlet;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture cf= new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder())
                                .addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf bf= (ByteBuf) msg;
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080))
                .sync()
                .channel()
                .writeAndFlush("hello ,world!");
        Channel ch= cf.sync().channel();
        AtomicInteger flag= new AtomicInteger();
        new Thread(()->{
            Scanner sc=new Scanner(System.in);
            while (true){
                String line=sc.nextLine();
                if("q".equals(line)){
                    ch.close();
                    flag.set(1);
                    break;
                }else{
                    ch.writeAndFlush(line);
                }
            }
        },"input").start();

        cf.addListener(new ChannelFutureListener() {
             @Override
             public void operationComplete(ChannelFuture channelFuture) throws Exception {

                 log.debug("处理关闭操作！");


             }
         });
        

    }

}
