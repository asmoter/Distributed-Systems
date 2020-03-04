import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;

public class ClientHandler implements Runnable {

    public Socket clientSocket = null;
    public  Socket[] sockets;

    public ClientHandler(Socket clientSocket, Socket[] sockets){
        this.clientSocket = clientSocket;
        this.sockets = sockets;
    }

//    @Override
    public void run() {

        // in & out streams
        boolean clientOn = true;
        String msg;
        String response;

//        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
        try {
            while(clientOn) {
                
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                int clientID = clientSocket.getPort();

                if((msg = in.readLine()) != null) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println(timestamp + " msg from  " + clientID + " -> " + msg);

                    for (int i = 0; i < 10; i++) {
                        if (sockets[i] != clientSocket && sockets[i] != null) {
                            PrintWriter out = new PrintWriter(sockets[i].getOutputStream(), true);
                            response = clientID + ": " + msg;
                            out.println(response);
                        }
                    }
                }
                else{

                    clientSocket.close();
                    clientOn = false;
                    System.out.printf("Client " + clientSocket.getPort() + " disconnected\n");
                }
            }

//            for (int i = 0; i < 10; i++) {
//                if(sockets[i] != clientSocket && sockets[i] != null){
//                    PrintWriter out = new PrintWriter(sockets[i].getOutputStream(), true);
//                    while(clientOn){
//                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                        int clientID = clientSocket.getPort();
//
//                        if((msg = in.readLine()) != null){
//                            System.out.println("msg from client " + clientID + " -> " + msg);
//                            response = "Client ID: " + clientID + " msg: " + msg;
//                            out.println(response);
//                        }
//                        else{
//                            clientSocket.close();
//                            clientOn = false;
//                            System.out.printf("Client " + clientSocket.getPort() + " disconnected\n");
//                        }
//                    }
//                }
//            }
        } catch (IOException e) {
            System.out.printf("Error " + e.getMessage());
            System.exit(-1);
        }
    }

    public static void main(String args[]){
        System.out.printf("Hi!\n");
    }
}
