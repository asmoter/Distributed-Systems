import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    Socket socket;

    Server(Socket clientSocket){
        this.socket = clientSocket;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("JAVA SERVER");
        int portNumber = 1234;
        boolean run = true;

        // create socket
        ServerSocket serverSocket = new ServerSocket(portNumber);

        while(run){

            // accept client
            Socket clientSocket = serverSocket.accept();
            System.out.println("client " + clientSocket.getPort() + " connected");
            new Thread(new Server(clientSocket)).start();
        }
//        try{
//            serverSocket.close();
//            System.out.printf("Server connection closed successfully\n");
//        }
//        catch (Exception e){
//            System.out.printf("Failed to close server connection\n");
//        }
    }

    @Override
    public void run() {
        // in & out streams
        boolean clientOn = true;
        String msg;
        String response;

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            while(clientOn){
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                int clientID = socket.getPort();

                if((msg = in.readLine()) != null){
                    System.out.println("msg from client " + clientID + " -> " + msg);
//                    response = "Client ID: ".concat(String.valueOf(clientID)).concat(msg).concat(msg);
                    response = "Client ID: " + clientID + " msg: " + msg;
                    out.println(response);
                }
                else{
                    socket.close();
                    clientOn = false;
                    System.out.printf("Client " + socket.getPort() + " disconnected\n");
                }
            }
        } catch (IOException e) {
            System.out.printf("Error " + e.getMessage());
            System.exit(-1);
        }
    }
}

