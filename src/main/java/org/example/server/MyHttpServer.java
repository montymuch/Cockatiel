package org.example.server;/*
package org.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHttpServer {
    private int port = 704;
    public void receiving(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
             //循环接受
            while (true){
                Socket socket=serverSocket.accept();
                System.out.println(socket);
              //获取连接对象的输入流
                InputStream inputStream = socket.getInputStream();
                System.out.println(inputStream);
                MyHttpRequest request=new MyHttpRequest();
                //解析请求
                   request.parse();
                //创建response
                OutputStream outputStream = socket.getOutputStream();
                MyHttpResponse myHttpResponse=new MyHttpResponse(outputStream);
                myHttpResponse.sendRedirect(request.getUri());

            }
        } catch (IOException e) {
            System.out.println(port+"被占用，服务器启动失败！");
            System.exit(0);
            throw new RuntimeException(e);

        }
    }


}
*/
