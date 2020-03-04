import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    public Socket clientSocket = null;
    public int[] clients;

    public ClientHandler(Socket clientSocket, int[] clients){
        this.clientSocket = clientSocket;
        this.clients = clients;
    }

//    @Override
    public void run() {

        // in & out streams
        boolean clientOn = true;
        String msg;
        String response;

//        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
        try {
//            for (int i = 0; i < 10; i++) {
//
//            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            while(clientOn){
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                int clientID = clientSocket.getPort();

                if((msg = in.readLine()) != null){
                    System.out.println("msg from client " + clientID + " -> " + msg);
                    response = "Client ID: " + clientID + " msg: " + msg;
                    out.println(response);
                }
                else{
                    clientSocket.close();
                    clientOn = false;
                    System.out.printf("Client " + clientSocket.getPort() + " disconnected\n");
                }
            }
        } catch (IOException e) {
            System.out.printf("Error " + e.getMessage());
            System.exit(-1);
        }
    }

    public static void main(String args[]){
        System.out.printf("Hi!\n");
    }
}
