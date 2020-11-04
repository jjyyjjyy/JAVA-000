package io.github.jjyy.server;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author jy
 */
public class HttpOutboundHandler {

    @SneakyThrows
    public static void write(Socket socket) {
        Thread.sleep(20);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type:text/html;charset=utf-8");
        String body = "hello,nio";
        printWriter.println("Content-Length:" + body.getBytes().length);
        printWriter.println();
        printWriter.write(body);
        printWriter.close();
        socket.close();
    }
}
