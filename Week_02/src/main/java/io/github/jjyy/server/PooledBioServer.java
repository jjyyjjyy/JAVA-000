package io.github.jjyy.server;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jy
 */
public class PooledBioServer {

    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3);

    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8002));
        while (true) {
            Socket client = serverSocket.accept();
            THREAD_POOL.submit(() -> HttpOutboundHandler.write(client));
        }
    }

}
