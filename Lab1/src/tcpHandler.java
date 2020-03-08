import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;

public class tcpHandler implements Runnable {

    private Socket clientSocketTCP = null;
    private Socket[] sockets;

    public tcpHandler(Socket tcpCS, Socket[] sockets){
        this.clientSocketTCP = tcpCS;
        this.sockets = sockets;
    }

    public void run() {

        boolean clientOn = true;
        String msg;
        String response;
        int clientID = -1;

        try {
            while(clientOn) {

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocketTCP.getInputStream()));

                for(int i = 0; i < 5; i++){
                    if(sockets[i] == clientSocketTCP){
                        clientID = i+1;
                        PrintWriter outInit = new PrintWriter(sockets[i].getOutputStream(), true);
                        response = "ID: C" + clientID;
                        outInit.println(response);
                    }
                }

                if((msg = in.readLine()) != null) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println(timestamp + " tcp msg from " + msg);

                    for (int i = 0; i < 5; i++) {
                        if (sockets[i] != clientSocketTCP && sockets[i] != null) {
                            PrintWriter out = new PrintWriter(sockets[i].getOutputStream(), true);
                            out.println(msg);
                        }
                    }
                }
                else{
                    clientSocketTCP.close();
                    clientOn = false;
                    System.out.print("Client C" + clientID + " disconnected\n");
                }
            }
        } catch (IOException e) {
            System.out.print("Error " + e.getMessage());
            System.exit(-1);
        }
    }

    public static void main(String[] args){
        System.out.print("Hi!\n");
    }
}
