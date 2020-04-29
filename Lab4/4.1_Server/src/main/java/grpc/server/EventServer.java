package grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class EventServer {

    private static final Logger logger = Logger.getLogger(EventServer.class.getName());

    private int port = 50051;
    private Server server;
    private static EventGenerator generator = new EventGenerator();
    private static EventService eventService = new EventService();

    private void start() throws IOException
    {
        server = ServerBuilder.forPort(port)
                .addService(eventService)
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

        Thread generatingEventsThread = new Thread(new Runnable() {
            public void run() {


//                Event event = generator.generateEvent();
//                            System.out.println("New event! " + event.getEventType() + ": " +
//                                    event.getName() + " in " + event.getCity());
//                            events.add(event);
//                            Thread.sleep(timeToWait);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
                generator.generateEvent();
            }
        });
        generatingEventsThread.start();
        try{
            final EventServer server = new EventServer();
            server.start();
            server.blockUntilShutdown();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
