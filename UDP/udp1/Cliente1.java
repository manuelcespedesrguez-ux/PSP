package UDP.udp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente1 {

    public static void main(String[] args) {

        int puerto = 12345;
        try (DatagramSocket socket = new DatagramSocket();
                Scanner susanita = new Scanner(System.in)) {

            System.out.println("Escribe el mensaje: ");

            InetAddress destino = InetAddress.getLocalHost();
            boolean activo = true;

            while (activo) {
                String mensaje = susanita.nextLine();
                System.out.println("Patrón: " + mensaje);

                if (mensaje.equalsIgnoreCase("quit")) {
                    activo = false;
                }

                byte[] mensajito = mensaje.getBytes(StandardCharsets.UTF_8);

                DatagramPacket envio = new DatagramPacket(mensajito, mensajito.length, destino, puerto);

                socket.send(envio);
                System.out.println("Mensaje enviado correctamente a servidor: " + destino + ": " + puerto);

                byte[] respuestaServidor = new byte[1024];
                DatagramPacket respuestadelServidor = new DatagramPacket(respuestaServidor, respuestaServidor.length);

                socket.receive(respuestadelServidor);

                int bytesRespuesta = respuestadelServidor.getLength();
                String textoRespuesta = new String(respuestadelServidor.getData(), 0, bytesRespuesta, StandardCharsets.UTF_8);

                System.out.println("El servidor dice: " + textoRespuesta);

            }
        } catch (Exception e) {
            System.err.println("Error al enviar el paquete UDP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}