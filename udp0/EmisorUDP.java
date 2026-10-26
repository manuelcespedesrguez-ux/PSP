package udp0;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class EmisorUDP {
    
    public static void main(String[] args) {
        
        int puerto = 12345;
            try (DatagramSocket socket = new DatagramSocket()) {

                InetAddress destino = InetAddress.getLocalHost();
                String saludo = "Enviando saludos desde el cliente";

                byte[] mensaje = saludo.getBytes(StandardCharsets.UTF_8);

                DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, puerto);

                socket.send(envio);
                System.out.println("Mensaje enviado correctamente a " + destino + ": " + puerto);

            } catch (Exception e) {
                System.err.println("Error al enviar el paquete UDP: " + e.getMessage());
                e.printStackTrace();
        }

    }

}
