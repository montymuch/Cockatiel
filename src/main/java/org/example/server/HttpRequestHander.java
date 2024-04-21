package org.example.server;
import cn.hutool.json.JSONUtil;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.example.common.OutObject;
import org.example.controller.Observer;
import org.example.controller.TestController;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class HttpRequestHander  extends SimpleChannelInboundHandler<FullHttpRequest>{

    public static ThreadLocal<HashMap> threadLocal=  new ThreadLocal<>();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

       /* DefaultFullHttpRequest request = new DefaultFullHttpRequest(msg.protocolVersion(), HttpMethod.GET, msg.getUri());
        System.out.println(msg);
        String url = (System.getProperty("user.dir")+"/src/main/resources/WebContent"+"/index.html");
        System.out.println(url);
        File file  = new File(url);
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        HttpResponse response = new DefaultHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
        ctx.write(response);
        ctx.write(new DefaultFileRegion(raf.getChannel(),0, raf.length()));
        ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        future.addListener(ChannelFutureListener.CLOSE);*/
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        System.out.println(msg);
        System.out.println(msg.getUri());





        if(msg.getUri().contains(".html")||msg.getUri().contains(".jpg")&&!msg.getUri().contains("?")){
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(msg.protocolVersion(), HttpMethod.GET, msg.getUri());
            System.out.println(msg);
            String url = (System.getProperty("user.dir")+"/src/main/java/org/example/server/WebContent"+msg.getUri());
            System.out.println(url);
            File file  = new File(url);
            if(file.exists()){
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                HttpResponse response = new DefaultHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
                ctx.write(response);
                ctx.write(new DefaultFileRegion(raf.getChannel(),0, raf.length()));
                ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
                future.addListener(ChannelFutureListener.CLOSE);}
            else{
                String error = (System.getProperty("user.dir")+"/src/main/java/org/example/server/WebContent"+"/404.html");
                File f=new File(error);
                RandomAccessFile rafe=new RandomAccessFile(f,"r");
                HttpResponse response = new DefaultHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
                ctx.write(response);
                ctx.write(new DefaultFileRegion(rafe.getChannel(),0, rafe.length()));
                ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
                future.addListener(ChannelFutureListener.CLOSE);

            }

//        }else if(!msg.getUri().contains(".html")&&!msg.getUri().contains(".jpg")&&msg.getUri().contains("?")){
        }else if(!msg.getUri().contains(".html")&&!msg.getUri().contains(".jpg")){
            HttpRequestParse httpRequestParser = new HttpRequestParse();
            Map<String, Object> stringObjectMap = httpRequestParser.readParams(fullHttpRequest);
            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
            threadLocal.set(objectObjectHashMap);
            for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
            System.out.println("1");
            String mapKey = entry.getKey();
            Object mapValue = entry.getValue();
            System.out.println(mapKey + "：" + mapValue);
            objectObjectHashMap.put(mapKey,mapValue);
            }
            Observer observer =  new Observer();

            String path="/readUser";
            OutObject print = (OutObject) observer.handle(path, new TestController(), "print", new Object[]{(Object) objectObjectHashMap});
//            HttpResponse response = new DefaultHttpResponse(msg.protocolVersion(),HttpResponseStatus.OK);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            //返回JSON字符串
            String s = String.valueOf(JSONUtil.parse(print));
            response.content().writeBytes(s.getBytes());
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

            // 将响应写入到Channel并刷新
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            threadLocal.remove();


        }}
}

