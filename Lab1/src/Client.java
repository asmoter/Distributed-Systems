import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String args[]) throws IOException {

        System.out.println("JAVA CLIENT");
        int port = 1234;
        String hostName = "localhost";

        Socket tcp_socket = null;
        DatagramSocket udp_socket = null;

        String asciiArt = "";

        try {

            // create sockets
            tcp_socket = new Socket(hostName, port);

            udp_socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            byte[] buff = "udp init".getBytes();

            DatagramPacket packet = new DatagramPacket(buff, buff.length, address, port);
            udp_socket.send(packet);

            System.out.printf("~~ Client connected successfully ~~\n");

           // in & out streams
            PrintWriter out = new PrintWriter(tcp_socket.getOutputStream(), true);
            BufferedReader ser_in = new BufferedReader(new InputStreamReader(tcp_socket.getInputStream()));
            BufferedReader con_in = new BufferedReader(new InputStreamReader(System.in)); // czytanie z konsoli


            DatagramSocket finalUdp_socket = udp_socket;
            Thread sendMsg = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            System.out.printf("Me: ");
                            String msg = con_in.readLine();
                            if (msg.equals("U")) {
                                System.out.printf("~~ switched to UDP ~~\n");
                                System.out.printf("Me: ");

                                byte[] buff = con_in.readLine().getBytes();

                                DatagramPacket packet = new DatagramPacket(buff, buff.length, address, port);
                                finalUdp_socket.send(packet);
                                System.out.printf("~~ switched to TCP ~~\n");
                            }
                            else{
                                out.println(msg); // sending TCP message
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


//            DatagramSocket final_Udp_socket = udp_socket;
            Thread listenTCP = new Thread(new Runnable() {
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

            DatagramSocket final_Udp_socket = udp_socket;
            Thread listenUDP = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            byte[] buff = new byte[512];
                            DatagramPacket packet = new DatagramPacket(buff, buff.length);
                            final_Udp_socket.receive(packet);
                            System.out.print("\b\b\b\b");
                            String received = new String(packet.getData(), 0, packet.getLength());
                            System.out.printf(received + "\n");
                            System.out.printf("Me: ");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            sendMsg.start();
            listenTCP.start();
            listenUDP.start();


        } catch (Exception e) {
            System.out.printf("Failed to connect\n");
            e.printStackTrace();
//            } finally {
//                if (tcp_socket != null){
//                    tcp_socket.close();
//                    System.out.printf("~~ Client disconnected ~~\n");
//                }
//            }
        }
    }
}
