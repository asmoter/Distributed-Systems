//import java.io.*;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.net.Socket;
//import java.sql.Timestamp;
//
//public class ClientHandler implements Runnable {
//
//    public Socket tcpClientSocket = null;
//    public DatagramSocket udpSocket = null;
//    public Socket[] sockets;
//    public DatagramPacket[] packets;
//
//    public ClientHandler(Socket tcpCS, Socket[] sockets, DatagramSocket udpS, DatagramPacket[] packets){
//        this.tcpClientSocket = tcpCS;
//        this.sockets = sockets;
//        this.udpSocket = udpS;
//        this.packets = packets;
//
//    }
//
////    @Override
//    public void run() {
//
//        // in & out streams
//        boolean clientOn = true;
//        String msg;
//        String response;
//        int clientID = -1;
//
//
//        try {
//            while(clientOn) {
//
//                BufferedReader in = new BufferedReader(new InputStreamReader(tcpClientSocket.getInputStream()));
//                byte[] buff = new byte[256]; // buffer do udp
//
//                for(int i = 0; i < 5; i++){
//                    if(sockets[i] == tcpClientSocket){
//                        clientID = i+1;
//                    }
//                }
//
//                if((msg = in.readLine()) != null) {
//                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                    System.out.println(timestamp + " tcp msg from C" + clientID + " -> " + msg);
//
//                    if(msg.equals("U")){
//
//                        for (int i = 0; i < 5; i++) { // informacja do innych klientow, ze przyjdzie po UDP
//                            if (sockets[i] != tcpClientSocket && sockets[i] != null) {
//                                PrintWriter out = new PrintWriter(sockets[i].getOutputStream(), true);
//                                response = "U";
//                                out.println(response);
//                            }
//                        }
//
//                        DatagramPacket packet = new DatagramPacket(buff, buff.length);
//                        udpSocket.receive(packet); // odbior pakietu, ktory przyszedl do serwera
//
//                        String received = new String(packet.getData(), 0, packet.getLength());
//                        response = "C" + clientID + "_udp: " + received;
//                        System.out.println(timestamp + " udp msg from C" + clientID + " -> " + received);
//
//                        int senderPort = packet.getPort();
//
//                        for(int i = 0; i < 5; i++){
//                            if(packets[i] != null && packets[i].getPort() != senderPort){
//                                buff = response.getBytes();
//                                packet = new DatagramPacket(buff, buff.length, packets[i].getAddress(), packets[i].getPort());
//                                udpSocket.send(packet);
//                            }
//                        }
//                    }
//                    else{
//                        for (int i = 0; i < 5; i++) {
//                            if (sockets[i] != tcpClientSocket && sockets[i] != null) {
//                                PrintWriter out = new PrintWriter(sockets[i].getOutputStream(), true);
//                                response = "C" + clientID + "_tcp: " + msg;
//                                out.println(response);
//                            }
//                        }
//                    }
//                }
//                else{
//                    tcpClientSocket.close();
//                    clientOn = false;
//                    System.out.printf("Client C" + clientID + " disconnected\n");
//                }
//            }
//        } catch (IOException e) {
//            System.out.printf("Error " + e.getMessage());
//            System.exit(-1);
//        }
//    }
//
//    public static void main(String args[]){
//        System.out.printf("Hi!\n");
//    }
//}
