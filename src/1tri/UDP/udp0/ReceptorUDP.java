package UDP.udp0;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class ReceptorUDP {
    
    public static void main(String[] args) {
        
        int puertoEscucha = 12345;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)){

            System.out.println("Esperando datagrama en el puerto: " + puertoEscucha + "...");

            DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
            socket.receive(recibo);

            int byteRec = recibo.getLength();

            String paquete = new String(recibo.getData(), 0, byteRec, StandardCharsets.UTF_8);

            System.out.println("Mensaje recibido: " + paquete);
            System.out.println(("Puerto origen del mensaje: " + recibo.getPort()));
            System.out.println("IP del origen: " + recibo.getAddress().getHostAddress());

        } catch (IOException e) {

            System.err.println("Error al recibir el paquete UDP: " + e.getMessage());
            e.printStackTrace();

        }

    }

}
