import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) throws IOException {

        System.out.println("JAVA CLIENT");
        int port = 1234;
        int portMult = 12345;
        String hostName = "localhost";
        final String[] ClientID = {""};

        Socket tcp_socket = null;
        DatagramSocket udp_socket = null;

        InetAddress group_address = InetAddress.getByName("224.1.1.1");
        MulticastSocket mult_socket = null;


        StringBuilder asciiArt = new StringBuilder();

        asciiArt.append("\n" + "        _    .  ,   .           .\n" +
                "    *  / \\_ *  / \\_      _  *        *   /\\'__        *\n" +
                "      /    \\  /    \\,   ((        .    _/  /  \\  *'.\n" +
                " .   /\\/\\  /\\/ :' __ \\_  `          _^/  ^/    `--.\n" +
                "    /    \\/  \\  _/  \\-'\\      *    /.' ^_   \\_   .'\\  *\n" +
                "  /\\  .-   `. \\/     \\ /==~=-=~=-=-;.  _/ \\ -. `_/   \\ \n" +
                " /  `-.__ ^   / .-'.--\\ =-=~_=-=~=^/  _ `--./ .-'  `-\n" +
                "/        `.  / /       `.~-^=-=~=^=.-'      '-._ `._\n");

        try {

            // create sockets
            tcp_socket = new Socket(hostName, port);

            udp_socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            byte[] buff = "udp init".getBytes();

            DatagramPacket udp_packet = new DatagramPacket(buff, buff.length, address, port);
            udp_socket.send(udp_packet);

            mult_socket = new MulticastSocket(portMult);
            mult_socket.joinGroup(group_address);

            System.out.printf("~~ Client connected successfully ~~\n");

           // in & out streams
            PrintWriter out = new PrintWriter(tcp_socket.getOutputStream(), true);
            BufferedReader ser_in = new BufferedReader(new InputStreamReader(tcp_socket.getInputStream()));
            BufferedReader con_in = new BufferedReader(new InputStreamReader(System.in)); // czytanie z konsoli


            DatagramSocket finalUdp_socket = udp_socket;
            MulticastSocket finalMult_socket = mult_socket;
            Thread sendMsg = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            System.out.printf("Me: ");
                            String msg = con_in.readLine();

                            // UDP
                            if (msg.equals("U")) {
                                System.out.printf("~~ switched to UDP ~~\n");
                                System.out.printf("Me: ");

                                byte[] buff = con_in.readLine().getBytes();
                                DatagramPacket packet = new DatagramPacket(buff, buff.length, address, port);
                                String received = new String(packet.getData(), 0, packet.getLength());
                                if(received.equals("")){
                                    msg = ClientID[0] + ": \n" + asciiArt.toString();
                                    buff = msg.getBytes();
                                    packet = new DatagramPacket(buff, buff.length, address, port);
                                    finalUdp_socket.send(packet);
                                }
                                else{
                                    msg = ClientID[0] + ": " + received;
                                    buff = msg.getBytes();
                                    packet = new DatagramPacket(buff, buff.length, address, port);
                                    finalUdp_socket.send(packet);
                                }
                                System.out.printf("~~ switched to TCP ~~\n");
                            }

                            // Multicast
                            else if(msg.equals("M")){
                                System.out.printf("~~ switched to UDP Multicast ~~\n");
                                System.out.printf("Me: ");
                                byte[] buff = con_in.readLine().getBytes();
                                DatagramPacket packet = new DatagramPacket(buff, buff.length, group_address, portMult);
                                String received = new String(packet.getData(), 0, packet.getLength());
                                msg = ClientID[0] + ": " + received;
                                buff = msg.getBytes();
                                packet = new DatagramPacket(buff, buff.length, group_address, portMult);
                                finalMult_socket.send(packet);
                                System.out.printf("~~ switched to TCP ~~\n");
                            }

                            // TCP
                            else{
                                String r = ClientID[0] + ": " + msg;
                                out.println(r); // sending TCP message
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            Thread listenTCP = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String msg = ser_in.readLine();
                            if(msg.contains("ID: ")){
                                String[] a = msg.split(" ");
                                ClientID[0] = a[1];
                            }
                            else{
                                System.out.print("\b\b\b\b");
                                System.out.println(msg);
                                System.out.printf("Me: ");
                            }
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

            DatagramSocket final_Mult_socket = mult_socket;
            Thread listenMult = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            byte[] buff = new byte[512];
                            DatagramPacket packet = new DatagramPacket(buff, buff.length);
                            final_Mult_socket.receive(packet);
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
            listenMult.start();


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
