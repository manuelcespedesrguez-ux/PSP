package udp0;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;

public class Server0 {
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        
        byte[] buffers = new byte[1024];
        DatagramPacket recibo = new DatagramPacket(buffers, buffers.length);

        int bytesRec = recibo.getLength();
        String llegada = new String( recibo.getData(), 0, bytesRec, StandardCharsets.UTF_8 );

        System.out.println("Puerto origen del mensaje: " +recibo.getPort());
        System.out.println("IP de origen: " + recibo.getAddress().getHostAddress());

    }

}
