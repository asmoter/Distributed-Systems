import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws IOException {

        System.out.println("JAVA CLIENT");
        int portNumber = 1234;
        boolean clientOn = true;

        if (args.length > 0) {
            if (args[0].equals("M")) {
                System.out.printf("Multicast\n");
            } else if (args[0].equals("U")) {
                System.out.println("UDP Channel\n");
            } else {
                System.out.printf("Bad argument\n");
            }
        } else {
            System.out.printf("TCP Chat\n");
            String hostName = "localhost";
            Socket socket = null;


            try {
                // create socket
                socket = new Socket(hostName, portNumber);
                System.out.printf("~~ Client connected successfully ~~\n");

               // in & out streams
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader ser_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader con_in = new BufferedReader(new InputStreamReader(System.in)); // czytanie z konsoli

                // send msg, read response
                Thread sendMsg = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                System.out.printf("Me: ");
                                String msg = con_in.readLine();
                                if (msg.equals("Exit")) {
//                                        clientOn = false;
                                    System.out.printf("Exit\n");
                                } else {
                                    out.println(msg);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                Thread listen = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                String msg = ser_in.readLine();
                                System.out.print("\b\b\b\b");
                                System.out.println(msg);
                                System.out.printf("Me: ");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                sendMsg.start();
                listen.start();

            } catch (Exception e) {
                System.out.printf("Failed to connect\n");
                e.printStackTrace();
//            } finally {
//                if (socket != null){
//                    socket.close();
//                    System.out.printf("~~ Client disconnected ~~\n");
//                }
//            }
            }
        }
    }
}
