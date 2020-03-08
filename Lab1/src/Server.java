import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{

    private int port = 1234;
    private boolean run = true;
    Socket[] tcp_sockets = new Socket[5];
    DatagramPacket[] udp_packets = new DatagramPacket[5];

    ServerSocket serverSocketTCP = null;
    DatagramSocket serverSocketUDP = null;

    ExecutorService threadPoolTCP = Executors.newFixedThreadPool(5);
    ExecutorService threadPoolUDP = Executors.newFixedThreadPool(5);


    public static void main(String[] args){

        System.out.println("JAVA SERVER");
        Server server = new Server();
        new Thread(server).start();
        try {
            Thread.sleep(1000 * 1000);
        } catch (InterruptedException e) {
            System.out.printf(e.getMessage() + "\n");
        }
        server.stop();
    }

    @Override
    public void run() {

        Socket clientSocketTCP = null;
        int i = 0;

        try {
            this.serverSocketTCP = new ServerSocket(port); // create TCP server
            this.serverSocketUDP = new DatagramSocket(port); // create UDP server
        } catch (IOException e) {
            System.out.printf("Failed to open port " + port);
            System.out.printf(e.getMessage());
        }
        while (run) {
            // accept client
            try {
                clientSocketTCP = this.serverSocketTCP.accept();
                tcp_sockets[i] = clientSocketTCP;

                i++;
                System.out.println("client C" + i + " connected");

            } catch (IOException e) {
                System.out.printf("Failed to connect client " + clientSocketTCP.getPort() + "\n");
            }
            this.threadPoolTCP.execute(new tcpHandler(clientSocketTCP, tcp_sockets));
            this.threadPoolUDP.execute(new udpHandler(serverSocketUDP, udp_packets));
        }
        this.threadPoolTCP.shutdown();
        this.threadPoolUDP.shutdown();
        System.out.printf("~~ Server disconnected ~~\n");
    }

    public synchronized void stop() {
        this.run = false;
        try {
            serverSocketTCP.close();
            System.out.printf("Server connection closed successfully\n");
        } catch (Exception e) {
            System.out.printf("Failed to close server connection\n");
            System.out.printf(e.getMessage());
        }
    }
}

