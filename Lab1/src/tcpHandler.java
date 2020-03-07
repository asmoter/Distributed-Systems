import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;

public class tcpHandler implements Runnable {

    public Socket tcpClientSocket = null;
    public Socket[] sockets;

    public tcpHandler(Socket tcpCS, Socket[] sockets){
        this.tcpClientSocket = tcpCS;
        this.sockets = sockets;
    }

    //    @Override
    public void run() {

        // in & out streams
        boolean clientOn = true;
        String msg;
        String response;
        int clientID = -1;


        try {
            while(clientOn) {

                BufferedReader in = new BufferedReader(new InputStreamReader(tcpClientSocket.getInputStream()));

                for(int i = 0; i < 5; i++){
                    if(sockets[i] == tcpClientSocket){
                        clientID = i+1;
                    }
                }

                if((msg = in.readLine()) != null) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println(timestamp + " tcp msg from C" + clientID + " -> " + msg);

                    for (int i = 0; i < 5; i++) {
                        if (sockets[i] != tcpClientSocket && sockets[i] != null) {
                            PrintWriter out = new PrintWriter(sockets[i].getOutputStream(), true);
                            response = "C" + clientID + "_tcp: " + msg;
                            out.println(response);
                        }
                    }
                }
                else{
                    tcpClientSocket.close();
                    clientOn = false;
                    System.out.printf("Client C" + clientID + " disconnected\n");
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
