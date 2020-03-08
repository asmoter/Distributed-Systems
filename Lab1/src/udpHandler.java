import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Timestamp;

public class udpHandler implements Runnable {

    private DatagramSocket serverSocketUDP = null;
    private DatagramPacket[] packets;

    public udpHandler(DatagramSocket udpSocket, DatagramPacket[] packets){
        this.serverSocketUDP = udpSocket;
        this.packets = packets;
    }

    public void run() {

        // in & out streams
        boolean clientOn = true;
        String msg;
        int j = 0;

        try {
            while(clientOn) {

                byte[] buff = new byte[512]; // buffer do udp

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DatagramPacket packet = new DatagramPacket(buff, buff.length);
                serverSocketUDP.receive(packet); // odbior pakietu, ktory przyszedl do serwera

                msg = new String(packet.getData(), 0, packet.getLength());

                if(msg.equals("udp init")){
                    packets[j] = packet;
                    j++;
                }
                else{
                    int senderPort = packet.getPort();
                    System.out.println(timestamp + " udp msg from " + msg);

                    for(int i = 0; i < 5; i++){
                        if(packets[i] != null && packets[i].getPort() != senderPort){
                            buff = msg.getBytes();
                            packet = new DatagramPacket(buff, buff.length, packets[i].getAddress(), packets[i].getPort());
                            serverSocketUDP.send(packet);
                        }
                    }
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
