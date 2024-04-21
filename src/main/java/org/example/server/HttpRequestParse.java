package org.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.CharsetUtil;
import org.example.HotLoad.BaseManager;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class HttpRequestParse extends BaseManager {
    public static Map<String, Object> readParams(FullHttpRequest request){
        if(request.method() == HttpMethod.GET){
            return readGetParams(request);
        }
        else if(request.method() == HttpMethod.POST){
            return readPostParams(request);
        }
        else{
            return  null;
        }
    }
    /**
     * 读取GET方法的参数
     * @param request
     * @return
     */
    public static Map<String, Object> readGetParams(FullHttpRequest request) {

        if (request.method() == HttpMethod.GET) {

            Map<String, Object> paramMap = new HashMap<>();

            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            decoder.parameters().entrySet().forEach(entry -> {
                paramMap.put(entry.getKey(), entry.getValue().get(0));
            });

            return paramMap;
        }

        return null;
    }


    /**
     * 读取POST方法的表单参数
     *
     * @param request
     * @return 返回一个Map
     */
    public static Map<String, Object> readPostParams(FullHttpRequest request) {

        if (request.method() == HttpMethod.POST) {
            String contentType = request.headers().get("Content-Type").trim().toLowerCase();

            if (contentType.contains("x-www-form-urlencoded")) {
                Map<String, Object> paramMap = new HashMap<>();

                HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);

                List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();

                for (InterfaceHttpData parm : parmList) {
                    if (parm.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                        MemoryAttribute data = (MemoryAttribute) parm;
                        paramMap.put(data.getName(), data.getValue());
                    }
                }

                return paramMap;
            }
            else if(contentType.contains("json")){
                ByteBuf content = request.content();
                String msg = content.toString(CharsetUtil.UTF_8);

                Map<String, Object> paramMap = GsonUtils.fromJson(msg, Map.class);

                return paramMap;

            }
        }

        return null;

    }


}
