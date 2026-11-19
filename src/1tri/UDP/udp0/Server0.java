package UDP.udp0;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Server0 {

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(12345)) {
            byte[] buffers = new byte[1024];
            DatagramPacket recibo = new DatagramPacket(buffers, buffers.length);

            System.out.println("Esperando datos...");
            socket.receive(recibo); 

            int bytesRec = recibo.getLength();
            String llegada = new String(recibo.getData(), 0, bytesRec, StandardCharsets.UTF_8);

            System.out.println("Puerto origen del mensaje: " + recibo.getPort());
            System.out.println("IP de origen: " + recibo.getAddress().getHostAddress());
            System.out.println("Mensaje: " + llegada);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
