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

        boolean clientOn = true;
        Socket tcp_socket = null;

        DatagramSocket udp_socket = null;

        try {

            // create sockets
            tcp_socket = new Socket(hostName, port);

            udp_socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            byte[] buff = "hi".getBytes();

            DatagramPacket packet = new DatagramPacket(buff, buff.length, address, port);
            udp_socket.send(packet);

            System.out.printf("~~ Client connected successfully ~~\n");

           // in & out streams
            PrintWriter out = new PrintWriter(tcp_socket.getOutputStream(), true);
            BufferedReader ser_in = new BufferedReader(new InputStreamReader(tcp_socket.getInputStream()));
            BufferedReader con_in = new BufferedReader(new InputStreamReader(System.in)); // czytanie z konsoli

            // send msg, read response
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
                                out.println(msg); // info do servera, ze sie przerzucamy na udp

                                byte[] buff = con_in.readLine().getBytes();

                                DatagramPacket packet = new DatagramPacket(buff, buff.length, address, port);
//                                String received = new String(packet.getData(), 0, packet.getLength());
//                                System.out.printf(received + "\n");
                                finalUdp_socket.send(packet);
                                System.out.printf("~~ switched to TCP ~~\n");
                            } else {
                                out.println(msg); // sending TCP message
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            DatagramSocket final_Udp_socket = udp_socket;
            Thread listen = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String msg = ser_in.readLine();
                            if (msg.equals("U")) {
                                byte[] buff = new byte[512];
                                DatagramPacket packet = new DatagramPacket(buff, buff.length);
                                final_Udp_socket.receive(packet);
                                System.out.print("\b\b\b\b");
                                String received = new String(packet.getData(), 0, packet.getLength());
                                System.out.printf(received + "\n");
                            } else {
                                System.out.print("\b\b\b\b");
                                System.out.println(msg);
                            }
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
//                if (tcp_socket != null){
//                    tcp_socket.close();
//                    System.out.printf("~~ Client disconnected ~~\n");
//                }
//            }
        }
    }
}
