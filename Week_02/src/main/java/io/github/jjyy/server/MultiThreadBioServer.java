package io.github.jjyy.server;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jy
 */
public class MultiThreadBioServer {

    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8001));
        while (true) {
            Socket client = serverSocket.accept();
            new Thread(() -> HttpOutboundHandler.write(client)).start();
        }
    }

}
