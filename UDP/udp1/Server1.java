package UDP.udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server1 {

    public static void main(String[] args) {

        int puertoEscucha = 12345;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(puertoEscucha);
            Scanner susito = new Scanner(System.in)) {

            System.out.println("Esperando datagrama en el puerto: " + puertoEscucha + "...");
            boolean activo = true;

            while (activo) {
                DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
                socket.receive(recibo);

                int byteRec = recibo.getLength();
                String paquete = new String(recibo.getData(), 0, byteRec, StandardCharsets.UTF_8);

                System.out.println("Cliente (" + recibo.getAddress().getHostAddress() + " : " + recibo.getPort() + "): "
                        + paquete);

                        
                System.out.println("Escribe el mensaje: ");

                if (paquete.equalsIgnoreCase("quit")) {
                    System.out.println("Se ha escrito 'quit', así que sayonara beibi");
                    activo = false;
                }

                String respuesta = susito.nextLine();
                System.out.println("Vasallo: " + respuesta);

                InetAddress ipCliente = recibo.getAddress();
                int puertoCliente = recibo.getPort();
                
                byte[] mensajito = respuesta.getBytes(StandardCharsets.UTF_8);

                DatagramPacket envio = new DatagramPacket(mensajito, mensajito.length, ipCliente, puertoCliente);

                socket.send(envio);
                System.out.println("Mensaje enviado correctamente a cliente: " + ipCliente + ": " + puertoCliente);

            }
        } catch (IOException e) {

            System.err.println("Error al recibir el paquete UDP: " + e.getMessage());
            e.printStackTrace();

        }
    }
}
