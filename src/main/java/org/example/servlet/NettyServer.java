package org.example.servlet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.HotLoad.HotLoadServer;
import org.example.server.HttpRequestHander;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Component
public class NettyServer implements CommandLineRunner {
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    public void start(){

        NioEventLoopGroup group1 = new NioEventLoopGroup();
        NioEventLoopGroup group2 = new NioEventLoopGroup(4);
        ChannelFuture bind = new ServerBootstrap()
                .group(group1,group2 )
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {

                        channel.pipeline()
                                .addLast(new StringDecoder())
                                .addLast(new HttpServerCodec())
                                .addLast(new LoggingHandler(LogLevel.DEBUG))
                                .addLast(new HttpObjectAggregator(512*1024))
                                .addLast(new HttpRequestHander())
                                .addLast(new ChannelInboundHandlerAdapter(){
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                       log.info((String) msg);
                                       channel.writeAndFlush(msg);
                                    }
                                });



                    }
                })
                .bind(8080);
        Channel ch= bind.channel();
        AtomicInteger flag= new AtomicInteger();
        flag.set(0);

//        bind.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture channelFuture) throws Exception {
////                new Thread(()->{
////                    Scanner sc=new Scanner(System.in);
////                    while (true){
////                        String line=sc.nextLine();
////                        if("q".equals(line)){
////                            ch.close();
////                            flag.set(1);
////                            break;
////                        }else{
////                            ch.writeAndFlush(line);
////                        }
////                    }
////                },"input").start();
//                log.info("异步等待关闭服务器");
//
//                group1.shutdownGracefully();
//                group2.shutdownGracefully();}
//
//
//        });
         System.out.println("热部署服务器："+bind);
    }

    public static void main(String[] args) {
        NioEventLoopGroup group1 = new NioEventLoopGroup();
        NioEventLoopGroup group2 = new NioEventLoopGroup(16);
          ChannelFuture bind = new ServerBootstrap()
                .group(group1, group2)
                .channel(NioServerSocketChannel.class)
                 .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
//                        channel.pipeline().addLast(new LoggingHandler())
//                                .addLast(new StringDecoder());
//                                 .addLast(new ChannelInboundHandlerAdapter(){
//                                    @Override
//                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                        log.info((String) msg);
//                                        channel.writeAndFlush(msg);
//                                    }
//                                });
                     channel.pipeline().addLast(new HttpServerCodec())
                                        .addLast(new HttpObjectAggregator(512*1024))
                                                .addLast(new HttpRequestHander());


                    }
                })
                .bind(8888);
        Channel ch= bind.channel();
//        AtomicInteger flag= new AtomicInteger();
//        flag.set(0);
//        new Thread(()->{
//            Scanner sc=new Scanner(System.in);
//            while (true){
//                String line=sc.nextLine();
//                if("q".equals(line)){
//                    ch.close();
//                    flag.set(1);
//                    break;
//                }else{
//                    ch.writeAndFlush(line);
//                }
//            }
//        },"input").start();
        bind.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {

                log.info("异步等待关闭服务器");


            }
        });
        System.out.println("热部署服务器："+bind);
    }

    @Override
    public void run(String... args) throws Exception {
        scheduledExecutorService.scheduleAtFixedRate(HotLoadServer::classLoaderTest, 1, 1, TimeUnit.SECONDS);
        System.out.println("Netty启动");
        NioEventLoopGroup group1 = new NioEventLoopGroup();
        NioEventLoopGroup group2 = new NioEventLoopGroup(16);
        ChannelFuture bind = new ServerBootstrap()
                .group(group1, group2)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
//                        channel.pipeline().addLast(new LoggingHandler())
//                                .addLast(new StringDecoder());
//                                 .addLast(new ChannelInboundHandlerAdapter(){
//                                    @Override
//                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                        log.info((String) msg);
//                                        channel.writeAndFlush(msg);
//                                    }
//                                });
                        channel.pipeline().addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(512*1024))
                                .addLast(new HttpRequestHander());


                    }
                })
                .bind(8888);
        Channel ch= bind.channel();
//        AtomicInteger flag= new AtomicInteger();
//        flag.set(0);
//        new Thread(()->{
//            Scanner sc=new Scanner(System.in);
//            while (true){
//                String line=sc.nextLine();
//                if("q".equals(line)){
//                    ch.close();
//                    flag.set(1);
//                    break;
//                }else{
//                    ch.writeAndFlush(line);
//                }
//            }
//        },"input").start();
        bind.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {

                log.info("异步等待关闭服务器");


            }
        });
        System.out.println("热部署服务器："+bind);
    }
}

