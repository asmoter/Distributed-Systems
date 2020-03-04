import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{

    ServerSocket serverSocket = null;
    int portNumber = 1234;
    boolean run = true;
    ExecutorService threadPool = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws IOException {

        System.out.println("JAVA SERVER");
        Server server = new Server();
        new Thread(server).start();
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            System.out.printf(e.getMessage());
        }
//        System.out.printf("Stop server\n");
        server.stop();
    }

    @Override
    public void run() {

        Socket clientSocket = null;

        // create socket
        try {
            this.serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.printf("Failed to open port " + portNumber);
            System.out.printf(e.getMessage());
        }
        while (run) {
            // accept client
            try {
                clientSocket = this.serverSocket.accept();
                System.out.println("client " + clientSocket.getPort() + " connected");
            } catch (IOException e) {
                System.out.printf("Failed to connect client " + clientSocket.getPort() + "\n");
//                System.out.printf(e.getMessage());
            }
            this.threadPool.execute(new ClientHandler(clientSocket));
        }
        this.threadPool.shutdown();
        System.out.printf("~~ Server disconnected ~~\n");
    }

    public synchronized void stop() {
        this.run = false;
        try {
            serverSocket.close();
            System.out.printf("Server connection closed successfully\n");
        } catch (Exception e) {
            System.out.printf("Failed to close server connection\n");
            System.out.printf(e.getMessage());
        }
    }
}

