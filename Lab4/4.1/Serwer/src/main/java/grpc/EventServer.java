package grpc;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;


public class EventServer {

    private static final Logger logger = Logger.getLogger(EventServer.class.getName());

    private int port = 50051;
    private Server server;

    private void start() throws IOException
    {
        ReentrantLock lock = new ReentrantLock();
        EventGenerator generator = new EventGenerator(lock);
        server = NettyServerBuilder
                .forPort(port)
                .addService(new EventService(generator))
                .permitKeepAliveWithoutCalls(true)
                .permitKeepAliveTime(30, TimeUnit.SECONDS)
                .keepAliveTime(15, TimeUnit.SECONDS)
                .keepAliveTimeout(20, TimeUnit.SECONDS)
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("~~ shutting down gRPC server since JVM is shutting down ~~");
                EventServer.this.stop();
                System.err.println("~~ server shut down ~~");
            }
        });
        generator.generateEvents();
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) {

        try{
            final EventServer server = new EventServer();
            server.start();
            server.blockUntilShutdown();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}