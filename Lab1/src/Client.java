import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void main(String args[]) throws IOException {

        System.out.println("JAVA CLIENT");
        int portNumber = 1234;
        boolean clientOn = true;

        if(args.length > 0){
            if(args[0].equals("M")){
                System.out.printf("Multicast\n");
            }
            else if(args[0].equals("U")){
                System.out.println("UDP Channel\n");
            }
            else{
                System.out.printf("Bad argument\n");
            }
        }
        else{
            System.out.printf("TCP Chat\n");
            String hostName = "localhost";
            Socket socket = null;
            String msg;
            String response;

            try {
                // create socket
                socket = new Socket(hostName, portNumber);
                System.out.printf("~~ Client connected successfully ~~\n");
                while(clientOn) {
                    // in & out streams
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader ser_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedReader con_in = new BufferedReader(new InputStreamReader(System.in)); // czytanie z konsoli

                    // send msg, read response

                    System.out.printf("Me: ");
                    msg = con_in.readLine();
                    if(msg.equals("Exit")){
                        clientOn = false;
                    }
                    else{
                        out.println(msg);
                        response = ser_in.readLine();
                        System.out.println(response);
                    }
                }
            } catch (Exception e) {
                System.out.printf("Failed to connect\n");
                e.printStackTrace();
            } finally {
                if (socket != null){
                    socket.close();
                    System.out.printf("~~ Client disconnected ~~\n");
                }
            }
        }
    }
}
